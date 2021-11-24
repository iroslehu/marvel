package com.albo.marvel.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "creators")
public class Creators {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "type")
    @NotNull
    private String type;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "comics_id")
    private Comics comics;
}
