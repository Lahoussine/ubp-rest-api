package com.example.ubprest.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Service
public class RandomNumbersGeneratorService {

    public List<Double> getRandomDouble(int size) throws Exception{

        if(size<=0){
            throw new Exception("Le nombre d'élément doit etre strictement supérieur à 0");
        }
        return  DoubleStream.generate(() -> new Random().nextDouble(100)).limit(size).boxed().collect(Collectors.toList());
    }
}
