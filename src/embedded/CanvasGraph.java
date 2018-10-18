package embedded;

import java.awt.*;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class CanvasGraph extends Canvas{

    private int width;  
    private int height; 
    private int padding = 10;
    private int labelPadding = 10;
    private List<Integer> temp = new ArrayList<Integer>();
   
    
    public CanvasGraph(int width, int height){
        //Set the size of the canvas according to the size set in the GUI
    	this.setSize(width,height);
        this.width = width;
        this.height = height;
        this.repaint();
    }
    
    //Method to draw the graph
    public void paint(Graphics g) {
    	
    	if(this.temp!= null){
	    	g.setColor(Color.BLACK);
	    	
	    	// Set x and y axis scales
	    	double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (20);
	        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxTemp() - getMinTemp());
	
	        //Create new list for graph points and fill with temperature data values 
	        List<Point> graphPoints = new ArrayList<Point>();
	        for (int i = 0; i < temp.size(); i++) {
	            int x1 = (int) (i * xScale + padding + labelPadding);
	            int y1 = (int) ((getMaxTemp() - temp.get(i)) * yScale + padding);
	            graphPoints.add(new Point(x1, y1));
	        }
	        // create x and y axes 
	        g.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
	        g.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);
	
	        //Draw a red line between the points
	        g.setColor(Color.red);
	        for (int j = 0; j < graphPoints.size() - 1; j++) {
	            int x1 = graphPoints.get(j).x;
	            int y1 = graphPoints.get(j).y;
	            int x2 = graphPoints.get(j + 1).x;
	            int y2 = graphPoints.get(j + 1).y;
	            g.drawLine(x1, y1, x2, y2);         
	         }
	    }

    }
    
    //Method for getting the minimum temperature value
    private double getMinTemp() {
        double minTemp = Integer.MAX_VALUE;
        for (Integer item : temp) {
            minTemp = Math.min(minTemp, item);
        }
        return minTemp;
    }

    private double getMaxTemp() {
        double maxTemp = Integer.MIN_VALUE;
        for (Integer item : temp) {
            maxTemp = Math.max(maxTemp, item);
        }
        return maxTemp;
    }
    
    //Add values from server to a list
    public void addValues(int value)
    {
    	//If greater than 20 then remove the last element in the arraylist
        if(temp.size()>=19) temp.remove(0);
        
        //Add temperature point to list
     	temp.add(value);
     	this.repaint();
    }
}
