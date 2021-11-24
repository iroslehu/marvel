package com.albo.marvel.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "lastsync")
public class LastSync {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "date")
    @NotNull
    private Date date;
}
