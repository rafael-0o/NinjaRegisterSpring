package com.dev.myninjaregister.ninjaregister1.Missions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MissionsDTO {
    private Long id;
    private String name;
    private String difficulty;
}
