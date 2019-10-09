package br.com.fcamara.asnpark.error.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import br.com.fcamara.asnpark.domain.Estabelecimento;
import br.com.fcamara.asnpark.repository.EstabelecimentoRepository;

public class EstabelecimentoValidator implements ConstraintValidator<CnpjEstabelecimento, String> {

	@Autowired
	EstabelecimentoRepository repoEstabelecimento;	
	
	@Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
      
        Example<Estabelecimento> example = Example.of(new Estabelecimento().inscritoNoCNPJ(value)); 
        
        return repoEstabelecimento.findOne(example).isEmpty();
    }
}