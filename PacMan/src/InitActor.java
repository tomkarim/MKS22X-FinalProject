
public class InitActor extends Actor<Pacman> {
    public InitActor(Pacman game){
        super(game);
    }

    @Override
    public void update(){
        switch (game.getState()){
            case Pacman.State.INITIALIZING: updateInitializing(); break;
            case Pacman.State.TITLE: updateTitle(); break;
            case Pacman.State.READY: updateReady(); break;
            case Pacman.State.READY2: updateReady2(); break;
            case Pacman.State.PLAYING: updatePlaying(); break;
            case Pacman.State.PACMAN_DIED: updatePacmanDied(); break;
            case Pacman.State.GHOST_CAUGHT: updateGhostCatched(); break;
            case Pacman.State.LEVEL_CLEARED: updateLevelCleared(); break;
            case Pacman.State.GAME_OVER: updateGameOver(); break;

        }
    }
}
