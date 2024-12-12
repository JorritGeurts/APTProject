package com.example.partijlidservice.model;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "partijlid")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Partijlid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naam;
    private String email;
    private String partijNaam;
    private String regeringNaam;
}
