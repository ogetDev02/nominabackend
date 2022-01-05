package com.oget.ogetpro.mapper.seg;

import com.oget.ogetpro.model.seg.Opciones;
import com.oget.ogetpro.dto.OpcionesDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface OpcionesMapper {
    @Mapping(source = "menus.menuidn", target = "menuidn_Menus")
    public OpcionesDTO opcionesToOpcionesDTO(Opciones opciones);

    @Mapping(source = "menuidn_Menus", target = "menus.menuidn")
    public Opciones opcionesDTOToOpciones(OpcionesDTO opcionesDTO);

    public List<OpcionesDTO> listOpcionesToListOpcionesDTO(
        List<Opciones> opcioness);

    public List<Opciones> listOpcionesDTOToListOpciones(
        List<OpcionesDTO> opcionesDTOs);
}
