package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.datos.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/topico")
public class TopicoController {
@Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                          UriComponentsBuilder uriComponentsBuilder){
        System.out.println("El request registrar Topico llega correctamente");
       // System.out.println(datosRegistroTopico);
      Topico topico =  topicoRepository.save(new Topico(datosRegistroTopico));
      DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(),
              topico.getMensaje(), topico.getFechaCreacion(),
              topico.getStatus(), topico.getCurso(),topico.getRespuesta()
      );
       URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }
   //Using generated security password: bdea989f-d49b-431d-ab0c-2831873c230b
    @GetMapping
    public List<Topico> listadoTopicos(){
      return topicoRepository.findAll();
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico (@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
             Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
             topico.actualizarDatos(datosActualizarTopico);
             return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(), topico.getTitulo(),
                     topico.getMensaje(), topico.getFechaCreacion(),
                     topico.getStatus(), topico.getCurso(),topico.getRespuesta()
                     ));

    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico (@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topicoRepository.delete(topico); //Delete total base datos Nota debe estar activo uno delos dos
        topico.desactivarTopico();  //Delete logico
        return ResponseEntity.noContent().build();
    }


    //Retorna lod datos de un Topico en especifico
    @GetMapping ("/{id}")

    public ResponseEntity<DatosRespuestaTopico> retornaDatosTopico (@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(),
                topico.getMensaje(), topico.getFechaCreacion(),
                topico.getStatus(), topico.getCurso(),topico.getRespuesta()
        );

        return ResponseEntity.ok(datosTopico);
    }
}
