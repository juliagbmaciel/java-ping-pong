import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// Definição da classe GameFrame, que herda características da classe JFrame
public class GameFrame extends JFrame {

    GamePanel panel; // Painel de jogo para conter os elementos do jogo

    // Construtor da classe GameFrame
    public GameFrame() {
        panel = new GamePanel(); // Cria uma instância do painel de jogo (GamePanel)
        this.add(panel); // Adiciona o painel ao quadro (frame)

        this.setTitle("Pong Game"); // Define o título da janela
        this.setResizable(false); // Impede o redimensionamento da janela
        this.setBackground(Color.BLACK); // Define a cor de fundo da janela como preto
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Encerra o programa ao fechar a janela
        this.pack(); // Ajusta o tamanho da janela para se adequar aos componentes
        this.setVisible(true); // Torna a janela visível
        this.setLocationRelativeTo(null); // Centraliza a janela na tela
    }
}
