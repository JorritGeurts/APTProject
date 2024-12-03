package fact.it.regeringen.controller;

import fact.it.regeringen.dtos.RegeringResponse;
import fact.it.regeringen.service.RegeringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/regering")
@RequiredArgsConstructor
public class RegeringController {

    private final RegeringService regeringService;

    @GetMapping
    public ResponseEntity<List<RegeringResponse>> getAllRegeringen() {
        List<RegeringResponse> responses = regeringService.getAllRegeringen();
        return ResponseEntity.ok(responses);
    }
}
