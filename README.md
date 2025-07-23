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

# 📦 Como adicionar o `sqlite-jdbc-3.50.2.0` ao Classpath no IntelliJ IDEA

Para que o projeto funcione corretamente com SQLite, é necessário adicionar o driver `sqlite-jdbc` ao classpath. Siga os passos abaixo:

---

## ❓ Por que isso é necessário?

O Java, por padrão, **não possui suporte nativo ao SQLite**. Para que seu projeto consiga:

- Estabelecer conexão com o banco (`DriverManager.getConnection(...)`)
- Executar comandos SQL (`INSERT`, `SELECT`, etc.)
- Manipular arquivos `.db`

… ele precisa de um **driver JDBC externo**, como o `sqlite-jdbc`.

Sem isso, seu código não conseguirá se comunicar com o banco de dados e exibirá erros como:
java.sql.SQLException: No suitable driver found for jdbc:sqlite:...

---

## 📁 1. Verifique se o `.jar` está disponível

O arquivo `sqlite-jdbc-3.50.2.0.jar` já está incluso no repositório, na pasta:
/lib/sqlite-jdbc-3.50.2.0.jar


Caso não esteja, você pode baixá-lo em:  
👉 https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.50.2.0/sqlite-jdbc-3.50.2.0.jar

---

## 🛠️ 2. Adicione o `.jar` ao Classpath no IntelliJ IDEA

1. Abra o IntelliJ IDEA  
2. Vá em **File > Project Structure...** (ou use `Ctrl + Alt + Shift + S`)  
3. Selecione a aba **Modules**  
4. Vá até a aba **Dependencies**  
5. Clique no botão `+` > **JARs or directories...**  
6. Selecione o arquivo `sqlite-jdbc-3.50.2.0.jar` dentro da pasta `/lib`  
7. Marque a opção **"Classes"** e clique em OK  
8. Clique em **Apply** e depois em **OK**

---

## ✅ Pronto!

Seu projeto agora está configurado para se conectar corretamente ao banco de dados SQLite.

> 💡 Em projetos com Maven ou Gradle, o ideal seria adicionar como dependência. Mas para projetos simples ou acadêmicos, o `.jar` local funciona perfeitamente.


