package com.universidad.service;

import com.universidad.dto.MateriaDTO;
import com.universidad.model.Materia;
import java.util.List;

public interface IMateriaService {
    List<MateriaDTO> obtenerTodasLasMaterias();
    MateriaDTO obtenerMateriaPorId(Long id);
    MateriaDTO obtenerMateriaPorCodigoUnico(String codigoUnico);
    MateriaDTO crearMateria(MateriaDTO materia);
    MateriaDTO actualizarMateria(Long id, MateriaDTO materia);
    void eliminarMateria(Long id);
    /**
     * Obtiene la entidad Materia por su ID (para uso interno).
     * @param id ID de la materia.
     * @return Materia (entidad JPA).
     */
    Materia obtenerEntidadMateriaPorId(Long id);

    /**
     * Guarda una entidad Materia (para uso interno).
     * @param materia Materia a guardar.
     * @return Materia guardada.
     */
    Materia guardarEntidad(Materia materia);
}
