
public class Jogo extends Midia {
    private int nota;
    private String comentario;
    private String plataforma;

    public Jogo(String nome, String genero, int ano, int nota, String comentario, String plataforma) {
        super(nome, genero, ano);
        this.nota = nota;
        this.comentario = comentario;
        this.plataforma = plataforma;
    }

    @Override
    public String exibirDetalhes() {
        return nome + " (" + ano + ") - " + genero + " - " + plataforma + " - Nota: " + nota;
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
