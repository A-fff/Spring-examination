package com.duyi.examonline.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.duyi.examonline.domain.Teacher;
import com.duyi.examonline.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 提供一些基本功能处理
 */
@Controller
public class CommonController {

    @Autowired
    private TeacherService teacherService;
//    需要一个日志属性
    Logger logger = LoggerFactory.getLogger(CommonController.class);



    @RequestMapping({"/common/login.html","/"})
    public String login(){
        return "common/login";
    }

    @RequestMapping("/common/login")
    @ResponseBody
    public boolean checkLogin(String tname, String pass, HttpSession session){
        logger.info(tname);
        logger.info(pass);
//        先根据这个tname寻找这个用户信息
        Teacher teacher = teacherService.findByName(tname);
        if(teacher == null){
            logger.info("没有获取到teacher对象");
            return false;
        }
//        使密码进行一个加密操作
        pass = DigestUtil.md5Hex(pass);
        if(!teacher.getPass().equals(pass)){
            logger.info("存储的密码和输入的密码不一致");
            return false;
        }

//        代码自此说明账号或密码都是正确的 需要存入到session中
        session.setAttribute("loginTeacher",teacher);
        return true;
    }

    @RequestMapping("/common/main.html")
    public String mian(){
        return "common/main.html";
    }

    @RequestMapping("common/exit")
    public String exit(HttpSession session){
        session.removeAttribute("loginTeacher");
        return "common/login";
    }

    @RequestMapping("/common/timeout.html")
    public String toTimeOut(){
        return "common/timeout";
    }

    @RequestMapping("/common/toUpdatePass.html")
    public String   toUpdatePass(){
        return "common/toUpdatePass";
    }

    @RequestMapping("/common/UpdatePass")
    @ResponseBody
    public boolean UpdatePass( String oldPass, String newPass, HttpSession session){
        logger.info("oldPass="+oldPass);
        logger.info("newPass="+newPass);

        //这里我们需要在session中获取当前用户的
        Teacher teacher = (Teacher) session.getAttribute("loginTeacher");
//        判断数据中存放的密码和用户输入的密码是否一致
        if(teacher.getPass().equals(oldPass)){
            return false;
        }
//        把新密码进行一个加密操作存放到数据库当中去
        newPass = DigestUtil.md5Hex(newPass);
        teacher.setUpdateTime(new Date());

        teacherService.updatePass(teacher.getId(),newPass);
        return true;

    }
}
