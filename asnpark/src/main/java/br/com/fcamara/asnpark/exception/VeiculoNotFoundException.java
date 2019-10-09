package br.com.fcamara.asnpark.exception;

@SuppressWarnings("serial")
public class VeiculoNotFoundException extends RuntimeException {
	
	public VeiculoNotFoundException(Long id) {
		super("Veiculo: " + id + " nao encontrado");
	}
}
