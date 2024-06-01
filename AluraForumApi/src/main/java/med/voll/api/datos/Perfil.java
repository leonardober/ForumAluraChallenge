package med.voll.api.datos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "perfil")
@Entity(name = "Perfil")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (of = "id")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private boolean activo;

    public Perfil(DatosRegistroPerfil datosRegistroPerfil) {
     this.nombre = datosRegistroPerfil.nombre();
     this.activo = true;
    }

    public void actualizarDatos(DatosActualizarPerfil datosActualizarPerfil) {
     if(datosActualizarPerfil.nombre() !=null){
         this.nombre = datosActualizarPerfil.nombre();
     }


    }
}
