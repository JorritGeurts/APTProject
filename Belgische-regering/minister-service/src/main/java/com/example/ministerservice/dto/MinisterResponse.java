package com.example.ministerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class MinisterResponse {
    private Long id;
    private String naam;
    private String partijlidNaam;
    private String regeringNaam;
}
