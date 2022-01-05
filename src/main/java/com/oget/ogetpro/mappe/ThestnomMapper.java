package com.oget.ogetpro.mappe;

import com.oget.ogetpro.model.Thestnom;
import com.oget.ogetpro.dto.ThestnomDTO;

import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel="spring")
public interface ThestnomMapper {
    public ThestnomDTO thestnomToThestnomDTO(Thestnom thestnom);

    public Thestnom thestnomDTOToThestnom(ThestnomDTO thestnomDTO);

    public List<ThestnomDTO> listThestnomToListThestnomDTO(
        List<Thestnom> thestnoms);

    public List<Thestnom> listThestnomDTOToListThestnom(
        List<ThestnomDTO> thestnomDTOs);
}
