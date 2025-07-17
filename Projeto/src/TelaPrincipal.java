
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Biblioteca de Jogos 🎮");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos
        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();
        JLabel lblGenero = new JLabel("Gênero:");
        JTextField txtGenero = new JTextField();
        JLabel lblAno = new JLabel("Ano:");
        JTextField txtAno = new JTextField();
        JLabel lblPlataforma = new JLabel("Plataforma:");
        JTextField txtPlataforma = new JTextField();
        JLabel lblNota = new JLabel("Nota:");
        JTextField txtNota = new JTextField();
        JLabel lblComentario = new JLabel("Comentário:");
        JTextField txtComentario = new JTextField();

        gbc.gridx = 0; gbc.gridy = 0; painel.add(lblNome, gbc);
        gbc.gridx = 1; gbc.gridy = 0; painel.add(txtNome, gbc);
        gbc.gridx = 0; gbc.gridy = 1; painel.add(lblGenero, gbc);
        gbc.gridx = 1; gbc.gridy = 1; painel.add(txtGenero, gbc);
        gbc.gridx = 0; gbc.gridy = 2; painel.add(lblAno, gbc);
        gbc.gridx = 1; gbc.gridy = 2; painel.add(txtAno, gbc);
        gbc.gridx = 0; gbc.gridy = 3; painel.add(lblPlataforma, gbc);
        gbc.gridx = 1; gbc.gridy = 3; painel.add(txtPlataforma, gbc);
        gbc.gridx = 0; gbc.gridy = 4; painel.add(lblNota, gbc);
        gbc.gridx = 1; gbc.gridy = 4; painel.add(txtNota, gbc);
        gbc.gridx = 0; gbc.gridy = 5; painel.add(lblComentario, gbc);
        gbc.gridx = 1; gbc.gridy = 5; painel.add(txtComentario, gbc);

        JPanel painelBotoes = new JPanel();
        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnEditar = new JButton("Editar Selecionado");
        JButton btnExcluir = new JButton("Excluir Selecionado");
        JButton btnListar = new JButton("Atualizar Lista");
        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnListar);

        String[] colunas = {"Nome", "Gênero", "Ano", "Plataforma", "Nota", "Comentário"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabela);

        Runnable atualizarTabela = () -> {
            model.setRowCount(0);
            for (Jogo j : DatabaseController.listarJogos()) {
                model.addRow(new Object[]{
                        j.getNome(), j.getGenero(), j.getAno(), j.getPlataforma(), j.getNota(), j.getComentario()
                });
            }
        };
        atualizarTabela.run();

        btnCadastrar.addActionListener(e -> {
            try {
                Jogo j = new Jogo(txtNome.getText(), txtGenero.getText(),
                        Integer.parseInt(txtAno.getText()), Integer.parseInt(txtNota.getText()),
                        txtComentario.getText(), txtPlataforma.getText());
                DatabaseController.inserirJogo(j);
                atualizarTabela.run();
                txtNome.setText(""); txtGenero.setText(""); txtAno.setText("");
                txtNota.setText(""); txtComentario.setText(""); txtPlataforma.setText("");
                JOptionPane.showMessageDialog(this, "Jogo inserido com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao inserir jogo.");
            }
        });

        btnEditar.addActionListener(e -> {
            int row = tabela.getSelectedRow();
            if (row != -1) {
                String nomeAntigo = (String) tabela.getValueAt(row, 0);

                JTextField novoNome = new JTextField((String) tabela.getValueAt(row, 0));
                JTextField novoGenero = new JTextField((String) tabela.getValueAt(row, 1));
                JTextField novoAno = new JTextField(tabela.getValueAt(row, 2).toString());
                JTextField novaPlataforma = new JTextField((String) tabela.getValueAt(row, 3));
                JTextField novaNota = new JTextField(tabela.getValueAt(row, 4).toString());
                JTextField novoComentario = new JTextField((String) tabela.getValueAt(row, 5));

                JPanel panel = new JPanel(new GridLayout(6, 2));
                panel.add(new JLabel("Nome:")); panel.add(novoNome);
                panel.add(new JLabel("Gênero:")); panel.add(novoGenero);
                panel.add(new JLabel("Ano:")); panel.add(novoAno);
                panel.add(new JLabel("Plataforma:")); panel.add(novaPlataforma);
                panel.add(new JLabel("Nota:")); panel.add(novaNota);
                panel.add(new JLabel("Comentário:")); panel.add(novoComentario);

                int result = JOptionPane.showConfirmDialog(null, panel, "Editar Jogo", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    Jogo antigo = new Jogo(nomeAntigo, "", 0, 0, "", "");
                    Jogo novo = new Jogo(
                            novoNome.getText(), novoGenero.getText(),
                            Integer.parseInt(novoAno.getText()), Integer.parseInt(novaNota.getText()),
                            novoComentario.getText(), novaPlataforma.getText()
                    );
                    DatabaseController.atualizarJogo(antigo, novo);
                    atualizarTabela.run();
                }
            }
        });

        btnExcluir.addActionListener(e -> {
            int row = tabela.getSelectedRow();
            if (row != -1) {
                String nome = (String) tabela.getValueAt(row, 0);
                int confirm = JOptionPane.showConfirmDialog(null, "Excluir o jogo: " + nome + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    DatabaseController.excluirJogoPorNome(nome);
                    atualizarTabela.run();
                }
            }
        });

        btnListar.addActionListener(e -> atualizarTabela.run());

        add(painel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
        setVisible(true);
    }

    public static void main(String[] args) {
        DatabaseController.inicializarBanco();
        SwingUtilities.invokeLater(() -> new TelaPrincipal());
    }
}
