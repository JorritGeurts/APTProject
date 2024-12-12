package com.example.partijlidservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartijlidResponse {
    private Long id;
    private String naam;
    private String email;
    private String partijNaam;
    private String regeringNaam;
}
