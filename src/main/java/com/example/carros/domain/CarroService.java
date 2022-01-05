package com.example.carros.domain;

import com.example.carros.domain.dto.CarroDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository repository;

    public List<CarroDto> getCarros(){

        return repository.findAll().stream().map(c -> new CarroDto(c)).collect(Collectors.toList());

    }


    public Optional<CarroDto> getCarroById(Long id) {
        return repository.findById(id).map(CarroDto::new);
    }

    public List<CarroDto> getCarroByTipo(String tipo) {
        return repository.findByTipo(tipo).stream().map(c -> new CarroDto(c)).collect(Collectors.toList());
    }

    public Carro save(Carro carro) {
        return repository.save(carro);
    }

    public CarroDto update(Carro carro, Long id) {
        Assert.notNull(id, "Não foi possível atualizar o registro");

        Optional<Carro> optional = repository.getCarroById(id);
        if(optional.isPresent()){
            Carro db = optional.get();
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.println("Carro id" + db.getId());
            repository.save(db);

            return CarroDto.create(db);
        }else{
            return null;
//            throw new RuntimeException("Não foi possível encontrar o registro");
        }

    }

    public boolean delete(Long id) {
        Optional<CarroDto> carro = getCarroById(id);
        if(carro.isPresent()){
            repository.deleteById(id);
            return true;
        }
        return false;

    }

    public CarroDto insert(Carro carro) {
        Assert.isNull(carro.getId(), "Nãio foi possivel inserir o registro");
        return CarroDto.create(repository.save(carro));
    }
}
