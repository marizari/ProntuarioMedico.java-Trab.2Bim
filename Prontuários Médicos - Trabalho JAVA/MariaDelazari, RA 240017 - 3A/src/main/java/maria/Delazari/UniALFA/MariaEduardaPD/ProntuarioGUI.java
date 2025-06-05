package maria.Delazari.UniALFA.MariaEduardaPD;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ProntuarioGUI extends JFrame {
    private JTextField tfNomePaciente, tfCpfPaciente, tfNomeMedico, tfEspecialidade, tfDataConsulta;
    private JTextArea taDiagnostico, taPrescricao;
    private JButton btnSalvar, btnLimpar, btnExibir;

    public ProntuarioGUI() {
        setTitle("Cadastro de Prontuário Médico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 650);
        setLocationRelativeTo(null); // ele  centraliza a janela
        setLayout(new BorderLayout());

        // Painel principal com padding
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setBorder(new EmptyBorder(20, 30, 20, 30));
        painelPrincipal.setLayout(new GridLayout(10, 2, 10, 10));
        painelPrincipal.setBackground(new Color(240, 248, 255));

        Font fonteLabel = new Font("SansSerif", Font.BOLD, 14);
        Font fonteInput = new Font("SansSerif", Font.PLAIN, 14);

        tfNomePaciente = criarCampoTexto(fonteInput);
        tfCpfPaciente = criarCampoTexto(fonteInput);
        tfNomeMedico = criarCampoTexto(fonteInput);
        tfEspecialidade = criarCampoTexto(fonteInput);
        tfDataConsulta = criarCampoTexto(fonteInput);
        taDiagnostico = criarAreaTexto(fonteInput);
        taPrescricao = criarAreaTexto(fonteInput);

        btnSalvar = criarBotao("💾 Salvar Prontuário");
        btnLimpar = criarBotao("🧹 Limpar Campos");
        btnExibir = criarBotao("📂 Exibir Prontuários");

        painelPrincipal.add(new JLabel("Nome do Paciente:")).setFont(fonteLabel);
        painelPrincipal.add(tfNomePaciente);
        painelPrincipal.add(new JLabel("CPF do Paciente (ou seu RA):")).setFont(fonteLabel);
        painelPrincipal.add(tfCpfPaciente);
        painelPrincipal.add(new JLabel("Nome do Médico:")).setFont(fonteLabel);
        painelPrincipal.add(tfNomeMedico);
        painelPrincipal.add(new JLabel("Especialidade:")).setFont(fonteLabel);
        painelPrincipal.add(tfEspecialidade);
        painelPrincipal.add(new JLabel("Data da Consulta:")).setFont(fonteLabel);
        painelPrincipal.add(tfDataConsulta);
        painelPrincipal.add(new JLabel("Diagnóstico:")).setFont(fonteLabel);
        painelPrincipal.add(new JScrollPane(taDiagnostico));
        painelPrincipal.add(new JLabel("Prescrição:")).setFont(fonteLabel);
        painelPrincipal.add(new JScrollPane(taPrescricao));
        painelPrincipal.add(btnSalvar);
        painelPrincipal.add(btnLimpar);
        painelPrincipal.add(btnExibir);

        add(painelPrincipal, BorderLayout.CENTER);

        btnSalvar.addActionListener(e -> salvarProntuario());
        btnLimpar.addActionListener(e -> limparCampos());
        btnExibir.addActionListener(e -> exibirProntuarios());

        setVisible(true);
    }

    private JTextField criarCampoTexto(Font font) {
        JTextField campo = new JTextField();
        campo.setFont(font);
        campo.setBorder(BorderFactory.createLineBorder(new Color(173, 216, 230)));
        return campo;
    }

    private JTextArea criarAreaTexto(Font font) {
        JTextArea area = new JTextArea(3, 20);
        area.setFont(font);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createLineBorder(new Color(173, 216, 230)));
        return area;
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFocusPainted(false);
        botao.setBackground(new Color(100, 149, 237));
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("SansSerif", Font.BOLD, 13));
        return botao;
    }

    private void salvarProntuario() {
        if (tfNomePaciente.getText().isEmpty() || tfCpfPaciente.getText().isEmpty() ||
                tfNomeMedico.getText().isEmpty() || tfEspecialidade.getText().isEmpty() ||
                tfDataConsulta.getText().isEmpty() || taDiagnostico.getText().isEmpty() ||
                taPrescricao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!");
            return;
        }

        Prontuario prontuario = new Prontuario(
                tfNomePaciente.getText(),
                tfCpfPaciente.getText(),
                tfNomeMedico.getText(),
                tfEspecialidade.getText(),
                tfDataConsulta.getText(),
                taDiagnostico.getText(),
                taPrescricao.getText()
        );

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("prontuarios.txt", true))) {
            writer.write(prontuario.toString());
            writer.newLine();
            JOptionPane.showMessageDialog(this, " Prontuário salvo com sucesso!");
            limparCampos();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar prontuário: " + ex.getMessage());
        }
    }

    private void limparCampos() {
        tfNomePaciente.setText("");
        tfCpfPaciente.setText("");
        tfNomeMedico.setText("");
        tfEspecialidade.setText("");
        tfDataConsulta.setText("");
        taDiagnostico.setText("");
        taPrescricao.setText("");
    }

    private void exibirProntuarios() {
        try {
            String conteudo = Files.readString(Paths.get("prontuarios.txt"));
            JTextArea taExibir = new JTextArea(conteudo);
            taExibir.setEditable(false);
            taExibir.setFont(new Font("Monospaced", Font.PLAIN, 13));
            JScrollPane scrollPane = new JScrollPane(taExibir);
            scrollPane.setPreferredSize(new Dimension(500, 400));
            JOptionPane.showMessageDialog(this, scrollPane, " Prontuários Salvos", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao ler o arquivo: " + ex.getMessage());
        }
    }
}
