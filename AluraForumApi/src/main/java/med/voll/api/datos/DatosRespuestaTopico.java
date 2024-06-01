package med.voll.api.datos;

public record DatosRespuestaTopico(

        Long id,
        String titulo,
        String mensaje,
        String fechaCreacion,
        String status,
        String curso,
        String respuesta )

{
}
