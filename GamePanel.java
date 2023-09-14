import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// Definição da classe GamePanel, que herda características da classe JPanel e implementa Runnable
// Runnable serve para executar trechos de codigo ao mesmo temppo
public class GamePanel extends JPanel implements Runnable {

    // Definições de constantes para o tamanho do jogo e elementos do jogo
    static final int GAME_WIDTH = 1000; //largura do jogo
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555)); //altura do jogo
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT); //dimensao do jogo, o setPreferredSize só
    //aceita tamanhos em dimension e precisamos dele para definir o tamanho do nosso jpanel
    static final int BALL_DIAMETER = 20; //diametro da bolinha, se aumentar ele aumenta ela
    static final int PADDLE_WIDTH = 25; //largura da raquete
    static final int PADDLE_HEIGHT = 100; //altura da raquete

    // Thread para executar o loop do jogo
    Thread gameThread;
    Image image;
    Graphics graphics;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;

    // Construtor da classe GamePanel
    public GamePanel() {
        System.out.println("criando novos paddles");
        newPaddles(); //iniciando novas raquetes
        System.out.println("criando nova ball");
        newBall();//iniciando novas balls
        score = new Score(GAME_WIDTH, GAME_HEIGHT); // manda altura e largura da tela para que o score fique responsivo
        this.setFocusable(true);
        this.addKeyListener(new AL());
        System.out.println(SCREEN_SIZE);
        this.setPreferredSize(SCREEN_SIZE); //define o tamanho do jpanel de acordo com o screen_size

        gameThread = new Thread(this);//o thread vai executar o codigo presente aqui na classe
        gameThread.start(); // ele começa, e permite que eu rode o jogo de forma paralela ao inves de sequencial
    }

    // Método para criar uma nova bola
    public void newBall() {
        ball = new Ball((GAME_WIDTH/2) - (BALL_DIAMETER/2), (GAME_HEIGHT/2) - (BALL_DIAMETER/2), BALL_DIAMETER, BALL_DIAMETER);
        //instancia de uma nova bola, mando onde ela vai começar e qual o tamanho dela, a largura eh a mesma da altura pq eh um circulo
    }

    // Método para criar novas raquetes
    public void newPaddles() {
        paddle1 = new Paddle(0, (GAME_HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);//instanciando os paddles
        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }

    // Sobrescrita do método paint para desenhar elementos na tela
    public void paint(Graphics g) {
        // Cria uma imagem temporária com base nas dimensões do componente
        image = createImage(getWidth(), getHeight());
        // Obtém um objeto Graphics para desenhar na imagem temporária
        graphics = image.getGraphics();
        // Chama o método draw para desenhar os elementos do jogo na imagem temporária
        draw(graphics);
        // Desenha a imagem temporária na tela atualizando a renderização
        g.drawImage(image, 0, 0, this);
    }


    // Método para desenhar elementos na tela
    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    // Método para mover elementos do jogo
    public void move() {
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    // Método para verificar colisões entre elementos
    public void checkCollision() {
        // Lógica de colisão da bola com as paredes superior e inferior
        if (ball.y <= 0) {
            ball.setYDirection(-ball.yVelocity);
        }
        if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
        }

        // Lógica de colisão da bola com as raquetes
        if (ball.intersects(paddle1)) {
            // Altera a direção da bola e ajusta as velocidades
            System.out.println(ball.xVelocity);
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            System.out.println(ball.yVelocity);
            if (ball.yVelocity > 0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        if (ball.intersects(paddle2)) {
            // Altera a direção da bola e ajusta as velocidades
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            if (ball.yVelocity > 0)
                ball.yVelocity++;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        // Limites de movimento das raquetes
        if (paddle1.y <= 0)
            paddle1.y = 0;
        if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        if (paddle2.y <= 0)
            paddle2.y = 0;
        if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;

        // Lógica de pontuação e reinício do jogo
        if (ball.x <= 0) {
            score.player2++;
            newPaddles();
            newBall();
            System.out.println("P1: " + score.player2);
        }

        if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
            score.player1++;
            newPaddles();
            newBall();
            System.out.println("P2 " + score.player1);
        }
    }

    // Método para executar o loop principal do jogo
    public void run() {
        long lastTime = System.nanoTime();
        double velocity = 60.0;
        double ns = 1000000000 / velocity;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    // Classe interna para lidar com eventos de teclado
    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
