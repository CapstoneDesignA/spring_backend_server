package com.example.backend.congest.presentation;

import com.example.backend.congest.dto.CongestResponse;
import com.example.backend.congest.service.CongestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/congest")
public class CongestController {

    private final CongestService service;

    public CongestController(final CongestService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public CongestResponse getCongestByStoreId(@PathVariable Long id) {
        return service.getCongest(id);
    }

}
