package com.oget.ogetpro.mapper.seg;

import com.oget.ogetpro.model.seg.Diasfestivos;
import com.oget.ogetpro.dto.DiasfestivosDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper
public interface DiasfestivosMapper {
    public DiasfestivosDTO diasfestivosToDiasfestivosDTO(
        Diasfestivos diasfestivos);

    public Diasfestivos diasfestivosDTOToDiasfestivos(
        DiasfestivosDTO diasfestivosDTO);

    public List<DiasfestivosDTO> listDiasfestivosToListDiasfestivosDTO(
        List<Diasfestivos> diasfestivoss);

    public List<Diasfestivos> listDiasfestivosDTOToListDiasfestivos(
        List<DiasfestivosDTO> diasfestivosDTOs);
}
