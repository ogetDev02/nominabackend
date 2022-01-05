package com.oget.ogetpro.mappe;

import com.oget.ogetpro.model.Thnome;
import com.oget.ogetpro.dto.ThnomeDTO;

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
public interface ThnomeMapper {
    @Mapping(source = "thdocide.thdocideId", target = "thdocideId_Thdocide")
    @Mapping(source = "thestnom.thestnomId", target = "thestnomId_Thestnom")
    public ThnomeDTO thnomeToThnomeDTO(Thnome thnome);

    @Mapping(source = "thdocideId_Thdocide", target = "thdocide.thdocideId")
    @Mapping(source = "thestnomId_Thestnom", target = "thestnom.thestnomId")
    public Thnome thnomeDTOToThnome(ThnomeDTO thnomeDTO);

    public List<ThnomeDTO> listThnomeToListThnomeDTO(List<Thnome> thnomes);

    public List<Thnome> listThnomeDTOToListThnome(List<ThnomeDTO> thnomeDTOs);
}
