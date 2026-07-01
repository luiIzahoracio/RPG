package aula;

import java.awt.*;
import javax.swing.*;

public class auraa extends JFrame {

    private String nomeJogador;
    private int idadeJogador;
    private String classeJogador;

    public auraa() {
        this("Recruta", 20, "Civil");
    }

    public auraa(String nome, int idade, String classe) {

        this.nomeJogador = nome;
        this.idadeJogador = idade;
        this.classeJogador = classe;

        setTitle("RPG - Apocalipse Zumbi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        setLocationRelativeTo(null);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(20, 20, 20));
        panel.setBounds(0, 0, 900, 600);
        add(panel);

        JLabel titulo = new JLabel("APOCALYPSE");
        titulo.setFont(new Font("Impact", Font.BOLD, 60));
        titulo.setForeground(new Color(180, 0, 0));
        titulo.setBounds(280, 40, 500, 70);
        panel.add(titulo);

        JButton btnPersonagem = new JButton("PERSONAGEM");
        btnPersonagem.setBounds(330, 220, 220, 45);
        panel.add(btnPersonagem);

        JButton btnDecisao = new JButton("DECISÃO");
        btnDecisao.setBounds(330, 280, 220, 45);
        panel.add(btnDecisao);

        btnPersonagem.addActionListener(e -> new Personagem().setVisible(true));

        btnDecisao.addActionListener(e ->
                new decisao(nomeJogador, idadeJogador, classeJogador).setVisible(true)
        );
    }

    public static void main(String[] args) {
        new auraa().setVisible(true);
    }
}