package com.dev.myninjaregister.ninjaregister1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class NinjaController {
    @GetMapping("/wellcome")
    public String wellcome(){
        return "Wellcome in my first spring page!";
    }
}
