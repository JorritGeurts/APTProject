package com.example.ministerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MinisterRequest {
    private String naam;
    private String partijlidNaam;
    private String regeringNaam;
}
