package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class AnuncioRestController {
    private Map<Long, Anuncio> anuncios = new ConcurrentHashMap<>();
    private AtomicLong ultimoID = new AtomicLong();

    @GetMapping("/anuncio")
    public Collection<Anuncio> todosAnuncios() {
        return anuncios.values();
    }

    @GetMapping("/anuncios/{id}")
    public ResponseEntity<Anuncio> getAnuncio(@PathVariable long id) {
        Anuncio anuncio = anuncios.get(id);
        if (anuncio != null) {
            return new ResponseEntity<>(anuncio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/anuncio")
    @ResponseStatus(HttpStatus.CREATED)
    public Anuncio nuevoAnuncio(@RequestBody Anuncio anuncio) {
        long id = ultimoID.incrementAndGet();
        anuncio.setId(id);
        anuncios.put(id, anuncio);
        return anuncio;
    }

    @DeleteMapping("/anuncio/{id}")
    public ResponseEntity<Anuncio> borrarAnuncio(@PathVariable long id) {
        Anuncio anuncio = anuncios.get(id);
        if(anuncio!=null) {
            return new ResponseEntity<>(anuncio, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/anuncio/{id}")
    public ResponseEntity<Anuncio> actualizaAnuncio(@PathVariable long id, @RequestBody Anuncio anuncioActualizado) {
        Anuncio anuncio = anuncios.get(id);
        if (anuncio != null) {
            anuncioActualizado.setId(id);
            anuncios.put(id, anuncioActualizado);
            return new ResponseEntity<>(anuncioActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
