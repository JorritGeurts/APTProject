package com.example.partijservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartijRequest {
    private String naam;
    private String adres;
    private String postcode;
    private String stad;
    private boolean coalitieVlaams;
    private boolean coalitieFederaal;
    private String website;
}

