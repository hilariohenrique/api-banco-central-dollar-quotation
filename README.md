# Dollar Quotation - Testando a Api do Banco Central
Projeto do módulo de testes automatizados - ADA & Americanas Futuro Polo Tech

O projeto foi realizado para testar a integração da API do banco central e o banco de dados da aplicação.

Para rodar a aplicação é necessário passar uma data na url do localHost -> localhost:8080/cotacao-dolar/cotacao/yyyy/mm/dd.

Além disso, é necessário configurar o banco de dados postgres na máquina do usuário e as propiedades do projeto.

É necessário criar dois bancos de dados, um para a aplicação e um para os testes da aplicação.

Como pedido na atividade, o sistema faz a primeira busca de data através da API do banco central, já a partir da 
segunda vez, o sistema verifica se aquela data já havia sido requisitada e traz o valor da cotação do banco de dados.

O sistema também faz a verificação da conexão com a API, caso ocorra algum erro, uma exceção é lançada.

Como é necessário que sempre a aplicação esteja com o banco de dados de testes limpo, ao iniciar os testes, são rodados dois 
Scripts sql que deletam e recriam a tabela no banco. 

O sistema utiliza um vo, "Value Object", que geralmente são imutáveis. Eles são frequentemente usados para representar dados que são lidos de um banco de dados ou de um serviço de API.

Meus agradecimentos ao Guilherme Moreira, minha dupla nesse projeto, que deu muito apoio e suporte nesse módulo.

