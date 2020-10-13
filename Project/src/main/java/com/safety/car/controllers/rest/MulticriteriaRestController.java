package com.safety.car.controllers.rest;

import com.safety.car.services.interfaces.MulticriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/multi")
public class MulticriteriaRestController {
    private final MulticriteriaService multicriteriaService;

    @Autowired
    public MulticriteriaRestController(MulticriteriaService multicriteriaService) {
        this.multicriteriaService = multicriteriaService;
    }

    @GetMapping
    public Double getByCcAndAge(@RequestParam int cc, @RequestParam int age) {
        try {
            return multicriteriaService.getByCCAndAge(cc, age);
        } catch (Exception e) {
            return 0.0;
        }
    }
}