import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Paddle extends Rectangle {

    int id;             // Identificação da raquete (jogador 1 ou jogador 2)
    int yVelocity;      // Velocidade vertical da raquete
    int speed = 10;     // Velocidade padrão de movimento da raquete

    // Construtor para criar uma raquete
    public Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {

        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;
        System.out.println("entrei no construtor paddle");
    }

    // Método chamado quando uma tecla é pressionada
    public void keyPressed(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(-speed); // Move a raquete para cima
                    System.out.println("entrei no keypressed w");
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(speed);   // Move a raquete para baixo
                    System.out.println("entrei no keypressed s");
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(-speed);  // Move a raquete para cima
                    System.out.println("entrei no keypressed up");
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(speed);   // Move a raquete para baixo
                    System.out.println("entrei no keypressed down");
                    move();
                }
                break;
        }
    }

    // Método chamado quando uma tecla é solta
    public void keyReleased(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);       // Para o movimento vertical da raquete
                    System.out.println("entrei no keyreleased 1");
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0);       // Para o movimento vertical da raquete
                    System.out.println("entrei no keyreleased 2");
                    move();
                }
                break;
        }
    }

    // Define a direção vertical da raquete
    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }

    // Move a raquete com base na velocidade vertical
    public void move() {
        //soma a posição atual + a posiçãao futura, fazendo com que ande
        y = y + yVelocity;
    }

    // Desenha a raquete na tela
    public void draw(Graphics g) {
        if (id == 1)
            g.setColor(Color.blue);
        else
            g.setColor(Color.red);

        //essas variaveis estao sendo herdadas da classe retangulo, por isso nao fica visível a nós
        g.fillRect(x, y, width, height);
    }
}
