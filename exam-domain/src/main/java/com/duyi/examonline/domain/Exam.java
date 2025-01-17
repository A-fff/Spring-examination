package com.duyi.examonline.domain;

import java.util.Date;

public class Exam {
    private Long id;

    private String name;

    private String course;

    private Long templateId;

    private Long tid;

    private Date startTime;

    private Date 	endTime;

    private Integer duration;

    private String 	status;

    private Date 	createTime;

    private Date updateTime;

    private String yl1;

    private String yl2;

    private String yl3;

    private String yl4;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return 	endTime;
    }

    public void setEndTime(Date 	endTime) {
        this.	endTime = 	endTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return 	status;
    }

    public void setStatus(String 	status) {
        this.	status = 	status;
    }

    public Date getCreateTime() {
        return 	createTime;
    }

    public void setCreateTime(Date 	createTime) {
        this.	createTime = 	createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getYl1() {
        return yl1;
    }

    public void setYl1(String yl1) {
        this.yl1 = yl1;
    }

    public String getYl2() {
        return yl2;
    }

    public void setYl2(String yl2) {
        this.yl2 = yl2;
    }

    public String getYl3() {
        return yl3;
    }

    public void setYl3(String yl3) {
        this.yl3 = yl3;
    }

    public String getYl4() {
        return yl4;
    }

    public void setYl4(String yl4) {
        this.yl4 = yl4;
    }
}