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

import br.com.fcamara.asnpark.domain.EntradaSaidaVeiculo;
import br.com.fcamara.asnpark.domain.Estabelecimento;
import br.com.fcamara.asnpark.domain.Veiculo;
import br.com.fcamara.asnpark.exception.EntradaVeiculoNotFoundException;
import br.com.fcamara.asnpark.repository.EntradaSaidaVeiculoRepository;
import br.com.fcamara.asnpark.repository.EstabelecimentoRepository;
import br.com.fcamara.asnpark.repository.VeiculoRepository;

@RestController
@Validated
public class EntradaSaidaVeiculoController {

	@Autowired
	private EntradaSaidaVeiculoRepository repository;
	
	@Autowired
	private VeiculoRepository repoVeiculo;
	
	@Autowired
	private EstabelecimentoRepository repoEstabelecimento;
	
	@GetMapping("/entradaSaidaVeiculos")
	List<EntradaSaidaVeiculo> findAll() {
		return repository.findAll();
	}
	
	//Save
	//return 201 instead of 200
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/entradaSaidaVeiculo")
	EntradaSaidaVeiculo novaEntrada(@Valid @RequestBody EntradaSaidaVeiculo novaEntrada) {
		
		EntradaSaidaVeiculo entradaVeiculo = repository.saveAndFlush(novaEntrada);
		
		Veiculo veiculo = new Veiculo();     
		veiculo = repoVeiculo.findById(entradaVeiculo.getVeiculo().getId()).get();
		
		entradaVeiculo.setVeiculo(veiculo);
		
		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento = repoEstabelecimento.findById(entradaVeiculo.getEstabelecimento().getId()).get();
		
		entradaVeiculo.setEstabelecimento(estabelecimento);

		return entradaVeiculo;		
	}
	
	//Find
	@GetMapping("/entradaSaidaVeiculo/{id}")
	EntradaSaidaVeiculo findOne(@PathVariable @Min(1) Long id) {
		return repository.findById(id)
						 .orElseThrow(() -> new EntradaVeiculoNotFoundException(id));
	}

	// Save or update
    @PutMapping("/entradaSaidaVeiculo/{id}")	
	EntradaSaidaVeiculo saveOrUpdate(@RequestBody EntradaSaidaVeiculo entradaVeiculo, @PathVariable Long id) {	  
    	return repository.findById(id)
                .map(x -> {
	                 x.setDataHoraEntrada(entradaVeiculo.getDataHoraEntrada());                     
	                 x.setDataHoraSaida(entradaVeiculo.getDataHoraSaida());
	                 x.setEstabelecimento(entradaVeiculo.getEstabelecimento());
	                 x.setVeiculo(entradaVeiculo.getVeiculo());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                	entradaVeiculo.setId(id);
                    return repository.save(entradaVeiculo);
                });
		}
	 

    @DeleteMapping("/entradaSaidaVeiculo/{id}")
    void deleteBook(@Valid @PathVariable Long id) {
        repository.deleteById(id);
    }	
}
