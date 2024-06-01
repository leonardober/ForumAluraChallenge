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
@RequestMapping("/perfil")
public class PerfilController {
@Autowired
    private PerfilRepository perfilRepository;
    @PostMapping
    public ResponseEntity<DatosRespuestaPerfil> registrarPefil(@RequestBody @Valid DatosRegistroPerfil datosRegistroPerfil,
                                         UriComponentsBuilder uriComponentsBuilder){
        System.out.println("El request registrar Perfil llega exitosamente");
       // System.out.println(datosRegistroPerfil);
        Perfil perfil = perfilRepository.save(new Perfil(datosRegistroPerfil));
        DatosRespuestaPerfil datosRespuestaPerfil = new DatosRespuestaPerfil(perfil.getId(), perfil.getNombre());

        URI url = uriComponentsBuilder.path("/perfil/{id}").buildAndExpand(perfil.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaPerfil);
    }
    @GetMapping
    public List<Perfil> listadoPerfiles(){
        return perfilRepository.findAll();
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarPerfil (@RequestBody @Valid DatosActualizarPerfil datosActualizarPerfil){
      Perfil perfil = perfilRepository.getReferenceById(datosActualizarPerfil.id());
      perfil.actualizarDatos(datosActualizarPerfil);
      return ResponseEntity.ok(new DatosRespuestaPerfil(perfil.getId(), perfil.getNombre()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarPerfil (@PathVariable Long id){
        Perfil perfil = perfilRepository.getReferenceById(id);
        perfilRepository.delete(perfil);
        return ResponseEntity.noContent().build();
    }
//Retorna los datos de un perfil especifico
    @GetMapping("/{id}")

    public ResponseEntity<DatosRespuestaPerfil> retornaDatosPerfil (@PathVariable Long id){
        Perfil perfil = perfilRepository.getReferenceById(id);
        var datosPerfil = new DatosRespuestaPerfil(perfil.getId(), perfil.getNombre());
        return ResponseEntity.ok(datosPerfil);
    }
}
