package beeper;

public class Beeper {
    private int numberOfBeepers;
    private int positionX;
    private int PositionY;

    public Beeper(int positionx, int positiony, int count_of_beepers){
        this.positionX = positionx;
        this.PositionY = positiony;
        this.numberOfBeepers = count_of_beepers;

    }
    public void restBeepersPicked() {
            this.numberOfBeepers -= 1;
    }

    public int getNumberOfBeepers() {
        return numberOfBeepers;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return PositionY;
    }

}

