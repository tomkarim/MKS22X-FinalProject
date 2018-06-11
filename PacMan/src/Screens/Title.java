import java.awt.*;
import java.awt.event.KeyEvent;

public class Title extends InitActor{

    private boolean pushSpaceToStartVisible;

    public Title(Pacman game) {
        super(game);
    }

    @Override
    public void initialize() {
        loadFrames("/res/title.png");
        x = 21;
        y = 100;
    }

    @Override
    public void updateTitle() {
        yield:
        while (true) {
            switch (instructionPointer) {
                case 0:
                    waitTime = System.currentTimeMillis();
                    instructionPointer = 1;
                case 1:
                    if (System.currentTimeMillis() - waitTime < 500) {
                        break yield;
                    }
                    instructionPointer = 2;
                case 2:
                    double dy = 100 - y;
                    y = y + dy * 0.1;
                    if (Math.abs(dy) < 1) {
                        waitTime = System.currentTimeMillis();
                        instructionPointer = 3;
                    }
                    break yield;
                case 3:
                    if (System.currentTimeMillis() - waitTime < 200) {
                        break yield;
                    }
                    instructionPointer = 4;
                case 4:
                    pushSpaceToStartVisible = ((int) (System.nanoTime() * 0.0000000075) % 3) > 0;
                    if (KeyListener.keyPressed[KeyEvent.VK_SPACE]) {
                        game.startGame();
                    }
                    break yield;
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        if (!visible) {
            return;
        }
        super.draw(g);
        if (pushSpaceToStartVisible) {
            game.drawText(g, "SPACE TO START", 37, 170);
        }
        game.drawText(g, "PROGRAMMED BY O.L. 2017", 20, 240);
        game.drawText(g, "ORIGINAL GAME BY NAMCO 1980", 5, 255);
    }


    // broadcast messages

    @Override
    public void stateChanged() {
        visible = false;
        if (game.state == Pacman.State.TITLE) {
            y = -150;
            visible = true;
            pushSpaceToStartVisible = false;
            instructionPointer = 0;
        }
    }



}
