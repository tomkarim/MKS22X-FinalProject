import java.util.*;
import java.io.*;
import java.awt.*;
import javax.imageio.*;

public class Actor {
    public class Actor<Typ extends Game> {

        public static final boolean DRAW_COLLIDER = false;

        public Typ game;
        public double x, y;
        public boolean visible;
        public BufferedImage frame;
        public BufferedImage[] frames;
        public Rectangle collider;

        protected int instructionPointer;
        protected long waitTime;


        public Actor(Typ game) {
            this.game = game;
        }

        public void init() {
        }

        public void update() {
        }

        public void draw(Graphics2D graph) {
            if (!visible) {
                return;
            }
            if (frame != null) {
                graph.drawImage(frame, (int) x, (int) y, frame.getWidth(), frame.getHeight(), null);
            }
            if (DRAW_COLLIDER && collider != null) {
                updateCollider();
                graph.setColor(Color.RED);
                graph.draw(collider);
            }
        }

        protected void loadFrames(String ... framesRes) {
            try {
                frames = new BufferedImage[framesRes.length];
                for (int i = 0; i < framesRes.length; i++) {
                    String frameRes = framesRes[i];
                    frames[i] = ImageIO.read(getClass().getResourceAsStream(frameRes));
                }
                frame = frames[0];
            } catch (IOException ex) {
                Logger.getLogger(Actor.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(-1);
            }
        }

        public void updateCollider() {
            if (collider != null) {
                collider.setLocation((int) x, (int) y);
            }
        }

    }
    
}