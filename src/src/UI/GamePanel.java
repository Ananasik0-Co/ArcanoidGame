package UI;

import engine.PhysicsEngine;
import entities.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import Input.InputHandler;
import UI.GameState;

public class GamePanel extends JPanel implements ActionListener {

    private final int WIDTH = 600;
    private final int HEIGHT = 400;

    private Ball ball;
    private Paddle paddle;
    private List<Brick> bricks;

    private Timer timer;
    private RenderMaster render;
    private PhysicsEngine physics;
    private InputHandler input;

    private GameState currentState = GameState.MENU;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);

        render = new RenderMaster();
        physics = new PhysicsEngine(WIDTH, HEIGHT);
        input = new InputHandler();

        addKeyListener(input);

        // Создаем объекты
        ball = new Ball(WIDTH / 2, HEIGHT - 100, 15, Color.YELLOW);
        paddle = new Paddle(250, HEIGHT - 30, 100, 15, Color.CYAN);
        initLevel();

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
        render.render(g, ball, paddle, bricks,  currentState);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        handleGlobalInput();

        if (currentState == GameState.PLAYING)
        {
            handleInput();

            physics.update(ball, paddle, bricks);

            checkGameOver();
        }

        repaint();
    }

    private void handleInput() {
        if (input.isMovingLeft() && paddle.getX() > 0) {
            paddle.SetX(paddle.getX() - 7);
        }
        if (input.isMovingRight() && paddle.getX() < WIDTH - paddle.getWidth()) {
            paddle.SetX(paddle.getX() + 7);
        }
    }

    private void handleGlobalInput()
    {
        if (input.enterPressed)
        {
            if (currentState == GameState.MENU) currentState = GameState.PLAYING;
            input.enterPressed = false;
        }

        if (input.escPressed)
        {
            if (currentState == GameState.PLAYING) currentState = GameState.PAUSED;
            else if (currentState == GameState.PAUSED) currentState = GameState.PLAYING;
            input.escPressed = false;
        }

        if (input.rPressed && currentState == GameState.GAME_OVER)
        {
            restartGame();
            input.rPressed = false;
        }
    }

    private void restartGame()
    {
        ball =  new Ball(WIDTH / 2, HEIGHT - 100, 15, Color.YELLOW);
        paddle =  new Paddle(250, HEIGHT - 30, 100, 15, Color.CYAN);
        initLevel();
        currentState = GameState.PLAYING;
    }

    private void checkGameOver() {
        if (ball.getY() > HEIGHT) {
            currentState = GameState.GAME_OVER;
        }
    }
}