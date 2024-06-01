package med.voll.api.datos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        Long id,
        @NotBlank
        String titulo,
        @NotBlank

        String mensaje,
        @NotBlank
        String fechaCreacion,
        @NotBlank
        String status,
        @NotBlank
        String curso,
        @NotBlank
        String respuesta) {
}
