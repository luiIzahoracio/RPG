package aula;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Personagem extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNome;
    private JTextField txtIdade;
    private JTextArea descricao;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Personagem frame = new Personagem();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Personagem() {
        setTitle("Criação de Personagem");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 550);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(30, 30, 30));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("CRIAÇÃO DE PERSONAGEM");
        lblTitulo.setForeground(Color.RED);
        lblTitulo.setFont(new Font("Impact", Font.BOLD, 30));
        lblTitulo.setBounds(160, 20, 400, 40);
        contentPane.add(lblTitulo);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setForeground(Color.WHITE);
        lblNome.setFont(new Font("Arial", Font.BOLD, 18));
        lblNome.setBounds(50, 90, 80, 25);
        contentPane.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(120, 90, 220, 30);
        contentPane.add(txtNome);

        JLabel lblIdade = new JLabel("Idade:");
        lblIdade.setForeground(Color.WHITE);
        lblIdade.setFont(new Font("Arial", Font.BOLD, 18));
        lblIdade.setBounds(380, 90, 80, 25);
        contentPane.add(lblIdade);

        txtIdade = new JTextField();
        txtIdade.setBounds(450, 90, 80, 30);
        contentPane.add(txtIdade);

        JLabel lblClasse = new JLabel("Escolha sua classe:");
        lblClasse.setForeground(Color.WHITE);
        lblClasse.setFont(new Font("Arial", Font.BOLD, 18));
        lblClasse.setBounds(50, 150, 220, 25);
        contentPane.add(lblClasse);

        JRadioButton rbCivil = new JRadioButton("Civil");
        JRadioButton rbMilitar = new JRadioButton("Militar");
        JRadioButton rbPesquisador = new JRadioButton("Pesquisador");

        rbCivil.setBounds(50, 190, 100, 30);
        rbMilitar.setBounds(170, 190, 100, 30);
        rbPesquisador.setBounds(290, 190, 150, 30);

        rbCivil.setBackground(new Color(30, 30, 30));
        rbMilitar.setBackground(new Color(30, 30, 30));
        rbPesquisador.setBackground(new Color(30, 30, 30));

        rbCivil.setForeground(Color.WHITE);
        rbMilitar.setForeground(Color.WHITE);
        rbPesquisador.setForeground(Color.WHITE);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbCivil);
        grupo.add(rbMilitar);
        grupo.add(rbPesquisador);

        contentPane.add(rbCivil);
        contentPane.add(rbMilitar);
        contentPane.add(rbPesquisador);

        descricao = new JTextArea();
        descricao.setEditable(false);
        descricao.setFont(new Font("Monospaced", Font.PLAIN, 14));
        descricao.setBounds(50, 240, 580, 150);
        contentPane.add(descricao);

        rbCivil.addActionListener(e ->
            descricao.setText("CIVIL\nVida: 100\nPassiva: Socorrista (Cura aprimorada)")
        );

        rbMilitar.addActionListener(e ->
            descricao.setText("MILITAR\nVida: 150\nPassiva: Casca Grossa (Aguenta mais dano)")
        );

        rbPesquisador.addActionListener(e ->
            descricao.setText("PESQUISADOR\nVida: 90\nPassiva: Intelecto Avançado (Descobre fraquezas inimigas)")
        );

        JButton btnCriar = new JButton("CRIAR PERSONAGEM");
        btnCriar.setFont(new Font("Arial", Font.BOLD, 18));
        btnCriar.setBounds(190, 430, 300, 40);
        contentPane.add(btnCriar);

        btnCriar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                String idadeTexto = txtIdade.getText();

                if (nome.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Digite o nome!");
                    return;
                }

                int idade;
                try {
                    idade = Integer.parseInt(idadeTexto);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Idade inválida!");
                    return;
                }

                String classe = "";
                if (rbCivil.isSelected()) classe = "Civil";
                else if (rbMilitar.isSelected()) classe = "Militar";
                else if (rbPesquisador.isSelected()) classe = "Pesquisador";
                else {
                    JOptionPane.showMessageDialog(null, "Escolha uma classe!");
                    return;
                }

                String msgSucesso = "🟢 SOBREVIVENTE REGISTRADO COM SUCESSO!\n\n"
                        + "👤 Nome: " + nome + "\n"
                        + "🎂 Idade: " + idade + " anos\n"
                        + "🛡️ Classe: " + classe + "\n\n"
                        + "Prepare-se... O apocalipse começou!";

                JOptionPane.showMessageDialog(null, msgSucesso, "Ficha Salva",
                        JOptionPane.INFORMATION_MESSAGE);

                decisao d = new decisao(nome, idade, classe);
                d.setVisible(true);

                dispose();
            }
        });
    }
}