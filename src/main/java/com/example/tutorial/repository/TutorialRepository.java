package com.example.tutorial.repository;

import com.example.tutorial.model.TutorialVO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TutorialRepository extends MongoRepository<TutorialVO, String> {


}
