package med.voll.api.datos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "curso")
@Entity(name= "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String categoria;
    private boolean activo;

    public Curso(DatosRegistroCurso datosRegistroCurso) {
        this.nombre = datosRegistroCurso.nombre();
        this.categoria = datosRegistroCurso.categoria();
        this.activo =true;
    }

    public void actualizarDatos(DatosActualizarCurso datosActualizarCurso) {
       if(datosActualizarCurso.nombre() !=null){
           this.nombre = datosActualizarCurso.nombre();
       }

       if (datosActualizarCurso.categoria() !=null){
           this.categoria = datosActualizarCurso.categoria();
       }

    }
}
