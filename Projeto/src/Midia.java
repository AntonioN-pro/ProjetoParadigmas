
public abstract class Midia {
    protected String nome;
    protected String genero;
    protected int ano;

    public Midia(String nome, String genero, int ano) {
        this.nome = nome;
        this.genero = genero;
        this.ano = ano;
    }

    public abstract String exibirDetalhes();

}
