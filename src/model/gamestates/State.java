package model.gamestates;

import model.Game;
import model.AudioPlayer;
import view.MenuButton;

import java.awt.event.MouseEvent;

public class State {
    protected Game game;
    public State(Game game){
        this.game=game;
    }

    public boolean isIn(MouseEvent e, MenuButton mb) {
        return mb.getBounds().contains(e.getX(), e.getY());
    }
    public Game getGame() {
        return game;
    }
    public void setGamState(Gamestate state){
        switch (state){
            case MENU -> game.getAudioPlayer().playSong(AudioPlayer.MENU_1);
            case PLAYING -> game.getAudioPlayer().setLevelSong(game.getPlaying().getLevelManager().getInnext());
        }
        Gamestate.state = state;
    }
}
