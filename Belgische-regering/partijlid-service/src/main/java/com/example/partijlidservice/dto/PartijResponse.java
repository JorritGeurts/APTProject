package com.example.partijlidservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartijResponse {
    private String partijNaam;
    private String adres;
    private String postCode;
    private String stad;
    private boolean coalitieVlaams;
    private boolean coalitieFederaal;
}
