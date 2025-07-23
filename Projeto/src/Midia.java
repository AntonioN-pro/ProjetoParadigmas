// Classe abstrata que serve como modelo base para diferentes tipos de mídia (ex: Jogo, Filme, Série)
// Aplica o conceito de herança — requisito obrigatório da avaliação ✅
public abstract class Midia {
    protected String nome;
    protected String genero;
    protected int ano;

    // Construtor comum a todas as mídias
    public Midia(String nome, String genero, int ano) {
        this.nome = nome;
        this.genero = genero;
        this.ano = ano;
    }

    // Metodo abstrato que será implementado de forma diferente em cada subclasse
    // Representa o conceito de polimorfismo — outro requisito obrigatório ✅
    public abstract String exibirDetalhes();

    // Getters básicos usados pelas subclasses ou pelo programa principal
    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }

    public int getAno() {
        return ano;
    }
}
