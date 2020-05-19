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
public class Point {
    Double xaxis;
    Double yaxis;

    public Point(Double xaxis, Double yaxis) {
        this.xaxis = xaxis;
        this.yaxis = yaxis;
    }
    
    public String toString(){
        return "("+xaxis+", "+yaxis+")";
    }
    
}
