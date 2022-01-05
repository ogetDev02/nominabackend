package com.oget.ogetpro.mapper.seg;

import com.oget.ogetpro.model.seg.Parametros;
import com.oget.ogetpro.dto.ParametrosDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper
public interface ParametrosMapper {
    public ParametrosDTO parametrosToParametrosDTO(Parametros parametros);

    public Parametros parametrosDTOToParametros(ParametrosDTO parametrosDTO);

    public List<ParametrosDTO> listParametrosToListParametrosDTO(
        List<Parametros> parametross);

    public List<Parametros> listParametrosDTOToListParametros(
        List<ParametrosDTO> parametrosDTOs);
}
