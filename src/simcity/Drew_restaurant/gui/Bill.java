package simcity.Drew_restaurant.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import simcity.Drew_restaurant.CustomerRole;
import simcity.Drew_restaurant.WaiterRole;

public class Bill {
	
	Double cost;
	
	public Bill(Double c){
		cost=c;
	}
	
	public Double getCost(){
		return cost;
	}
}