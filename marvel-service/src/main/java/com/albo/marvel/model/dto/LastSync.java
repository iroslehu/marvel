package com.albo.marvel.model.dto;

import lombok.*;

import java.util.Date;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LastSync {
    private Integer id;
    private Date date;
}
