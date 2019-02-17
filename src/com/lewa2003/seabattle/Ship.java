package com.lewa2003.seabattle;

enum DeckCount {
    Invalid(-1), One(1), Two(2), Three(3), Four(4);

    private final int _value;

    DeckCount(int value) {
        this._value = value;
    }

    private static DeckCount[] _map;
    static {
        _map = new DeckCount[5];

        int cnt = 0;
        for(DeckCount d : DeckCount.values()){
            _map[cnt++] = d;
        }
    }


    public int get_value(){
        return _value;
    }

    public static DeckCount value_of(int i) {
        if(i >= 0 && i < 5) {
            return _map[i];
        }
        return Invalid;
    }
}

public class Ship {

    private Deck[] _decks;
    private DeckCount _deck_count;
    private Orientation _orientation;
    private Point[] _coords;

    public Ship() {
        _decks = null;
        _deck_count = DeckCount.Invalid;
        _orientation = Orientation.None;
        _coords = null;
    }

    public Ship(Map map, DeckCount deck_count, Orientation orientation, Point[] coords) {
        _deck_count = deck_count;
        _orientation = orientation;
        _coords = coords;
        _decks = new Deck[deck_count.get_value()];

        for(int i = 0; i < _decks.length; ++i) {
            Deck deck = new Deck (_coords[i], 'O', 'X');
            _decks[i] = deck;
            map.add_object(deck);
        }
    }

    public boolean is_valid(){
        return _coords != null &&
               _deck_count != DeckCount.Invalid &&
               _orientation != Orientation.None;
    }

    public DeckCount get_deck_count() {
        return _deck_count;
    }

    public boolean is_alive(){

        boolean is_alive = false;

        for(Deck deck : _decks) {
            if(deck.is_alive()) {
                is_alive = true;
            }
        }
        return is_alive;
    }

    public static Point[] get_coords_for_ship(Map map, DeckCount deck_count, Orientation orientation, Point start_coord) {

        Point step = orientation.get_direction();

        boolean is_possible_to_place = true;

        Point[] coords = new Point[deck_count.get_value()];
        Point position = new Point(start_coord.x, start_coord.y);
        for(int i = 0; i < deck_count.get_value(); ++i) {

            if(!map.is_valid_coords(position) || map.is_collide(position) ||
                    map.has_neighbours(position)) {
                is_possible_to_place = false;
            }

            coords[i] = new Point(position.x, position.y);
            position.x += step.x;
            position.y += step.y;
        }

        if(is_possible_to_place) {
            return coords;
        }

        return null;
    }


 }
