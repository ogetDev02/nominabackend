package com.oget.ogetpro.mapper.seg;

import com.oget.ogetpro.model.seg.Usuario;
import com.oget.ogetpro.dto.UsuarioDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel="spring")
public interface UsuarioMapper {
    @Mapping(source = "roles.roleid", target = "roleid_Roles")
    public UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);

    @Mapping(source = "roleid_Roles", target = "roles.roleid")
    public Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO);

    public List<UsuarioDTO> listUsuarioToListUsuarioDTO(List<Usuario> usuarios);

    public List<Usuario> listUsuarioDTOToListUsuario(
        List<UsuarioDTO> usuarioDTOs);
}
