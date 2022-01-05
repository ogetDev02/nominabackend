package com.oget.ogetpro.mapper.seg;

import com.oget.ogetpro.model.seg.Ciudad;
import com.oget.ogetpro.dto.CiudadDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper
public interface CiudadMapper {
    @Mapping(source = "departamento.departamentoid", target = "departamentoid_Departamento")
    public CiudadDTO ciudadToCiudadDTO(Ciudad ciudad);

    @Mapping(source = "departamentoid_Departamento", target = "departamento.departamentoid")
    public Ciudad ciudadDTOToCiudad(CiudadDTO ciudadDTO);

    public List<CiudadDTO> listCiudadToListCiudadDTO(List<Ciudad> ciudads);

    public List<Ciudad> listCiudadDTOToListCiudad(List<CiudadDTO> ciudadDTOs);
}
