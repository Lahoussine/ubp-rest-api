package com.example.ubprest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class RandomNumbersGeneratorServiceTest {

    private RandomNumbersGeneratorService randomNumbersGeneratorService;



    @BeforeEach
    void setup(){
        randomNumbersGeneratorService = new RandomNumbersGeneratorService();
    }

    @Test
    void testOk(){
        //Given
        int nombreRandomVoulus= 5;
        //When
        AtomicReference<List<Double>> resultat = new AtomicReference<>();
        Assertions.assertDoesNotThrow(()->{
            resultat.set(randomNumbersGeneratorService.getRandomDouble(nombreRandomVoulus));
        });
        //Then
        //On check que le nombre de random est celui demandé
        Assertions.assertEquals(nombreRandomVoulus, resultat.get().size());
    }

    @Test
    void testKoExceptionThrown(){
        //Given
        int nombreRandomVoulus= 0;
        //When Then
        Assertions.assertThrows(Exception.class,()->{
                    randomNumbersGeneratorService.getRandomDouble(nombreRandomVoulus);
                }
                );

    }

}
