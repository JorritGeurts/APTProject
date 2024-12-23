package com.example.partijlidservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegeringResponse {
    private String id;
    private String naam;
    private String beschrijving;
    private String website;
}
