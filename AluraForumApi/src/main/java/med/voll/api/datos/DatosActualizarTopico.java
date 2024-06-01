package med.voll.api.datos;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull
        Long id,
        String titulo,
         String mensaje,
         String fechaCreacion,
         String status,
         String curso,
        String respuesta){


}
