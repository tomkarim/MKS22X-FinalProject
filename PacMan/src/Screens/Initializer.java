
public class Initializer extends InitActor {

    public Initializer(Pacman game) {
        super(game);
    }

    @Override
    public void updateInitializing() {
        yield:
        while (true) {
            switch (instructionPointer) {
                case 0:
                    waitTime = System.currentTimeMillis();
                    instructionPointer = 1;
                case 1:
                    if (System.currentTimeMillis() - waitTime < 3000) {
                        break yield;
                    }
                    instructionPointer = 2;
                case 2:
                    game.setState(Pacman.State.OL_PRESENTS);
                    break yield;
            }
        }
    }
}
