package com.oget.ogetpro.mappe;

import com.oget.ogetpro.model.Thtippro;
import com.oget.ogetpro.dto.ThtipproDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel="spring")
public interface ThtipproMapper {
    public ThtipproDTO thtipproToThtipproDTO(Thtippro thtippro);

    public Thtippro thtipproDTOToThtippro(ThtipproDTO thtipproDTO);

    public List<ThtipproDTO> listThtipproToListThtipproDTO(
        List<Thtippro> thtippros);

    public List<Thtippro> listThtipproDTOToListThtippro(
        List<ThtipproDTO> thtipproDTOs);
}
