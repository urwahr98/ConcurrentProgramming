/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrentprogramming;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JComponent;

/**
 *
 * @author aida
 */
class LineComponent extends JComponent {

    ArrayList<Line2D.Double> lines;
    Random random;

    LineComponent(int width, int height) {
        super();
        setPreferredSize(new Dimension(width,height));
        lines = new ArrayList<Line2D.Double>();
        random = new Random();
    }

    public void addLine() {
        int width = (int)getPreferredSize().getWidth();
        int height = (int)getPreferredSize().getHeight();
        Line2D.Double line = new Line2D.Double(
            random.nextInt(width),
            random.nextInt(height),
            random.nextInt(width),
            random.nextInt(height)
            );
        lines.add(line);
        repaint();
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        Dimension d = getPreferredSize();
        int R = (int) (Math.random( )*256);
        int G = (int)(Math.random( )*256);
        int B= (int)(Math.random( )*256);
        Color randomColor = new Color(R, G, B);
        g.setColor(randomColor);
        for (Line2D.Double line : lines) {
            g.drawLine(
                (int)line.getX1(),
                (int)line.getY1(),
                (int)line.getX2(),
                (int)line.getY2()
                );
        }
    }
}