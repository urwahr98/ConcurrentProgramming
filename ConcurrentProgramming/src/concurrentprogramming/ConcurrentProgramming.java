/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrentprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;


/**
 *
 * @author urwah
 */
public class ConcurrentProgramming {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int n = 5;
        int t;
        int m;
        int count =0;
        double xaxis,yaxis;
        
        //Hashmap to store the coordinates of Points
        HashMap<Double, Double> map = new HashMap<>();
        
        //Arraylist to store the Point created
        ArrayList<Point> pointArr = new ArrayList<>();
        
        //Creating n numbers of Point
        while(count < n ){
            Random coor = new Random();
            
            //Creating a Double coordinate with 2 decimal point from range 0.00
            //to 1000.00
            xaxis = (double)coor.nextInt(1001)+((double)coor.nextInt(100)/100);
            yaxis = (double)coor.nextInt(1001)+((double)coor.nextInt(100)/100);
            
            //Checking whether the x-axis, y-axis pair has already existed
            //if exist, ignore
            //if not, insert coordinate in hashmap and create new point
            if (!Objects.equals(map.get(xaxis), yaxis)){
                map.put(xaxis, yaxis);
                pointArr.add(new Point(xaxis, yaxis));
                count++;
            }
        }
        
        
        System.out.println(pointArr);
    }
    
}
