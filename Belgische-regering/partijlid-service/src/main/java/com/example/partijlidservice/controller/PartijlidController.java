package com.example.partijlidservice.controller;

import com.example.partijlidservice.dto.PartijlidRequest;
import com.example.partijlidservice.dto.PartijlidResponse;
import com.example.partijlidservice.service.PartijlidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partijlid")
@RequiredArgsConstructor
public class PartijlidController {

    private final PartijlidService partijlidService;

    /*@GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PartijlidResponse getPartijlidById(@PathVariable Long id) {
        return partijlidService.getPartijlidById(id);
    }*/

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PartijlidResponse> getAllPartijleden() {
        return partijlidService.getAllPartijleden();
    }
}
