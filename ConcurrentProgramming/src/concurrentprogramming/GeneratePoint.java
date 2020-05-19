/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrentprogramming;

/**
 *
 * @author urwah
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.Objects;

public class GeneratePoint {
    int noOfPoints;
    Double xaxis;
    Double yaxis;
    int count = 0;
    HashMap<Double, Double> map;
    ArrayList<Point> pointArr;
    
    public GeneratePoint(int noOfPoints, HashMap<Double, Double> map, ArrayList<Point> pointArr) {
        this.noOfPoints = noOfPoints;
        this.map = map;
        this.pointArr = pointArr;
    }
    
    public void start(){
        while(count < noOfPoints ){
            Random coor = new Random();
            xaxis = (double)coor.nextInt(1001)+((double)coor.nextInt(100)/100);
            yaxis = (double)coor.nextInt(1001)+((double)coor.nextInt(100)/100);
            if (!Objects.equals(map.get(xaxis), yaxis)){
                map.put(xaxis, yaxis);
                pointArr.add(new Point(xaxis, yaxis));
                count++;
            }
            
        }
    }
    
}
