package com.duyi.examonline.test;

import cn.hutool.crypto.digest.DigestUtil;
import com.duyi.examonline.dao.TeacherMapper;
import com.duyi.examonline.domain.Teacher;
import com.duyi.examonline.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;


/**
 * 初始化教师的信息
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class Test1 {

    Logger logger = LoggerFactory.getLogger(Test1.class);
//    需要一个TeacherService属性
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherMapper teacherMapper;
    @Test
    public void t1(){
        Teacher teacher = new Teacher();
        teacher.setTname("张三");
//        这个密码是需要进行加密的
        teacher.setPass(DigestUtil.md5Hex("123"));
        teacher.setUpdateTime(new Date());
        teacherService.save(teacher);
    }

}
