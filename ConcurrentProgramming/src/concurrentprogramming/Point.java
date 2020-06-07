/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrentprogramming;


import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author urwah
 */
public class Point {
    Double xaxis;
    Double yaxis;
    AtomicBoolean isPair;

    public Point(Double x, Double y) {
        xaxis = x;
        yaxis = y;
        
        //to prevent race condition
        isPair = new AtomicBoolean(false);
    }
    
    public String isPair(){
        if(isPair.get())
            return "Paired";
        else
            return "Not Paired";
    }
    
    public String toString(){
        return "("+xaxis+", "+yaxis+": "+isPair()+" )";
    }
    
}
