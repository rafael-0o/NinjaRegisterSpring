package com.dev.myninjaregister.ninjaregister1.Ninjas;


import com.dev.myninjaregister.ninjaregister1.Common.Response.GenericAnswer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/deletebyid/{id}")
    public String deleteNinjaById(@PathVariable Long id){
        ninjaService.deleteNinjaId(id);
        return "redirect:/api/ninjas/ui/showall";
    }

    @GetMapping("/showbyid/{id}")
    public String showNinjaById(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.showNinjaById(id);
        if(ninja!=null){
            model.addAttribute("ninjas",ninja);
            return "details";
        } else{
            model.addAttribute("Mensage","Ninja not found");
            return "ninjalist";
        }
    }
    @GetMapping("/create")
    public String ninjaForms(Model model){
        model.addAttribute("ninja",new NinjaDTO());
        return "createninja";
    }
    @PostMapping("/save")
    public String saveNinja(@ModelAttribute NinjaDTO ninja, RedirectAttributes redirectAttributes){
        ninjaService.createNewNinja(ninja);
        redirectAttributes.addFlashAttribute("Mensage","Ninja Created");
        return "redirect:/api/ninjas/ui/showall";
    }

}
