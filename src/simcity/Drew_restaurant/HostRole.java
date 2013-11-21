package simcity.Drew_restaurant;

import agent.Role;
import simcity.PersonAgent;
//import restaurant.gui.RestaurantPanel;
//import restaurant.Customer.AgentState;
//import restaurant.gui.WaiterGui;
import simcity.Drew_restaurant.interfaces.*;

import java.util.*;

/**
 * Restaurant Host Agent
 */
//We only have 2 types of agents in this prototype. A customer and an agent that
//does all the rest. Rather than calling the other agent a waiter, we called him
//the HostAgent. A Host is the manager of a restaurant who sees that all
//is proceeded as he wishes.
public class HostRole extends Role implements Host{
	public static final int NTABLES = 4;//a global for the number of tables.
	//Notice that we implement waitingCustomers using ArrayList, but type it
	//with List semantics.
	
	public int count=0;		//Keeps track of total # seated
	
	public List<Customer> waitingCustomers
	= new ArrayList<Customer>();
	
	public List<MyWaiter> waiters
	= new ArrayList<MyWaiter>();
	
	public Collection<Table> tables;
	//note that tables is typed with Collection semantics.
	//Later we will see how it is implemented

	private String name;
	//private Semaphore atTable = new Semaphore(0,true);

	public HostRole(PersonAgent p) {
		super(p);

		this.name = name;
		
		// make some tables
		tables = new ArrayList<Table>(NTABLES);
		for (int ix = 1; ix <= NTABLES; ix++) {
			Table t = new Table(ix);			
			tables.add(t);//how you add to a collections
		}
	}
	
	public void addWaiter(Waiter w){
		waiters.add(new MyWaiter(w));
		stateChanged();
	}

	public String getMaitreDName() {
		return name;
	}

	public String getName() {
		return name;
	}

	public List<Customer> getWaitingCustomers() {
		return waitingCustomers;
	}

	public Collection<Table> getTables() {
		return tables;
	}
	// Messages
	
	public void whatIsWait(Customer cust) {
		cust.wait(waitingCustomers.size());
		stateChanged();
	}
	
	public void msgIWantFood(Customer cust) {
		waitingCustomers.add(cust);
		count++;
		cust.wait(waitingCustomers.size()-1);
		stateChanged();
	}

	public void tableIsFree(int table){
		for(Table t : tables){
			if(t.tableNumber==table) t.setUnoccupied();
		}
		stateChanged();
	}
	
	public void iWantToGoOnBreak(Waiter wait){
		MyWaiter mw=null;
		for(MyWaiter w : waiters){
			if(w.waiter.equals(wait)) mw=w;
		}
		checkForBreak(mw);
	}
	
	public void backFromBreak(Waiter wait){
		MyWaiter mw=null;
		for(MyWaiter w : waiters){
			if(w.waiter.equals(wait)) mw=w;
		}
		mw.onBreak=false;
	}
	
	public void leaving(Customer cust){
		waitingCustomers.remove(cust);
	}

	/**
	 * Scheduler.  Determine what action is called for, and do it.
	 */
	public boolean pickAndExecuteAnAction() {
		/* Think of this next rule as:
            Does there exist a table and customer,
            so that table is unoccupied and customer is waiting.
            If so seat him at the table.
		 */
		
		for (Table table : tables) {
			if (!table.isOccupied()) {
				if (!waitingCustomers.isEmpty() & !waiters.isEmpty()) {
					seatCustomer(waitingCustomers.get(0), table);
					return true;//return true to the abstract agent to reinvoke the scheduler.
				}
			}
		}

		return false;
		//we have tried all our rules and found
		//nothing to do. So return false to main loop of abstract agent
		//and wait.
	}

	// Actions

	private void seatCustomer(Customer customer, Table table) {
		MyWaiter MW=waiters.get(count%waiters.size());
		while(MW.onBreak){
			count++;
			MW=waiters.get(count%waiters.size());
		}
		DoSeatCustomer(customer, table, MW);
		table.setOccupant(customer);
		waitingCustomers.remove(customer);
	}

	private void DoSeatCustomer(Customer customer, Table table, MyWaiter MW) {
		print("Telling waiter " + MW.waiter.getName() + " to seat " + customer + " at " + table);   //ADD WHICH WAITER YOUR TELLING		
		customer.setWaiter(MW.waiter);
		MW.waiter.sitAtTable(customer, table.tableNumber); 
	}
	
	private void checkForBreak(MyWaiter mw){
		int workingWaiters=0;
		for(MyWaiter waiter : waiters){
			if(!waiter.onBreak) workingWaiters++;
		}
		if (workingWaiters<=1){
			mw.waiter.breakResponse(false);
			print("You can not go on break right now");
		}
		else{
			mw.waiter.breakResponse(true);
			mw.onBreak=true;
			print("Go on break when you finish with current customers");
		}
	}

	//utilities

	private class MyWaiter{
		Waiter waiter;
		boolean onBreak;
		
		MyWaiter(Waiter w){
			waiter=w;
			onBreak=false;
		}
	}
	
	public class Table {
		Customer occupiedBy;
		int tableNumber;

		Table(int tableNumber) {
			this.tableNumber = tableNumber;
		}

		void setOccupant(Customer cust) {
			occupiedBy = cust;
		}

		void setUnoccupied() {
			occupiedBy = null;
		}

		Customer getOccupant() {
			return occupiedBy;
		}
		

		boolean isOccupied() {
			return occupiedBy != null;
		}

		public String toString() {
			return "table " + tableNumber;
		}
		
		
	}
}

