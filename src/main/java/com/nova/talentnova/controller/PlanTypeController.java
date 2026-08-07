package com.nova.talentnova.controller;

import com.nova.talentnova.model.PlanType;
import com.nova.talentnova.repository.IPlanTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/plan-type")
@CrossOrigin("*")
@RequiredArgsConstructor
public class PlanTypeController {

    private final IPlanTypeRepository repo;

    @GetMapping
    public List<PlanType> getAll() {
        return repo.findAll();
    }
}
