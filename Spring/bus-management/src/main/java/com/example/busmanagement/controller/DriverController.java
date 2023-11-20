package com.example.busmanagement.controller;

import com.example.busmanagement.enity.Driver;
import com.example.busmanagement.exception.DriverNotFoundException;
import com.example.busmanagement.model.request.DriverCreationRequest;
import com.example.busmanagement.model.request.DriverUpdateRequest;
import com.example.busmanagement.model.response.DriverDetailResponse;
import com.example.busmanagement.service.DriverService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor

public class DriverController {

    private final DriverService driverService;

    @GetMapping("/drivers")
    public String home(Model model) {
        List<Driver> drivers = driverService.getAll();
        model.addAttribute("dsTaiXe", drivers);
        return "drivers";
    }

    @GetMapping("/delete-driver/{id}")
    public String deleteDriver(@PathVariable("id") int id, Model model) throws DriverNotFoundException {
        List<Driver> drivers = driverService.deleteDriver(id);
        model.addAttribute("dsTaiXe", drivers);
        return "drivers";
    }

    @GetMapping("/create-driver")
    public String forwardToDriverCreation(Model model) {
        model.addAttribute("taiXeThemMoi", new DriverCreationRequest());
        return "driver-creation";
    }

    @PostMapping("/create-driver")
    public String createDriver(@ModelAttribute("taiXeThemMoi") @Valid DriverCreationRequest driver,  Errors errors) {
        if (null != errors && errors.getErrorCount() > 0) {
            return "driver-creation";
        }
        List<Driver> drivers = driverService.createDriver(driver);
        return "redirect:/drivers";
    }


    @GetMapping("/update-driver/{driver-id}")
    public String forwardToDriverUpdate(Model model, @PathVariable("driver-id") int id) throws DriverNotFoundException {
        DriverDetailResponse driver = driverService.findById(id);
        model.addAttribute("taiXeMuonCapNhat", driver);
        return "driver-update";
    }

    @PostMapping("/update-driver")
    public String updateDriver(@ModelAttribute("taiXeMuonCapNhat") @Valid DriverUpdateRequest driver, Errors errors) throws DriverNotFoundException {
        if (null != errors && errors.getErrorCount() > 0) {
            return "driver-update";
        }
        List<Driver> drivers = driverService.updateDriver(driver);
        return "redirect:/drivers";
    }
}
