package engine;

import entities.*;
import java.util.List;

public class PhysicsEngine {

    private final int width;
    private final int height;
    public int score = 0;

    public PhysicsEngine(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int update(Ball ball, Paddle paddle, List<Brick> bricks) {

        ball.move();

        checkWallCollisions(ball);

        checkPaddleCollision(ball, paddle);

        checkBrickCollisions(ball, bricks);

        return checkBrickCollisions(ball, bricks);
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

            ball.setY(paddle.getY() - ball.getHeight());

            double paddleCenter = paddle.getX() + paddle.getWidth() / 2.0;
            double ballCenter = ball.getX() + ball.getWidth() / 2.0;
            double relativeHit = (ballCenter - paddleCenter) / (paddle.getWidth() / 2.0);

            double baseSpeed = 7.0;
            double maxAngleFactor = 0.75;

            double newDX = relativeHit * (baseSpeed * maxAngleFactor);
            double newDY = Math.sqrt(baseSpeed * baseSpeed - newDX * newDX);

            ball.setDX(newDX);
            ball.setDY(-newDY);
        }
    }

    private int checkBrickCollisions(Ball ball, List<Brick> bricks) {
        for (int i = 0; i < bricks.size(); i++) {
            Brick b = bricks.get(i);
            if (ball.getBounds().intersects(b.getBounds())) {
                ball.reverseY();
                bricks.remove(i);
                score += 10;
                return 1;
            }
        }
        return 0;
    }
}