package com.dev.myninjaregister.ninjaregister1.Ninjas;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {
    private NinjaRepository ninjaRepository;
    private NinjaMapper ninjaMapper;




    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    public List<NinjaDTO> showAllNinjas(){
        List<NinjaModel> allninjas = ninjaRepository.findAll();
        return allninjas.stream()
                .map(ninjaMapper::map)
                .collect(Collectors.toList());
    }
    public NinjaDTO showNinjaById(Long id){
        Optional<NinjaModel> ninjaid = ninjaRepository.findById(id);
        return ninjaid.map(ninjaMapper::map).orElse(null);
    }
    public NinjaDTO createNewNinja(NinjaDTO ninjaDTO){
        NinjaModel ninja = ninjaMapper.map(ninjaDTO);
        ninja=ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja);
    }
    public void deleteNinjaId(Long id){
        ninjaRepository.deleteById(id);
    }
    public NinjaDTO alterNinja(@PathVariable Long id, @RequestBody NinjaDTO ninjaDTO){
        Optional<NinjaModel> existentNinja = ninjaRepository.findById(id);
        if(existentNinja.isPresent()){
            NinjaModel ninjaupdated = ninjaMapper.map(ninjaDTO);
            ninjaupdated.setId(id);
            NinjaModel savedNinja = ninjaRepository.save(ninjaupdated);
            return ninjaMapper.map(ninjaupdated);
        }
        return null;
    }
}
