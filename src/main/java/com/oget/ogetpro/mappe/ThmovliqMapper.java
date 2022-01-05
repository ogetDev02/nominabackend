package com.oget.ogetpro.mappe;

import com.oget.ogetpro.model.Thmovliq;
import com.oget.ogetpro.dto.ThmovliqDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel="spring")
public interface ThmovliqMapper {
    @Mapping(source = "thctrliq.thctrliqId", target = "thctrliqId_Thctrliq")
    @Mapping(source = "thvida2.thvida2Id", target = "thvida2Id_Thvida2")
    public ThmovliqDTO thmovliqToThmovliqDTO(Thmovliq thmovliq);

    @Mapping(source = "thctrliqId_Thctrliq", target = "thctrliq.thctrliqId")
    @Mapping(source = "thvida2Id_Thvida2", target = "thvida2.thvida2Id")
    public Thmovliq thmovliqDTOToThmovliq(ThmovliqDTO thmovliqDTO);

    public List<ThmovliqDTO> listThmovliqToListThmovliqDTO(
        List<Thmovliq> thmovliqs);

    public List<Thmovliq> listThmovliqDTOToListThmovliq(
        List<ThmovliqDTO> thmovliqDTOs);
}
