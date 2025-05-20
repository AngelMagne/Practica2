package com.universidad.controller;

import com.universidad.dto.InscripcionDTO;
import com.universidad.service.IInscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    @Autowired
    private IInscripcionService inscripcionService;

    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<InscripcionDTO>> obtenerPorEstudiante(@PathVariable Long estudianteId) {
        return ResponseEntity.ok(inscripcionService.obtenerInscripcionesPorEstudiante(estudianteId));
    }

    @PostMapping
    public ResponseEntity<InscripcionDTO> crear(@RequestBody InscripcionDTO inscripcionDTO) {
        return ResponseEntity.ok(inscripcionService.crearInscripcion(inscripcionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        inscripcionService.eliminarInscripcion(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<InscripcionDTO> obtenerPorId(@PathVariable Long id) {
    InscripcionDTO inscripcion = inscripcionService.obtenerInscripcionPorId(id);
    if (inscripcion == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(inscripcion);
    }

    // (Opcional) Actualizar inscripción
    @PutMapping("/{id}")
    public ResponseEntity<InscripcionDTO> actualizar(@PathVariable Long id, @RequestBody InscripcionDTO inscripcionDTO) {
    InscripcionDTO actualizada = inscripcionService.actualizarInscripcion(id, inscripcionDTO);
    return ResponseEntity.ok(actualizada);
    }
}