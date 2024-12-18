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
@CrossOrigin(origins = "http://localhost:4200")
public class PartijlidController {

    private final PartijlidService partijlidService;

    @PutMapping("/{id}/edit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editPartijlid(@PathVariable long id, @RequestBody PartijlidRequest partijlidRequest) {
        partijlidService.editPartijlid(id, partijlidRequest);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public void createPartijlid(@RequestBody PartijlidRequest partijlidRequest) {
        partijlidService.createPartijlid(partijlidRequest);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<PartijlidResponse> getAllPartijleden() {
        return partijlidService.getAllPartijleden();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PartijlidResponse getPartijlidById(@PathVariable long id) {
        return partijlidService.getPartijlidById(id);
    }

    @GetMapping("/naam/{naam}")
    @ResponseStatus(HttpStatus.OK)
    public PartijlidResponse getPartijlidByNaam(@PathVariable String naam) {
        return partijlidService.getPartijlidByNaam(naam);
    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAppointment(@PathVariable long id) {
        partijlidService.deletePartijlid(id);
    }
}
