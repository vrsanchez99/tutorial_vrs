package com.example.tutorial.service.impl;

import com.example.tutorial.model.TutorialVO;
import com.example.tutorial.model.dto.TutorialDTO;

import java.util.List;
import java.util.Optional;

public interface TutorialService {

    List<TutorialDTO> getAll();

    Optional<TutorialVO> findById(String s);

    List<TutorialDTO> findByPublished();

    List<TutorialDTO> findByTitleContaining(final String title);

    TutorialVO create(final TutorialDTO tutorialDTO);

    TutorialDTO update(String id,final TutorialDTO tutorialDTO);

    boolean delete(final String id);

    boolean deleteAll();

}
