package com.example.carros.api;


import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import com.example.carros.domain.dto.CarroDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
    @Autowired
    private CarroService service;

    @GetMapping
    public ResponseEntity<List<CarroDto>> get(){
        return ResponseEntity.ok(service.getCarros());
    }

    @GetMapping("/{id}")
    public Optional<CarroDto> get(@PathVariable("id") Long id){
        return service.getCarroById(id);
    }

    @GetMapping("/tipo/{tipo}")
    public List<CarroDto> get(@PathVariable("tipo") String tipo){
        return service.getCarroByTipo(tipo);
    }

    @PostMapping
    public ResponseEntity<Object> post (@RequestBody Carro carro) {
        try {
            CarroDto c = service.insert(carro);

            URI location = getURI(c.getId());
            return ResponseEntity.created(null).build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    private URI getURI(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand().toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put (@PathVariable("id") Long id, @RequestBody Carro carro){
        carro.setId(id);
        CarroDto c = service.update(carro, id);
        return c != null ? ResponseEntity.ok(c) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        boolean ok = service.delete(id);
        return ok ? ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

}
