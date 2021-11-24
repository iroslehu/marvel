package com.albo.marvel.model.entity;

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
@Entity(name = "characters")
public class Characters {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "uid")
    private Integer uid;

    @Column(name = "name")
    @NotNull
    private String name;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "characters",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Comics> comics = new ArrayList<>();

}
