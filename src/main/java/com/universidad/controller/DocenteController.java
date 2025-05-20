package com.universidad.controller;

import com.universidad.dto.DocenteDTO;
import com.universidad.service.IDocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/docentes")
public class DocenteController {

    @Autowired
    private IDocenteService docenteService;

    @GetMapping
    public ResponseEntity<List<DocenteDTO>> obtenerTodos() {
        return ResponseEntity.ok(docenteService.obtenerTodosLosDocentes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocenteDTO> obtenerPorId(@PathVariable Long id) {
        DocenteDTO docente = docenteService.obtenerDocentePorId(id);
        if (docente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(docente);
    }

    @PostMapping
    public ResponseEntity<DocenteDTO> crear(@RequestBody DocenteDTO docenteDTO) {
        DocenteDTO creado = docenteService.crearDocente(docenteDTO);
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocenteDTO> actualizar(@PathVariable Long id, @RequestBody DocenteDTO docenteDTO) {
        DocenteDTO actualizado = docenteService.actualizarDocente(id, docenteDTO);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        docenteService.eliminarDocente(id);
        return ResponseEntity.noContent().build();
    }
}