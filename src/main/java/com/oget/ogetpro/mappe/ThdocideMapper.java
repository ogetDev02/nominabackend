package com.oget.ogetpro.mappe;



import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.oget.ogetpro.dto.ThdocideDTO;
import com.oget.ogetpro.model.Thdocide;

import java.util.List;


@Mapper(componentModel="spring")
public interface ThdocideMapper {
    public ThdocideDTO thdocideToThdocideDTO(Thdocide thdocide);

    public Thdocide thdocideDTOToThdocide(ThdocideDTO thdocideDTO);

    public List<ThdocideDTO> listThdocideToListThdocideDTO(
        List<Thdocide> thdocides);

    public List<Thdocide> listThdocideDTOToListThdocide(
        List<ThdocideDTO> thdocideDTOs);
}
