package com.lewa2003.seabattle;

public class Game {

    private GameState _state;
    private BasePlayer[] _players;
    private int _current_player;
    private boolean _is_finished;

    public Game() {
        _state = GameState.Filling;

        _players = new BasePlayer[2];
        _players[0] = new Player(this);
        _players[1] = new BasePlayer(this);
        _players[0].auto_fill();
        _players[1].auto_fill();
        _current_player = 0;
    }

    public void process() {

        if(_state != GameState.Battle &&
                _players[0].is_all_ships_placed() &&
                _players[1].is_all_ships_placed()) {

            _players[0].set_enemy_map(_players[1].get_map());
            _players[1].set_enemy_map(_players[0].get_map());
            _state = GameState.Battle;

        }
        _players[_current_player].process();

        change_player();
    }

    public GameState get_state() {
        return _state;
    }

    public void set_end_game() {
        _is_finished = true;
    }

    public boolean finished(){
        return _is_finished;
    }

    private void change_player() {
        _current_player = 1 - _current_player;
    }
}
