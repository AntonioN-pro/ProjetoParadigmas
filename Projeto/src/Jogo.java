// Esta classe representa um jogo e herda da classe base Midia
// Aplica os conceitos de herança e polimorfismo exigidos na avaliação ✅
public class Jogo extends Midia {
    private int id;               // Identificador único, vindo do banco (necessário para UPDATE e DELETE)
    private int nota;             // Avaliação do jogo (0 a 10)
    private String comentario;    // Observações do usuário
    private String plataforma;    // Ex: PC, Xbox, PS5, etc.

    // Construtor usado quando o jogo vem do banco de dados e já possui ID
    // Reforça o controle sobre a identificação dos registros salvos ✅
    public Jogo(int id, String nome, String genero, int ano, int nota, String comentario, String plataforma) {
        super(nome, genero, ano); // Chamada à superclasse Midia — aplica o conceito de herança ✅
        this.id = id;
        this.nota = nota;
        this.comentario = comentario;
        this.plataforma = plataforma;
    }

    // Construtor usado ao criar um novo jogo antes de salvar no banco (sem ID ainda)
    public Jogo(String nome, String genero, int ano, int nota, String comentario, String plataforma) {
        super(nome, genero, ano); // Herança
        this.nota = nota;
        this.comentario = comentario;
        this.plataforma = plataforma;
    }

    // Sobrescrita de metodo abstrato de Midia
    // Aplica o conceito de polimorfismo — cada tipo de mídia pode ter sua própria implementação ✅
    @Override
    public String exibirDetalhes() {
        return nome + " (" + ano + ") - " + genero + " - " + plataforma + " - Nota: " + nota;
    }

    // Getters
    // Utilizados para preencher tabelas, formulários e salvar no banco
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }

    public int getAno() {
        return ano;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public int getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }
}
