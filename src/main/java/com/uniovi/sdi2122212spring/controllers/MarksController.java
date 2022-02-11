package com.uniovi.sdi2122212spring.controllers;

import com.uniovi.sdi2122212spring.entities.*;
import com.uniovi.sdi2122212spring.services.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
public class MarksController {

    @Autowired //Inyectar el servicio
    private MarksService marksService;

    @RequestMapping("/mark/list")
    public String getList() {

        return marksService.getMarks().toString();
    }

    @RequestMapping(value = "/mark/add", method = RequestMethod.POST)
    public String setMark(@ModelAttribute Mark mark) {
        marksService.addMark(mark);
        return "Ok";
    }

    @RequestMapping("/mark/details/{id}")
    public String getDetail(@PathVariable Long id) {
        return marksService.getMark(id).toString();
    }

    @RequestMapping("/mark/delete/{id}")
    public String deleteMark(@PathVariable Long id){
        marksService.deleteMark(id);
        return "Ok";
    }
}
