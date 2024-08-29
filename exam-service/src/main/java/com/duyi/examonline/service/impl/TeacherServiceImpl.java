package com.duyi.examonline.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import com.duyi.examonline.commo.CommonData;
import com.duyi.examonline.dao.TeacherMapper;
import com.duyi.examonline.domain.Teacher;
import com.duyi.examonline.service.TeacherService;
import com.duyi.examonline.vo.PageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class TeacherServiceImpl implements TeacherService {
//    这里需要dao的属性
    @Autowired
    private TeacherMapper teacherMapper;

    private Logger log = LoggerFactory.getLogger(TeacherServiceImpl.class);

    @Override
    public boolean save(Teacher teacher) {
//        生成教师需要的助记码 需要hutool工具的使用，还需要额外使用pin4j
        String mnemonicCode = PinyinUtil.getPinyin(teacher.getTname(),"");
        teacher.setMnemonicCode(mnemonicCode);
        if(teacher.getPass() == null){
        //  因为是添加老师的信息，所以这里我们需要一个默认的密码
            teacher.setPass(CommonData.DEFAULT_PASS);
        }

//        验证，确保教师名称和助记码是不重复的
//        利用数据库唯一性，也可以自行实现逻辑

//        将密码进行加密
        String pass = DigestUtil.md5Hex(teacher.getPass());
        teacher.setPass(pass);
//      设置创建时间
        teacher.setCreateTime(new Date());
//      异常处理
        try{
            teacherMapper.insert(teacher);
            return true;
        }catch (DuplicateKeyException e){
            //用户名或助记码已经重复
            log.info("名称或助记码重复");
            return false;
        }

    }


    @Override
    public Teacher findByName(String tname) {
        return teacherMapper.findByName(tname);
    }

//    方法是根据id去找用户的信息
    public Teacher findById(Long id){
        return teacherMapper.findById(id);
    }
    @Override
    public void updatePass(Long id,String pass) {
        teacherMapper.updatePass(id,pass);
    }

//    更改用户信息
    public boolean updateTemplate(Teacher teacher){
        teacher.setMnemonicCode(PinyinUtil.getPinyin(teacher.getTname(),""));
        teacher.setUpdateTime(new Date());
        //      异常处理
        try{
            teacherMapper.updateTemplate(teacher);
            return true;
        }catch (DuplicateKeyException e){
            //用户名或助记码已经重复
            log.info("名称或助记码重复");
            return false;
        }
    }
    @Override
    public PageVO selectTeachers(int page, int rows, String tname) {
        //走到这里我们需要我们需要先判断这个page
        //确保这个page的下线
        if(page < 0){
            page = 1;
        }
//        确保完这个page的下线后，我么就确保这个page的上线
//        max为最大页
//        rows为每页存放多少条信息
//        需要获取数据库中teacher信息的个数
        Long total = teacherMapper.getTeacherNumber(tname);
        int max = (int) (total % rows == 0 ?  total / rows : (total / rows + 1));
//        这里判断这个max是否为0的情况
        max = Math.max(1,max);
        if(page > max){
            page = max;
        }
//        根据pege计算出mysql分页所需要的条件
        int start = (page-1) * rows;
        int length = rows;
        List<Teacher> teacherList = teacherMapper.selectTeachers(start,length,tname);
        Map<String,Object> condition = new HashMap<>();
        condition.put("tname",tname);

        return new PageVO(page,rows,total,max,start,(start+length-1),teacherList,condition);
    }

//    方法是用来实现删除操作的
    public void delete(String ids){
        String[] idArray = ids.split(",");
        for(int i = 0 ; i < idArray.length; i++){
            teacherMapper.deleteByPrimaryKey(Long.valueOf(idArray[i]));
        }
    }
}
