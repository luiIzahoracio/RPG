package aula;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.*;

import javax.swing.border.EmptyBorder;

public class decisao extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private int vidaJogador;
    private int vidaMaxima;
    private int danoJogador;
    private int itensCura = 2;

    private String nomeInimigo;
    private int vidaInimigo;
    private int danoInimigo;
    private boolean bossFase = false;

    private JLabel lblVidaJogador;
    private JLabel lblVidaInimigo;
    private JLabel lblNomeInimigo;
    private JTextArea txtLog;
    private JButton btnAtacar;
    private JButton btnCurar;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                decisao frame = new decisao("Recruta Teste", 25, "Militar");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public decisao(String nomeJogador, int idadeJogador, String classeJogador) {

        // ===== HISTÓRIA INICIAL =====
        if (classeJogador.equalsIgnoreCase("Militar")) {
            this.vidaMaxima = 150;
            this.danoJogador = 25;
        } else {
            this.vidaMaxima = 100;
            this.danoJogador = 18;
        }
        this.vidaJogador = this.vidaMaxima;

        setTitle("RPG Zumbi - Capítulo 1: O Início do Pesadelo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 550);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(30, 30, 30));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        configurarInimigo("Zumbi Pequeno", 50, 12);

        JLabel lblTitulo = new JLabel("ARENA DE COMBATE");
        lblTitulo.setForeground(Color.RED);
        lblTitulo.setFont(new Font("Impact", Font.BOLD, 30));
        lblTitulo.setBounds(230, 20, 300, 40);
        contentPane.add(lblTitulo);

        JLabel lblStatusJogador = new JLabel(nomeJogador + ", " + idadeJogador + " anos (" + classeJogador + "):");
        lblStatusJogador.setForeground(Color.WHITE);
        lblStatusJogador.setBounds(50, 80, 400, 25);
        contentPane.add(lblStatusJogador);

        lblVidaJogador = new JLabel("Sua Vida: " + vidaJogador + " / " + vidaMaxima);
        lblVidaJogador.setForeground(Color.GREEN);
        lblVidaJogador.setBounds(50, 110, 200, 25);
        contentPane.add(lblVidaJogador);

        lblNomeInimigo = new JLabel("Inimigo: " + nomeInimigo);
        lblNomeInimigo.setForeground(Color.ORANGE);
        lblNomeInimigo.setBounds(400, 80, 250, 25);
        contentPane.add(lblNomeInimigo);

        lblVidaInimigo = new JLabel("Vida do Inimigo: " + vidaInimigo);
        lblVidaInimigo.setForeground(Color.RED);
        lblVidaInimigo.setBounds(400, 110, 200, 25);
        contentPane.add(lblVidaInimigo);

        txtLog = new JTextArea();
        txtLog.setEditable(false);
        txtLog.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtLog.setBackground(new Color(20, 20, 20));
        txtLog.setForeground(Color.LIGHT_GRAY);

        JScrollPane scroll = new JScrollPane(txtLog);
        scroll.setBounds(50, 160, 580, 200);
        contentPane.add(scroll);

        
        txtLog.setText(
                "📡 TRANSMISSÃO INTERROMPIDA...\n" +
                "Você acorda em meio ao caos.\n" +
                "Sirens tocam ao fundo. A cidade caiu.\n\n" +
                "👤 " + classeJogador + " foi enviado para investigar o surto.\n" +
                "Mas algo deu errado...\n\n" +
                "Um ZUMBI PEQUENO surge das sombras...\n" +
                "Prepare-se para sobreviver.\n"
        );

        btnAtacar = new JButton("ATACAR");
        btnAtacar.setBounds(120, 390, 200, 45);
        contentPane.add(btnAtacar);

        btnCurar = new JButton("USAR MEDKIT (" + itensCura + ")");
        btnCurar.setBounds(360, 390, 200, 45);
        contentPane.add(btnCurar);

        btnAtacar.addActionListener(e -> turnoJogadorAtaque());
        btnCurar.addActionListener(e -> turnoJogadorCura(classeJogador));
    }

    private void configurarInimigo(String nome, int vida, int dano) {
        this.nomeInimigo = nome;
        this.vidaInimigo = vida;
        this.danoInimigo = dano;
    }

    private void turnoJogadorAtaque() {

        vidaInimigo -= danoJogador;
        if (vidaInimigo < 0) vidaInimigo = 0;

        txtLog.append("\n⚔ Você golpeia o " + nomeInimigo + " (-" + danoJogador + ")");

        lblVidaInimigo.setText("Vida do Inimigo: " + vidaInimigo);

        if (vidaInimigo <= 0) {
            verificarProgresso();
        } else {
            turnoInimigo();
        }
    }

    private void turnoJogadorCura(String classeJogador) {

        if (itensCura > 0) {

            int cura = classeJogador.equalsIgnoreCase("Civil") ? 55 : 30;

            vidaJogador += cura;
            if (vidaJogador > vidaMaxima) vidaJogador = vidaMaxima;

            itensCura--;

            txtLog.append("\n🩹 Você se recupera (+ " + cura + " HP)");

            lblVidaJogador.setText("Sua Vida: " + vidaJogador + " / " + vidaMaxima);
            btnCurar.setText("USAR MEDKIT (" + itensCura + ")");

            turnoInimigo();

        } else {
            JOptionPane.showMessageDialog(null, "Sem medkits!");
        }
    }

    private void turnoInimigo() {

        vidaJogador -= danoInimigo;
        if (vidaJogador < 0) vidaJogador = 0;

        txtLog.append("\n☠ " + nomeInimigo + " te ataca (-" + danoInimigo + ")");

        lblVidaJogador.setText("Sua Vida: " + vidaJogador + " / " + vidaMaxima);

        if (vidaJogador <= 0) {
            txtLog.append("\n\n💀 VOCÊ MORREU NO APÓCALIPSE");
            btnAtacar.setEnabled(false);
            btnCurar.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Fim de jogo.");
        }
    }

    private void verificarProgresso() {

        if (!bossFase) {

            bossFase = true;

            
            txtLog.append("\n\n📻 UMA TRANSMISSÃO SURGE:");
            txtLog.append("\n'Experimento falhou... Paciente 0 está livre...'");
            txtLog.append("\nO chão treme. Algo maior está chegando...\n");

            configurarInimigo("PACIENTE 0", 160, 25);

            lblNomeInimigo.setText("Inimigo: " + nomeInimigo);
            lblVidaInimigo.setText("Vida do Inimigo: " + vidaInimigo);

        } else {

            txtLog.append("\n\n🏆 VOCÊ DERROTOU O PACIENTE 0!");
            txtLog.append("\nA cidade ainda não está salva... mas você sobreviveu.");

            btnAtacar.setEnabled(false);
            btnCurar.setEnabled(false);

            JOptionPane.showMessageDialog(null, "Capítulo 1 concluído!");
        }
    }
}