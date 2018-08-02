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
            ArrayList<Integer> linesLength = new ArrayList<>();
        lines.forEach(line -> {
            linesLength.add(line.length());
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
                        try {
                            int number = Integer.parseInt(substring);
                            if (number > 0) {
                                Beeper mybeeper = new Beeper(column, row, number);
                                myMap.add_beepers(mybeeper);

                            }
                        } catch (NumberFormatException ex){
                            myMap.denyCanConstruct();
                            System.out.println("'"+substring+"' was found in map and is not a supported character , Only numbers<9, zeros(spaces) and '*'(walls) are allowed in the map.");
                        }
                        break;
                }
                myMap.setHeight(row+1);
                myMap.setWidth(column + 1);
            }
            for(int i = 1; i < linesLength.size(); i++) {
                if (!linesLength.get(i).equals(linesLength.get(0))) {
                    myMap.denyCanConstruct();

                }
            }
        });

            if (myMap.getCanConstruct()){
                System.out.println("Your Map has been compiled and transformed. Here's Your Map: ");
                System.out.println(myMap);
            }

        } catch (IOException exception){
            System.out.println("Error, Specified root does not have a valid extension or does not contain a map file. ");
        }
        int counter = 0;
        if (myMap.getCanConstruct()){
            ArrayList<String> instructions = new ArrayList<>();
            Scanner reader2 = new Scanner(System.in);
            System.out.println("Please enter the complete root of your .txt instructions file without spaces to upload a set of instructions:\n" +"" +
                    "format example: C:/xxx/xxx/xxx/instructions.txt");
            String instructionsRoot = reader2.next();
            try{
                Stream<String> lines = Files.lines(
                        Paths.get(instructionsRoot),
                        UTF_8);
                lines.forEach(instructions::add);
            } catch (IOException exception){
                System.out.println("Error, Specified root does not have a valid extension or does not contain instructions");
            }
            do{
                    if (instructions.get(counter).equals("ROTATE")){
                        myMap.getMyRobot().changeDir();
                        System.out.println("Robot changed orientation to: "+ myMap.getMyRobot().toString());
                    }
                    else if (instructions.get(counter).equals("MOVE")) {
                        if (myMap.canMoveForward()) {
                            myMap.getMyRobot().moveForward();
                            System.out.println("Robot has moved to position: " + (myMap.getMyRobot().getPositionX()) +" , " +(myMap.getMyRobot().getPositionY()));
                        } else {
                            System.out.println("The robot was unable to move, there might be a wall in front of him or he is in the limit of the map");
                        }
                    }
                    else if(instructions.get(counter).equals("PICK")){

                            if (myMap.canPickBeeper()){
                                myMap.getMyRobot().upgrade_Number_Beepers();
                                System.out.println("Robot picked a beeper, actual quantity of beepers: "+ myMap.getMyRobot().getNumberOfBeepers());
                            } else{
                                System.out.println("There is no beeper to pick in specified position.");
                            }

                    }
                    System.out.println("Actual map state:\n"+ myMap);
                    counter += 1;
                } while (counter<instructions.size() && myMap.getNumberOfBeepers()!=0 );
                if (myMap.getNumberOfBeepers()==0){
                    System.out.println("Robot has picked all the beepers!");
                }
        } else {
            System.out.println("Impossible to construct Map from root : " + mapRoot+ "  due to overload of beepers per position (>9)\nor invalid character in map");
        }
    }
}




