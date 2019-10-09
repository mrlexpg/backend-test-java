package br.com.fcamara.asnpark.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fcamara.asnpark.domain.Veiculo;
import br.com.fcamara.asnpark.exception.VeiculoNotFoundException;
import br.com.fcamara.asnpark.repository.VeiculoRepository;


@RestController
@Validated
public class VeiculoController {

	@Autowired
	private VeiculoRepository repository;
	
	@GetMapping("/veiculos")
	List<Veiculo> findAll() {
		return repository.findAll();
	}
	
	//Save
	//return 201 instead of 200
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/veiculo")
	Veiculo novoVeiculo(@Valid @RequestBody Veiculo novoVeiculo) {
		novoVeiculo.setCor(novoVeiculo.getCor().toUpperCase());
		return repository.save(novoVeiculo);		
	}
	
	//Find
	@GetMapping("/veiculo/{id}")
	Veiculo findOne(@PathVariable @Min(1) Long id) {
		return repository.findById(id)
						 .orElseThrow(() -> new VeiculoNotFoundException(id));
	}
	
	 // Save or update
    @PutMapping("/veiculo/{id}")
    Veiculo saveOrUpdate(@RequestBody Veiculo veiculo, @PathVariable Long id) {
        return repository.findById(id)
		                 .map(x -> {
		                      x.setMarca(veiculo.getMarca());
		                      x.setModelo(veiculo.getModelo());
		                      x.setPlaca(veiculo.getPlaca());
		                      x.setTipo(veiculo.getTipo());
		                    
		                      return repository.save(x);
		                 })
		                 .orElseGet(() -> {
		                     veiculo.setId(id);
		                   
		                     return repository.save(veiculo);
		                 });
    }

    @DeleteMapping("/veiculo/{id}")
    void deleteBook(@Valid @PathVariable Long id) {
        repository.deleteById(id);
    }	
}
