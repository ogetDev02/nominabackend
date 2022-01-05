package com.oget.ogetpro.mapper.seg;

import com.oget.ogetpro.model.seg.Rolxusuario;
import com.oget.ogetpro.dto.RolxusuarioDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper
public interface RolxusuarioMapper {
    @Mapping(source = "roles.roleid", target = "roleid_Roles")
    @Mapping(source = "usuario.usuarioid", target = "usuarioid_Usuario")
    public RolxusuarioDTO rolxusuarioToRolxusuarioDTO(Rolxusuario rolxusuario);

    @Mapping(source = "roleid_Roles", target = "roles.roleid")
    @Mapping(source = "usuarioid_Usuario", target = "usuario.usuarioid")
    public Rolxusuario rolxusuarioDTOToRolxusuario(
        RolxusuarioDTO rolxusuarioDTO);

    public List<RolxusuarioDTO> listRolxusuarioToListRolxusuarioDTO(
        List<Rolxusuario> rolxusuarios);

    public List<Rolxusuario> listRolxusuarioDTOToListRolxusuario(
        List<RolxusuarioDTO> rolxusuarioDTOs);
}
