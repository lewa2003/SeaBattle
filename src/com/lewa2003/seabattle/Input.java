package com.lewa2003.seabattle;

import java.util.Scanner;


interface InputListener {

    void createShip(Point start_coords, DeckCount size, Orientation o);

    void attack(Point attack_coords);

    void quit_game();
}


public class Input {

    private Scanner _scan;
    private InputListener _listener;


    public Input(InputListener listener) {
        _scan = new Scanner(System.in);
        _listener = listener;
    }


    void input_message(GameState state){
        if(state == GameState.Filling){
            System.out.print("add ship (format: XY V/H decks): ");
        }
        else {
            System.out.print("attack ship (format: XY): ");
        }
    }
    public void reading(GameState state) {

        input_message(state);
        String in = _scan.nextLine();

        boolean quit_game = "q".equals(in) || "quit".equals(in);


        if ( _listener != null){
            if (quit_game){
                _listener.quit_game();
                return;
            }
            else if(state == GameState.Filling){
                fill_process(in);
            }
            else if (state == GameState.Battle){
                game(in);
            }

        }
    }

    private Point parse_coords(String in) {
        int x, y;

        String [] coords = new String[2];
        coords[0] = in.substring(0, 1);
        coords[1] = in.substring(1);

        char symbol = Character.toLowerCase(coords[0].charAt(0));
        x = symbol - 'a';
        y = Integer.parseInt(coords[1]) - 1;

        return new Point(x, y);
    }

    private void fill_process(String in){


        String [] chunks = in.split(" ");

        Point c = parse_coords(chunks[0]);

        int size = Integer.parseInt(chunks[2]);
        Orientation orient = Orientation.None;

        if("H".equals(chunks[1]) || "h".equals(chunks[1])){
            orient = Orientation.Horizontal;
        }
        else if("V".equals(chunks[1]) || "v".equals(chunks[1])){
            orient = Orientation.Vertical;
        }
        if(orient != Orientation.None && c.x >= 0 && c.y >= 0) {
            _listener.createShip(c, DeckCount.value_of(size), orient);
        }
    }

    private void game(String in) {

        Point c = parse_coords(in);

        if (c.x >= 0 && c.y >= 0) {
            _listener.attack(c);
        }
    }
}
