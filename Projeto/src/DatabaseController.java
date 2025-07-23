import java.sql.*;
import java.util.ArrayList;

public class DatabaseController {
    // Caminho para o banco SQLite — usado para cumprir o requisito de armazenamento permanente
    private static final String DB_URL = "jdbc:sqlite:TESTE.db";

    // Bloco estático: carrega o driver JDBC
    // Necessário para conectar com o SQLite — requisito técnico para persistência
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado!");
        }
    }

    // Metodo que cria a tabela no banco caso ainda não exista
    // Aqui aplicamos o requisito de armazenamento permanente
    public static void inicializarBanco() {
        String sql = "CREATE TABLE IF NOT EXISTS jogos ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nome TEXT NOT NULL, "
                + "genero TEXT, "
                + "ano INTEGER, "
                + "plataforma TEXT, "
                + "nota INTEGER, "
                + "comentario TEXT"
                + ");";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql); // Criação da tabela
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CREATE — insere novo registro no banco de dados
    // Atende ao requisito: "Cadastro de registros"
    public static void inserirJogo(Jogo jogo) {
        String sql = "INSERT INTO jogos (nome, genero, ano, plataforma, nota, comentario) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Preenche os parâmetros com os dados do objeto
            pstmt.setString(1, jogo.getNome());
            pstmt.setString(2, jogo.getGenero());
            pstmt.setInt(3, jogo.getAno());
            pstmt.setString(4, jogo.getPlataforma());
            pstmt.setInt(5, jogo.getNota());
            pstmt.setString(6, jogo.getComentario());

            pstmt.executeUpdate(); // Executa o INSERT
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ — lista todos os jogos do banco
    // Atende ao requisito: "Listagem de registros"
    public static ArrayList<Jogo> listarJogos() {
        ArrayList<Jogo> lista = new ArrayList<>();
        String sql = "SELECT * FROM jogos";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Construtor com ID → suporte completo ao CRUD
                Jogo j = new Jogo(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("genero"),
                        rs.getInt("ano"),
                        rs.getInt("nota"),
                        rs.getString("comentario"),
                        rs.getString("plataforma")
                );
                lista.add(j);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista; // Retorna todos os registros existentes
    }

    // DELETE — exclui um jogo com base no ID
    // Atende ao requisito: "Exclusão de registros"
    public static void excluirJogo(int id) {
        String sql = "DELETE FROM jogos WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate(); // Executa o DELETE
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE — atualiza dados de um jogo existente
    // Atende ao requisito: "Alteração de registros"
    public static void atualizarJogo(Jogo jogo) {
        String sql = "UPDATE jogos SET nome = ?, genero = ?, ano = ?, plataforma = ?, nota = ?, comentario = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, jogo.getNome());
            pstmt.setString(2, jogo.getGenero());
            pstmt.setInt(3, jogo.getAno());
            pstmt.setString(4, jogo.getPlataforma());
            pstmt.setInt(5, jogo.getNota());
            pstmt.setString(6, jogo.getComentario());
            pstmt.setInt(7, jogo.getId());

            pstmt.executeUpdate(); // Executa o UPDATE
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
