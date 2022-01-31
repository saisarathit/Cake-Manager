package com.waracle.cakemanager.service;

import com.waracle.cakemanager.entity.Cake;
import com.waracle.cakemanager.repository.CakeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CakeServiceTest {

    @Autowired
    private CakeService cakeService;

    @Autowired
    private CakeRepository cakeRepository;

    @Test
    public void testGetCakes_whenCallCakeService_shouldReturnAvailableCakes(){
        List<Cake> cakes = (List<Cake>) cakeRepository.findAll();
        Assert.assertTrue(cakeService.getCakes().containsAll(cakes));
    }

    @Test
    public void testSaveCakes_whenInsertNewCake_shouldSaveIntoDb(){
        Cake newCake = new Cake("Test Cake", "This is junit cake", "url");
        Assert.assertEquals(newCake, cakeService.saveCakes(newCake));
        Assert.assertNotNull(cakeRepository.findById(newCake.getTitle()));
    }
}
