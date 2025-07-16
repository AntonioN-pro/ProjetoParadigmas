# Projeto: Biblioteca de Jogos 🎮
## Disciplina: Paradigmas de Programação - UFMA

### Objetivo
Desenvolver uma aplicação em Java com interface gráfica (`javax.swing`) que permita ao usuário cadastrar, listar, editar e excluir jogos que jogou, atribuindo notas e comentários.

---

## ✅ Requisitos e Como Foram Atendidos

### 1. Interface gráfica com Swing
- Uso de `JFrame`, `JTextField`, `JTextArea`, `JComboBox`, `JButton` para a criação da interface.
- Tela com:
    - Cadastro de novo jogo.
    - Listagem de jogos cadastrados (`JTable` ou `JList`).
    - Botões para editar e excluir jogos.

### 2. Cadastro, alteração e exclusão de registros
- Cada jogo possui: `nome`, `gênero`, `nota`, `comentário`, `plataforma`.
- Implementado através de botões com `ActionListener`:
    - **Cadastrar**: Adiciona novo jogo à lista.
    - **Editar**: Atualiza o jogo selecionado.
    - **Excluir**: Remove o jogo selecionado.

### 3. Uso de herança e polimorfismo
- Classe base: `Midia` com atributos: `nome`, `ano`, `gênero`.
- Subclasse: `Jogo` que herda de `Midia` e adiciona `nota`, `comentário`, `plataforma`.
- Uso de polimorfismo:
    - Lista de `Midia` com objetos do tipo `Jogo` (possibilita extensão para `Filme`, `Série`, etc).
    - Método `exibirDetalhes()` sobrescrito em cada tipo de mídia.

### 4. Uso de Collections
- Uso de `ArrayList<Midia>` para armazenar os jogos.
- A lista é usada como fonte de dados para a `JTable`.
- Pode ser estendida com `HashMap` para mapear por ID.

### 5. Armazenamento permanente
- Opção 1: Uso de `FileWriter` / `BufferedReader` para salvar os dados em `.txt`.
- Opção 2: Uso de `ObjectOutputStream` / `ObjectInputStream` para salvar objetos serializados.
- A lista de jogos é salva e carregada automaticamente ao iniciar o programa.

---

## 🧩 Estrutura Sugerida de Classes

```java
public abstract class Midia {
    protected String nome;
    protected String genero;
    protected int ano;

    public abstract String exibirDetalhes();
}
```

```java
public class Jogo extends Midia {
    private int nota;
    private String comentario;
    private String plataforma;

    @Override
    public String exibirDetalhes() {
        return nome + " (" + ano + ") - " + genero + " - Nota: " + nota;
    }
}
```

---

## 📂 Estrutura do Projeto

```
/src
  ├── model/         # Classes Midia e Jogo
  ├── view/          # Interfaces gráficas (JFrame)
  ├── controller/    # Controle da lógica e armazenamento
  └── data/          # Arquivos .txt ou .dat
```

---

## 👥 Equipe
- Nome 1 - Matrícula
- Nome 2 - Matrícula
- Nome 3 - Matrícula

---

## 📅 Datas
- Entrega: 23/07/2025 via SIGAA
- Apresentação: 25/07/2025 (obrigatória para pontuação)

---

⚠️ O projeto não pode ser feito com NetBeans.