package com.oget.ogetpro.mappe;


import com.oget.ogetpro.dto.ThcptosdianDTO;
import com.oget.ogetpro.model.Thcptosdian;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel="spring")
public interface ThcptosdianMapper {
    @Mapping(source = "thcptos.thcptosId", target = "thcptosId_Thcptos")
    public ThcptosdianDTO thcptosdianToThcptosdianDTO(Thcptosdian thcptosdian);

    @Mapping(source = "thcptosId_Thcptos", target = "thcptos.thcptosId")
    public Thcptosdian thcptosdianDTOToThcptosdian(
        ThcptosdianDTO thcptosdianDTO);

    public List<ThcptosdianDTO> listThcptosdianToListThcptosdianDTO(
        List<Thcptosdian> thcptosdians);

    public List<Thcptosdian> listThcptosdianDTOToListThcptosdian(
        List<ThcptosdianDTO> thcptosdianDTOs);
}
