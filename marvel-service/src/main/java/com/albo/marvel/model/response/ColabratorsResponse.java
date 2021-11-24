package com.albo.marvel.model.response;

import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ColabratorsResponse {
    private Date lastSync;
    private List<String> editors;
    private List<String> writers;
    private List<String> colorists;
}
