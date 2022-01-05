package com.oget.ogetpro.mapper.seg;

import com.oget.ogetpro.model.seg.Modulo;
import com.oget.ogetpro.dto.ModuloDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper
public interface ModuloMapper {
    public ModuloDTO moduloToModuloDTO(Modulo modulo);

    public Modulo moduloDTOToModulo(ModuloDTO moduloDTO);

    public List<ModuloDTO> listModuloToListModuloDTO(List<Modulo> modulos);

    public List<Modulo> listModuloDTOToListModulo(List<ModuloDTO> moduloDTOs);
}
