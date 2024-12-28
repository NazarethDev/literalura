# LiterAlura - challenge Alura

Aplicação elaborada em participação para o projeto ONE (Oracle Next Education) 
com a Alura, fase de especialização em backend com Java e Spring Boot
___
 
## Sobre o projeto 
Este projeto é uma ferramenta de busca de livros na API [Guntedex](https://gutendex.com/) e armazena os resultados das buscas em um banco de 
dados de acordo com a modelagem dos dados especificados pela aplicação e fornecidos pela API. 

A aplicação permite que os usuários:

- Realizem buscas de novos livros através do providos pela [Guntedex](https://gutendex.com/);

- Com base nos livros procurados, o banco de dados é alimentado,
e mais opções de persistência de dados se tornam possíveis, como:
  - Apresentar os livros salvos no banco de dados;
  - Listar os autores salvos no banco de dados;
  - Selecionar uma quantidade mínima de downloads para apresentar livros do banco de dados;
  - Listar os autores vivos em determinado ano.

___
## Tecnologias utilizadas

- Java, versão 17
- Spring Boot, versão 3.4.0
- Jackson, versão 2.18.0
- PostgreSQL, versão 17.0 (Banco de dados)
- Gutendex.com API 

___

## Como inicializar em seu dispositivo

Para Utilizar o Literalura em seu próprio device,
siga os seguintes passos:
1. faça o download da aplicação usando o comando _Git clone_
através do terminal na pasta onde melhor preferir em seu 
computador usando este o link https://github.com/NazarethDev/literalura.git
(pertencente ao projeto no Github).
2. Abra o arquivo na IDE de sua preferência com suporte a Java em sua versão 17;
3. **Edite as variáveis de ambiente da aplicação para as suas próprias.**
Elas se encontram no arquivo _literalura/src/main/resources/application.properties_;
nas linhas 3 e 4, e devem corresponder ao usuário e senha do banco de dados PostgreSQL respectivamente;
4. Tenha em mente que na linha 2, a porta em que estiver utilizando o PostreSQL 
(neste arquivo definida como "localhost:5432") e o nome do banco de dados, o qual precisa ser criando 
manualmente por você no PostreSQL (neste código definido como "literalura") precisam ser os mesmo que existem
em sem computador, bem como as demais variáveis de ambiente. Tenha ciência de que os passos 3 e 4 indicados são essenciais para 
o funcionamento e inicialização da aplicação em seu dispositivo, uma vez que dizem respeito a
conexão do banco de dados com a aplicação;
5. Execute a aplicação.

## Agradecimentos

Muito obrigado a todas as comunidades de desenvolvedores e pessoas 
que disponibilizaram tanto de seu tempo, talento e tecnologias desenvolvidas
para a elaboração desse projeto.

Assim como a eles, agradeço a todos que colaboraram de alguma maneira para que o programa
Oracle Next Education (ONE) fosse possível, está sendo uma oportunidade preciosa e crucial para meu início e crescimento
na tecnologia e desenvolvimento de sistema.

## Contato do desenvolvedor
- email: lorran.nazareth@gmail.com
- LinkedIn: https://www.linkedin.com/in/lorrannazareth/