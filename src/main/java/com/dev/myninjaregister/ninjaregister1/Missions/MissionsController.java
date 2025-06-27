package com.dev.myninjaregister.ninjaregister1.Missions;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/missions")
public class MissionsController {
    private MissionsService missionsService;

    public MissionsController(MissionsService missionsService) {
        this.missionsService = missionsService;
    }
    @Operation(summary = "create mission", description = "create a mission and store de mission data in Db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Mission was created"),
            @ApiResponse(responseCode = "400", description = "Mission was not created")
    })
    @PostMapping("/create")
    public ResponseEntity<String> createMission(@RequestBody MissionsDTO mission) {
        MissionsDTO missionsDTO = missionsService.createNewMission(mission);
        return ResponseEntity.status(HttpStatus.OK)
                .body("The mission was created!");
    }

    @Operation(summary = "show all missions", description = "Show all mission data from Db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missions were shown"),
            @ApiResponse(responseCode = "404", description = "Missions were not found")
    })
    @GetMapping("/showall")
    public ResponseEntity<List<?>> showAllMissions() {
        List<MissionsDTO> missionsList = missionsService.showAllMissions();
        if (missionsList != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(missionsList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonList("Not found any mission"));
        }
    }

    @Operation(summary = "show mission by id", description = "Show a mission from Db using mission id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mission was found"),
            @ApiResponse(responseCode = "404", description = "Mission was not found")
    })
    @GetMapping("/showbyid/{id}")
    public ResponseEntity<?> showMissionById(@PathVariable Long id) {
        MissionsDTO missionsDTOid = missionsService.showMissionsById(id);
        if (missionsDTOid != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(missionsDTOid);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Mission with this id: "+ id+" was not found");
        }
    }

    @Operation(summary = "update a mission", description = "Update a mission from Db using mission id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Mission was updated"),
            @ApiResponse(responseCode = "400", description = "Mission was not updated")
    })
    @PutMapping("/editbyid/{id}")
    public ResponseEntity<?> updateMission(@PathVariable Long id, @RequestBody MissionsDTO attmission) {
        MissionsDTO missionsDTOedit = missionsService.modifyMission(id, attmission);
        if (missionsDTOedit != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("The mission with id: "+id+" was updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Not found this id");
        }
    }

    @Operation(summary = "delete a mission", description = "Delete a mission from Db using mission id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mission was deleted"),
            @ApiResponse(responseCode = "400", description = "Mission was not deleted")
    })
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