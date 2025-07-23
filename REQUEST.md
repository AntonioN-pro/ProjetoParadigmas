# Projeto: Biblioteca de Jogos
## Disciplina: Paradigmas de Programação - UFMA
### Professora: VANDECIA REJANE MONTEIRO FERNANDES

### Objetivo
Desenvolver uma aplicação em Java com interface gráfica (`javax.swing`) que permita ao usuário **cadastrar, listar, editar e excluir jogos**, atribuindo **notas e comentários**, aplicando os conceitos de **herança**, **polimorfismo**, **collections** e **armazenamento permanente**.

---

##  Requisitos e Como Foram Atendidos

### 1. Interface gráfica com Swing
**Local:** `TelaPrincipal.java`
- Uso de `JFrame`, `JTextField`, `JTextArea`, `JButton`, `JTable` e `JScrollPane`.
- Interface com formulário de entrada e exibição em tabela.
- Botões com ações de cadastrar, editar e excluir.

### 2. Cadastro, alteração e exclusão de registros (CRUD)
**Local:** `TelaPrincipal.java` + `DatabaseController.java`
- Método `cadastrarJogo()` para inserção.
- Método `editarJogo()` com preenchimento automático ao selecionar item da tabela.
- Método `excluirJogo()` com confirmação.
- Tudo persistido em banco SQLite via `DatabaseController`.

### 3. Herança e Polimorfismo
**Local:** `Midia.java` (classe abstrata) + `Jogo.java` (subclasse)
- `Midia` define atributos comuns e método abstrato `exibirDetalhes()`.
- `Jogo` herda de `Midia` e sobrescreve o método com comportamento específico.
- Uso real de polimorfismo, não se limitando ao `toString()`.

### 4. Uso de Collections
**Local:** `TelaPrincipal.java`
- Uso de `List<Jogo>` no método `carregarJogos()` para preencher a `JTable` dinamicamente.
- Lista obtida via `DatabaseController.listarJogos()`.

### 5. Armazenamento permanente
**Local:** `DatabaseController.java`
- Persistência com SQLite usando JDBC.
- Tabela `jogos` é criada se não existir (`inicializarBanco()`).
- Dados são salvos e recuperados automaticamente.

---

## 📂 Estrutura do Projeto

📁 Projeto  
├── 📁 src  
│   ├── Midia.java               (*Classe abstrata — classe mãe*)  
│   ├── Jogo.java                (*Classe principal — herda de Midia.java*)  
│   ├── TelaPrincipal.java       (*Classe executável — interface gráfica*)  
│   └── DatabaseController.java  (*Controle da base de dados — SQLite*)  
└── TESTE.db                     (*Banco de dados SQLite — criado automaticamente*)



---

## Equipe

- Antonio Neto de Moura Melo - 2022003488
- Carla Sofia Santos Ribeiro - 2022014015
- Marcos Davi Taveira de Sousa - 2022007431

---

### Entrega Feita: (23/07/2025)

---
