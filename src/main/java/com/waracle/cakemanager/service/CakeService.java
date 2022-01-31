package com.waracle.cakemanager.service;

import com.waracle.cakemanager.entity.Cake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.waracle.cakemanager.repository.CakeRepository;

import java.util.List;

@Service
public class CakeService {

    @Autowired
    private CakeRepository cakeRepository;

    public List<Cake> getCakes(){
       return ( List<Cake> )cakeRepository.findAll();
    }

    public Cake saveCake(Cake cake) {
        return  cakeRepository.save(cake);
    }

    public List<Cake> saveCakes(List<Cake> cakes) {
        return  (List<Cake>) cakeRepository.saveAll(cakes);
    }
}
