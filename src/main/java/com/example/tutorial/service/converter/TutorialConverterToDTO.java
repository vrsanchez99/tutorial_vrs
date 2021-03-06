package com.example.tutorial.service.converter;


import com.example.tutorial.model.TutorialVO;
import com.example.tutorial.model.dto.TutorialDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TutorialConverterToDTO implements Converter<TutorialVO, TutorialDTO> {

    @Override
    public TutorialDTO convert(TutorialVO tutorialVO) {
        return TutorialDTO.builder()
                .id(tutorialVO.getId())
                .titulo(tutorialVO.getTitulo())
                .descripcion(tutorialVO.getDescripcion())
                .publicado(tutorialVO.isPublicado())
                .build();

    }
}
