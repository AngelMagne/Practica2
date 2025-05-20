package com.universidad.service.impl;

import com.universidad.dto.InscripcionDTO;
import com.universidad.model.Estudiante;
import com.universidad.model.Inscripcion;
import com.universidad.model.Materia;
import com.universidad.repository.EstudianteRepository;
import com.universidad.repository.InscripcionRepository;
import com.universidad.repository.MateriaRepository;
import com.universidad.service.IInscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InscripcionServiceImpl implements IInscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;
    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private MateriaRepository materiaRepository;

    private InscripcionDTO toDTO(Inscripcion inscripcion) {
        InscripcionDTO dto = new InscripcionDTO();
        dto.setId(inscripcion.getId());
        dto.setEstudianteId(inscripcion.getEstudiante().getId());
        dto.setMateriaId(inscripcion.getMateria().getId());
        dto.setFechaInscripcion(inscripcion.getFechaInscripcion());
        return dto;
    }

    @Override
    public List<InscripcionDTO> obtenerInscripcionesPorEstudiante(Long estudianteId) {
        return inscripcionRepository.findByEstudianteId(estudianteId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public InscripcionDTO obtenerInscripcionPorId(Long id) {
    return inscripcionRepository.findById(id)
        .map(this::toDTO)
        .orElse(null);
    }

    @Override
    public InscripcionDTO actualizarInscripcion(Long id, InscripcionDTO inscripcionDTO) {
    Inscripcion inscripcion = inscripcionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));
    // Actualiza solo los campos permitidos
    if (inscripcionDTO.getFechaInscripcion() != null) {
        inscripcion.setFechaInscripcion(inscripcionDTO.getFechaInscripcion());
    }
    // Si permites cambiar estudiante o materia, agrega aquí la lógica
    return toDTO(inscripcionRepository.save(inscripcion));
    }

    @Override
    public InscripcionDTO crearInscripcion(InscripcionDTO inscripcionDTO) {
        Estudiante estudiante = estudianteRepository.findById(inscripcionDTO.getEstudianteId())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        Materia materia = materiaRepository.findById(inscripcionDTO.getMateriaId())
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));
        Inscripcion inscripcion = Inscripcion.builder()
                .estudiante(estudiante)
                .materia(materia)
                .fechaInscripcion(inscripcionDTO.getFechaInscripcion() != null ? inscripcionDTO.getFechaInscripcion() : LocalDate.now())
                .build();
        return toDTO(inscripcionRepository.save(inscripcion));
    }

    @Override
    public void eliminarInscripcion(Long id) {
        inscripcionRepository.deleteById(id);
    }
}