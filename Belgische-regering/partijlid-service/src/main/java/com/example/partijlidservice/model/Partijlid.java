package com.example.partijlidservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "partijlid")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Partijlid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naam;

    private String telefoonnummer;

    /*
    @ManyToOne
    @JoinColumn(name = "partijId", referencedColumnName = "partijId")
    private Partij partij;

    @ManyToOne
    @JoinColumn(name = "regeringId", referencedColumnName = "regeringId")
    private Regering regering;
    */
}
