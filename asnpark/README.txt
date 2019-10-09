Autor: Alexandre Santos do Nascimento
Contato: (13) 9817-93102

Nome da Aplicacao: ASNPARK

==========
Descricao 
==========
   A aplicacao ASNPARK foi desenvolvido como parte do processo seletivo para recrutamento na FCAMARA.
   Foram desenvolvido todas as funcionalidades requisitadas. Sao elas:

    • Estabelecimento: CRUD; 
    • Veículos: CRUD; 
    • Controle de entrada e saída de veículos.

   Tambem foram desenvolvidos dois requisitos nao funcionais (Desenvolver utilizando TDD e Criar uma solução de autenticação)

=======================
Tecnologias utilizadas
=======================

* Java 8 (ou superior);
* Maven 3.2.3 (ou superior);
* IDE Eclipse Spring Tool Suite 4 
  Version: 4.4.0.RELEASE
* Linux Mint 19.2 Cinnamon (kernel 4.15.0-65-generic)

* Spring Boot;
* Spring Rest;
* Spring Security;
* Spring Data;
* H2 Database (em memoria)

Obs.: A aplicacao foi desenvolvida no ambiente Linux conforme descrito.Embora nao tenha sido testado no ambient Windows, 
      a aplicacao e portavel desde que as tecnologias indicadas (Java/Maven) estejam instaladas no S.O. 

================
Execucao do App
================
Passo 1: Antes de executar, e necessario entrar na pasta do projeto (asnpark) e executar o comando para resolver as dependencias.
         Comando: mvn dependency:tree
         Uma arvore de dependencia aparecera ate que mensagem [INFO] BUILD SUCCESS.

Passo 2: Nao havendo problema no passo anterior, executar a aplicacao usando,
         Comando : mvn spring-boot run 


Passo 3: Para consultar as persistencias dos dados no H2, utilizar o console atraves da url : http://localhost:8080/h2-console
         Para logar no console , utilizar o usuario e a senha configurado no application.properties:
         
         User Name: sa
         Password:  adm123

=========================
Comando para teste da app
=========================

   A aplicacao foi testa utilizando o comando CURL. Este comando disponível na maioria dos sistemas baseado em Unix. Ele é usado 
   como abreviação para “Client URL”. Comandos Curl são destinados para funcionar como uma forma de verificar a conectividade da URL, 
   além de ser uma ótima ferramenta de transferência de dados.

   Ao executar a aplicacao, tres API estao disponiveis. Sao eles: estabelecimentos, veiculos e entradaSaidaVeiculo. 
   Cada um possui um CRUD (GET, POST, PUT ou DELETE). Todas as chamadas deverao ser feita passando usuario e senha caso contrario, uma
   mensagem de erro do HTTP sera devolvido (401)
   
   Para executa as operacoes, utilizar os metodos HTTP:

    GET, POST, PUT: perfils <USER e ADMIN>
    DELETE: <ADMIN>

   *Observacao: Para utilizar a aplicacao em ambiente Windows, devera verificar se este esteja disponivel no CMD ou no PowerShell 

