package br.com.fcamara.asnpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fcamara.asnpark.domain.Estabelecimento;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {

}
