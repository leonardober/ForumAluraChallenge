package med.voll.api.datos;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsuario(
         @NotNull
         Long id,
         String nombre,
         String correoElectronico,
         String perfiles
) {
}
