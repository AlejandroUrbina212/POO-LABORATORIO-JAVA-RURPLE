package map;

import beeper.Beeper;
import robot.Robot;
import wall.Wall;

import java.util.ArrayList;

public class Map {
    public ArrayList<Wall> walls = new ArrayList<>();
    public ArrayList<Beeper> beepers = new ArrayList<>();
    public Robot robot;
    private int width;
    private int height;

    public Map() {

    }

    public Map(int width_dimension, int height_dimension) {
        this.width = width_dimension;
        this.height = height_dimension;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean add_wall(Wall wall) {
        if (this.walls.contains(wall)) {
            return false;
        }
        this.walls.add(wall);
        return true;
    }

    public boolean add_beepers(Beeper beeper) {
        if (this.beepers.contains(beeper)) {
            return false;
        }
        this.beepers.add(beeper);
        return true;
    }

    public void add_robot(Robot robot) {
        this.robot = robot;
    }
    //hacer un verificador que permita saber si la posición del robot en x o y mas o menos 1 según la posición se encuentra
    //la posición de alguna de las paredes del mapa y que devuelva 1 si es una pared, que revise en el mapa si es una
    //de beepers y si si, que le informe al usuario. y devuelva 2 y si es un espacio en blanco que devuelva 0
    //también que verifique si no se pasa de la longitud y amplitud del mapa
    //para cuando toque la instruccion pick, verificar que las posiciones en x y y del robot y del beeper sean los mismos
    // y llamar a la función de restar beepers  del beeper y a la de aumentar del robto.


}

