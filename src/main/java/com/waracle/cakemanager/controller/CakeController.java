package com.waracle.cakemanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemanager.entity.Cake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.waracle.cakemanager.service.CakeService;

import java.util.List;

import static com.waracle.cakemanager.util.Constants.CONTENT_DISPOSITION;
import static com.waracle.cakemanager.util.Constants.JSON_FILE;

@RestController
public class CakeController {

    @Autowired
    private CakeService cakeService;

    @GetMapping("/")
    public List<Cake> showCakes(){
        return cakeService.getCakes();
    }

    @GetMapping("/cakes")
    public ResponseEntity<byte[]> downloadCakesResponse(@RequestParam(required = false) String fileName) throws JsonProcessingException {
        List<Cake> cakes = cakeService.getCakes();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cakes);
        byte[] isr = json.getBytes();

        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentLength(isr.length);
        respHeaders.setContentType(new MediaType("text", "json"));
        respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        if(fileName == null || fileName.isEmpty()){
            fileName = JSON_FILE;
        }
        respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, CONTENT_DISPOSITION+ fileName);
        return new ResponseEntity<>(isr, respHeaders, HttpStatus.OK);
    }

    @PostMapping("/cakes")
    public Cake saveCakes(@RequestBody Cake cake){
        return cakeService.saveCakes(cake);
    }

    @GetMapping("/oauth")
    public String oAuthRequest(){
        return "welcome to oauth request!";
    }
}
