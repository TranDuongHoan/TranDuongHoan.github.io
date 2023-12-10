package com.example.testspringwebmvc.controller;

import com.example.testspringwebmvc.model.response.MedExamScheduleResponse;
import com.example.testspringwebmvc.service.MedExamScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/contact")
public class MedExamScheduleController {

    private final MedExamScheduleService medExamScheduleService;

    @GetMapping
    public String getMedExamSchedule(Model model) {
        List<MedExamScheduleResponse> medexamschedules = medExamScheduleService.getMedExamSchedule();
        model.addAttribute("dsLichKham", medexamschedules);
        return "contact";
    }


}
