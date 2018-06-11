import java.awt.*;
import PacmanActor; 

public class Points extends InitActor{

    private PacmanActor pacman;

    public Points(Pacman game, Pacman pacman) {
        super(game);
        this.pacman = pacman;
    }

    @Override
    public void initialize() {
        loadFrames("/res/point_0.png", "/res/point_1.png"
                , "/res/point_2.png", "/res/point_3.png");

        collider = new Rectangle(0, 0, 4, 4);
    }

    private void updatePosition(int col, int row) {
        x = col * 8 - 4 - 32;
        y = (row + 3) * 8 + 1;
    }

    @Override
    public void updateGhostCaught() {
        yield:
        while (true) {
            switch (instructionPointer) {
                case 0:
                    updatePosition(game.caughtGhost.col, game.caughtGhost.row);
                    pacman.visible = false;
                    game.caughtGhost.visible = false;
                    int frameIndex = game.currentCaughtGhostScoreTableIndex;
                    frame = frames[frameIndex];
                    game.addScore(game.caughtGhostScoreTable[frameIndex]);
                    game.currentCaughtGhostScoreTableIndex++;
                    waitTime = System.currentTimeMillis();
                    instructionPointer = 1;
                case 1:
                    while (System.currentTimeMillis() - waitTime < 500) {
                        break yield;
                    }
                    pacman.visible = true;
                    pacman.updatePosition();
                    game.caughtGhost.visible = true;
                    game.caughtGhost.updatePosition();
                    game.caughtGhost.died();
                    game.setState(State.PLAYING);
                    break yield;
            }
        }
    }

    // broadcast messages

    @Override
    public void stateChanged() {
        visible = false;
        if (game.getState() == Pacman.State.GHOST_CAUGHT) {
            visible = true;
            instructionPointer = 0;
        }
    }

    public void hideAll() {
        visible = false;
    }

}

    
}

