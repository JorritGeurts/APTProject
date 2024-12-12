package com.example.partijlidservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartijlidRequest {
    private String naam;
    private String email;
    private String partijNaam;
    private String regeringNaam;
}
