package com.dev.myninjaregister.ninjaregister1.Ninjas;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/ninjas/ui")
public class NinjaControllerUI {
    private final NinjaService ninjaService;

    public NinjaControllerUI(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }
    @GetMapping("/showall")
    public String showAll(Model model){
        List<NinjaDTO> ninjas = ninjaService.showAllNinjas();
        model.addAttribute("ninjas", ninjas);
        return "ninjalist";
    }
}
