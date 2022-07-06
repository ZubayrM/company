package com.company.API.controllers.resources;

import com.company.API.model.EquipmentScheduleDto;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/equipmentSchedule")
public interface EquipmentScheduleController {

    @PostMapping("/add")
    String add(EquipmentScheduleDto dto, Model model, Errors errors);

}
