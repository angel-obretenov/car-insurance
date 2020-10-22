package com.safety.car.controllers.rest;

import com.safety.car.exceptions.EmptyException;
import com.safety.car.exceptions.NotFoundException;
import com.safety.car.models.dto.rest.ModelDto;
import com.safety.car.models.entity.Model;
import com.safety.car.services.interfaces.ModelService;
import com.safety.car.utils.mappers.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/model")
public class ModelRestController {
    private final ModelService modelService;
    private final Helper helper;

    @Autowired
    public ModelRestController(ModelService modelService, Helper helper) {
        this.modelService = modelService;
        this.helper = helper;
    }

    @GetMapping
    public List<Model> getAll() {
        try {
            return modelService.getAll();
        } catch (EmptyException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Model getById(@PathVariable int id) {
        try {
            return modelService.getById(id);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/brandId/{id}")
    public List<ModelDto> getByBrandId(@PathVariable int id) {
        try {
            return helper.modelToDto(modelService.getByBrandId(id));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
