package com.dev.myninjaregister.ninjaregister1.Missions;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissionsService {
    private MissionsRepository missionsRepository;
    private MissionsMapper missionsMapper;

    public MissionsService(MissionsRepository missionsRepository, MissionsMapper missionsMapper) {
        this.missionsRepository = missionsRepository;
        this.missionsMapper = missionsMapper;
    }

    public MissionsDTO createNewMission(MissionsDTO missionDTO){
        MissionsModel mission = missionsMapper.map(missionDTO);
        mission = missionsRepository.save(mission);
        return missionsMapper.map(mission);
    }

    public List<MissionsDTO> showAllMissions(){
        List<MissionsModel> allmissions = missionsRepository.findAll();
        return allmissions.stream()
                .map(missionsMapper::map)
                .collect(Collectors.toList());
    }

    public MissionsDTO showMissionsById(Long id){
        Optional<MissionsModel> missionid = missionsRepository.findById(id);
        return missionid.map(missionsMapper::map).orElse(null);
    }

    public void deleteMissionById(Long id){
        missionsRepository.deleteById(id);
    }

    public MissionsDTO modifyMission( @PathVariable Long id,@RequestBody MissionsDTO missionsDTO){
        Optional<MissionsModel> existentmission = missionsRepository.findById(id);
        if(existentmission.isPresent()){
            MissionsModel missionatt = missionsMapper.map(missionsDTO);
            missionatt.setId(id);
            MissionsModel savedmission= missionsRepository.save(missionatt);
            return missionsMapper.map(savedmission);
        }
        return null;
    }
}
