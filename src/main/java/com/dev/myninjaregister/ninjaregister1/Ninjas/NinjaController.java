package com.dev.myninjaregister.ninjaregister1.Ninjas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping
public class NinjaController {
    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/wellcome")
    public String wellcome(){
        return "Wellcome in my first spring page!";
    }

    @PostMapping("/addninja")
    public NinjaModel addNewNinja(@RequestBody NinjaModel ninja){
        return ninjaService.createNewNinja(ninja);
    }
    @GetMapping("/showallninjas")
    public List<NinjaModel> showAllNinjas(){
        return ninjaService.showAllNinjas();
    }
    @GetMapping("/ninjasbyid/{id}")
    public NinjaModel showNinjaById(@PathVariable Long id){
        return ninjaService.showNinjaById(id);
    }
    @PutMapping("/alterninjabyid/{id}")
    public NinjaModel  alterNinjaById(@PathVariable Long id ,@RequestBody NinjaModel attninja){
        return ninjaService.alterNinja(id,attninja);
    }
    @DeleteMapping("/deleteninjabyid/{id}")
    public void deleteNinjaById(@PathVariable Long id){
        ninjaService.deleteNinjaId(id);
    }

}
