# Brewery

O sistema realiza um CRUD para cadastro de tipos de cerveja e sua temperatura miníma e máxima ideal para consumo.
Retorna a melhor cerveja com base no tipo informado junto com uma playlist do spotfy com o nome da cerveja.

Observações
- Para o cuncionamento da aplicação é necessario possui o java e o gradle instalados, se for compilar imagem e subir em ambiente docker é necessario o docker esta instalado.

Tecnologias:
- Java 8(https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)
- Gradle(https://gradle.org/install/)
- Docker(https://docs.docker.com/install/)
- Spring

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
- Na raiz do projeto ou pelo terminal de sua IDE execute o comando ./gradlew clean build buildDocker 
- Verifique a sua imagem gerada através do comando docker images deve aparecer o repositorio e a tag como descrito abaixo

| REPOSITORY     | TAG    | IMAGE | 
| --------|---------|-------|
| br.com.rbs/brewery  | 0.0.1-SNAPSHOT | d5744c9d24a5 |


Iniciando em docker

- Com sua imagem criada basta executar o seguinte comando: docker run -p8082:8082 br.com.rbs/brewery:0.0.1-SNAPSHOT
- Pronto vc pode utilizar os recursos da aplicação através das funcionaliades listadas acima.
- Utilize o postman - https://www.getpostman.com/
- Dentro da pasta resources/sample existes alguns modelos de json previamente configurados que podem ser utilizados para inserir um novo tipo de cerveja ou uma lista de tipos decerveja.

Pendências:
   - Integração com spotfy

