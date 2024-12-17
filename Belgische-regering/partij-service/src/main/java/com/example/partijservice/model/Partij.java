package com.example.partijservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "partij")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Partij {
    private String id;
    private String naam;
    private String adres;
    private String postcode;
    private String stad;
    private boolean coalitieVlaams;
    private boolean coalitieFederaal;
    private String website;
}
