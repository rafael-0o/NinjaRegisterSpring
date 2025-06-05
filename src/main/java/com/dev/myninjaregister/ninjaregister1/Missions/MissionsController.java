package com.dev.myninjaregister.ninjaregister1.Missions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RestController
@RequestMapping("/api/missions")
public class MissionsController {
    private MissionsService missionsService;
    private final MissionsRepository missionsRepository;

    public MissionsController(MissionsService missionsService,
                              MissionsRepository missionsRepository) {
        this.missionsService = missionsService;
        this.missionsRepository = missionsRepository;
    }
    @PostMapping("/createmission")
    public MissionsModel createMission(@RequestBody MissionsModel mission){
        return missionsRepository.save(mission);
    }
    @GetMapping("/showmissions")
    public List<MissionsModel> showAllMissions(){
        return missionsService.showAllMissions();
    }
    @GetMapping("/showmissionsbyid/{id}")
    public MissionsModel showMissionById(@PathVariable Long id){
        return missionsService.showMissionsById(id);
    }
    @PutMapping("/updatemission")
    public String updateMission(){
        return "Mission Updated";
    }
    @DeleteMapping("/deletemissionbyid/{id}")
    public void deleteMission(@PathVariable Long id){
        missionsService.deleteMissionById(id);
    }
}
