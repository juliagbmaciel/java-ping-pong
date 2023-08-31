import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Score extends Rectangle {

    static int GAME_WIDTH;   // Largura do jogo
    static int GAME_HEIGHT;  // Altura do jogo
    int player1;             // Pontuação do jogador 1
    int player2;             // Pontuação do jogador 2

    // Construtor para inicializar a largura e altura do jogo
    // Precisamos da largura e da altura para deixar o score sempre posicionado no local correto da tellaa
    // independente se vamos redimensionar ou não, por isso, usar os parametros game_width e game_height sao importantes
    // basicamente, responsividade
    public Score(int GAME_WIDTH, int GAME_HEIGHT) {
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }

    // Método para desenhar a pontuação na tela
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.PLAIN, 50));

        // Desenha uma linha vertical no meio da tela
        g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);

        // Desenha a pontuação do jogador 1 à esquerda e do jogador 2 à direita
        g.drawString(String.valueOf(player1), (GAME_WIDTH/2)-85, 50);
        g.drawString(String.valueOf(player2), (GAME_WIDTH/2)+50, 50);
    }
}
