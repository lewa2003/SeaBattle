package com.lewa2003.seabattle;

public class BasePlayer {

    protected Game _game;
    protected Map _map;
    protected Map _enemy_map;
    protected java.util.List<Ship> _ships;
    protected java.util.Map<DeckCount, Integer> _ships_cnt;
    protected int _max_ships_cnt;
    protected int _alive_ships_cnt;
    protected ShipsFiller _filler;


    public BasePlayer(Game game) {
        _game = game;
        _map = new Map();
        _enemy_map = new Map();
        init_ships_cnt();
        _filler = new ShipsFiller(this);
    }

    public final void init_ships_cnt(){

        _max_ships_cnt = 0;
        _alive_ships_cnt = 0;

        _ships_cnt = new java.util.HashMap<DeckCount, Integer>();
        for(int i = 1; i < DeckCount.values().length; ++i) {
            int ship_cnt = 5 - i;
            _ships_cnt.put(DeckCount.value_of(i), ship_cnt);

            _max_ships_cnt += ship_cnt;
        }

        if(_max_ships_cnt > 0) {
            _ships = new java.util.ArrayList<Ship>();
        }
    }
    //return free places for ships
    public int get_free_places (DeckCount dc) {
        return _ships_cnt.get(dc);
    }

    public boolean add_ship(DeckCount dc, Orientation orient, Point start_coords) {

        int free_places = get_free_places(dc);

        if (free_places > 0) {

            Point[] coords = Ship.get_coords_for_ship(_map, dc, orient, start_coords);
            if(coords != null) {
                Ship ship = new Ship(_map, dc, orient, coords);
                _ships.add(ship);
                _alive_ships_cnt++;
                free_places--;
                _ships_cnt.replace(ship.get_deck_count(), free_places);

                return true;
            }
        }

        return false;
    }


    public void process() {

        if (_game.get_state() == GameState.Battle) {

        }

    }


    public boolean is_all_ships_placed(){

        return _max_ships_cnt == _ships.size();
    }

    public int get_alive_ships_cnt(){
        return _alive_ships_cnt;
    }

    public void auto_fill(){
        _filler.fill();
    }

    public Map get_map() {
        return _map;
    }

    public void set_enemy_map(Map map) {
        _enemy_map = map;
    }
}
