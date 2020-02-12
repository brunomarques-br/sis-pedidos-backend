# SisPedidos
## Springboot Backend, Mysql, Java 8, Maven 3.5

# Objetivo
* Um sistema de pedidos que gerencia clientes, produtos, pedidos e pagamentos.

# Objetivo Técnico
* Todo o backend do projeto foi desenvolvido em SpringBoot REST.

# Arquitetura
* Controladores REST (resources), responsáveis por receber requisições e entregar dados JSON para a aplicação do cliente.
* Camada de Serviço (services), responsável pelo processamento da requisição do controlador.
* Camada de acesso aos dados (repositories), interfaces genéricas que são utilizadas para persistência na base de dados (dominio).
* Modelo de dados (dominio), são as entidades modeladas no banco de dados.  

# Tecnologias
* JavaEE: 8
* Spring Boot: 2.0.3.RELEASE
* Base de dados: Mysql
* Encapsulamento dos modelos de dados utilizando Lombok.
* Maven: 3.5
