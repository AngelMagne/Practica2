package com.universidad.service.impl;

import com.universidad.dto.DocenteDTO;
import com.universidad.model.Docente;
import com.universidad.repository.DocenteRepository;
import com.universidad.service.IDocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocenteServiceImpl implements IDocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    // Si tienes un mapper, úsalo aquí. Si no, puedes mapear manualmente.
    private DocenteDTO toDTO(Docente docente) {
        if (docente == null) return null;
        return DocenteDTO.builder()
            .id(docente.getId())
            .nombre(docente.getNombre())
            .apellido(docente.getApellido())
            .email(docente.getEmail())
            .fechaNacimiento(docente.getFechaNacimiento())
            .nroEmpleado(docente.getNroEmpleado())
            .departamento(docente.getDepartamento())
            .build();
    }

    private Docente toEntity(DocenteDTO dto) {
        if (dto == null) return null;
        return Docente.builder()
            .id(dto.getId())
            .nombre(dto.getNombre())
            .apellido(dto.getApellido())
            .email(dto.getEmail())
            .fechaNacimiento(dto.getFechaNacimiento())
            .nroEmpleado(dto.getNroEmpleado())
            .departamento(dto.getDepartamento())
            .build();
    }


    @Override
    public List<DocenteDTO> obtenerTodosLosDocentes() {
        return docenteRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Docente obtenerEntidadDocentePorId(Long id) {
        return docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado con id: " + id));
    }

    @Override
    public DocenteDTO obtenerDocentePorId(Long id) {
        Docente docente = obtenerEntidadDocentePorId(id);
        return toDTO(docente);
    }

    @Override
    public DocenteDTO crearDocente(DocenteDTO docenteDTO) {
        Docente docente = toEntity(docenteDTO);
        docente.setId(null); // Para evitar sobrescribir
        Docente guardado = docenteRepository.save(docente);
        return toDTO(guardado);
    }

    @Override
    public DocenteDTO actualizarDocente(Long id, DocenteDTO docenteDTO) {
        Docente existente = obtenerEntidadDocentePorId(id);
        existente.setNombre(docenteDTO.getNombre());
        existente.setApellido(docenteDTO.getApellido());
        existente.setEmail(docenteDTO.getEmail());
        existente.setFechaNacimiento(docenteDTO.getFechaNacimiento());
        existente.setNroEmpleado(docenteDTO.getNroEmpleado());
        existente.setDepartamento(docenteDTO.getDepartamento());
        Docente actualizado = docenteRepository.save(existente);
        return toDTO(actualizado);
    }

    @Override
    public void eliminarDocente(Long id) {
        docenteRepository.deleteById(id);
    }
}