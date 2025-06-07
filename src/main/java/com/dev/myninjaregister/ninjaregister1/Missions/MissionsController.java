package com.dev.myninjaregister.ninjaregister1.Missions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/missions")
public class MissionsController {
    private MissionsService missionsService;

    public MissionsController(MissionsService missionsService) {
        this.missionsService = missionsService;
    }

    @PostMapping("/create")
    public MissionsDTO createMission(@RequestBody MissionsDTO mission){
        return missionsService.createNewMission(mission);
    }
    @GetMapping("/showall")
    public List<MissionsDTO> showAllMissions(){
        return missionsService.showAllMissions();
    }
    @GetMapping("/showbyid/{id}")
    public MissionsDTO showMissionById(@PathVariable Long id){
        return missionsService.showMissionsById(id);
    }

    @PutMapping("/editbyid/{id}")
    public MissionsDTO updateMission(@PathVariable Long id, @RequestBody MissionsDTO attmission){
        return missionsService.modifyMission(id,attmission);
    }

    @DeleteMapping("/deletebyid/{id}")
    public void deleteMission(@PathVariable Long id){
        missionsService.deleteMissionById(id);
    }

}
