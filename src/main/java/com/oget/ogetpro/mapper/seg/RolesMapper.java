package com.oget.ogetpro.mapper.seg;

import com.oget.ogetpro.model.seg.Roles;
import com.oget.ogetpro.dto.RolesDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper
public interface RolesMapper {
    public RolesDTO rolesToRolesDTO(Roles roles);

    public Roles rolesDTOToRoles(RolesDTO rolesDTO);

    public List<RolesDTO> listRolesToListRolesDTO(List<Roles> roless);

    public List<Roles> listRolesDTOToListRoles(List<RolesDTO> rolesDTOs);
}
