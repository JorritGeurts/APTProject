package fact.it.politiekeparitijen.controller;

import fact.it.politiekeparitijen.dto.PartijResponse;
import fact.it.politiekeparitijen.service.PartijService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/partij")
@RequiredArgsConstructor
public class PartijController {

    private final PartijService partijService;

    @GetMapping
    public ResponseEntity<List<PartijResponse>> getAllPartijen() {
        List<PartijResponse> responses = partijService.getAllPartijen();
        return ResponseEntity.ok(responses);
    }

}
