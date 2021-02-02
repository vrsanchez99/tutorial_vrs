package com.example.tutorial.service;

import com.example.tutorial.model.TutorialVO;
import com.example.tutorial.model.dto.TutorialDTO;
import com.example.tutorial.repository.TutorialRepository;
import com.example.tutorial.service.converter.TutorialConverterToDTO;
import com.example.tutorial.service.converter.TutorialConverterToVO;
import com.example.tutorial.service.impl.Notification;
import com.example.tutorial.service.impl.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TutorialServiceImpl implements TutorialService {

    @Autowired
    private TutorialRepository tutorialRepository;
    @Autowired
    private TutorialConverterToDTO toDTO;
    @Autowired
    private TutorialConverterToVO toVO;

    public final ApplicationEventPublisher eventPublisher;

    //************************* NUEVO CODIGO PARA SSE***************************
    public TutorialServiceImpl(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public List<TutorialDTO> getAll() {
        return tutorialRepository.findAll()
                .stream()
                .map(toDTO::convert).collect(Collectors.toList());
    }

    @Override
    public Optional<TutorialVO> findById(String id) {
        return tutorialRepository.findById(id);
    }

    @Override
    public List<TutorialDTO> findByPublished() {
        return getAll().stream().filter(TutorialDTO::isPublicado).collect(Collectors.toList());
    }

    /**
     * @param title
     * @return
     */
    @Override
    public List<TutorialDTO> findByTitleContaining(String title) {
        return getAll().stream().filter(l -> l.getTitulo().contains(title)).collect(Collectors.toList());
    }

    /**
     * @param tutorialDTO
     * @return
     */
    @Override
    public TutorialVO create(TutorialDTO tutorialDTO) {
        TutorialVO tutorialVO = tutorialRepository.insert(toVO.convert(tutorialDTO));

        try {
            publishJobNotifications();
            return tutorialVO;
        } catch (Exception e) {
            return tutorialVO;
        }


    }

    /**
     * @param tutorialDTO
     * @return
     */
    @Override
    public TutorialDTO update(String id, TutorialDTO tutorialDTO) {

        Optional<TutorialVO> actual = findById(id);
        TutorialVO tutorialVO = actual.get();
        tutorialVO = toVO.convert(tutorialDTO);

        return toDTO.convert(tutorialRepository.save(tutorialVO));
    }

    @Override
    public boolean delete(String id) {

        try {
            tutorialRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }

    }

    @Override
    public boolean deleteAll() {
        try {
            tutorialRepository.deleteAll();
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    //************************* NUEVO CODIGO PARA SSE***************************
    public void publishJobNotifications() throws InterruptedException {
        Integer jobId = Notification.getNextJobId();
        Notification nStarted = new Notification("Job No. " + jobId + " started.", new Date());

        this.eventPublisher.publishEvent(nStarted);

        //Thread.sleep(2000);
        //Notification nFinished = new Notification("Job No. " + jobId + " finished.", new Date());
        //this.eventPublisher.publishEvent(nFinished);
    }

}
