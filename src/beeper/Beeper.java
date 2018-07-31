package beeper;

public class Beeper {
    private int  number_of_beepers;
    private int positionX;
    private int PositionY;

    public Beeper(int positionx, int positiony, int count_of_beepers){
        this.positionX = positionx;
        this.PositionY = positiony;
        this.number_of_beepers = count_of_beepers;
    }
    public void rest_beepers_picked() {
            this.number_of_beepers -= 1;
    }

    public int getNumber_of_beepers() {
        return number_of_beepers;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return PositionY;
    }
}
