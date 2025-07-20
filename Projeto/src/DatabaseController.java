
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

    public static ArrayList<Jogo> listarJogos() {
        ArrayList<Jogo> lista = new ArrayList<>();
        String sql = "SELECT * FROM jogos";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Jogo j = new Jogo(
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

    public static void excluirJogoPorNome(String nome) {
        String sql = "DELETE FROM jogos WHERE nome = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void atualizarJogo(Jogo jogoAntigo, Jogo jogoNovo) {
        String sql = "UPDATE jogos SET nome = ?, genero = ?, ano = ?, plataforma = ?, nota = ?, comentario = ? WHERE nome = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, jogoNovo.getNome());
            pstmt.setString(2, jogoNovo.getGenero());
            pstmt.setInt(3, jogoNovo.getAno());
            pstmt.setString(4, jogoNovo.getPlataforma());
            pstmt.setInt(5, jogoNovo.getNota());
            pstmt.setString(6, jogoNovo.getComentario());
            pstmt.setString(7, jogoAntigo.getNome()); // critério para localizar
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
