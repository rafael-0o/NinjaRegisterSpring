package com.dev.myninjaregister.ninjaregister1.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api/ninjas")
public class NinjaController {
    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/wellcome")
    public String wellcome(){
        return "Wellcome in my first spring page!";
    }

    @PostMapping("/create")
    @Operation(summary = "Create a ninja", description = "create a new ninja and store the data of ninja in Db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ninja Created"),
            @ApiResponse(responseCode = "400", description = "Error in creation")
    })

    public ResponseEntity<String> addNewNinja(@RequestBody NinjaDTO ninja){
        NinjaDTO createdNinja = ninjaService.createNewNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("The ninja was created");
    }

    @Operation(summary = "show all ninjas", description = "show all data of ninja from the Db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninjas were shown"),
            @ApiResponse(responseCode = "404", description ="Ninjas were not found")
    })
    @GetMapping("/showall")
    public  ResponseEntity<List<?>> showAllNinjas(){
        List <NinjaDTO> ninjasList = ninjaService.showAllNinjas();
        if (!ninjasList.isEmpty()) {
            return ResponseEntity.ok(ninjasList);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonList("Not found ninjas"));
        }
    }
    @Operation(summary = "Show ninja by id", description = "take the saved data of a ninja and show it asking for the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja was found"),
            @ApiResponse(responseCode = "404", description = "Ninja not found")
    })
    @GetMapping("/showbyid/{id}")
    public ResponseEntity<?> showNinjaById(@PathVariable Long id){
        NinjaDTO ninjaDTO = ninjaService.showNinjaById(id);
        if (ninjaDTO!=null){
            return ResponseEntity.ok("The ninja is: "+ninjaDTO );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Didn't find any ninja with that id"+ id);
        }
    }
    @Operation(summary = "Update a ninja", description = "Update a ninja using ninja id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The ninja was updated"),
            @ApiResponse(responseCode = "400", description = "Ninja was not updated")
    })
    @PutMapping("/editbyid/{id}")
    public ResponseEntity<?>  alterNinjaById(@PathVariable Long id ,@RequestBody NinjaDTO updateNinja){
        NinjaDTO ninjaDTO = ninjaService.alterNinja(id,updateNinja);
        if (ninjaDTO!=null){
            return ResponseEntity.ok("Ninja Updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja not found");
        }
    }
    @Operation(summary = "Delete a ninja", description = "Delete a ninja from Db using ninja id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja was deleted"),
            @ApiResponse(responseCode = "400", description = "Ninja was not deleted")
    })
    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<?> deleteNinjaById(@PathVariable Long id){
        if (ninjaService.showNinjaById(id)!=null) {
            ninjaService.deleteNinjaId(id);
            return ResponseEntity.ok("The ninja was deleted ");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The ninja with "+id+" wasn't found");
        }
    }
}
