package com.albo.marvel.model.response;

import com.albo.marvel.model.dto.Characters;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharactersResponse {
    private Date lastSync;
    private List<Characters> characters;
}
