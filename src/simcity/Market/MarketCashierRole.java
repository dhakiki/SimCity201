package simcity.Market;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import simcity.PersonAgent;
import simcity.restaurant.CashierRole;
import agent.Role;


public class MarketCashierRole extends Role{

	List<MOrder> orders =Collections.synchronizedList(new ArrayList<MOrder>());
	
	boolean active;
	double marketMoney;
	
	InventoryBoyRole ib;
	MarketManagerRole manager; 
	
	enum orderState{pending, inquiring, ready, given, paid};
	enum myState{arrived, working, goHome, unavailable};
	
	myState state;
	
	protected MarketCashierRole(PersonAgent p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	
	//Messages
	public void msgOrder(Role r, List<MFoodOrder> foods, String building){
		orders.add(new MOrder(foods, building, r, orderState.pending));
		stateChanged();
	}
	
	public void msgOrder(Role r, List<MFoodOrder> foods, String building, CashierRole c){
		orders.add(new MOrder(foods, building, r, c, orderState.pending));
		stateChanged();
	}
	
	public void canGive(MOrder o){
		MOrder current = ((MOrder) orders).find(o);
		current.state = orderState.ready;
		stateChanged();
	}
	
	public void HereIsPayment(Role r, double payment){
		MOrder current = ((MOrder) orders).find(r);
		current.state = orderState.paid;
		marketMoney += payment;
		stateChanged();
	}
	
	public void GoHome(){
		state = myState.goHome;
		stateChanged();
	}
	
	//Scheduler
	@Override
	public boolean pickAndExecuteAnAction() {
		
		for(MOrder o: orders){
			if(o.state == orderState.ready){
				giveOrder(o);
				return true;
			}
		}
		
		for(MOrder o: orders){
			if(o.state == orderState.pending){
				tryToFulFillOrder(o);
				return true;
			}
		}
		
		for(MOrder o: orders){
			if(o.state == orderState.paid){
				updateManager(o);
				return true;
			}
		}
		
		if(state == myState.goHome){
			goHome();
			return true;
		}
		
		if(state == myState.arrived){
			tellManager();
			return true;
		}
		
		return false;
	}
	
}




