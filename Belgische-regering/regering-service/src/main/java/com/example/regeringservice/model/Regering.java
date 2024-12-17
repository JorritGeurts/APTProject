package com.example.regeringservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "regering")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Regering {
    private String id;
    private String naam;
    private String beschrijving;
    private String website;
}
