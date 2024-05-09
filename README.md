# ms-orders

Este projeto é uma API desenvolvida com Java e Spring Boot, designada para Gerenciamento de Pedidos com integração com a API ms-customers 

## Tecnologias Utilizadas

Este projeto foi desenvolvido com as seguintes tecnologias e bibliotecas:

- **Java 17**: Versão do Java utilizada no projeto.
- **Spring Boot**: Framework principal para a criação de aplicações Spring.
- **Spring Boot DevTools**: Para desenvolvimento rápido com reinício automático.
- **Spring Boot Starter Security**: Para autenticação e segurança da aplicação.
- **Spring Boot Starter Web**: Para construção de aplicações web, usando o Spring MVC.
- **Spring Boot Starter Data Jpa**: Para auxílio na persistência no Banco de Dados.
- **Spring Boot Validation**: Para validação de campos.
- **Springdoc OpenAPI**: Para documentação da API REST com Swagger.
- **Lombok**: Para reduzir o código boilerplate, como getters e setters.
- **MySQL**: banco de dados relacional.
- **Render**: Deploy da aplicação Java e do banco de dados Postgres em plataforma grátis
- **Docker**: utilização de container para criação de ambiente de execução local

## Documentação
A Collection utilizada no Postman está disponível na URL:
https://github.com/MendesMoe/java-ms-orders/blob/main/src/main/resources/PosTech_TC4.postman_collection.json

A documentação detalhada da API está acessível na URL:
http://localhost:8082/swagger-ui/index.html

## Portas configuradas

App: http://localhost:8082
MySQL: jdbc:mysql://localhost:3307/orderdb
