package br.com.fcamara.asnpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fcamara.asnpark.domain.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

}
