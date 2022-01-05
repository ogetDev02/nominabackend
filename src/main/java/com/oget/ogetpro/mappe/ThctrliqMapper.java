package com.oget.ogetpro.mappe;

import com.oget.ogetpro.model.Thctrliq;
import com.oget.ogetpro.dto.ThctrliqDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel="spring")
public interface ThctrliqMapper {
    @Mapping(source = "thpertli.thpertliId", target = "thpertliId_Thpertli")
   // @Mapping(source = "thtipproByThtipproId.thtipproId", target = "thtipproId_Thtippro")
    @Mapping(source = "thtipproByThtipproSecId.thtipproId", target = "thtipproId_Thtippro")
    public ThctrliqDTO thctrliqToThctrliqDTO(Thctrliq thctrliq);

    @Mapping(source = "thpertliId_Thpertli", target = "thpertli.thpertliId")
    @Mapping(source = "thtipproId_Thtippro", target = "thtipproByThtipproId.thtipproId")
    @Mapping(source = "thtipproId_Thtippro", target = "thtipproByThtipproSecId.thtipproId")
    public Thctrliq thctrliqDTOToThctrliq(ThctrliqDTO thctrliqDTO);

    public List<ThctrliqDTO> listThctrliqToListThctrliqDTO(
        List<Thctrliq> thctrliqs);

    public List<Thctrliq> listThctrliqDTOToListThctrliq(
        List<ThctrliqDTO> thctrliqDTOs);
}
