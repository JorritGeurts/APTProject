package com.example.partijlidservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartijlidResponse {
    private Long id;
    private String naam;
    private String telefoonnummer;
    private String partijNaam;  // Derived from the `Partij` entity
    private String regeringNaam;  // Derived from the `Regering` entity
}
