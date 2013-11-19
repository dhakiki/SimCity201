package simcity.Market;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import simcity.PersonAgent;
import simcity.interfaces.MarketCashier;
import simcity.interfaces.MarketCustomer;
import simcity.interfaces.MarketManager;
import simcity.test.mock.LoggedEvent;
import simcity.test.mock.MockMarketManager;
import agent.Role;
import simcity.test.mock.EventLog;

public class MarketCustomerRole extends Role implements MarketCustomer{

	public List<MFoodOrder> order = Collections.synchronizedList(new ArrayList<MFoodOrder>());
	private double myCheck;
	
	public enum customerState { talkToManager, timeToOrder, waiting, paying, done, storeClosed, pending }
	public customerState state;
	
	private MarketCashier mc;
	private MarketManager manager;
	
    public EventLog log;

	public MarketCustomerRole(PersonAgent p) {
		super(p); 
	}
	
	
	// messages
	
	public void msgGoToCashier(MarketCashier c) {
		LoggedEvent e = new LoggedEvent("told to go to cashier");
        log.add(e);
		mc = c;
		state = customerState.timeToOrder;	
        log = new EventLog();

		stateChanged();
	}
	
	public void msgHereIsOrderAndCheck(List<MFoodOrder> canGive, double check) {
		LoggedEvent e = new LoggedEvent("got food and check");
        log.add(e); 
		myCheck = calculateBill(canGive);
		updateMyFood(canGive);
		state = customerState.paying;
		stateChanged();
	}

	public void msgMarketClosed() {
		LoggedEvent e = new LoggedEvent("told market is closed");
        log.add(e);
		state = customerState.storeClosed;
		stateChanged();
	}
	
	//scheduler
	public boolean pickAndExecuteAnAction() {
		if ( state == customerState.talkToManager) {
			goToManager();
			return true; 
		}
		if ( state == customerState.timeToOrder ) {
			orderFood();
			return true;
		}
		if ( state == customerState.paying ) {
			payCheck();
			return true;
		}
		if ( state == customerState.storeClosed ) {
			leaveStore();
			return true;
		}
		
		return false;
	}


	// actions
	private void goToManager() {
		LoggedEvent e = new LoggedEvent("telling manager I'm here");
        log.add(e);
		manager.msgIAmHere(this, "customer");
	}
	
	private void orderFood() {
		state = customerState.waiting;
		LoggedEvent e = new LoggedEvent("telling cashier my order");
        log.add(e);
		
/************fix this message******************************************/
		mc.msgOrder(this, order, "");
	}

	private void payCheck() {
		 LoggedEvent e = new LoggedEvent("paying check");
         log.add(e);
		state = customerState.done;
		mc.msgHereIsPayment(this, myCheck);
	}
	
	private void leaveStore() {
		 LoggedEvent e = new LoggedEvent("leaving market");
         log.add(e);
		state = customerState.done;
		super.isActive = false;
		DoGoHome();
	}
	
	// animation
	private void DoGoHome() {
		// TODO Auto-generated method stub
		
	}

	// utilities
	private void updateMyFood(List<MFoodOrder> canGive) {
	// TODO Auto-generated method stub
	
	}
	
	private double calculateBill(List<MFoodOrder> canGive) {
		 double myCalculation = 0;
         for(MFoodOrder o : canGive) {
                 myCalculation += o.amount * o.price;
         }
         return myCalculation;
	}


	public void setMarketManager(MockMarketManager manager) {
			this.manager = manager;
	}
	
	 public double getMyCheck() {
         return myCheck;
	 }
}