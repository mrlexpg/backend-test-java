INSERT INTO TB_ESTABELECIMENTO (ds_razao_social, ds_logradouro, nm_logradouro, nm_cnpj, cd_ddd, nm_telefone, qt_moto, qt_carro) VALUES
('FCamara Parks LTDA', 'Av: Floriano Peixoto', 163, '21503967000166', 13, 35672310, 10, 20);
 
INSERT INTO TB_VEICULO (ds_placa, ds_marca, ds_modelo, ds_cor, ds_tipo) VALUES
 ('ABC-1234','Volks','GOLF','CINZA','CARRO'); 
 
INSERT INTO TB_ENTRADA_SAIDA_VEICULO (dt_hora_entrada, dt_hora_saida, id_veiculo_fk, id_estabelecimento_fk ) VALUES
('2019-10-07 11:45','2019-10-07 15:45',1,1);