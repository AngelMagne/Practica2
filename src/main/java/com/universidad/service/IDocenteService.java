package com.universidad.service;

import com.universidad.dto.DocenteDTO;
import com.universidad.model.Docente;
import java.util.List;

public interface IDocenteService {
    /**
     * Obtiene todos los docentes.
     * @return Lista de DocenteDTO.
     */
    List<DocenteDTO> obtenerTodosLosDocentes();

    /**
     * Obtiene la entidad Docente por su ID (para uso interno).
     * @param id ID del docente.
     * @return Docente (entidad JPA).
     */
    Docente obtenerEntidadDocentePorId(Long id);
    
    /**
     * Obtiene un docente por su ID.
     * @param id ID del docente.
     * @return DocenteDTO con el ID especificado.
     */
    DocenteDTO obtenerDocentePorId(Long id);

    /**
     * Crea un nuevo docente.
     * @param docenteDTO DTO del docente a crear.
     * @return DocenteDTO creado.
     */
    DocenteDTO crearDocente(DocenteDTO docenteDTO);

    /**
     * Actualiza un docente existente.
     * @param id ID del docente a actualizar.
     * @param docenteDTO DTO del docente con los nuevos datos.
     * @return DocenteDTO actualizado.
     */
    DocenteDTO actualizarDocente(Long id, DocenteDTO docenteDTO);

    /**
     * Elimina un docente por su ID.
     * @param id ID del docente a eliminar.
     */
    void eliminarDocente(Long id);
}