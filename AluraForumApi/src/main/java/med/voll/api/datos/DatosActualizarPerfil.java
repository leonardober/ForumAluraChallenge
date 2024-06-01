package med.voll.api.datos;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarPerfil(
        @NotNull
        Long id,
        String nombre
) {
}
