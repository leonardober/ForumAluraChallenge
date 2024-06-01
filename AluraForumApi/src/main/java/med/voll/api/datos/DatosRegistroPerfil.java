package med.voll.api.datos;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroPerfil(

        Long id,

        @NotBlank
        String nombre) {
}
