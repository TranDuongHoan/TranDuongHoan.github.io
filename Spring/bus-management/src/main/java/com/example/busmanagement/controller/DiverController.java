package com.example.busmanagement.controller;

import com.example.busmanagement.enity.Driver;
import com.example.busmanagement.model.request.DriverCreationRequest;
import com.example.busmanagement.model.request.DriverUpdateRequest;
import com.example.busmanagement.model.response.DriverDetailResponse;
import com.example.busmanagement.service.DriverService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
@AllArgsConstructor

public class DiverController {

    private final DriverService  driverService;

    @GetMapping("/drivers")
    public String home(Model model) {
        List<Driver> drivers = driverService.getAll();
        model.addAttribute("dsTaiXe", drivers);
        return "drivers";
    }

    @GetMapping("/create-driver")
    public String forwardToDriverCreation(Model model) {
        model.addAttribute("taiXeThemMoi", new DriverCreationRequest());
        return "driver-creation";
    }

    @GetMapping("/delete-driver/{id}")
    public String deleteDriver(@PathVariable("id") int id, Model model) {
        driverService.deleteDriver(id);
        List<Driver> drivers = driverService.getAll();
        model.addAttribute("dsTaiXe", drivers);
        return "drivers";
    }


    @PostMapping("/create-driver")
    public String createDriver(@ModelAttribute("taiXeThemMoi") DriverCreationRequest driver, Model model) {
        driverService.createDriver(driver);
        List<Driver> drivers = driverService.getAll();
        model.addAttribute("dsTaiXe", drivers);
        return "drivers";
    }

    @GetMapping("/update-driver/{driver-id}")
    public String forwardToDriverUpdate(Model model, @PathVariable("driver-id") int id){
        DriverDetailResponse driver = driverService.findById(id);
        model.addAttribute("taiXeMuonCapNhat", driver);
        return "driver-update";
    }

    @PostMapping("/update-driver")
    public String updateDriver(@ModelAttribute("taiXeMuonCapNhat") DriverUpdateRequest driver, Model model) {
        driverService.updateDriver(driver);
        List<Driver> drivers = driverService.getAll();
        model.addAttribute("dsTaiXe", drivers);
        return "drivers";
    }
}
