package com.example.carros.domain.dto;

import com.example.carros.domain.Carro;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
public class CarroDto {

    private Long id;
    private String nome;
    private String tipo;

    public CarroDto(Carro carro){
        this.id = carro.getId();
        this.nome = carro.getNome();
        this.tipo = carro.getTipo();

    }

    public static CarroDto create(Carro carro) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(carro, CarroDto.class);

    }
}
