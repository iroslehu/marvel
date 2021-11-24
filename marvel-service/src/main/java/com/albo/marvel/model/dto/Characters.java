package com.albo.marvel.model.dto;

import lombok.*;

import java.util.List;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Characters {
    private String character;
    private List<String> comics;
}
