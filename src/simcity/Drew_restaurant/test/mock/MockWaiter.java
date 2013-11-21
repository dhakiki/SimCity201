package simcity.Drew_restaurant.test.mock;


import java.util.ArrayList;
import java.util.List;

import simcity.Drew_restaurant.WaiterRole.MyCustomer;
import simcity.Drew_restaurant.gui.WaiterGui;
import simcity.Drew_restaurant.interfaces.Cashier;
import simcity.Drew_restaurant.interfaces.Cook;
import simcity.Drew_restaurant.interfaces.Customer;
import simcity.Drew_restaurant.interfaces.Waiter;
import simcity.Drew_restaurant.interfaces.Host;

/**
 * A sample MockCustomer built to unit test a CashierAgent.
 *
 * @author Monroe Ekilah
 *
 */
public class MockWaiter extends Mock implements Waiter {
	


	//Data
	public Cashier cashier;
	public Host host;
	public Customer customer;
	public EventLog log;
	public String name;
	public double debt;
	public WaiterGui waitergui;
	
	public List<MyCustomer> customers
	= new ArrayList<MyCustomer>();
	
	//functions
	public MockWaiter(String n) {
		super(n);
		//name=n;
	}
	
	public void sitAtTable(Customer c, int table){
		
	}
	
	public void readyToOrder(Customer c){
		
	}
	
	public void heresMyChoice(Customer c, String ch){
		
	}
	
	public void orderDone(int table, String choice){
		
	}
	
	public void DoneEating(Customer c){
		
	}
	
	public void outOf(String choice, int Table){
		
	}

	public void msgAtDest(){
		
	}

	public void breakResponse(boolean Response){
		
	}

	public void heresBill(Double bill, int table){
		System.out.println("Waiter: Bill is for $"+bill);
	}
	
	//Get & Set
	
	public void addCashier(Cashier c){
		
	}
	
	public void setHost(Host host){
		
	}
	
	public void setCook(Cook cook){
		
	}

	public String getName(){
		return name;
	}
	
	public WaiterGui getGui(){
		return waitergui;
	}

	public List<MyCustomer> getWaitingCustomers(){
		return customers;
	}

}
