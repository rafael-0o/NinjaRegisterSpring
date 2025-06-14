package com.dev.myninjaregister.ninjaregister1.Ninjas;

import com.dev.myninjaregister.ninjaregister1.Common.Response.GenericAnswer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> addNewNinja(@RequestBody NinjaDTO ninja){
        NinjaDTO createdNinja = ninjaService.createNewNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("The ninja was created");
    }
    @GetMapping("/showall")
    public  ResponseEntity<GenericAnswer<List<NinjaDTO>>> showAllNinjas(){
        List <NinjaDTO> ninjasList = ninjaService.showAllNinjas();
        if (!ninjasList.isEmpty()) {
            GenericAnswer<List<NinjaDTO>> answer = new GenericAnswer<>(
                    ninjasList,
                    "This is the full ninjas List"
                    );
            return ResponseEntity.ok(answer);
        }
        else {
            GenericAnswer<List<NinjaDTO>> noAnswer = new GenericAnswer<>(
                    null,
                    "No ninja found"
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(noAnswer);
        }
    }
    @GetMapping("/showbyid/{id}")
    public ResponseEntity<GenericAnswer> showNinjaById(@PathVariable Long id){
        NinjaDTO ninjaDTO = ninjaService.showNinjaById(id);
        if (ninjaDTO!=null){
            GenericAnswer okNinja = new GenericAnswer<>(
                    ninjaDTO,
                    "Ninja successfully found!"
            );
            return ResponseEntity.ok(okNinja);
        } else {
            GenericAnswer notFoundNinja= new GenericAnswer<>(
                    null,
                    "Didn't find any ninja with that id"
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(notFoundNinja);
        }
    }
    @PutMapping("/editbyid/{id}")
    public ResponseEntity<GenericAnswer>  alterNinjaById(@PathVariable Long id ,@RequestBody NinjaDTO updateNinja){
        NinjaDTO ninjaDTO = ninjaService.alterNinja(id,updateNinja);
        if (ninjaDTO!=null){
            GenericAnswer foundNinja = new GenericAnswer<>(
              ninjaDTO,
              "Ninja updated"
            );
            return ResponseEntity.ok(foundNinja);
        } else {
            GenericAnswer notFoundNinja = new GenericAnswer<>(
                    null,
                    "Ninja not found"
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(notFoundNinja);
        }

    }
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
