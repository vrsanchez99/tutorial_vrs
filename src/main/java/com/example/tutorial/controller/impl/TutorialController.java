package com.example.tutorial.controller.impl;

import com.example.tutorial.controller.TutorialApi;
import com.example.tutorial.model.TutorialVO;
import com.example.tutorial.model.dto.TutorialDTO;
import com.example.tutorial.repository.TutorialRepository;
import com.example.tutorial.service.impl.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TutorialController implements TutorialApi {

    @Autowired
    private TutorialService tutorialService;

    @Autowired
    private TutorialRepository tutorialRepository;

    @Override
    public ResponseEntity<List<TutorialDTO>> getAll() {
        return ResponseEntity.ok(tutorialService.getAll());
    }

    @Override
    public ResponseEntity<Optional<TutorialDTO>> findById(String id) {
        Optional<TutorialVO> datos = tutorialRepository.findById(id);
        return new ResponseEntity(datos.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TutorialDTO>> findByTitleContaining(String title) {
        return ResponseEntity.ok(tutorialService.findByTitleContaining(title));
    }

    @Override
    public ResponseEntity<List<TutorialDTO>> findByPublished() {
        return ResponseEntity.ok(tutorialService.findByPublished());
    }

    @Override
    public ResponseEntity<TutorialDTO> create(TutorialDTO tutorialDTO) {
        return new ResponseEntity(tutorialService.create(tutorialDTO), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TutorialDTO> update(TutorialDTO tutorialDTO) {
        return new ResponseEntity<>(tutorialService.update(tutorialDTO), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Boolean> delete(String id) {
        return tutorialService.delete(id)
                ? ResponseEntity.ok(true)
                : new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Boolean> deleteAll() {
        return tutorialService.deleteAll()
                ? ResponseEntity.ok(true)
                : new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
