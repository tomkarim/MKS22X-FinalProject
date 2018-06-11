import java.awt.*;
import java.util.*;
import java.lang.*;

public class Display extends Canvas{
    private Game game;
    private boolean running;
    private BufferStrategy bs;

    public Display(Game game) {
        this.game = game;
        int sx = (int) (game.screenSize.width * game.screenScale.getX());
        int sy = (int) (game.screenSize.height * game.screenScale.getY());
        setPreferredSize(new Dimension(sx, sy));
        addKeyListener(new KeyListener());
    }

    public void start() {
        if (running) {
            return;
        }
        createBufferStrategy(3);
        bs = getBufferStrategy();
        game.initialize();
        running = true;
        Thread thread = new Thread(new MainLoop());
        thread.start();
    }

    private class MainLoop implements Runnable {

        @Override
        public void run() {
            long desiredFrameRateTime = 1000 / 60;
            long currentTime = System.currentTimeMillis();
            long lastTime = currentTime - desiredFrameRateTime;
            long unprocessedTime = 0;
            boolean needsRender = false;
            while (running) {
                currentTime = System.currentTimeMillis();
                unprocessedTime += currentTime - lastTime;
                lastTime = currentTime;

                while (unprocessedTime >= desiredFrameRateTime) {
                    unprocessedTime -= desiredFrameRateTime;
                    update();
                    needsRender = true;
                }

                if (needsRender) {
                    Graphics2D graph = (Graphics2D) bs.getDrawGraphics();
                    graph.setBackground(Color.BLACK);
                    graph.clearRect(0, 0, getWidth(), getHeight());
                    graph.scale(game.screenScale.getX(), game.screenScale.getY());
                    draw(graph);
                    graph.dispose();
                    bs.show();
                    needsRender = false;
                }
                else {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }

    }

    public void update() {
        game.update();
    }

    public void draw(Graphics2D graph) {
        game.draw(graph);
    }

}

