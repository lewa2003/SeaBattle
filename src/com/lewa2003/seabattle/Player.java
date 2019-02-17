package com.lewa2003.seabattle;

public class Player extends BasePlayer implements InputListener{

    Input _input;
    Map my_shoots;

    public Player(Game game){

        super(game);
        _input = new Input(this);
        my_shoots = new Map();
    }

    public void createShip(Point start_coords, DeckCount dc, Orientation orient) {
        //Ship ship = Ship.create(_map, DeckCount.value_of(size), o, new Point(x, y));
        add_ship(dc, orient, start_coords);


    }

    public void attack(Point attack_coords) {
    }

    public void quit_game() {
        _game.set_end_game();
    }

    @Override
    public void process(){

        if(_game.get_state() == GameState.Battle) {
            _enemy_map.draw();
            my_shoots.draw();
        }
        _map.draw();
        _input.reading(_game.get_state());
    }
}
