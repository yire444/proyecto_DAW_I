package com.nova.talentnova.controller;

import com.nova.talentnova.model.BillingCycle;
import com.nova.talentnova.repository.IBillingCycleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/billing-cycle")
@CrossOrigin("*")
@RequiredArgsConstructor
public class BillingCycleController {

    private final IBillingCycleRepository repo;

    @GetMapping
    public List<BillingCycle> getBillingCycles() {
        return repo.findAll();
    }
}
