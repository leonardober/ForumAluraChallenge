package med.voll.api.datos;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroCurso(

        Long id,

        @NotBlank
        String nombre,
        @NotBlank
        String categoria) {
}
