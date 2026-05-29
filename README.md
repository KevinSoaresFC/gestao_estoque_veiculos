<h1 align="center">🚗 Sistema de Gestão de Estoque Automotivo</h1>

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL">
</p>

---

## 📝 Visão Geral do Projeto
Este sistema é uma solução de software voltada para a gestão inteligente de pátios automotivos. Desenvolvido no contexto acadêmico do curso de **Análise e Desenvolvimento de Sistemas (UniFECAF)**, o projeto aplica os pilares da **Programação Orientada a Objetos (POO)** para resolver o problema de controle de inventário manual, comum em concessionárias de médio e pequeno porte.

O software centraliza todas as informações pertinentes a um veículo, garantindo a integridade dos dados e permitindo uma consulta ágil através de filtros inteligentes.

---

## 🏗️ Arquitetura e Estrutura de Software
O projeto foi estruturado seguindo o padrão **MVC (Model-View-Controller)**, garantindo uma separação clara de responsabilidades:

### 1. Camada Model (Entidades)
Representa o domínio de negócio do sistema. As classes foram desenhadas com foco em **encapsulamento**, onde todos os atributos são privados e protegidos por métodos *Getters* e *Setters*. 
* **Veiculo:** Entidade principal com atributos de marca, modelo, ano, preço, quilometragem e status.
* **Marca/Modelo:** Classes de apoio que garantem a normalização dos dados e evitam a redundância de informações através de chaves estrangeiras no banco de dados.

### 2. Camada Controller (Lógica de Negócio)
Responsável por processar a lógica do sistema e gerenciar a persistência de dados. A comunicação com o banco de dados **MySQL** é realizada através da API **JDBC (Java Database Connectivity)**. O uso da interface `Statement` permite a execução de queries complexas, como filtros compostos e a gestão automática de registros vinculados.

### 3. Camada View (Interface)
Consiste em um terminal de interface intuitiva. Foi projetado para proporcionar uma experiência de navegação rápida para o usuário final, utilizando menus interativos para as operações de CRUD (Create, Read, Update, Delete).

---

## 🧠 Aplicação dos Conceitos de POO
O sistema foi concebido para ser uma prova de conceito de boas práticas de desenvolvimento:

*   **Encapsulamento:** Proteção dos dados sensíveis do veículo, garantindo que alterações como a atualização de preço passem por validações de lógica antes de serem persistidas no banco.
*   **Abstração:** O usuário do sistema não precisa conhecer a estrutura das tabelas do banco de dados para realizar operações de cadastro; ele interage apenas com os métodos de alto nível disponibilizados pela classe `VeiculoController`.
*   **Integridade Relacional:** Através da modelagem de classes, o sistema reflete uma relação de agregação e composição, onde um `Veiculo` depende intrinsecamente de um `Modelo`, que por sua vez depende de uma `Marca`.

---

## 🎯 Requisitos de Negócio Atendidos
*   **Gestão de Estoque:** Controle total sobre a disponibilidade de veículos, com status atualizáveis.
*   **Consulta Avançada:** Implementação de filtros dinâmicos que permitem localizar veículos com base em múltiplos critérios, otimizando o tempo de atendimento em balcão.
*   **Consistência de Dados:** Uso de tipagem forte e validações via Java para evitar a inserção de dados inconsistentes ou órfãos no banco.

