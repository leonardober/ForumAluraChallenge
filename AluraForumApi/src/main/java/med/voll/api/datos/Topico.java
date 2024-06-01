package med.voll.api.datos;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table (name = "topico")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private String fechaCreacion;
    private String status;
    private String curso;
    private String respuesta;
    private boolean activo;

    public Topico(DatosRegistroTopico datosRegistroTopico) {
        this.titulo = datosRegistroTopico.titulo();
        this.curso = datosRegistroTopico.curso();
        this.fechaCreacion = datosRegistroTopico.fechaCreacion();
        this.mensaje = datosRegistroTopico.mensaje();
        this.respuesta = datosRegistroTopico.respuesta();
        this.status = datosRegistroTopico.status();
        this.activo = true;
    }

    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico) {
         if(datosActualizarTopico.titulo() !=null){
             this.titulo = datosActualizarTopico.titulo();
         }
         if(datosActualizarTopico.curso() !=null){
            this.curso = datosActualizarTopico.curso();
        }

        if(datosActualizarTopico.fechaCreacion() !=null){
            this.fechaCreacion = datosActualizarTopico.fechaCreacion();
        }

        if (datosActualizarTopico.mensaje() !=null){
            this.mensaje = datosActualizarTopico.mensaje();
        }
       if (datosActualizarTopico.respuesta() != null){
           this.respuesta = datosActualizarTopico.respuesta();
       }

      if(datosActualizarTopico.status() !=null){
          this.status = datosActualizarTopico.status();
      }

    }
//Delete logico
    public void desactivarTopico() {
        this.activo = false;
    }
}
