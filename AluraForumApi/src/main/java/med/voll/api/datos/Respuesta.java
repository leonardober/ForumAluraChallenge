package med.voll.api.datos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "respuesta")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

            private Long id;
            private String mensaje;
            private String topico;
            private String fechaCreacion;
            private String autor;
            private String solucion;
            private boolean activo;

    public Respuesta(DatosRegistroRespuesta datosRegistroRespuesta) {
     this.topico = datosRegistroRespuesta.topico();
     this.autor = datosRegistroRespuesta.autor();
     this.mensaje = datosRegistroRespuesta.mensaje();
     this.fechaCreacion = datosRegistroRespuesta.fechaCreacion();
     this.solucion = datosRegistroRespuesta.solucion();
     this.activo =true;
    }

    public void actualizarDatos(DatosActualizarRespuesta datosActualizarRespuesta) {

        if(datosActualizarRespuesta.topico() !=null){
            this.topico = datosActualizarRespuesta.topico();
        }
        if (datosActualizarRespuesta.autor() !=null){
            this.autor = datosActualizarRespuesta.autor();
        }

       if(datosActualizarRespuesta.mensaje() !=null)
       {
           this.mensaje = datosActualizarRespuesta.mensaje();
       }

       if(datosActualizarRespuesta.fechaCreacion() !=null){
           this.fechaCreacion = datosActualizarRespuesta.fechaCreacion();
       }

     if(datosActualizarRespuesta.solucion() !=null){
         this.solucion = datosActualizarRespuesta.solucion();
     }

    }
}
