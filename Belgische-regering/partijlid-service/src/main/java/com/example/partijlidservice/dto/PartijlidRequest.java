package com.example.partijlidservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartijlidRequest {
    private String naam;
    private String telefoonnummer;
    private Long partijId;  // Assuming the frontend provides IDs for Partij
    private Long regeringId;  // Assuming the frontend provides IDs for Regering
}
