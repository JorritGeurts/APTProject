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

    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editRegering(@PathVariable String id, @RequestParam String naam) {
        // Create the RegeringRequest object directly with the query parameter
        RegeringRequest request = new RegeringRequest(naam);
        // Call the service to edit the Regering
        regeringService.editRegering(id, request);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<RegeringResponse> getAllRegeringen() {
        return regeringService.getAllRegeringen();
    }
}
