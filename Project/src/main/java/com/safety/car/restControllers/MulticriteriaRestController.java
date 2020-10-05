package com.safety.car.restControllers;

import com.safety.car.models.entity.MulticriteriaTable;
import com.safety.car.services.interfaces.MulticriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
            return multicriteriaService.getByCCandAge(cc, age);
        } catch (Exception e) {
            return 0.0;
        }
    }

}
