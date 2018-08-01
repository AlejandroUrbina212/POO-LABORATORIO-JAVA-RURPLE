package map;
import beeper.Beeper;
import robot.Robot;
import wall.Wall;

import java.util.ArrayList;

public class Map {
    private ArrayList<Wall> walls = new ArrayList<>();
    private ArrayList<Beeper> beepers = new ArrayList<>();
    private Robot myRobot;
    private int width;
    private int height;

    public Map() {

    }
    public Robot getMyRobot(){
        return this.myRobot;
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

    public Boolean findWall(int positionx, int positiony) {
        ArrayList<Wall> single_wall = new ArrayList<>();
        for (int i = 0; i < walls.size(); i++) {
            Wall actualWall = this.walls.get(i);
            if (actualWall.getPositionX() == positionx && actualWall.getPositionY() == positiony) {
                single_wall.add(actualWall);
            }
        }
        if (single_wall.isEmpty()) {
            return false;
        }
        return true;
    }

    public Beeper findBeeper(int positionx, int positiony) {

        ArrayList<Beeper> single_beeper = new ArrayList<>();
        for (int i = 0; i < beepers.size(); i++) {
            Beeper actualBeeper = this.beepers.get(i);
            if (actualBeeper.getPositionX() == positionx && actualBeeper.getPositionY() == positiony) {
                single_beeper.add(actualBeeper);
            }
        }
        if (single_beeper.isEmpty()) {
            return null;
        }
        return single_beeper.get(0);
    }

    public boolean canPickBeeper() {
        Beeper beeper = findBeeper(this.myRobot.getPositionX(), this.myRobot.getPositionY()); {
            if (beeper != null) {
                for (int i = 0; i < beepers.size(); i++) {
                    if (beepers.get(i).getPositionX() == beeper.getPositionX() && beepers.get(i).getPositionY() == beeper.getPositionY()) {
                        beepers.get(i).rest_beepers_picked();
                        if (beepers.get(i).getNumber_of_beepers() == 0) {
                            beepers.remove(i);
                        }
                    }
                }
                return true;
            }
            return false;
        }

    }

    public boolean canMoveForward() {
        int desiredPositionX = this.myRobot.getPositionX();
        int desiredPositionY = this.myRobot.getPositionY();

        if (this.myRobot.getDirection()==0) {
            desiredPositionY = this.myRobot.getPositionY() - 1;
        }
        else if (this.myRobot.getDirection()==1){
            desiredPositionX += 1;
        }
        else if (this.myRobot.getDirection()==2){
            desiredPositionY = this.myRobot.getPositionY() + 1;
        }
        else if (this.myRobot.getDirection()==3) {
            desiredPositionX = this.myRobot.getPositionX() - 1;
        }

        if (desiredPositionY == height || desiredPositionY == -1 || desiredPositionX == width || desiredPositionX == -1) {
            return false;
        }
        for (Wall wall : walls) {
            if (wall.getPositionX() == desiredPositionX && wall.getPositionY() == desiredPositionY) {
                return false;
            }
        }
        return true;
    }


    public void add_robot(Robot robot) {
        this.myRobot = robot;
    }

    private Beeper getBeepersInUbication(int positionx, int positiony){
        return beepers.stream()
                .filter(beeper -> beeper.getPositionX()==positionx && beeper.getPositionY() == positiony)
                .findAny()
                .orElse(null);
    }
    private boolean VerifyRobotInPosition(int positionx, int positiony){
        return myRobot.getPositionX() == positionx && myRobot.getPositionY() == positiony;
    }

    private boolean VerifyWallInPosition(int positionx, int positiony){
        return walls.stream()
                .filter(wall -> wall.getPositionX()==positionx && wall.getPositionY()==positiony)
                .count()>0;
    }
    public Integer getNumberOfWalls(){
        return this.walls.size();
    }
    public Integer getNumberOfBeepers(){
        return this.beepers.size();
    }

    @Override
    public String toString() {
        String actualMapState = "";
        for (int y=0; y<this.height; y++){
            for (int x=0; x<this.width; x++){
                Beeper beeperByUbication = getBeepersInUbication(x, y);
                boolean IsAWall = VerifyWallInPosition(x, y);
                boolean IsARobot = VerifyRobotInPosition(x,y);
                if (IsAWall){
                    actualMapState += "*";
                }
                else if (IsARobot){
                    actualMapState += myRobot;
                }
                else if (getBeepersInUbication(x, y)!= null){
                    actualMapState += String.valueOf(beeperByUbication.getNumber_of_beepers());
                }
                else {
                    actualMapState += " ";
                }
            }
            actualMapState += "\n";
        }
        return actualMapState;

    }
}


