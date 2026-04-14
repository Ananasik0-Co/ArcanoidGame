package UI;

import engine.PhysicsEngine;
import entities.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements ActionListener {

    private final int WIDTH = 600;
    private final int HEIGHT = 400;

    private Ball ball;
    private Paddle paddle;
    private List<Brick> bricks;

    private Timer timer;
    private RenderMaster render;
    private PhysicsEngine physics;

    private boolean isMovingLeft = false;
    private boolean isMovingRight = false;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);

        render = new RenderMaster();
        physics = new PhysicsEngine(WIDTH, HEIGHT);

        // Создаем объекты
        ball = new Ball(WIDTH / 2, HEIGHT - 100, 15, Color.YELLOW);
        paddle = new Paddle(250, HEIGHT - 30, 100, 15, Color.CYAN);
        initLevel();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) isMovingLeft = true;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) isMovingRight = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) isMovingLeft = false;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) isMovingRight = false;
            }
        });

        timer = new Timer(16, this);
        timer.start();
    }

    private void initLevel() {
        bricks = new ArrayList<>();
        int rows = 4, cols = 8, bWidth = 60, bHeight = 20, gap = 10;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                bricks.add(new Brick(j * (bWidth + gap) + 30, i * (bHeight + gap) + 50, bWidth, bHeight, Color.RED));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render.render(g, ball, paddle, bricks);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 1. Сначала обрабатываем ввод пользователя (платформа)
        handleInput();

        // 2. Делегируем всю физику движку
        physics.update(ball, paddle, bricks);

        // 3. Проверяем условие проигрыша
        checkGameOver();

        // 4. Перерисовываем
        repaint();
    }

    private void handleInput() {
        if (isMovingLeft && paddle.getX() > 0) {
            paddle.SetX(paddle.getX() - 7);
        }
        if (isMovingRight && paddle.getX() < WIDTH - paddle.getWidth()) {
            paddle.SetX(paddle.getX() + 7);
        }
    }

    private void checkGameOver() {
        if (ball.getY() > HEIGHT) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Игра окончена!");
        }
    }
}