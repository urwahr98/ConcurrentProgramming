/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrentprogramming;

import java.util.ArrayList;

/**
 *
 * @author urwah
 */
public class Point {
    Double xaxis;
    Double yaxis;

    public Point(Double x, Double y) {
        xaxis = x;
        yaxis = y;
    }
    
    public String toString(){
        return "("+xaxis+", "+yaxis+")";
    }
    
}
