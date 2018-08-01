
package robot;

import beeper.Beeper;
import map.Map;

public class Robot {
    private int positionX;
    private int positionY;
    private int number_Beepers = 0;
    private int direction = 0;

    public Robot(int positionx, int positiony, String initial_direction){
        this.positionX = positionx;
        this.positionY = positiony;
        if (initial_direction.equals("^")){
            this.direction = 0;
        }
        else if (initial_direction.equals(">")){
            this.direction = 1;
        }
        else if (initial_direction.equals("v")){
            this.direction = 2;
        }
        else if (initial_direction.equals("<")){
            this.direction = 3;
        }
    }

    public void moveForward() {
        if (direction==1){
            this.positionX += 1;
        }
        else if (direction==3){
            this.positionX -= 1;
        }
        else if (direction==0){
            this.positionY -= 1;
        }
        else if (direction==2){
            this.positionY += 1;
        }


    }
    public int getPositionX() {
        return positionX;
    }
    public int getPositionY() {
        return positionY;
    }



    public int getNumber_Beepers() {
        return number_Beepers;
    }


    public void upgrade_Number_Beepers() {
        if (this.number_Beepers<9){
            this.number_Beepers += 1;
        }

    }

    public int getDirection() {
        return direction;
    }


    public void change_Direction() {
        if (this.direction<3){this.direction += 1;}
        else this.direction = 0;


    }






    @Override
    public String toString() {
        String robot = "";
        int direction = this.getDirection();
        if (this.direction == 0) {
            robot = "^";
        } else if (this.direction == 1) {
            robot = ">";
        } else if (this.direction == 2) {
            robot = "v";
        } else if (this.direction == 3) {
            robot = "<";
        }
        return robot;
    }
}