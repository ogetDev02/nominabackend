package com.oget.ogetpro.mappe;

import java.util.List;

import org.mapstruct.Mapper;

import com.oget.ogetpro.dto.ThcptosDTO;
import com.oget.ogetpro.model.Thcptos;

@Mapper(componentModel="spring")
public interface ThcptosMapper {
    public ThcptosDTO thcptosToThcptosDTO(Thcptos thcptos);

    public Thcptos thcptosDTOToThcptos(ThcptosDTO thcptosDTO);

    public List<ThcptosDTO> listThcptosToListThcptosDTO(List<Thcptos> thcptoss);

    public List<Thcptos> listThcptosDTOToListThcptos(
        List<ThcptosDTO> thcptosDTOs);
}
