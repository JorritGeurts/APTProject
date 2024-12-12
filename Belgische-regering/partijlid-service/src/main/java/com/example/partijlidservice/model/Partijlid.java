package com.example.partijlidservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "partijlid")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Partijlid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naam;
    private String email;
    private String partijNaam;
    private String regeringNaam;
}
