package simcity.LRestaurant.gui;


import simcity.LRestaurant.LCookRole;
import simcity.LRestaurant.LCustomerRole;
import simcity.LRestaurant.LWaiterRole;
//import restaurant.WaiterAgent.WaiterState;





import simcity.LRestaurant.LCookRole;

import java.awt.*;

public class LCookGui implements LGui {

    private LCookRole agent = null;
    private boolean hasFood = false;
    private boolean plateFood = false;
    private String food;
    private String name;
    LRestaurantGui gui;
    
    public enum commandState{noCommand, moving};
    private commandState command;

//    private int xPos = -20, yPos = -20;//default waiter position
//    private int xDestination = -20, yDestination = -20;//default start position
//    private int xDpos = -20, yDpos = -20;
//    private int xGCpos = -21, yGCpos = -21;
    
    private int xPos = 125, yPos = 350;//default waiter position
    private int xDestination = 125, yDestination = 350;//default start position
    private int xDpos = 125, yDpos = 350;
    private int xGCpos = 125, yGCpos = 350;
    
    private int rectSize = 10;

    public static final int xCooking = 138;
    public static final int yCooking = 350;
    
    public static final int xPlating = 125;
    public static final int yPlating = 330;
    
    public static final int xRefrig = 110;
    public static final int yRefrig = 350;
    
    private int tableNum;

    public LCookGui(LCookRole agent, String name, LRestaurantGui gui) {
        this.agent = agent;
        this.name = name;
        this.gui = gui;
        command =  commandState.noCommand;
    }
    
    
    public void setFood(String f){
    	food = f;
    }
    
    public void setPlateFood(){
    	plateFood = true;
    }
    
    public void takeFood(){
    	plateFood = false;
    }
    
    public void updatePosition() {
    	
        if (xPos < xDestination)
            xPos++;
        else if (xPos > xDestination)
            xPos--;

        if (yPos < yDestination)
            yPos++;
        else if (yPos > yDestination)
            yPos--;
        
        //notify that animation job is done
        if(xPos == xDestination && yPos == yDestination){
    		if(command == commandState.moving){
    			command = commandState.noCommand;
    			agent.msgTask();
    		}
    	}
        
        
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.pink);
        g.fillRect(xPos, yPos, rectSize, rectSize);
        g.drawString(name, xPos-20, yPos+25);
        if(hasFood){
        	g.drawString(food, xPos+16, yPos+11);
        }
        if(plateFood){
        	g.drawString(food, xPlating, yPlating-5);
        }
    }

    public boolean isPresent() {
        return true;
    }
    
    public void DoGetFood(){
    	xDestination = xRefrig;
    	yDestination = yRefrig;
    	command = commandState.moving;
    }
    
    public void DoCooking() {
//    	DoGetFood();
    	hasFood = true;
        xDestination = xCooking;
        yDestination = yCooking;
        command = commandState.moving;
    }
    
    public void DoPlating() {
    	hasFood = false;
        xDestination = xPlating;
        yDestination = yPlating;
        command = commandState.moving;
    }
    
    public void DoHome(){
    	xDestination = xDpos;
    	yDestination = yDpos;
    	command = commandState.moving;
    }
    
    
    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }
}