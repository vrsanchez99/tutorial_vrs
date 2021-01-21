package com.example.tutorial.controller;

import com.example.tutorial.controller.constant.EndPointUris;
import com.example.tutorial.model.dto.TutorialDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping( EndPointUris.API + EndPointUris.V1 + EndPointUris.TUTORIAL )
public interface TutorialApi {


    @GetMapping
    ResponseEntity<List<TutorialDTO>> getAll();

    @GetMapping(EndPointUris.ID)
    ResponseEntity<Optional<TutorialDTO>> findById(@PathVariable("id") String id);

    @GetMapping(EndPointUris.TITULO)
    ResponseEntity<List<TutorialDTO>> findByTitleContaining(@PathVariable("titulo") String title);

    @GetMapping(EndPointUris.PUBLICADO)
    ResponseEntity<List<TutorialDTO>> findByPublished();

    @PostMapping
    ResponseEntity< TutorialDTO > create( @RequestBody final TutorialDTO TutorialDTO );

    @PutMapping(EndPointUris.UPDATE)
    ResponseEntity< TutorialDTO > update( @RequestBody final TutorialDTO TutorialDTO );

    @DeleteMapping( EndPointUris.DELETE+EndPointUris.ID )
    ResponseEntity< Boolean > delete( @PathVariable final String id );

    @DeleteMapping(EndPointUris.DELETE+EndPointUris.ALL)
    ResponseEntity<Boolean> deleteAll();
}
