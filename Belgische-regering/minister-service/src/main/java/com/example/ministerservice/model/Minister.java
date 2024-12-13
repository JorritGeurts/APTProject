package com.example.ministerservice.model;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "minister")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Minister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naam;
    private String partijlidNaam;
    private String regeringNaam;
}
