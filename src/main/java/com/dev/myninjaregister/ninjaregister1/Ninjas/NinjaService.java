package com.dev.myninjaregister.ninjaregister1.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NinjaService {
    public NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }
    public List<NinjaModel> showAllNinjas(){
        return ninjaRepository.findAll();
    }

}
