package com.oget.ogetpro.mapper.seg;

import com.oget.ogetpro.model.seg.Thgrupos;
import com.oget.ogetpro.dto.ThgruposegDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper
public interface ThgruposMapper {
    public ThgruposegDTO thgruposToThgruposDTO(Thgrupos thgrupos);

    public Thgrupos thgruposDTOToThgrupos(ThgruposegDTO thgruposDTO);

    public List<ThgruposegDTO> listThgruposToListThgruposDTO(
        List<Thgrupos> thgruposs);

    public List<Thgrupos> listThgruposDTOToListThgrupos(
        List<ThgruposegDTO> thgruposDTOs);
}
