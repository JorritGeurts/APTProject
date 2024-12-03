package com.example.partijlidservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "partijlidItem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartijlidItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String PartijNaam;
}
