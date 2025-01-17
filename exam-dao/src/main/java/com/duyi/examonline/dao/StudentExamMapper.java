package com.duyi.examonline.dao;

import com.duyi.examonline.domain.StudentExam;
import org.apache.ibatis.annotations.Param;

public interface StudentExamMapper {
    int deleteByPrimaryKey(@Param("examId") Long examId, @Param("studentId") Long studentId);

    int insert(StudentExam record);

    int insertSelective(StudentExam record);

    StudentExam selectByPrimaryKey(@Param("examId") Long examId, @Param("studentId") Long studentId);

    int updateByPrimaryKeySelective(StudentExam record);

    int updateByPrimaryKeyWithBLOBs(StudentExam record);

    int updateByPrimaryKey(StudentExam record);
}