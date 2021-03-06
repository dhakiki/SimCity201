package simcity.Drew_restaurant;

import simcity.PersonAgent;
import simcity.gui.SimCityGui;
import simcity.interfaces.*;



public class Drew_WaiterNormalRole extends Drew_WaiterRole implements Drew_Waiter{

	public Drew_WaiterNormalRole(SimCityGui gui) {
		super(gui);
	}

	@Override
	protected void putInOrder(MyCustomer c) {
		waitergui.pickUpOrder();
		waitergui.goToKitchen();
		finishTask();
		waitergui.dropOff();
		cook.hereIsOrder(this, c.choice, c.t);
		c.s=CustomerState.waitingForFood;
		stateChanged();
	}

}
