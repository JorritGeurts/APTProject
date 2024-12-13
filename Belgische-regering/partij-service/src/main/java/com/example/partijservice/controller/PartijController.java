package com.example.partijservice.controller;

import com.example.partijservice.dto.PartijResponse;
import com.example.partijservice.service.PartijService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partij")
@RequiredArgsConstructor
public class PartijController {

    private final PartijService partijService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<PartijResponse> getAllPartijen() {
        return partijService.getAllPartijen();
    }

    @GetMapping("/naam/{naam}")
    @ResponseStatus(HttpStatus.OK)
    public PartijResponse getPartijlidByNaam(@PathVariable String naam) {
        return partijService.getPartijByNaam(naam);
    }
}
