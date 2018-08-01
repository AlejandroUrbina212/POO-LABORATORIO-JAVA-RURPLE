import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import static java.nio.charset.StandardCharsets.UTF_8;
import beeper.Beeper;
import map.Map;
import robot.Robot;
import wall.Wall;

public class Main {
    public static void main (String[] args) {
        System.out.println("WELCOME TO RURPLE \n" + "The objective is to complete the map by picking all the beepers"+
                "\n"+ "The game ends once you pick all beepers or if the instructions you uploaded are donde. " );
        Map myMap = new Map();
        Scanner reader = new Scanner(System.in);
        System.out.println("Please enter the complete root of your .txt file without spaces to upload a map:\n" +"" +
                "format example: C:/Users/xxx/Desktop/map.txt");
        String mapRoot = reader.next();
        try{
        Stream<String> lines = Files.lines(
                Paths.get(mapRoot),
                UTF_8);
            AtomicInteger atomicInteger = new AtomicInteger();
        lines.forEach(line -> {
            int row = atomicInteger.getAndIncrement();
            for (int column=0; column<line.length(); column++){
                String substring = line.substring(column, column+1);
                switch (substring) {
                    case "*":
                        Wall myWall = new Wall(column, row);
                        myMap.add_wall(myWall);
                        break;
                    case "^": {
                        Robot myrobot = new Robot(column, row, "^");
                        myMap.add_robot(myrobot);
                        break;
                    }
                    case ">": {
                        Robot myrobot = new Robot(column, row, ">");
                        myMap.add_robot(myrobot);
                        break;
                    }
                    case "v": {
                        Robot myrobot = new Robot(column, row, "v");
                        myMap.add_robot(myrobot);
                        break;
                    }
                    case "<": {
                        Robot myrobot = new Robot(column, row, "<");
                        myMap.add_robot(myrobot);
                        break;
                    }
                    default:
                        int number = Integer.parseInt(substring);
                        if (number > 0) {
                            Beeper mybeeper = new Beeper(column, row, number);
                            myMap.add_beepers(mybeeper);
                        }
                        break;
                }
                myMap.setHeight(row+1);
                myMap.setWidth(column + 1);

            }
        });
            System.out.println("Your Map has been compiled and transformed. Here's Your Map: ");
            System.out.println(myMap);
        } catch (IOException exception){
            System.out.println("Error");
        }

        ArrayList<String> instructions = new ArrayList<>();
        Scanner reader2 = new Scanner(System.in);
        System.out.println("Please enter the complete root of your .txt instructions file without spaces to upload a set of instructions:\n" +"" +
                "format example: C:/xxx/xxx/xxx/instructions.txt");
        String instructionsRoot = reader2.next();
        try{
            Stream<String> lines = Files.lines(
                    Paths.get(instructionsRoot),
                    UTF_8);
            lines.forEach(e -> instructions.add(e));
        } catch (IOException exception){
            System.out.println("Error");
        }
        AtomicInteger MyAtomicInteger = new AtomicInteger(0);
        do {
            int counter = MyAtomicInteger.getAndIncrement();
            if (counter<instructions.size() ){
                if (instructions.get(counter).equals("ROTATE")){
                    myMap.getMyRobot().change_Direction();
                    System.out.println("Robot changed orientation to: "+ myMap.getMyRobot().toString());
                }
                else if (instructions.get(counter).equals("MOVE")) {
                    if (myMap.canMoveForward()) {
                        myMap.getMyRobot().moveForward();
                        System.out.println("Robot has moved to position: " + (myMap.getMyRobot().getPositionX()+1) +" , " +(myMap.getMyRobot().getPositionY()+1));
                    } else {
                        System.out.println("The robot was unable to move, there might be a wall in front of him or he is in the limit of the map");
                    }
                }
                else if(instructions.get(counter).equals("PICK")){
                    if (myMap.getMyRobot().getNumber_Beepers()<9){
                        if (myMap.canPickBeeper()){
                            myMap.getMyRobot().upgrade_Number_Beepers();
                            System.out.println("Robot picked a beeper, actual quantity of beepers: "+ myMap.getMyRobot().getNumber_Beepers());
                        }
                    }
                    else{System.out.println("Robot already has its bag full of beepers");}
                }
                System.out.println("Actual map state:\n"+ myMap);
            }

        }
        while(myMap.getNumberOfBeepers()!=0);
        System.out.println(myMap);
    }
}




