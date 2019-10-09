package br.com.fcamara.asnpark.exception;

@SuppressWarnings("serial")
public class EstabelecimentoNotFoundException extends RuntimeException {
	
	public EstabelecimentoNotFoundException(Long id) {
		super("Estabelecimento: " + id + " nao encontrado");
	}
}
