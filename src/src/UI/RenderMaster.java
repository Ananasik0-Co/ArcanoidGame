package UI;

import entities.*;
import java.awt.*;
import java.util.List;

 public class RenderMaster
 {
     public void render(Graphics g, Ball ball, Paddle paddle, List<Brick> bricks)
     {
         // TODO нарисовать фон

         for(Brick brick : bricks)
         {
             drawBricks(g, brick);
         }

         drawPaddle(g, paddle);

         drawBall(g, ball);
     }

     private void drawBricks(Graphics g, Brick brick)
     {
         g.setColor(brick.getColor());
         g.fillRect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());

         g.setColor(Color.DARK_GRAY);
         g.drawRect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
     }

     private void drawPaddle(Graphics g, Paddle paddle)
     {
         g.setColor(paddle.getColor());
         g.fillRoundRect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight(), 10, 10);


     }

     private void drawBall(Graphics g, Ball ball)
     {
         g.setColor(ball.getColor());
         g.fillOval(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
     }

 }