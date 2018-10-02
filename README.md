# Brewery

O sistema realiza um CRUD para cadastro de tipos de cerveja e sua temperatura miníma e máxima ideal para consumo.
Retorna a melhor cerveja com base no tipo informado junto com uma playlist do spotfy com o nome da cerveja.

Tecnologias:
- Java 8
- Gradle
- Spring
- Docker

Pré requisitos
- Java - https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html
- Docker - https://docs.docker.com/install/

- Json de cadastro exemplo
{
    "beerStyle" : "Weissbier",
    "temperatureMin" : -1,
    "temperatureMax": 3
 }
  
Funcionalidades

| Verbo     | Funcionalidade    | Descrição |
| --------|---------|-------|
| POST  | http://localhost:8082/beer/beers  | Cria um novo tipo de cerveja.|
| PUT  | http://localhost:8082/beer/beers  | Recebe uma lista de cevejas para criação |
| Delete | http://localhost:8082/beer/beers/{id} | Remove a cerveja pelo id |
| Get | http://localhost:8082/beer/beers | Retorna todas as cervejas cadastradas |
| Get | http://localhost:8082/beer/beers/?page=1&size=3 | Retorna a cerveja paginada e ordenada pelo nome, recebe dois parametro page e size |
| Get | http://localhost:8082/best-beers/{temperature} | Retorna a melhor cerveja e uma lista do spotfy associada ao nome da cerveja ocm base na temperatura informada |


Inicando a aplicação

- Clone o repositório em sua maquina.
- Para rodar pela IDE basta realizar o run na classe BreweryApplication
- A Aplicação esta rodando na porta 8082

Build aplication em Docker
- Dentro da pasta raiz do projeto onde contenha o arquivo Dockerfile e build.gradle execute o seguinte comando: ./gradlew clean buil
este comoando realizara o build da sua aplicação gerando um jar e a imagem do mesmo.

Iniciando em docker

- Com sua imagem criada basta executar o seguinte comando
- Pronto vc pode consumir os recursos da aplicação através das funcionaliades listadas acima.
- Utilize o postman - https://www.getpostman.com/
- Dentro da pasta resources/sample existes alguns modelos de json previamente configurados que podem ser utilizados para inserir um novo tipo de cerveja ou uma lista de tipos decerveja.

Bonus
- Se quiser compilar sua imagem em docker e ainda inicia-la de modo automatico execute o seguinte comando: ./gradlew clean build-start-docker
- Pronto sua aplicação esta no ar e rodando na porta 8082, fique de olho no pré requisito, pois você precisa ter o docker instalado na sua maquina.