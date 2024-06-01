package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.datos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
@Autowired
    private UsuarioRepository usuarioRepository;
    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario,
                                           UriComponentsBuilder uriComponentsBuilder){
        System.out.println("El request registrar Usuario llega correctamente");
       // System.out.println(datosRegistroUsuario);
     Usuario usuario = usuarioRepository.save(new Usuario(datosRegistroUsuario));
     DatosRespuestaUsuario datosRespuestaUsuario =new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(),
             usuario.getCorreoElectronico(), usuario.getPerfiles());
        URI url = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }
    @GetMapping
    public List<Usuario> listadoUsuarios(){
        return usuarioRepository.findAll();
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarUsuario (@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario){
      Usuario usuario = usuarioRepository.getReferenceById(datosActualizarUsuario.id());
      usuario.actualizarDatos(datosActualizarUsuario);
      return ResponseEntity.ok(new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(), usuario.getCorreoElectronico(), usuario.getPerfiles()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario (@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(usuario);
        return ResponseEntity.noContent().build();
    }
//Retorna datos de un usuario en especifico
    @GetMapping("/{id}")

    public ResponseEntity<DatosRespuestaUsuario> retornaDatosUsuario (@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        var datosUsuario = new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(), usuario.getCorreoElectronico(), usuario.getPerfiles());
        return ResponseEntity.ok(datosUsuario);
    }
}
