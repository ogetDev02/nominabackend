package com.oget.ogetpro.mapper.seg;

import com.oget.ogetpro.model.seg.Departamento;
import com.oget.ogetpro.dto.DepartamentoDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper
public interface DepartamentoMapper {
    public DepartamentoDTO departamentoToDepartamentoDTO(
        Departamento departamento);

    public Departamento departamentoDTOToDepartamento(
        DepartamentoDTO departamentoDTO);

    public List<DepartamentoDTO> listDepartamentoToListDepartamentoDTO(
        List<Departamento> departamentos);

    public List<Departamento> listDepartamentoDTOToListDepartamento(
        List<DepartamentoDTO> departamentoDTOs);
}
