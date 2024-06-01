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
@RequestMapping("/respuesta")
public class RespuestaController {
@Autowired
    private RespuestaRepository respuestaRepository;
    @PostMapping
    public ResponseEntity<DatosRespuestaRespuesta> registroRespuesta(@RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta,
                                           UriComponentsBuilder uriComponentsBuilder){
        System.out.println("El request registrar Respuesta llega correctamente");
        //System.out.println(datosRegistroRespuesta);
       Respuesta respuesta = respuestaRepository.save(new Respuesta(datosRegistroRespuesta));
       DatosRespuestaRespuesta datosRespuestaRespuesta = new DatosRespuestaRespuesta(respuesta.getId(), respuesta.getMensaje(),
               respuesta.getTopico(), respuesta.getFechaCreacion(), respuesta.getAutor(), respuesta.getSolucion());
        URI url = uriComponentsBuilder.path("/respuesta/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaRespuesta);
    }
    @GetMapping
    public List<Respuesta> listadoRespuestas(){
        return respuestaRepository.findAll();
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarRespuesta (@RequestBody @Valid DatosActualizarRespuesta datosActualizarRespuesta){
        Respuesta respuesta = respuestaRepository.getReferenceById(datosActualizarRespuesta.id());
        respuesta.actualizarDatos(datosActualizarRespuesta);
        return ResponseEntity.ok(new DatosRespuestaRespuesta(respuesta.getId(), respuesta.getMensaje(), respuesta.getTopico(),
                respuesta.getFechaCreacion(), respuesta.getAutor(), respuesta.getSolucion()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta (@PathVariable Long id){
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
           respuestaRepository.delete(respuesta);
           return ResponseEntity.noContent().build();
    }
 //Retorna los datos deuna respuesta en especifico
   @GetMapping("/{id}")

    public ResponseEntity<DatosRespuestaRespuesta> retornaDatosRespuesta (@PathVariable Long id){
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        var datosRespuesta = new DatosRespuestaRespuesta(respuesta.getId(), respuesta.getMensaje(), respuesta.getTopico(),
                respuesta.getFechaCreacion(), respuesta.getAutor(), respuesta.getSolucion());
        return ResponseEntity.ok(datosRespuesta);
    }
}
