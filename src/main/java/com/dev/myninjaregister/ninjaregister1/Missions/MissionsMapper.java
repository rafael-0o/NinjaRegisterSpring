package com.dev.myninjaregister.ninjaregister1.Missions;

import com.dev.myninjaregister.ninjaregister1.Ninjas.NinjaDTO;
import org.springframework.stereotype.Component;

@Component
public class MissionsMapper {
    MissionsModel map(MissionsDTO missionsDTO){
        MissionsModel missionsModel = new MissionsModel();
        missionsModel.setId(missionsDTO.getId());
        missionsModel.setName(missionsDTO.getName());
        missionsModel.setDifficulty(missionsDTO.getDifficulty());
        return missionsModel;
    }
    MissionsDTO map(MissionsModel missionsModel){
        MissionsDTO missionsDTO= new MissionsDTO();
        missionsDTO.setId(missionsModel.getId());
        missionsDTO.setName(missionsModel.getName());
        missionsDTO.setDifficulty(missionsModel.getDifficulty());
        return missionsDTO;
    }
}
