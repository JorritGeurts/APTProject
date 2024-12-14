package com.example.regeringservice.controller;

import com.example.regeringservice.dto.RegeringRequest;
import com.example.regeringservice.dto.RegeringResponse;
import com.example.regeringservice.service.RegeringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regering")
@RequiredArgsConstructor
public class RegeringController {

    private final RegeringService regeringService;

    @PutMapping("/{id}/edit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editRegering(@PathVariable String id, @RequestBody RegeringRequest regeringRequest) {
        regeringService.editRegering(id,regeringRequest);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<RegeringResponse> getAllRegeringen() {
        return regeringService.getAllRegeringen();
    }

    @GetMapping("/naam/{naam}")
    @ResponseStatus(HttpStatus.OK)
    public RegeringResponse getRegeringByNaam(@PathVariable String naam) {
        return regeringService.getRegeringByNaam(naam);
    }
}
