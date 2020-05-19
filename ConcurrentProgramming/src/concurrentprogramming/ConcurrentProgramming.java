/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrentprogramming;

import java.util.ArrayList;
import java.util.HashMap;


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
        HashMap<Double, Double> map = new HashMap<>();
        ArrayList<Point> pointArr = new ArrayList<>();
        
        GeneratePoint generate = new GeneratePoint(n, map, pointArr);
        generate.start();
        

    }
    
}
