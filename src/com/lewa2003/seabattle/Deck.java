package com.lewa2003.seabattle;

public class Deck implements IMapObject {

    private Point _position;
    private char _alive_view, _dead_view;
    private boolean _is_alive;

    public Deck(Point position, char alive_view, char dead_view){
        _position = position;
        _alive_view = alive_view;
        _dead_view = dead_view;
        _is_alive = true;
    }

    public boolean is_alive() {
        return _is_alive;
    }

    public boolean kill() {return _is_alive = false; }

    public Point get_position() {
        return _position;
    }

    public char get_view() {
        return _is_alive ? _alive_view : _dead_view;
    }
}
