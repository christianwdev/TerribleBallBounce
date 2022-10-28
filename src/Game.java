import com.sun.javafx.geom.Vec2d;

import javax.swing.*;
import java.util.ArrayList;

public class Game {

    private JFrame frame;
    private ArrayList<Ball> balls;

    public Game() {
        this.frame = new JFrame();
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        balls = new ArrayList<>();
    }

    public void startGame() {
        balls.add(new Ball(new Vec2d(100, 100), 50, new Vec2d(5, 5)));

        gameLoop();
    }

    public void gameLoop() {

        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
        long lastFpsTime = 0;

        while (true){
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double)OPTIMAL_TIME);

            lastFpsTime += updateLength;
            if(lastFpsTime >= 1000000000){
                lastFpsTime = 0;
            }

            for (Ball ball : balls) {
                if (ball.pos.x + ball.velocity.x >= (frame.getWidth() - ball.size * 1.5) || ball.pos.x + ball.velocity.x <= 0) {
                    ball.velocity.x *= -1;
                }

                if (ball.pos.y + ball.velocity.y >= (frame.getHeight() - ball.size * 1.5) || ball.pos.y + ball.velocity.y <= 0) {
                    ball.velocity.y *= -1;
                }

                ball.moveBall();
                frame.add(ball);
            }
            frame.repaint(); // Repaint all of our new balls

            try{
                long sleepTime = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
                Thread.sleep(sleepTime);
            } catch(Exception e){ }
        }
    }
}
