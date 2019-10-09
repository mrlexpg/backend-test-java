package br.com.fcamara.asnpark.exception;

@SuppressWarnings("serial")
public class EntradaVeiculoNotFoundException extends RuntimeException {
	
	public EntradaVeiculoNotFoundException(Long id) {
		super("Entrada de veiculo: " + id + " nao encontrado");
	}
}
