package com.dev.myninjaregister.ninjaregister1.Missions;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissionsService {
    public MissionsRepository missionsRepository;
    public MissionsModel createNewMission(MissionsModel mission){
        return missionsRepository.save(mission);

    }

    public List<MissionsModel> showAllMissions(){
        return missionsRepository.findAll();
    }
    public MissionsModel showMissionsById(Long id){
        Optional<MissionsModel> missionid = missionsRepository.findById(id);
        return missionid.orElse(null);
    }
    public void deleteMissionById(Long id){
        missionsRepository.deleteById(id);
    }

}
