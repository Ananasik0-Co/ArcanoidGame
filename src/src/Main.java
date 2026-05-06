import UI.GamePanel;
import engine.PhysicsEngine;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("The Arcanoid Game");
        GamePanel panel = new GamePanel();
        frame.add(panel);
        frame.pack(); // Подгонит размер окна под панель
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // Окно по центру экрана
        frame.setVisible(true);
    }
}