VEICULO:
========  
    curl -X GET -H 'Accept: application/json' -H 'Content-Type: application/json' -i http://localhost:8080/veiculos -u fcadm:fcamara      (OU)
    curl -X GET -H 'Accept: application/json' -H 'Content-Type: application/json' -i http://localhost:8080/veiculos -u fcuser:fcamara

    Resposta em XML
    curl -X GET -H 'Accept: application/xml' -H 'Content-Type: application/json' -i http://localhost:8080/veiculos -u fcadm:fcamara

    curl -X DELETE -H 'Accept: application/json' -H 'Content-Type: application/json' -i http://localhost:8080/veiculo/1 -u fcuser:fcamara (ERROR)
    curl -X DELETE -H 'Accept: application/json' -H 'Content-Type: application/json' -i http://localhost:8080/veiculo/1 -u fcadm:fcamara  (OK)


    PARA SALVAR/ATUALIZAR, UTILIZAR O MODELO JSON:

    {
		"placa":"",
		"marca":"",
		"modelo":"",
		"cor":"",
		"tipo": "", 
		"entradaSaidaVeiculos":{}
	}

    curl -X POST -H 'Accept: application/json' -H 'Content-Type: application/json' -i http://localhost:8080/veiculo -u fcuser:fcamara 
     -d {\"placa"\:\"ABC-1234\",\"marca\":\"Volks\",\"modelo\":\"GOLF\",\"cor\":\"CINZA\",\"tipo\":\"CARRO\"}   (DEVERA PARECER UM ERRO DE CONSTRAINT. TROCAR A PLACA)

    curl -X POST -H 'Accept: application/json' -H 'Content-Type: application/json' -i http://localhost:8080/veiculo -u fcuser:fcamara 
     -d {\"placa"\:\"CBA-4321\",\"marca\":\"Volks\",\"modelo\":\"GOLF\",\"cor\":\"CINZA\",\"tipo\":\"CARRO\"} (OK)
       
    curl -X PUT -H 'Accept: application/json' -H 'Content-Type: application/json' -i http://localhost:8080/veiculo -u fcuser:fcamara 
     -d {\"placa"\:\"CBA-4321\",\"marca\":\"FIAT\",\"modelo\":\"UNO\",\"cor\":\"branco\",\"tipo\":\"CARRO\"}


ESTABELECIMENTO:
================	
	curl -X GET -H 'Accept: application/json' -H 'Content-Type: application/json' -i http://localhost:8080/estabelecimento -u fcadm:fcamara      (OU)
    curl -X GET -H 'Accept: application/json' -H 'Content-Type: application/json' -i http://localhost:8080/estabelecimento -u fcuser:fcamara

    Resposta em XML:
    curl -X GET -H 'Accept: application/xml' -H 'Content-Type: application/json' -i http://localhost:8080/estabelecimento -u fcadm:fcamara

    curl -X DELETE -H 'Accept: application/json' -H 'Content-Type: application/json' -i http://localhost:8080/estabelecimento/1 -u fcuser:fcamara (ERROR)
    curl -X DELETE -H 'Accept: application/json' -H 'Content-Type: application/json' -i http://localhost:8080/estabelecimento/1 -u fcadm:fcamara  (OK)

    PARA SALVAR/ATUALIZAR, UTILIZAR O MODELO JSON:

    {
        "nome": "",
        "endereco": "",
        "numeroLogradouro": ,
        "cnpj": "",   <- SEM MASCARA CNPJ
        "codigoDDD": ,
        "numeroTelefone": ,
        "quantidadeVagaMoto": ,
        "quantidadeVagaCarro":
    }

    curl -X POST -H 'Content-Type: application/json' -H 'Accept: application/json' -i http://localhost:8080/estabelecimento -u fcuser:fcamara 
    --data '{"nome": "Empresa LTDA", "endereco": "Av: Floriano Peixoto", "numeroLogradouro": 163, "cnpj": "11111111111111","codigoDDD": 13,"numeroTelefone": 35672310,"quantidadeVagaMoto": 10,"quantidadeVagaCarro": 20}'

    curl -X PUT -H 'Content-Type: application/json' -H 'Accept: application/json' -i http://localhost:8080/estabelecimento/1 -u fcuser:fcamara 
    --data '{"nome":"Empresa Fial 3 LTDA","endereco":"Av: Floriano Peixoto","numeroLogradouro":163,"cnpj":"56803967000177","codigoDDD":13,"numeroTelefone":35672310,"quantidadeVagaMoto":210,"quantidadeVagaCarro":20}'


ENTRADASAIDAVEICULO:
====================    
   
    curl -X GET -H 'Accept: application/json' -H 'Content-Type: application/json' -i http://localhost:8080/entradaSaidaVeiculos -u fcadm:fcamara      (OU)
    curl -X GET -H 'Accept: application/json' -H 'Content-Type: application/json' -i http://localhost:8080/entradaSaidaVeiculos -u fcuser:fcamara

    Resposta em XML:
    curl -X GET -H 'Accept: application/xml' -H 'Content-Type: application/json' -i http://localhost:8080/entradaSaidaVeiculos -u fcadm:fcamara

    curl -X DELETE -H 'Accept: application/json' -H 'Content-Type: application/json' -i http://localhost:8080/entradaSaidaVeiculo/1 -u fcuser:fcamara (ERROR)
    curl -X DELETE -H 'Accept: application/json' -H 'Content-Type: application/json' -i http://localhost:8080/entradaSaidaVeiculo/1 -u fcadm:fcamara  (OK)

    PARA SALVAR/ATUALIZAR, UTILIZAR O MODELO JSON:

     {
    	"dataHoraEntrada": "",  <- MASCARA YYYY-MM-DD HH:MM
    	"dataHoraSaida": "",    <- MASCARA YYYY-MM-DD HH:MM
    	"estabelecimento": { "id" : } <- ID ESTABELECIMENTO CADASTRADO. CONFERIR CONSULTANDO VIA GET O ESTABELECIMENTO CONFORME INSTRUCAO ANTERIOR
    	"veiculo": {"id": }           <- ID VEICULO CADASTRADO. CONFERIR CONSULTANDO VIA GET O VEICULO CONFORME INSTRUCAO ANTERIOR
	}

    curl -X POST -H 'Content-Type: application/json' -H 'Accept: application/json' -i http://localhost:8080/entradaSaidaVeiculo -u fcuser:fcamara 
    --data '{"dataHoraEntrada": "2019-10-08 20:12","dataHoraSaida": "","estabelecimento": { "id" : 1}, "veiculo": {"id": }}'

    curl -X PUT -H 'Content-Type: application/json' -H 'Accept: application/json' -i http://localhost:8080/entradaSaidaVeiculo/1 -u fcuser:fcamara 
    --data '{"dataHoraEntrada": "2019-10-08 11:00","dataHoraSaida": "2019-10-08 13:00","estabelecimento": { "id" : 1}, "veiculo": {"id": 1}}'
