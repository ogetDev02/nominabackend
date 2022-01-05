package com.oget.ogetpro.mappe;

import com.oget.ogetpro.model.Thtracking;
import com.oget.ogetpro.dto.ThtrackingDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
* Mapper Build with MapStruct https://mapstruct.org
* MapStruct is a code generator that greatly simplifies the
* implementation of mappings between Java bean type
* based on a convention over configuration approach.
*/
@Mapper(componentModel="spring")
public interface ThtrackingMapper {
    @Mapping(source = "thestnom.thestnomId", target = "thestnomId_Thestnom")
    @Mapping(source = "thnome.thnomeId", target = "thnomeId_Thnome")
    public ThtrackingDTO thtrackingToThtrackingDTO(Thtracking thtracking);

    @Mapping(source = "thestnomId_Thestnom", target = "thestnom.thestnomId")
    @Mapping(source = "thnomeId_Thnome", target = "thnome.thnomeId")
    public Thtracking thtrackingDTOToThtracking(ThtrackingDTO thtrackingDTO);

    public List<ThtrackingDTO> listThtrackingToListThtrackingDTO(
        List<Thtracking> thtrackings);

    public List<Thtracking> listThtrackingDTOToListThtracking(
        List<ThtrackingDTO> thtrackingDTOs);
}
