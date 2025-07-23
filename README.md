# Avaliador de Jogos - CRUD Java com Swing

UNIVERSIDADE FEDERAL DO MARANHÃO  
Centro de Ciências Exatas e Tecnologia  
Bacharelado Interdisciplinar em Ciência e Tecnologia  
Disciplina: Paradigmas de Programação
---
## 📌 Descrição do Projeto

Aplicação CRUD para avaliação de jogos com interface gráfica em Java Swing, implementando os conceitos de herança, polimorfismo e armazenamento persistente em banco de dados.

---
## 🎮 Componentes Principais

### Arquivos do Projeto:
- **`TelaPrincipal.java`**  
  Classe principal que contém a interface gráfica e a lógica de interação do usuário.

- **`Midia.java`**  
  Classe abstrata que define atributos e métodos comuns para todas as mídias, permitindo herança.

- **`Jogo.java`**  
  Subclasse concreta de `Midia` que implementa características específicas de jogos (plataforma, gênero, avaliação).

- **`DatabaseController.java`**  
  Responsável por todas as operações com o banco de dados SQLite, incluindo CRUD da lista de jogos.

---
## ✅ Funcionalidades Implementadas
- Cadastro, edição e exclusão de avaliações de jogos
- Herança: `Jogo` estende `Midia` (compartilhando atributos básicos)
- Polimorfismo: Métodos com comportamentos específicos em `Jogo`
- Armazenamento persistente em banco de dados SQLite
- Interface gráfica com componentes Swing
---
## 🧠 Conceitos Aplicados
- **Herança**: Hierarquia entre `Midia` (classe abstrata) e `Jogo`
- **Polimorfismo**: Sobrescrita de métodos para comportamentos específicos
- **Persistência**: Armazenamento em banco de dados via `DatabaseController`
- **GUI**: Componentes Swing em `TelaPrincipal`
---
## 👥 Equipe
- **Antonio Neto Moura Melo** - 2022003488
- **Carla Sofia Santos Ribeiro** - 2022014015
- **Marcos Davi Taveira de Sousa** - 2022007431
---
## 📥 Como Executar
1. Compilar os arquivos Java na pasta `/src`
2. Executar `TelaPrincipal.java` (contém o método `main`)
3. O banco de dados será criado automaticamente na primeira execução
---