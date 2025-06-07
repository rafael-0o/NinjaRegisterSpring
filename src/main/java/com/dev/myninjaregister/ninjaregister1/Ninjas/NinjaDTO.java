package com.dev.myninjaregister.ninjaregister1.Ninjas;

import com.dev.myninjaregister.ninjaregister1.Missions.MissionsDTO;
import com.dev.myninjaregister.ninjaregister1.Missions.MissionsModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NinjaDTO {
    
    private Long id;
    private String name;
    private String email;
    private int age;
    private String rank;
    private MissionsModel mission;

}
