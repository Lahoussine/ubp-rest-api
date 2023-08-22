package com.example.ubprest.controller;

import com.example.ubprest.service.RandomNumbersGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/random")
public class RandomDoublesController {

    private RandomNumbersGeneratorService randomNumbersGeneratorService;
    @Autowired
    RandomDoublesController(RandomNumbersGeneratorService randomNumbersGeneratorService){
        this.randomNumbersGeneratorService=randomNumbersGeneratorService;
    }
    @GetMapping("/numbers/{size}")
    public ResponseEntity<List<Double>>getNumbers(@PathVariable int size) throws Exception{
        //J'ai volontairement créé un service qui peut retourner une exception si la taille n est pas strictement supérieur à 0 pour mettre en oeuvre l 'exception handling de l'api REST dans springboot

        List<Double> listRandoms = randomNumbersGeneratorService.getRandomDouble(size);
        //si pas d'erreur on retourne la réponse avec le http code OK 200
        return ResponseEntity.ok(listRandoms);

    }

    // ici j'ai mis le handling d'une exception generique sur un endpoint on retourne par défaut BAD REQUEST + message exception
    // Il est plus propre de le mettre dans une classe dédiée comme RestResponseEntityExceptionHandler mais je ne sais pas pourquoi elle semble ne pas etre prise en compte lorsque l'erreur arrive
    // Pour l'exercice je l'ai mis dans le controller meme si ce n'est pas ce que j'aurais fait dans une situation réelle
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleNoSuchElementFoundException(
            Exception exception
    ) {
        return ResponseEntity.badRequest()              //.status(HttpStatus.I_AM_A_TEAPOT)
                .body(exception.getMessage());
    }
}
