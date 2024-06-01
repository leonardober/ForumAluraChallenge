package med.voll.api.datos;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroRespuesta(

        Long id,

        @NotBlank
        String mensaje,
        @NotBlank
        String topico,
        @NotBlank
        String fechaCreacion,
        @NotBlank
        String autor,
        @NotBlank
        String solucion) {
}
