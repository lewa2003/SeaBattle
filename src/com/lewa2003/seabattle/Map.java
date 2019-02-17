package com.lewa2003.seabattle;

import java.util.logging.Logger;

interface IMapObject {

    Point get_position();

    char get_view();

    boolean kill();
}


public class Map {

    public static final Logger log = Logger.getLogger(Map.class.getName());

    public final static int With = 10;
    public final static int Height = 10;

    private char[] _head;
    private char[][] _cells;
    private IMapObject[][] _objects;

    Point[] _neighbours;

    public Map() {
        _head = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        _cells = new char[With][Height];
        _objects = new IMapObject[With][Height];

        for (int y = 0; y < Height; ++y) {
            for (int x = 0; x < With; ++x) {
                _cells[x][y] = ' ';
            }
        }

        _neighbours = new Point[8];
        _neighbours[0] = new Point(-1, 1);
        _neighbours[1] = new Point(-1, 0);
        _neighbours[2] = new Point(-1, -1);
        _neighbours[3] = new Point(-0, 1);
        _neighbours[4] = new Point(0, -1);
        _neighbours[5] = new Point(1, 1);
        _neighbours[6] = new Point(1, 0);
        _neighbours[7] = new Point(1, -1);
    }

    public void add_object(IMapObject object) {

        Point position = object.get_position();
            if(is_valid_coords(position)) {
                 _objects[position.x][position.y] = object;
            }
            else {
                log.warning("can't add map object" );
            }
    }

    public boolean change_object(Point coords) {
        if(_objects[coords.x][coords.y] != null) {
            return _objects[coords.x][coords.y].kill();
        }
        return false;
    }

    public boolean is_valid_coords(Point point) {
        return point.x >= 0 && point.x < With && point.y >= 0 && point.y < Height;
    }


    public boolean is_collide(Point position) {
        return _objects[position.x][position.y] != null;
    }

    public boolean has_neighbours(Point position) {
        boolean result = false;
        for (Point p : _neighbours){
            Point neighbour = new Point(position.x + p.x, position.y + p.y);

            if (is_valid_coords(neighbour) && _objects[neighbour.x][neighbour.y] != null){
                result = true;
            }
        }
        return result;
    }


    public void clean() {
        for (int y = 0; y < Height; ++y) {
            for (int x = 0; x < With; ++x) {
                _cells[x][y] = ' ';
            }
        }
    }


    private void map_update() {
        clean();

        for (int y = 0; y < Height; ++y) {
            for (int x = 0; x < With; ++x) {
                IMapObject object = _objects[x][y];

                if (object != null) {
                    Point position = object.get_position();
                    _cells[position.x][position.y] = object.get_view();
                }
            }
        }
    }

    public void draw() {

        map_update();

        System.out.print("  ");
        for(int x = 0; x < With; ++x){
            System.out.print(' ');
            System.out.print(_head[x]);
        }
        System.out.println();


        for(int y = 0; y < Height; ++y) {
            if(y+1 < Height){
                System.out.print(' ');
            }
            System.out.print(y+1);

            for(int x = 0; x < With; ++x) {
                System.out.print(' ');
                System.out.print(_cells[x][y]);
            }
            System.out.println('|');
        }
    }
}
