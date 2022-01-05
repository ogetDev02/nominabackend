package com.oget.ogetpro.mapper.seg;

import com.oget.ogetpro.model.seg.Opcrole;
import com.oget.ogetpro.dto.OpcroleDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper
public interface OpcroleMapper {
    @Mapping(source = "opciones.opcidn", target = "opcidn_Opciones")
    @Mapping(source = "roles.roleid", target = "roleid_Roles")
    public OpcroleDTO opcroleToOpcroleDTO(Opcrole opcrole);

    @Mapping(source = "opcidn_Opciones", target = "opciones.opcidn")
    @Mapping(source = "roleid_Roles", target = "roles.roleid")
    public Opcrole opcroleDTOToOpcrole(OpcroleDTO opcroleDTO);

    public List<OpcroleDTO> listOpcroleToListOpcroleDTO(List<Opcrole> opcroles);

    public List<Opcrole> listOpcroleDTOToListOpcrole(
        List<OpcroleDTO> opcroleDTOs);
}
