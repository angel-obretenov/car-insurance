package com.safety.car.controllers.rest;

import com.safety.car.models.entity.Model;
import com.safety.car.services.interfaces.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static java.lang.String.format;

@RestController
@RequestMapping("/api/model")
public class ModelRestController {
    private final ModelService modelService;

    @Autowired
    public ModelRestController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/brandId/{id}")
    public List<Model> getByBrandId(@PathVariable int id) {
        try {
            return modelService.getByBrandId(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, format("Models with brand id %d not found!", id));
        }
    }
}
