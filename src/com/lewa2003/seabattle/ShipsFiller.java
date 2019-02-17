package com.lewa2003.seabattle;

import java.util.Random;

public class ShipsFiller {

    private BasePlayer _player;

    public ShipsFiller(BasePlayer player) {
        _player = player;
    }

    public void fill() {

        Random rand = new Random();

        for(int i = 1; i < DeckCount.values().length; ++i) {

            DeckCount decks = DeckCount.value_of(i);

            while(_player.get_free_places(decks) > 0) {
                Orientation orient = rand.nextInt(2) > 0 ? Orientation.Horizontal
                        : Orientation.Vertical;

                int x = rand.nextInt(10);
                int y = rand.nextInt(10);

                Point coord = new Point(x, y);

                _player.add_ship(decks, orient, coord);
            }
        }
    }
}
