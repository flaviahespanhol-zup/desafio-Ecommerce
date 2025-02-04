# E-commerce API

Este projeto é uma API para um sistema de e-commerce, que permite gerenciar clientes, produtos e compras. A API foi desenvolvida utilizando boas práticas de programação, como separação de responsabilidades, tratamento de exceções personalizadas e organização modular do código.

## 🚀 Funcionalidades

- **Gerenciamento de Clientes**:
    - Cadastro, consulta e validação de clientes por CPF.
- **Gerenciamento de Produtos**:
    - Cadastro, consulta e controle de estoque de produtos.
- **Processamento de Compras**:
    - Validação de clientes e produtos.
    - Atualização de estoque após a compra.
    - Retorno de produtos não disponíveis.

## 🛠️ Tecnologias Utilizadas

- **Java**: Linguagem principal do projeto.
- **Spring Boot**: Framework para desenvolvimento da API.
- **Hibernate/JPA**: Para mapeamento objeto-relacional.
- **H2 Database**: Banco de dados em memória para testes.
- **Maven**: Gerenciador de dependências.
- **JUnit**: Para testes unitários.

## ⚙️ Pré-requisitos

Antes de começar, você precisará ter as seguintes ferramentas instaladas em sua máquina:

- **Java 17+**
- **Maven**
- **Git**

## 🚀 Como Executar o Projeto

1. Clone e entre no repositório:
```xml
git clone https://github.com/flaviahespanhol-zup/desafio-Ecommerce.git
        
cd desafio-ecommerce
   ```

2. Compile o projeto:
```xml
mvn clean install
   ```

3. Execute a aplicação:
```xml
mvn spring-boot:run
   ```

## 📖 Exemplos de Uso

1. Consultar Cliente por CPF
   - **Endpoint**: GET /clientes/{cpf}
   - **Exemplo: http://localhost:8080/clientes/12345678900**

Exemplo de Resposta:
```xml
{
  "id": 1,
  "name": "João Silva",
  "cpf": "123.456.789-00",
  "email": "joao.silva@email.com"
}
   ```
<br>

2. Cadastrar um cliente
    - **Endpoint**: POST /clientes
    - **Exemplo: http://localhost:8080/clientes**

Exemplo de Payload:
```xml
{
  "name": "João Silva",
  "cpf": "12345678900",
  "email": "joao.silva@email.com"
}
   ```
<br>

3. Atualizar um cliente
    - **Endpoint**: UPDATE /clientes
    - **Exemplo: http://localhost:8080/clientes/1**

Exemplo de Payload:
```xml
{
  "name": "João Silva Souza",
  "cpf": "12345678900",
  "email": "joao.silva123@email.com"
}
   ```
<br>

4. Realizar Compra
   - **Endpoint**: POST /compras
   - - **Exemplo: http://localhost:8080/compras**

Exemplo de Payload:
```xml
{
  "cpf": "123.456.789-00",
  "products": [
   { "name": "Produto A" },
   { "name": "Produto B" }
  ]
}
   ```
<br>

5. Listar Produtos
- **Endpoint**: GET /produtos
- **Exemplo: http://localhost:8080/produtos**

Exemplo de Resposta:
```xml
[
  {
    "id": 1,
    "name": "camisa preta tamanho M",
    "price": 50,
    "stock": 10
  }
]
   ```
<br>

6. Cadastrar um produto
   - **Endpoint**: POST /produtos
   - **Exemplo: http://localhost:8080/produtos**

Exemplo de Payload:
```xml
{
  "name": "cel",
  "price": 1800,
  "stock": 10
}
   ```

7. Deletar um produto
- **Endpoint**: DELETE /produtos
- **Exemplo: http://localhost:8080/produtos/1**

## 🤝 Contribuindo
Contribuições são sempre bem-vindas! Siga os passos abaixo para contribuir:

1. Faça um fork do projeto.
2. Crie uma branch para sua feature: **git checkout -b nome-sua-feature**
3. Commit suas alterações
4. Envie para o repositório remoto: **git push origin nome-sua-feature**
5. Abra um Pull Request.