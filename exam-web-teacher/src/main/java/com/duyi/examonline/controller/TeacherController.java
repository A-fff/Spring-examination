package com.duyi.examonline.controller;

import com.duyi.examonline.commo.CommonData;
import com.duyi.examonline.domain.Teacher;
import com.duyi.examonline.service.TeacherService;
import com.duyi.examonline.vo.PageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TeacherController {
    private Logger logger = LoggerFactory.getLogger(TeacherController.class);
    @Autowired
    private TeacherService teacherService;
    @RequestMapping("/teacher/teacher.html")
    public String toTeacher(Model model){
        logger.info("===============方法执行了======================");
        //这个里面我们需要携带数据
        PageVO pageVO = teacherService.selectTeachers(CommonData.DEFAULT_PAGE,CommonData.DEFAULT_ROWS,null);
        model.addAttribute("pageVO",pageVO);
        return "teacher/teacher";
    }

    @RequestMapping("/teacher/pageTeacher.html")
    public String toPageTeacher(Integer pageNo,String tname,Model model){

        if(pageNo == null){
            pageNo = 1;
        }
        logger.info("pageNo="+pageNo);
        logger.info("tname="+tname);

        PageVO pageVO = teacherService.selectTeachers(pageNo,CommonData.DEFAULT_ROWS,tname);

        model.addAttribute("pageVO",pageVO);
        return "teacher/teacher::#pageTemplate";
    }

    @RequestMapping("/teacher/teacherAddTemplate.html")
    public String toAddTeacher(Long id,Model model){
        if(id !=null){
            //说明这个时候id存在是存在的
            Teacher teacherTemplate = teacherService.findById(id);
            model.addAttribute("teacherTemplate",teacherTemplate);
        }
        return "teacher/teacherAddTemplate";
    }
    @RequestMapping("/teacher/teacherAddTemplate")
    @ResponseBody
    public boolean toAdd(String tname){
        Teacher teacher = new Teacher();
        teacher.setTname(tname);
        return teacherService.save(teacher);
    }

    @RequestMapping("/teacher/teacherEditTemplate")
    @ResponseBody
    public boolean toEdit(Teacher teacher){
        return teacherService.updateTemplate(teacher);
    }

    @RequestMapping("/teacher/deleteAll")
    @ResponseBody
    public void toDelete(String ids){
       teacherService.delete(ids);
    }
}
