package com.oget.ogetpro.mappe;

import com.oget.ogetpro.model.Thvida1;
import com.oget.ogetpro.dto.Thvida1DTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel="spring")
public interface Thvida1Mapper {
    public Thvida1DTO thvida1ToThvida1DTO(Thvida1 thvida1);

    public Thvida1 thvida1DTOToThvida1(Thvida1DTO thvida1DTO);

    public List<Thvida1DTO> listThvida1ToListThvida1DTO(List<Thvida1> thvida1s);

    public List<Thvida1> listThvida1DTOToListThvida1(
        List<Thvida1DTO> thvida1DTOs);
}
