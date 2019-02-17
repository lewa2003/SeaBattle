package com.lewa2003.seabattle;

enum Orientation {
    None(-1),
    Horizontal(0),
    Vertical(1);

    private final int value;

    Orientation(int value) {
        this.value = value;
    }

    public Point get_direction() {
        return value > 0 ? new Point(0, 1) : new Point(1, 0);
    }
}

enum GameState {
    Filling,
    Battle
}


public class SeaBattle {
    public static void main(String args[]){

        Game game = new Game();

        do {
            game.process();
/*            System.out.println("-------- My map -------");
            playerMap.draw();

            System.out.println("------ Enemy map ------");
            enemyMap.draw();

            in.reading(gs); */
        }
        while(!game.finished());
    }
}
