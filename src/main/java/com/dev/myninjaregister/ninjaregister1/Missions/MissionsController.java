package com.dev.myninjaregister.ninjaregister1.Missions;

import com.dev.myninjaregister.ninjaregister1.Common.Response.GenericAnswer;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createMission(@RequestBody MissionsDTO mission) {
        MissionsDTO missionsDTO = missionsService.createNewMission(mission);
        return ResponseEntity.status(HttpStatus.OK)
                .body("The mission was created!");
    }

    @GetMapping("/showall")
    public ResponseEntity<GenericAnswer<List<MissionsDTO>>> showAllMissions() {
        List<MissionsDTO> missionsList = missionsService.showAllMissions();
        if (missionsList != null) {
            GenericAnswer<List<MissionsDTO>> answer = new GenericAnswer<>(
                    missionsList,
                    "This s the full list of missions"

            );
            return ResponseEntity.status(HttpStatus.OK)
                    .body(answer);
        } else {
            GenericAnswer<List<MissionsDTO>> noAnswer = new GenericAnswer<>(
                    null,
                    "Do no exist any mission"
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(noAnswer);
        }
    }

    @GetMapping("/showbyid/{id}")
    public ResponseEntity<GenericAnswer<MissionsDTO>> showMissionById(@PathVariable Long id) {
        MissionsDTO missionsDTOid = missionsService.showMissionsById(id);
        if (missionsDTOid != null) {
            GenericAnswer<MissionsDTO> answer = new GenericAnswer<>(
                    missionsDTOid,
                    "The mission was found"
            );
            return ResponseEntity.status(HttpStatus.OK)
                    .body(answer);
        } else {
            GenericAnswer<MissionsDTO> noAnswer = new GenericAnswer<>(
                    null,
                    "The mission with this id was not found"
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(noAnswer);
        }
    }

    @PutMapping("/editbyid/{id}")
    public ResponseEntity<GenericAnswer<MissionsDTO>> updateMission(@PathVariable Long id, @RequestBody MissionsDTO attmission) {
        MissionsDTO missionsDTOedit = missionsService.modifyMission(id, attmission);
        if (missionsDTOedit != null) {
            GenericAnswer<MissionsDTO> answer = new GenericAnswer<>(
                    missionsDTOedit,
                    "The ninjas was edited"
            );
            return ResponseEntity.status(HttpStatus.OK)
                    .body(answer);
        } else {
            GenericAnswer<MissionsDTO> noAnswer = new GenericAnswer<>(
                    null,
                    "The mission was not found"
            );
            return ResponseEntity.status(HttpStatus.OK)
                    .body(noAnswer);
        }
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<String> deleteMission(@PathVariable Long id) {
        if (missionsService.showMissionsById(id) != null) {
            missionsService.deleteMissionById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("The ninja was deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The ninjas was not found");
        }
    }
}