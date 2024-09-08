# Sistema de Gerenciamento de Encomendas

O projeto Sistema de Gerenciamento de Encomendas é uma aplicação desenvolvida para facilitar o controle e a gestão de encomendas em condomínios, oferecendo funcionalidades como cadastro de moradores, registro de encomendas, notificações de chegada, e confirmação de recebimento. Utiliza uma arquitetura baseada em microserviços, seguindo as melhores práticas de desenvolvimento de software para garantir escalabilidade, manutenibilidade e facilidade de evolução.

## Tecnologias Adotadas

- **Java 17**: Linguagem de programação principal.
- **Spring Boot**: Para a criação de microserviços de forma rápida e eficiente.
- **Spring Data JPA**: Para acesso e manipulação de dados no banco de dados.
- **Maven**: Para gerenciamento de dependências e automação de builds.
- **Docker**: Para containerização e simplificação da execução em diferentes ambientes.
- **MySQL**: Como sistema de gerenciamento de banco de dados relacional.
- **JUnit e Mockito**: Para realização de testes unitários e de mock.
- **Spring Boot Test**: Para testes de integração.
- **GitHub Actions**: Para integração contínua (CI), automatizando testes e builds.
- **Swagger**: Para documentação do código.
- **Postman**: Para testes e documentação da API.
- **Lombok**: Para redução de código boilerplate.
- **ModelMapper**: Para mapeamento entre entidades e DTOs.
- **IDE**: IntelliJ IDEA 2024.1, recomendado para desenvolvimento.

## Desafios e Soluções
Durante o desenvolvimento, enfrentei desafios como a configuração do Docker,  configuração do envio de e-mails e implementação de testes automatizados. As soluções foram encontradas por meio de pesquisa, documentação oficial e comunidade de desenvolvedores.

## Estrutura do Projeto
O projeto é organizado em módulos como `Moradores`, `Encomendas` e `Notificações`, cada um encapsulando uma parte específica da lógica de negócio e funcionalidades do sistema.

## Execução e Testes
Para executar o sistema localmente, siga os passos de clonagem do repositório, importação como projeto Maven, execução via Docker Compose para os serviços necessários, e inicialização da aplicação principal através da IDE. A aplicação está configurada para rodar na porta padrão do Spring Boot (8080), a menos que especificado de outra forma no `application.properties`.

### Módulos do Sistema

#### Moradores
Gerencia o cadastro e informações dos moradores do condomínio.

curl --request GET \
--url http://localhost:8081/cadastrar-moradores \
--header 'User-Agent: insomnia/9.3.2'

curl --request GET \
--url http://localhost:8081/cadastrar-moradores/1 \
--header 'User-Agent: insomnia/9.3.2'
curl --request POST \
--url http://localhost:8081/cadastrar-moradores \
--header 'Content-Type: application/json' \
--header 'User-Agent: insomnia/9.3.2' \
--data '{
"telefone": "(11) 99611-7525",
"apartamento": "208"
}'

curl --request PUT \
--url http://localhost:8081/cadastrar-moradores/1 \
--header 'Content-Type: application/json' \
--header 'User-Agent: insomnia/9.3.2' \
--data '{
"telefone": "(11) 99611-7525",
"apartamento": "208"
}'

curl --request DELETE \
--url http://localhost:8081/cadastrar-moradores/2 \
--header 'Content-Type: application/json' \
--header 'User-Agent: insomnia/9.3.2'

#### Encomendas
Permite o registro de encomendas recebidas, associando-as aos moradores correspondentes.

curl --request GET \
--url http://localhost:8081/encomendas-portaria/processar \
--header 'User-Agent: insomnia/9.3.2'

curl --request POST \
--url http://localhost:8081/encomendas-portaria/retirar-morador/1 \
--header 'Content-Type: application/json' \
--header 'User-Agent: insomnia/9.3.2'

curl --request POST \
--url http://localhost:8081/encomendas-portaria/receber \
--header 'Content-Type: application/json' \
--header 'User-Agent: insomnia/9.3.2' \
--data '{
"nomeMorador": "Lucas",
"numeroApartamento": "8",
"descricao": "Blusa"
}'

#### Notificações
Envia notificações aos moradores sobre a chegada de encomendas e permite a confirmação de recebimento.

curl --request POST \
--url http://localhost:8081/notificacoes/confirmar/2 \
--header 'Content-Type: application/json' \
--header 'User-Agent: insomnia/9.3.2'

## Segurança

A configuração de segurança da aplicação foi feita utilizando Spring Security. Abaixo estão os principais pontos da configuração:  

- **Desabilitar CSRF:** A proteção CSRF foi desabilitada.
- **Configurar Autorização:** Todas as URLs estão permitidas sem exigir autenticação.
- **Gerenciamento de Sessão:** A política de criação de sessão está configurada como STATELESS.
- **Adicionar Filtro JWT:** O filtro JWT foi adicionado antes do filtro de autenticação de nome de usuário e senha.
- **Configurar CORS:** Configuração de CORS para permitir requisições de diferentes origens.

## MySQL

A aplicação utiliza MySQL como sistema de gerenciamento de banco de dados relacional. Abaixo estão as configurações necessárias:  
- **Configuração do banco de dados no application.properties:**  spring.datasource.url=jdbc:mysql://db:3306/myDB spring.datasource.username=root spring.datasource.password=yourpassword spring.jpa.hibernate.ddl-auto=update spring.jpa.show-sql=true

## Como Executar o Projeto

1. **Clone o Repositório:**
   git clone https://github.com/LucianneCharro/fase4/tree/fiap/fase4-substitutiva

3. **Importe o Projeto na sua IDE:**

3. **Executar o Docker:**
   Executar o Docker Desktop localmente
   docker-compose up -d
   docker ps

5. **Acessar a aplicação:**
   a aplicação estará disponível em http://localhost:8081/.

6. **Documentação da API:**
    Acesse a documentação da API em http://localhost:8081/swagger-ui/index.html.

7. **Banco de Dados:**
   O banco de dados MySQL estará disponível em http://localhost:3306/.

8. **Parar os contêineres:**
    docker-compose down

