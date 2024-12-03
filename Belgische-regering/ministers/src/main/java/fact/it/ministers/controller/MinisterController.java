package fact.it.ministers.controller;

import fact.it.ministers.dto.MinisterResponse;
import fact.it.ministers.service.MinisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mysql-minister")
@RequiredArgsConstructor
public class MinisterController {

    private final MinisterService ministerService;

    @GetMapping
    public ResponseEntity<List<MinisterResponse>> getAllPartijen() {
        List<MinisterResponse> responses = ministerService.getAllMinisters();
        return ResponseEntity.ok(responses);
    }
}
