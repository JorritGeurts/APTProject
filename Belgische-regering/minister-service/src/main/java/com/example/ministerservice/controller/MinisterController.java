package com.example.ministerservice.controller;

import com.example.ministerservice.dto.MinisterRequest;
import com.example.ministerservice.dto.MinisterResponse;
import com.example.ministerservice.service.MinisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/minister")
@RequiredArgsConstructor
public class MinisterController {

    private final MinisterService ministerService;

    @PutMapping("/{id}/edit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editMinister(@PathVariable long id, @RequestBody MinisterRequest ministerRequest) {
        ministerService.editMinister(id, ministerRequest);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public void createMinister(@RequestBody MinisterRequest ministerRequest) {
        ministerService.createMinister(ministerRequest);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<MinisterResponse> getAllMinisters() {
        return ministerService.getAllMinisters();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MinisterResponse getMinisterById(@PathVariable long id) {
        return ministerService.getMinisterById(id);
    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAppointment(@PathVariable long id) {
        ministerService.deleteMinister(id);
    }
}
