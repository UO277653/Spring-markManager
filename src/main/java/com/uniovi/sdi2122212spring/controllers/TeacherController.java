package com.uniovi.sdi2122212spring.controllers;

import com.uniovi.sdi2122212spring.entities.Teacher;
import com.uniovi.sdi2122212spring.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/teacher/add/{id}")
    public String addTeacher(@PathVariable Long id) {
        return "Teacher " + id + " was added";
    }

    @RequestMapping("/teacher/edit/{id}")
    public String editTeacher(@PathVariable Long id) {
        return "Teacher " + id + " was edited";
    }

    @RequestMapping("/teacher/detail/{id}")
    public String detailsTeacher(@PathVariable Long id) {
        return teacherService.getTeacher(id).toString();
    }

    @RequestMapping("/teacher/delete/{id}")
    public String removeTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return "Ok";
    }
}
