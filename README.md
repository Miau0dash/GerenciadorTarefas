# Gerenciador de Tarefas

Um sistema desktop de gerenciamento de tarefas desenvolvido em Java criado como projeto da disciplina de Programação Orientada a Objetos (POO) no Instituto Federal da Bahia (IFBA).

## Descrição do Projeto

A ideia principal é oferecer um programa onde o usuário pode registrar tarefas, visualizar as que já foram cadastradas e também remover aquelas que não são mais necessárias. Dessa forma, o sistema ajuda a organizar o dia a dia de maneira prática.

## Tecnologias e Ferramentas

- **Linguagem:** Java (JDK 17+)
- **Interface Gráfica:** Java Swing
- **Banco de Dados:** MariaDB

- **Padrões de Projeto:**

- MVC (Model-View-Controller)
- DAO (Data Access Object)
- **IDE Recomendada:** NetBeans

 ## Estrutura do projeto

```plaintext
 src/
├── dao/
│   ├── Conexao.java            # Classe que gerencia a conexão com o banco
│   └── TarefaDAO.java        # Classe para manipular tarefas no banco
├── main/
│   └── Main.java                 # Classe principal que inicia a aplicação
│
├── model/
│    └── Tarefa.java              # Classe que representa a tarefa
│
└── view/
       └── TelaPrincipal.java  # Tela principal do sistema
```

## Funcionalidades

### 1. Cadastrar tarefas

- Permite ao usuário digitar uma nova tarefa e salvar no banco de dados.

### 2. Listar tarefas

- Exibe todas as tarefas cadastradas em uma tabela na tela principal.
- Mostra se a tarefa está pendente ou concluída.

### 3. Marcar tarefas como concluídas

- Permite selecionar uma tarefa e marcar como concluída, alterando seu status no banco de dados.
- A tabela reflete o status atualizado.

### 4. Remover tarefas

- Permite selecionar uma tarefa na tabela e removê-la do banco de dados.

## Configuração e Instalação

### Pré-Requisitos

- Java JDK 17  ou superior
- MariaDB
- NetBeans IDE (recomendado)

### Configuração do Banco de Dados

 1. Crie um banco de dados MariaDB
2. Configure o arquivo `src/recurso/configuracao/database.properties`:
```properties
db.url=jdbc:mariadbl://localhost:3306/seu_banco
db.user=seu_usuario
db.senha=sua_senha
db.driver=com.mariadbl.jdbc.Driver
```

### Executando o Projeto

1. Clone o repositório:
```bash
git clone https://github.com/Miau0dash/GerenciadorTarefas.git
```

2. Abra o projeto no NetBeans

3. Configure as dependências do projeto

4. Executar uma classe `src/principal/Principal.java`

## Estrutura do Banco de Dados

### Mesa Principal

- `Tarefas` - Armazena todas as tarefas cadastradas no sistema

## Padrões do Projeto Utilizados

1. **MVC (Modelo-Visualização-Controlador)**

- Modelo: Classes em modelo/
- Visualização: Formulários em view/

2. **DAO (Objeto de Acesso a Dados)**

- Interfaces DAO para cada entidade
- Implementações JDBC
- DAOFactory para instituição

3. **Fábrica**

- DAOFactory para criar instituições DAO
- Isolamento da criação de objetos

## Contribuição

1. Faça um garfo do projeto
2. Crie uma filial para sua característica
3. Faça commit das alterações
4. Faça push para um galho
5. Abra um Pull Request

## Contexto Acadêmico

Projeto desenvolvido para disciplina de POO no IFBA, aplicando conceitos como:

- Encapsulamento
- Herança
- Interfaces
- Tratamento de Exceções
- Persistência de Dados
- Interface Gráfica

---
