package com.waracle.cakemanager.service;

import com.waracle.cakemanager.entity.Cake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public Cake saveCakes(Cake cake) {
        return  cakeRepository.save(cake);
    }
}
