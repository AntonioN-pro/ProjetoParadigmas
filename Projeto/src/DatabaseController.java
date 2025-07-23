import java.sql.*;
import java.util.ArrayList;

public class DatabaseController {
    private static final String DB_URL = "jdbc:sqlite:TESTE.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado!");
        }
    }

    // Cria a tabela se não existir
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
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insere novo jogo (sem ID)
    public static void inserirJogo(Jogo jogo) {
        String sql = "INSERT INTO jogos (nome, genero, ano, plataforma, nota, comentario) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, jogo.getNome());
            pstmt.setString(2, jogo.getGenero());
            pstmt.setInt(3, jogo.getAno());
            pstmt.setString(4, jogo.getPlataforma());
            pstmt.setInt(5, jogo.getNota());
            pstmt.setString(6, jogo.getComentario());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lista todos os jogos
    public static ArrayList<Jogo> listarJogos() {
        ArrayList<Jogo> lista = new ArrayList<>();
        String sql = "SELECT * FROM jogos";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
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
        return lista;
    }

    // Exclui jogo pelo ID
    public static void excluirJogo(int id) {
        String sql = "DELETE FROM jogos WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Atualiza um jogo usando o ID como referência
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
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
