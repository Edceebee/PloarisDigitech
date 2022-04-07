package com.example.PolarisAssessment.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Box {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tired;

    private int weightLimit;

    private int remainingWeightLimit;

    private int batteryCapacity;

    private State stateOfBox;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();
}

