package com.duyi.examonline.dao;

import com.duyi.examonline.domain.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    Teacher findByName(String tname);

    //    方法是根据id去找用户的信息
    Teacher findById(Long id);
    void updatePass(@Param("id") Long id, @Param("pass")String pass);

//    更改用户信息
    void updateTemplate(Teacher teacher);

    //    获取teacher的总个数
    Long getTeacherNumber(String tname);

    //    查询所有的用户信息
    List<Teacher> selectTeachers(@Param("start") int start, @Param("length") int length, @Param("tname") String tname);




}