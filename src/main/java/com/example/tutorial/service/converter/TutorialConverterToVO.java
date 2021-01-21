package com.example.tutorial.service.converter;

import com.example.tutorial.model.TutorialVO;
import com.example.tutorial.model.dto.TutorialDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TutorialConverterToVO implements Converter<TutorialDTO, TutorialVO> {

    @Override
    public TutorialVO convert(TutorialDTO tutorialDTO) {
        return TutorialVO.builder()
                .id(tutorialDTO.getId())
                .titulo(tutorialDTO.getTitulo())
                .descripcion(tutorialDTO.getDescripcion())
                .publicado(tutorialDTO.isPublicado())
                .build();
    }

}
