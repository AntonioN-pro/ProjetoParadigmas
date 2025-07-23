import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TelaPrincipal extends JFrame {

    private JTextField txtNome, txtGenero, txtAno, txtPlataforma, txtNota;
    private JTextArea txtComentario;
    private JTable tabelaJogos;
    private DefaultTableModel modeloTabela;

    private Integer idSelecionado = null; // armazena ID do jogo selecionado para edição

    public TelaPrincipal() {
        setTitle("Biblioteca de Jogos 🎮");
        setSize(800, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos
        JLabel lblNome = new JLabel("Nome:");
        txtNome = new JTextField();
        JLabel lblGenero = new JLabel("Gênero:");
        txtGenero = new JTextField();
        JLabel lblAno = new JLabel("Ano:");
        txtAno = new JTextField();
        JLabel lblPlataforma = new JLabel("Plataforma:");
        txtPlataforma = new JTextField();
        JLabel lblNota = new JLabel("Nota:");
        txtNota = new JTextField();
        JLabel lblComentario = new JLabel("Comentário:");
        txtComentario = new JTextArea(3, 20);
        JScrollPane scrollComentario = new JScrollPane(txtComentario);

        gbc.gridx = 0; gbc.gridy = 0; painel.add(lblNome, gbc);
        gbc.gridx = 1; painel.add(txtNome, gbc);
        gbc.gridx = 0; gbc.gridy = 1; painel.add(lblGenero, gbc);
        gbc.gridx = 1; painel.add(txtGenero, gbc);
        gbc.gridx = 0; gbc.gridy = 2; painel.add(lblAno, gbc);
        gbc.gridx = 1; painel.add(txtAno, gbc);
        gbc.gridx = 0; gbc.gridy = 3; painel.add(lblPlataforma, gbc);
        gbc.gridx = 1; painel.add(txtPlataforma, gbc);
        gbc.gridx = 0; gbc.gridy = 4; painel.add(lblNota, gbc);
        gbc.gridx = 1; painel.add(txtNota, gbc);
        gbc.gridx = 0; gbc.gridy = 5; painel.add(lblComentario, gbc);
        gbc.gridx = 1; painel.add(scrollComentario, gbc);

        // Tabela
        modeloTabela = new DefaultTableModel(new Object[]{"ID", "Nome", "Gênero", "Ano", "Plataforma", "Nota", "Comentário"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // evita edição direta
            }
        };
        tabelaJogos = new JTable(modeloTabela);
        JScrollPane scrollTabela = new JScrollPane(tabelaJogos);
        scrollTabela.setPreferredSize(new Dimension(750, 200));
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        painel.add(scrollTabela, gbc);

        // Botões
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(this::cadastrarJogo);

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(this::editarJogo);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(this::excluirJogo);

        JPanel botoes = new JPanel();
        botoes.add(btnCadastrar);
        botoes.add(btnEditar);
        botoes.add(btnExcluir);

        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        painel.add(botoes, gbc);

        add(painel);

        // Carrega dados e inicializa
        DatabaseController.inicializarBanco();
        carregarJogos();

        // Ao clicar na tabela, carrega os dados no formulário
        tabelaJogos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tabelaJogos.getSelectedRow();
                if (row != -1) {
                    idSelecionado = (Integer) modeloTabela.getValueAt(row, 0);
                    txtNome.setText((String) modeloTabela.getValueAt(row, 1));
                    txtGenero.setText((String) modeloTabela.getValueAt(row, 2));
                    txtAno.setText(String.valueOf(modeloTabela.getValueAt(row, 3)));
                    txtPlataforma.setText((String) modeloTabela.getValueAt(row, 4));
                    txtNota.setText(String.valueOf(modeloTabela.getValueAt(row, 5)));
                    txtComentario.setText((String) modeloTabela.getValueAt(row, 6));
                }
            }
        });

        setVisible(true);
    }

    // Cadastrar novo jogo
    private void cadastrarJogo(ActionEvent e) {
        try {
            String nome = txtNome.getText();
            String genero = txtGenero.getText();
            int ano = Integer.parseInt(txtAno.getText().trim());
            String plataforma = txtPlataforma.getText();
            int nota = Integer.parseInt(txtNota.getText().trim());
            String comentario = txtComentario.getText();

            Jogo jogo = new Jogo(nome, genero, ano, nota, comentario, plataforma);
            DatabaseController.inserirJogo(jogo);
            limparCampos();
            carregarJogos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ano e Nota devem ser inteiros.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Atualizar jogo existente
    private void editarJogo(ActionEvent e) {
        if (idSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um jogo para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String nome = txtNome.getText();
            String genero = txtGenero.getText();
            int ano = Integer.parseInt(txtAno.getText().trim());
            String plataforma = txtPlataforma.getText();
            int nota = Integer.parseInt(txtNota.getText().trim());
            String comentario = txtComentario.getText();

            Jogo jogoEditado = new Jogo(idSelecionado, nome, genero, ano, nota, comentario, plataforma);
            DatabaseController.atualizarJogo(jogoEditado);
            limparCampos();
            carregarJogos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ano e Nota devem ser inteiros.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Excluir jogo selecionado
    private void excluirJogo(ActionEvent e) {
        int linha = tabelaJogos.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um jogo para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modeloTabela.getValueAt(linha, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Deseja excluir este jogo?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            DatabaseController.excluirJogo(id);
            limparCampos();
            carregarJogos();
        }
    }

    // Limpa campos
    private void limparCampos() {
        txtNome.setText("");
        txtGenero.setText("");
        txtAno.setText("");
        txtPlataforma.setText("");
        txtNota.setText("");
        txtComentario.setText("");
        idSelecionado = null;
    }

    // Recarrega tabela
    private void carregarJogos() {
        modeloTabela.setRowCount(0);
        List<Jogo> jogos = DatabaseController.listarJogos();
        for (Jogo jogo : jogos) {
            modeloTabela.addRow(new Object[]{
                    jogo.getId(),
                    jogo.getNome(),
                    jogo.getGenero(),
                    jogo.getAno(),
                    jogo.getPlataforma(),
                    jogo.getNota(),
                    jogo.getComentario()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaPrincipal::new);
    }
}
