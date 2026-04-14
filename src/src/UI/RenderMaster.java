package UI;

import entities.*;
import java.awt.*;
import java.util.List;

 public class RenderMaster
 {
     public void render(Graphics g, Ball ball, Paddle paddle, List<Brick> bricks, GameState gameState)
     {
         // TODO нарисовать фон

         for(Brick brick : bricks)
         {
             drawBricks(g, brick);
         }

         drawPaddle(g, paddle);

         drawBall(g, ball);

         switch(gameState)
         {
             case MENU:
                 drawOverlay(g, "ARCANOID", "Press ENTER to Start", gameState);
                 break;
             case PAUSED:
                 drawOverlay(g, "PAUSED", "Press ESC to Resume", gameState);
                 break;
             case GAME_OVER:
                 drawOverlay(g, "GAME OVER", "Press R to Restart", gameState);
                 break;
         }
     }

     private void drawOverlay(Graphics g, String title, String subtitle, GameState gameState)
     {
         g.setColor(new Color(0, 0, 0, 150));
         g.fillRect(0,0,600,400);

         g.setColor(Color.white);
         g.setFont(new Font("Arial", Font.BOLD, 40));

         if (GameState.PAUSED == gameState) g.drawString(title, 225, 200);
         if (GameState.GAME_OVER == gameState) g.drawString(title, 190, 200);
         if (GameState.MENU == gameState) g.drawString(title, 200, 200);

         g.setFont(new Font("Arial", Font.PLAIN, 20));
         g.drawString(subtitle, 210, 230);
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