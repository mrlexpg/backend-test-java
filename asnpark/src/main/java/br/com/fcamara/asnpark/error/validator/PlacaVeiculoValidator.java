package br.com.fcamara.asnpark.error.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import br.com.fcamara.asnpark.domain.Veiculo;
import br.com.fcamara.asnpark.repository.VeiculoRepository;

public class PlacaVeiculoValidator implements ConstraintValidator<PlacaVeiculo, String> {

	@Autowired
	VeiculoRepository repoVeiculo;	
	
	@Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
	
        Example<Veiculo> example = Example.of(new Veiculo().placa(value)); 
        
        return repoVeiculo.findOne(example).isEmpty();
    }
}