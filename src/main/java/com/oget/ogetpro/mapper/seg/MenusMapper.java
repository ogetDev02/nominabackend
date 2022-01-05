package com.oget.ogetpro.mapper.seg;

import com.oget.ogetpro.model.seg.Menus;
import com.oget.ogetpro.dto.MenusDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper
public interface MenusMapper {
    @Mapping(source = "modulo.moduloid", target = "moduloid_Modulo")
    public MenusDTO menusToMenusDTO(Menus menus);

    @Mapping(source = "moduloid_Modulo", target = "modulo.moduloid")
    public Menus menusDTOToMenus(MenusDTO menusDTO);

    public List<MenusDTO> listMenusToListMenusDTO(List<Menus> menuss);

    public List<Menus> listMenusDTOToListMenus(List<MenusDTO> menusDTOs);
}
