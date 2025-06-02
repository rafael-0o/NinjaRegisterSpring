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
    public String addNewNinja(){
        return "Ninja added";
    }
    @GetMapping("/showallninjas")
    public List<NinjaModel> showAllNinjas(){
        return ninjaService.showAllNinjas();
    }
    @GetMapping("/ninjasbyid")
    public String showNinjaById(){
        return "Your specific ninja";
    }
    @PutMapping("/alterninjabyid")
    public String  alterNinjaById(){
        return "changed ninja";
    }
    @DeleteMapping("/deleteninjabyid")
    public String deleteNinjaById(){
        return "Ninja deleted";
    }

}
