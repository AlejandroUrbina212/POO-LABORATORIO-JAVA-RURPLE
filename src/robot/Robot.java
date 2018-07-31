package robot;

public class Robot {
    private int positionX;
    private int positionY;
    private int number_Beepers = 0;
    private int direction = 0;

    public Robot(int positionx, int positiony, String initial_direction){
        this.positionX = positionx;
        this.positionY = positiony;
        if (initial_direction=="^"){
            this.direction = 0;
        }
        else if (initial_direction==">"){
            this.direction = 1;
        }
        else if (initial_direction=="v"){
            this.direction = 2;
        }
        else if (initial_direction=="<"){
            this.direction = 3;
        }
    }

    public void move_in_x() {
        if (direction==1){
            this.positionX += 1;
        }
        else {
            this.positionX -= 1;
        }

    }
    public void move_in_y() {
        if (direction==0){
            this.positionY += 1;
        }
        else {
            this.positionY -= 1;
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
        this.number_Beepers +=1;
    }

    public int getDirection() {
        return direction;
    }

    public void change_Direction() {
        this.direction += 1;
    }

    @Override
    public String toString() {
        int direction = this.getDirection();
        switch (direction){
            case 0:
                return "^";
            case 1:
                return ">";
            case 2:
                return "v";
            case 3:
                return "<";
        }
        return super.toString();
    }
}