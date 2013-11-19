package simcity.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import agent.Role;
import simcity.PersonAgent;
import simcity.Market.InventoryBoyRole;
import simcity.Market.MFoodOrder;
import simcity.Market.MOrder;
import simcity.Market.MarketCashierRole;
import simcity.Market.MarketCashierRole.myState;
import simcity.Market.MarketManagerRole;
import simcity.interfaces.MarketCashier;
import simcity.test.mock.MockCook;
import simcity.test.mock.MockInventoryBoy;
import simcity.test.mock.MockMarketCashier;
import simcity.test.mock.MockMarketCustomer;
import simcity.test.mock.MockMarketManager;
import simcity.Market.MarketCashierRole.orderState;
import junit.framework.TestCase;

public class MarketManagerTest extends TestCase{

	PersonAgent p;
	MockMarketCashier mc;
	MockInventoryBoy ib;
	MockMarketCustomer c;
	MockCook cook;
	MarketManagerRole m;
	
	//List<MOrder> orders =Collections.synchronizedList(new ArrayList<MOrder>());
	List<MFoodOrder> foods =Collections.synchronizedList(new ArrayList<MFoodOrder>());
	MFoodOrder f1;
	//MOrder a;
	
	public void setUp() throws Exception{
		super.setUp();
		p = new PersonAgent("MarketCashier");
		mc = new MockMarketCashier("mockMarketCashier");
		p.addRole(m);
		ib = new MockInventoryBoy("mockInventoryBoy");
		c = new MockMarketCustomer("mockCustomer");
		cook = new MockCook("mockCook");
		m = new MarketManagerRole(p);
	}
	
	
	public void testMsgOrder() {
		mc.ib = ib;
		mc.manager = man;
		ib.mc = mc;
		c.mc = mc;
		f1 = new MFoodOrder("Ch", 2);
		foods.add(f1);
//		a = new MOrder(foods,"b1", c, orderState.pending);
//		orders.add(a);
		
		// preconditions
        assertEquals("MarketCashier should have zero orders but doesn't", mc.orders.size(), 0);
        assertEquals("MarketCashier should have collected zero money", mc.marketMoney, 0.0);
        assertEquals("MarketCashier should have an empty event log. The mc's event log read: " + mc.log.toString(), 0, mc.log.size());
        assertEquals("MockInventoryBoy should have an empty event log. The ib's event log reads: "
                + ib.log.toString(), 0, ib.log.size());
        
        
        //give mc adding an order
        mc.msgOrder(c, foods, "b1");
        assertEquals("MarketCashier should have one order", mc.orders.size(), 1);
        assertTrue("MarketCashier is giving order to ib.", mc.pickAndExecuteAnAction());
        assertEquals("Order state is inquiring.", mc.orders.get(0).state, orderState.inquiring);
        
        //give mc an order to fulfill
        assertTrue("InventoryBoy logged: " + ib.log.getLastLoggedEvent().toString(), ib.log.containsString("Received msgCheckInventory from market cashier."));
        assertFalse("MarketCashier has finished messaging ib.", mc.pickAndExecuteAnAction());
        
	}
	
}