package com.albo.marvel.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "comics")
public class Comics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "title")
    @NotNull
    private String title;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "characters_id")
    private Characters characters;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "comics",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Creators> creatorsList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(
            mappedBy = "comics",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Colaborators> colaboratorsLits = new ArrayList<>();

}
