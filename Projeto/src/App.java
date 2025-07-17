import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("🚀 Iniciando aplicação...");
        try {
            // Conexão com o banco
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:TESTE.db";
            Connection conn = DriverManager.getConnection(url);
            System.out.println("✅ Conectado ao banco!");

            Statement stmt = conn.createStatement();

            // Criação da tabela
            String criarTabela = """
                CREATE TABLE IF NOT EXISTS jogos (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    genero TEXT,
                    nota INTEGER
                );
            """;
            stmt.execute(criarTabela);

            // Entrada do usuário
            Scanner sc = new Scanner(System.in);
            System.out.print("🎮 Nome do jogo: ");
            String nome = sc.nextLine();

            System.out.print("📚 Gênero: ");
            String genero = sc.nextLine();

            System.out.print("⭐ Nota: ");
            int nota = sc.nextInt();

            // Inserção usando PreparedStatement
            String sql = "INSERT INTO jogos (nome, genero, nota) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nome);
            pstmt.setString(2, genero);
            pstmt.setInt(3, nota);

            int linhas = pstmt.executeUpdate();
            System.out.println("📝 Jogo inserido! Linhas afetadas: " + linhas);

            // Exibir todos os jogos
            System.out.println("\n📋 Jogos no banco:");
            ResultSet rs = stmt.executeQuery("SELECT * FROM jogos");
            while (rs.next()) {
                System.out.println("🎮 " + rs.getString("nome") + " (" + rs.getString("genero") + ") - Nota: " + rs.getInt("nota"));
            }

            // Encerrar conexão
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
