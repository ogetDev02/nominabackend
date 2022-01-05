package com.oget.ogetpro.mappe;

import com.oget.ogetpro.model.Thpertli;
import com.oget.ogetpro.dto.ThpertliDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel="spring")
public interface ThpertliMapper {
    public ThpertliDTO thpertliToThpertliDTO(Thpertli thpertli);

    public Thpertli thpertliDTOToThpertli(ThpertliDTO thpertliDTO);

    public List<ThpertliDTO> listThpertliToListThpertliDTO(
        List<Thpertli> thpertlis);

    public List<Thpertli> listThpertliDTOToListThpertli(
        List<ThpertliDTO> thpertliDTOs);
}
