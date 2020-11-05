package com.safety.car.controllers.mvc;

import com.safety.car.services.interfaces.MulticriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/multicriteria")
public class MulticriteriaTableController {

    private final MulticriteriaService multicriteriaService;

    @Autowired
    public MulticriteriaTableController(MulticriteriaService multicriteriaService) {
        this.multicriteriaService = multicriteriaService;
    }


    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("criterias", multicriteriaService.getAll());

        return "multicriteria";
    }
}
