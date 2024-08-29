package com.duyi.examonline.service;

import com.duyi.examonline.domain.Teacher;
import com.duyi.examonline.vo.PageVO;

public interface TeacherService {

    public boolean save(Teacher teacher);

//    根据tname找寻教师的信息
    public Teacher findByName(String tname);

    //    方法是根据id去找用户的信息
    public Teacher findById(Long id);

//    根据ID进行修改密码操作
    public void updatePass(Long id,String pass);

//    修改用户信息
    boolean updateTemplate(Teacher teacher);


//    查询所有的教师信息
    public PageVO selectTeachers(int start, int length, String tname);

//    方法是用来实现删除操作的
    void delete(String ids);

}
