package engine;

import entities.*;
import java.util.List;

public class PhysicsEngine {

    private final int width;
    private final int height;

    public PhysicsEngine(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void update(Ball ball, Paddle paddle, List<Brick> bricks) {
        // 1. Движение
        ball.move();

        // 2. Столкновение со стенами
        checkWallCollisions(ball);

        // 3. Столкновение с платформой
        checkPaddleCollision(ball, paddle);

        // 4. Столкновение с кирпичами
        checkBrickCollisions(ball, bricks);
    }

    private void checkWallCollisions(Ball ball) {
        if (ball.getX() <= 0 || ball.getX() >= width - ball.getWidth()) {
            ball.reverseX();
        }
        if (ball.getY() <= 0) {
            ball.reverseY();
        }
    }

    private void checkPaddleCollision(Ball ball, Paddle paddle) {
        if (ball.getBounds().intersects(paddle.getBounds())) {
            ball.reverseY();
            // Важно: выталкиваем мяч, чтобы он не "залип" в платформе
            // ball.setY(paddle.getY() - ball.getHeight());
        }
    }

    private void checkBrickCollisions(Ball ball, List<Brick> bricks) {
        for (int i = 0; i < bricks.size(); i++) {
            Brick b = bricks.get(i);
            if (ball.getBounds().intersects(b.getBounds())) {
                ball.reverseY();
                bricks.remove(i);
                break;
            }
        }
    }
}