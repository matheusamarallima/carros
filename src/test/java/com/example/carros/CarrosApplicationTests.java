package com.example.carros;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import com.example.carros.domain.dto.CarroDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.*;



@SpringBootTest
class CarrosApplicationTests {
	@Autowired
	private CarroService carroService;
	@Test
	public void save() {
		Carro carro = new Carro();
		carro.setNome("Grande carro");
		carro.setTipo("esportivos");
		CarroDto c = carroService.insert(carro);

		assertNotNull(c);

		Long id = c.getId();
		assertNotNull(id);

		Optional<CarroDto> op = carroService.getCarroById(id);
		assertTrue(op.isPresent());

		c = op.get();
		assertEquals("Grande carro", c.getNome());
		assertEquals("esportivos", c.getTipo());

		carroService.delete(id);
		assertFalse(carroService.getCarroById(id).isPresent());

	}

	@Test
	public void testLista(){
		List<CarroDto> carros = carroService.getCarros();
		assertEquals(30, carros.size());

	}
}
