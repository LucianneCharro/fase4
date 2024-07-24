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
- **AWS ECS**: Para deploy e gerenciamento dos serviços em nuvem.
- **JavaDoc**: Para documentação do código.
- **Postman**: Para testes e documentação da API.
- **Lombok**: Para redução de código boilerplate.
- **ModelMapper**: Para mapeamento entre entidades e DTOs.
- **IDE**: IntelliJ IDEA 2024.1, recomendado para desenvolvimento.

## Desafios e Soluções
Durante o desenvolvimento, enfrentamos desafios como a configuração do ambiente de desenvolvimento, integração entre os serviços, implementação de testes automatizados, e a criação de pipelines de CI/CD. As soluções foram encontradas por meio de pesquisa, documentação oficial e comunidade de desenvolvedores.

## Estrutura do Projeto
O projeto é organizado em módulos como `Moradores`, `Encomendas`, `Notificações`, e `Autenticação`, cada um encapsulando uma parte específica da lógica de negócio e funcionalidades do sistema.

## Execução e Testes
Para executar o sistema localmente, siga os passos de clonagem do repositório, importação como projeto Maven, execução via Docker Compose para os serviços necessários, e inicialização da aplicação principal através da IDE. A aplicação está configurada para rodar na porta padrão do Spring Boot (8080), a menos que especificado de outra forma no `application.properties`.

### Módulos do Sistema

#### Moradores
Gerencia o cadastro e informações dos moradores do condomínio.

#### Encomendas
Permite o registro de encomendas recebidas, associando-as aos moradores correspondentes.

#### Notificações
Envia notificações aos moradores sobre a chegada de encomendas e permite a confirmação de recebimento.

#### Autenticação
Gerencia o acesso ao sistema, garantindo que apenas usuários autorizados possam executar determinadas operações.

## Como Executar o Projeto

1. **Clone o Repositório:**
   git clone https://github.com/LucianneCharro/gerenciamento-encomendas.git

2. **Importe o Projeto na sua IDE:**