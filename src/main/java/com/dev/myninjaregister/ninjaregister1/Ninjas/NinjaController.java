package com.dev.myninjaregister.ninjaregister1.Ninjas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/ninjas")
public class NinjaController {
    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/wellcome")
    public String wellcome(){
        return "Wellcome in my first spring page!";
    }

    @PostMapping("/create")
    public NinjaDTO addNewNinja(@RequestBody NinjaDTO ninja){
        return ninjaService.createNewNinja(ninja);
    }
    @GetMapping("/showall")
    public List<NinjaDTO> showAllNinjas(){
        return ninjaService.showAllNinjas();
    }
    @GetMapping("/showbyid/{id}")
    public NinjaDTO showNinjaById(@PathVariable Long id){
        return ninjaService.showNinjaById(id);
    }
    @PutMapping("/editbyid/{id}")
    public NinjaDTO  alterNinjaById(@PathVariable Long id ,@RequestBody NinjaDTO updateNinja){
        return ninjaService.alterNinja(id,updateNinja);
    }
    @DeleteMapping("/deletebyid/{id}")
    public void deleteNinjaById(@PathVariable Long id){
        ninjaService.deleteNinjaId(id);
    }

}
