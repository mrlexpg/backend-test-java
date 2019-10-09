package br.com.fcamara.asnpark.controller;

import java.util.List;
import java.util.Objects;

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

import br.com.fcamara.asnpark.domain.Estabelecimento;
import br.com.fcamara.asnpark.exception.EstabelecimentoNotFoundException;
import br.com.fcamara.asnpark.repository.EstabelecimentoRepository;
import br.com.fcamara.asnpark.util.AsnParkFcmUtils;

@RestController
@Validated
public class EstabelecimentoController {

	@Autowired
	private EstabelecimentoRepository repository;
	
	private String maskCnpj = "99.999.999/9999-99";
	
	//Select all
	@GetMapping(value= "/estabelecimentos")
	List<Estabelecimento> findAll() {
		
		List<Estabelecimento> listParks = repository.findAll();		
		listParks.stream().forEach(item -> {
			item.setCnpj(AsnParkFcmUtils.getValueMaskFormat(maskCnpj, item.getCnpj(), true));
		});
		
		return listParks;
	}
	
	//Insert
	//return 201 instead of 200
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/estabelecimento")
	Estabelecimento novoEstabelecimento(@Valid @RequestBody Estabelecimento novo) {
		
		Estabelecimento novoEstacionamento = repository.save(novo);
		
		if(Objects.nonNull(novoEstacionamento)) {
			novoEstacionamento.setCnpj(AsnParkFcmUtils.getValueMaskFormat(maskCnpj, novoEstacionamento.getCnpj(), true));
		}
		
		return novoEstacionamento;		
	}
	
	//Select where
	@GetMapping("/estabelecimento/{id}")
	Estabelecimento findOne(@PathVariable @Min(1) Long id) {
		return repository.findById(id)
						 .orElseThrow(() -> new EstabelecimentoNotFoundException(id));
	}
	
	 //Insert or update
    @PutMapping("/estabelecimento/{id}")
    Estabelecimento saveOrUpdate(@RequestBody Estabelecimento park, @PathVariable Long id) {
        return repository.findById(id)
                .map(x -> {
                     x.setNome(park.getNome());
                     x.setEndereco(park.getEndereco());
                     x.setNumeroTelefone(park.getNumeroLogradouro());
                     x.setCnpj(park.getCnpj());
                     x.setCodigoDDD(park.getCodigoDDD());
                     x.setNumeroTelefone(park.getNumeroTelefone());
                     x.setQuantidadeVagaCarro(park.getQuantidadeVagaCarro());
                     x.setQuantidadeVagaMoto(park.getQuantidadeVagaMoto());
                                  
                     return repository.save(x);
                })
                .orElseGet(() -> {
                    park.setId(id);
                    return repository.save(park);
                });
    }  

    @DeleteMapping("/estacionamento/{id}")
    void deleteEstacionamento(@Valid @PathVariable Long id) {
        repository.deleteById(id);
    }	
}
