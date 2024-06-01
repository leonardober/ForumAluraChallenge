package med.voll.api.datos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table (name = "usuario")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

            private Long id;
            private String nombre;
            private String correoElectronico;
            private String perfiles;
            private boolean activo;

    public Usuario(DatosRegistroUsuario datosRegistroUsuario) {
        this.nombre = datosRegistroUsuario.nombre();
        this.correoElectronico = datosRegistroUsuario.correoElectronico();
        this.perfiles = datosRegistroUsuario.perfiles();
        this.activo = true;
    }

    public void actualizarDatos(DatosActualizarUsuario datosActualizarUsuario) {

        if(datosActualizarUsuario.nombre() !=null){
            this.nombre = datosActualizarUsuario.nombre();
        }

        if(datosActualizarUsuario.correoElectronico() !=null){
            this.correoElectronico = datosActualizarUsuario.correoElectronico();
        }

        if(datosActualizarUsuario.perfiles() !=null){
            this.perfiles = datosActualizarUsuario.perfiles();
        }


    }
}
