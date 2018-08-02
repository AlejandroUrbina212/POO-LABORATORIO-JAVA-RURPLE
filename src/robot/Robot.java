package robot;
public class Robot {
    private int positionX;
    private int positionY;
    private int numberOfBeepers = 0;
    private int direction = 0;

    public Robot(int positionx, int positiony, String StartDirection){
        this.positionX = positionx;
        this.positionY = positiony;
        if (StartDirection.equals("^")){
            this.direction = 0;
        }
        else if (StartDirection.equals(">")){
            this.direction = 1;
        }
        else if (StartDirection.equals("v")){
            this.direction = 2;
        }
        else if (StartDirection.equals("<")){
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



    public int getNumberOfBeepers() {
        return numberOfBeepers;
    }


    public void upgrade_Number_Beepers() {
            this.numberOfBeepers += 1;
    }

    public int getDirection() {
        return direction;
    }


    public void changeDir() {
        if (this.direction<3){this.direction += 1;}
        else this.direction = 0;


    }

    @Override
    public String toString() {
        String robot = "";
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