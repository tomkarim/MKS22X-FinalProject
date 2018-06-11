import java.awt.*;

public class Dots extends InitActor {
    private int col;
    private int row;

    public Dots(Pacman game, int col, int row) {
        super(game);
        this.col = col;
        this.row = row;
    }

    @Override
    public void initialize() {
        loadFrames("/res/food.png");
        x = col * 8 + 3 - 32;
        y = (row + 3) * 8 + 3;
        collider = new Rectangle(0, 0, 2, 2);
    }

    @Override
    public void updatePlaying() {
        if (game.checkCollision(this, PacmanActor.class) != null) {
            visible = false;
            game.currentFoodCount--;
            game.addScore(10);
            //System.out.println("current food count: " + game.currentFoodCount);
        }
    }

    @Override
    public void draw(Graphics2D g) {
        if (!visible) {
            return;
        }
        g.setColor(Color.WHITE);
        g.fillRect((int) (x), (int) (y), 2, 2);
    }

    // broadcast messages

    @Override
    public void stateChanged() {
        if (game.getState() == PacmanGame.State.TITLE) {
            visible = false;
        }
        else if (game.getState() == PacmanGame.State.READY) {
            visible = true;
        }
    }

    public void hideAll() {
        visible = false;
    }

}
