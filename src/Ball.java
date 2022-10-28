import com.sun.javafx.geom.Vec2d;

import javax.swing.*;
import java.awt.*;

public class Ball extends JComponent {

    int size;
    Vec2d pos;
    Vec2d velocity;

    public Ball(Vec2d pos, int size, Vec2d velocity) {
        this.pos = pos;
        this.size = size;
        this.velocity = velocity;
    }

    public void moveBall() {
        pos.x += velocity.x;
        pos.y += velocity.y;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.fillOval((int) pos.x, (int) pos.y, size, size);
    }

}
