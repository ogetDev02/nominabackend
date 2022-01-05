package com.oget.ogetpro.mappe;

import com.oget.ogetpro.model.Thvida2;
import com.oget.ogetpro.dto.Thvida2DTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel="spring")
public interface Thvida2Mapper {
    @Mapping(source = "thvida1.thvida1Id", target = "thvida1Id_Thvida1")
    public Thvida2DTO thvida2ToThvida2DTO(Thvida2 thvida2);

    @Mapping(source = "thvida1Id_Thvida1", target = "thvida1.thvida1Id")
    public Thvida2 thvida2DTOToThvida2(Thvida2DTO thvida2DTO);

    public List<Thvida2DTO> listThvida2ToListThvida2DTO(List<Thvida2> thvida2s);

    public List<Thvida2> listThvida2DTOToListThvida2(
        List<Thvida2DTO> thvida2DTOs);
}
