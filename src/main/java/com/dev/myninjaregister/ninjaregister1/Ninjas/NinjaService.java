package com.dev.myninjaregister.ninjaregister1.Ninjas;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {
    public NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }
    public List<NinjaModel> showAllNinjas(){
        return ninjaRepository.findAll();
    }
    public NinjaModel showNinjaById(Long id){
        Optional<NinjaModel> ninjaid = ninjaRepository.findById(id);
        return ninjaid.orElse(null);

    }
    public NinjaModel createNewNinja(NinjaModel ninja){
        return ninjaRepository.save(ninja);
    }
    public void deleteNinjaId(Long id){
        ninjaRepository.deleteById(id);
    }
    public NinjaModel alterNinja(@PathVariable Long id, @RequestBody NinjaModel attninja){
        if(ninjaRepository.existsById(id)){
            attninja.setId(id);
            return ninjaRepository.save(attninja);
        }
        return null;
    }

}
