package com.oget.ogetpro.mappe;

import com.oget.ogetpro.model.Thvarnom;
import com.oget.ogetpro.dto.ThvarnomDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel="spring")
public interface ThvarnomMapper {
    public ThvarnomDTO thvarnomToThvarnomDTO(Thvarnom thvarnom);

    public Thvarnom thvarnomDTOToThvarnom(ThvarnomDTO thvarnomDTO);

    public List<ThvarnomDTO> listThvarnomToListThvarnomDTO(
        List<Thvarnom> thvarnoms);

    public List<Thvarnom> listThvarnomDTOToListThvarnom(
        List<ThvarnomDTO> thvarnomDTOs);
}
