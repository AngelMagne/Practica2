package com.universidad.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class InscripcionDTO {
    private Long id;
    private Long estudianteId;
    private Long materiaId;
    private LocalDate fechaInscripcion;
}