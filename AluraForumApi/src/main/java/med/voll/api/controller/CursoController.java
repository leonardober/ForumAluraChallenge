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
@RequestMapping("/curso")
public class CursoController {
@Autowired
    private CursoRepository cursoRepository;
@PostMapping
    public ResponseEntity<DatosRespuestaCurso> registrarCurso(@RequestBody @Valid DatosRegistroCurso datosRegistroCurso,
                                         UriComponentsBuilder uriComponentsBuilder){
        System.out.println("El request registrar Curso llega correctamente");
   // System.out.println(datosRegistroCurso);
   Curso curso = cursoRepository.save(new Curso(datosRegistroCurso));
   DatosRespuestaCurso datosRespuestaCurso = new DatosRespuestaCurso(curso.getId(), curso.getNombre(), curso.getCategoria());

    URI url = uriComponentsBuilder.path("/curso/{id}").buildAndExpand(curso.getId()).toUri();
    return ResponseEntity.created(url).body(datosRespuestaCurso);
    }
    @GetMapping
    public List<Curso> listadoCursos(){
    return cursoRepository.findAll();
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarCurso(@RequestBody @Valid DatosActualizarCurso datosActualizarCurso){
           Curso curso = cursoRepository.getReferenceById(datosActualizarCurso.id());
           curso.actualizarDatos(datosActualizarCurso);
           return ResponseEntity.ok(new DatosRespuestaCurso(curso.getId(), curso.getNombre(), curso.getCategoria()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarCurso(@PathVariable Long id){
        Curso curso = cursoRepository.getReferenceById(id);
        cursoRepository.delete(curso);
        return ResponseEntity.noContent().build();
    }
//retorna los datos de un Curso especifico
   @GetMapping("/{id}")

    public ResponseEntity<DatosRespuestaCurso> retornaDatosCurso (@PathVariable Long id){
        Curso curso = cursoRepository.getReferenceById(id);
        var datosCurso = new DatosRespuestaCurso(curso.getId(), curso.getNombre(), curso.getCategoria());
        return ResponseEntity.ok(datosCurso);
    }
}
