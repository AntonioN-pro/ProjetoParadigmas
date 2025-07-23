public class Jogo extends Midia {
    private int id;  // 🔴 Adicionado
    private int nota;
    private String comentario;
    private String plataforma;

    // Construtor para criar novo jogo (sem id, quando cadastrando pela primeira vez)
    public Jogo(String nome, String genero, int ano, int nota, String comentario, String plataforma) {
        super(nome, genero, ano);
        this.nota = nota;
        this.comentario = comentario;
        this.plataforma = plataforma;
    }

    // Construtor com id (usado quando puxando do banco de dados)
    public Jogo(int id, String nome, String genero, int ano, int nota, String comentario, String plataforma) {
        super(nome, genero, ano);
        this.id = id;
        this.nota = nota;
        this.comentario = comentario;
        this.plataforma = plataforma;
    }

    @Override
    public String exibirDetalhes() {
        return nome + " (" + ano + ") - " + genero + " - " + plataforma + " - Nota: " + nota;
    }

    // Getters
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
