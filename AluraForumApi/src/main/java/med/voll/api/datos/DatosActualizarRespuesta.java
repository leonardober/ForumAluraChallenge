package med.voll.api.datos;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarRespuesta(
        @NotNull
        Long id,
        String mensaje,
        String topico,
        String fechaCreacion,
        String autor,
        String solucion
) {
}
