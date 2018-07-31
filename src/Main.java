import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.SignStyle;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import static java.nio.charset.StandardCharsets.UTF_8;

import beeper.Beeper;
import map.Map;
import robot.Robot;
import wall.Wall;

public class Main {
    public static void main (String[] args) {
        Map myMap = new Map();
        try{
        Stream<String> lines = Files.lines(
                Paths.get("C:/Users/luis2/Desktop/POO-LABORATORIO-JAVA-JUEGO/src/mapa1.txt"),
                UTF_8);
            AtomicInteger atomicInteger = new AtomicInteger();
        lines.forEach(line -> {
            System.out.println(line);
            int row = atomicInteger.incrementAndGet();
            for (int column=0; column<line.length(); column++){
                String substring = line.substring(column, column+1);
                if (substring.equals("*")){
                    Wall myWall = new Wall(column, row);
                    myMap.add_wall(myWall);
                }
                else if (substring.equals("^")){
                    Robot myrobot = new Robot(column,row,"^");
                    myMap.add_robot(myrobot);
                }
                else if (substring.equals(">")){
                    Robot myrobot = new Robot(column,row,">");
                    myMap.add_robot(myrobot);
                }
                else if (substring.equals("v")){
                    Robot myrobot = new Robot(column,row,"v");
                    myMap.add_robot(myrobot);
                }
                else if (substring.equals("<")){
                    Robot myrobot = new Robot(column,row,"<");
                    myMap.add_robot(myrobot);
                }
                else {
                    int number = Integer.parseInt(substring);
                    if (number>0){
                        Beeper mybeeper = new Beeper(column, row, number);
                        myMap.add_beepers(mybeeper);
                    }
                }
                myMap.setHeight(row);
                myMap.setWidth(column + 1);

            }

        });
        } catch (IOException exception){
            System.out.println("Error");
        }
        System.out.println(myMap.getHeight());
        System.out.println(myMap.getWidth());
        System.out.println(myMap.walls.size());
        System.out.println(myMap.beepers.size());
        System.out.println(myMap.robot.getPositionX());
        System.out.println(myMap.robot.getPositionY());
        System.out.println(myMap.robot.getDirection());

    }
}
