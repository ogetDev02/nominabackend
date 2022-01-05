package com.oget.ogetpro.mapper.seg;

import com.oget.ogetpro.model.seg.Opcuser;
import com.oget.ogetpro.dto.OpcuserDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper
public interface OpcuserMapper {
    @Mapping(source = "opciones.opcidn", target = "opcidn_Opciones")
    @Mapping(source = "usuario.usuarioid", target = "usuarioid_Usuario")
    public OpcuserDTO opcuserToOpcuserDTO(Opcuser opcuser);

    @Mapping(source = "opcidn_Opciones", target = "opciones.opcidn")
    @Mapping(source = "usuarioid_Usuario", target = "usuario.usuarioid")
    public Opcuser opcuserDTOToOpcuser(OpcuserDTO opcuserDTO);

    public List<OpcuserDTO> listOpcuserToListOpcuserDTO(List<Opcuser> opcusers);

    public List<Opcuser> listOpcuserDTOToListOpcuser(
        List<OpcuserDTO> opcuserDTOs);
}
