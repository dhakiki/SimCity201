package simcity.test;

import java.util.ArrayList;
import java.util.List;

import simcity.PersonAgent;
import simcity.housing.LandlordRole;
import simcity.test.mock.MockBankManager;
import simcity.test.mock.MockRepairMan;
import junit.framework.TestCase;

public class LandlordTest extends TestCase{
	PersonAgent person;
	PersonAgent resident; 
	PersonAgent resident2; 
	LandlordRole landlord;
	MockRepairMan repairman;
	MockRepairMan repairman2;
	MockBankManager bankmanager;
	
	public void setUp() throws Exception{
		super.setUp();
		person = new PersonAgent("Landlord");
		resident = new PersonAgent("Resident"); 
		resident2 = new PersonAgent("Resident2"); 
		landlord = new LandlordRole(person);
		repairman = new MockRepairMan("MockRepairman");
		repairman2 = new MockRepairMan("MockRepairman2"); 
		person.addRole(landlord);		
	}
	
	public void testAskForRent() {
		
	}
	
	public void testCallForRepair() {
		//checking preconditions
		assertEquals("Landlord should have no repairmen right now. It doesn't.", landlord.repairmen.size(), 0);
		assertEquals("Landlord should have no workers right now. It doesn't.", landlord.myWorkers.size(), 0);
		assertEquals("Landlord should have no tenants right now. It doesn't.", landlord.myTenants.size(), 0);
		
		landlord.addRepairMan(repairman);
		landlord.addTenant(resident, 12); 
		
		//checking postconditions
		assertEquals("Landlord should have one repairmen right now. It doesn't.", landlord.repairmen.size(), 1);
		assertEquals("Landlord should have one tenants right now. It doesn't.", landlord.myTenants.size(), 1);
		assertFalse("Landlord's scheduler should have returned false now, since it has nothing to do. It didn't.", landlord.pickAndExecuteAnAction());
		
		//changing hour to 10 so landlord calls repairman 
		landlord.TimeUpdate(10);
		
		//calling the scheduler and checking postconditions
		assertTrue("Landlord's scheduler should have returned true now, since it has to do something. It didn't.", landlord.pickAndExecuteAnAction());
		assertTrue("MockRepairMan should have logged an event for receiving a job but instead it's: " + repairman.log.getLastLoggedEvent().toString(), repairman.log.containsString("Received a job for building B2"));
		assertFalse("Landlord's scheduler should have returned false now, since it has nothing to do. It didn't.", landlord.pickAndExecuteAnAction());
		assertEquals("Landlord should have one worker right now. It doesn't.", landlord.myWorkers.size(), 1);

		
		//giving landlord money and sending message to pay repairman
		landlord.revenue = 100;
		landlord.jobDone(repairman, 30);
		
		//calling the scheduler and checking postconditions
		assertTrue("Landlord's scheduler should have returned true now, since it has to do something. It didn't.", landlord.pickAndExecuteAnAction());
		assertTrue("MockRepairMan should have logged an event for receiving payment but instead it's: " + repairman.log.getLastLoggedEvent().toString(), repairman.log.containsString("Received a payment for job for 30.0"));
		assertFalse("Landlord's scheduler should have returned false now, since it has nothing to do. It didn't.", landlord.pickAndExecuteAnAction());

		assertEquals("Landlord should have less money now, but it doesnt.", landlord.revenue, 70.0);
		assertEquals("Landlord should have no workers right now. It doesn't.", landlord.myWorkers.size(), 0);


	}
	
	public void testCallForTwoRepairs() {
		//checking preconditions
		assertEquals("Landlord should have no repairmen right now. It doesn't.", landlord.repairmen.size(), 0);
		assertEquals("Landlord should have no workers right now. It doesn't.", landlord.myWorkers.size(), 0);
		assertEquals("Landlord should have no tenants right now. It doesn't.", landlord.myTenants.size(), 0);
				
		landlord.addRepairMan(repairman);
		landlord.addRepairMan(repairman2); 
		landlord.addTenant(resident, 12);
		landlord.addTenant(resident2, 3); 
				
		//checking postconditions
		assertEquals("Landlord should have two repairmen right now. It doesn't.", landlord.repairmen.size(), 2);
		assertEquals("Landlord should have two tenants right now. It doesn't.", landlord.myTenants.size(), 2);
		assertFalse("Landlord's scheduler should have returned false now, since it has nothing to do. It didn't.", landlord.pickAndExecuteAnAction());
				
		//changing hour to 10 so landlord calls repairman 
		landlord.TimeUpdate(10);
				
		//calling the scheduler and checking postconditions
		assertTrue("Landlord's scheduler should have returned true now, since it has to do something. It didn't.", landlord.pickAndExecuteAnAction());
		assertTrue("MockRepairMan should have logged an event for receiving a job but instead it's: " + repairman.log.getLastLoggedEvent().toString(), repairman.log.containsString("Received a job for building B2"));
		assertFalse("Landlord's scheduler should have returned false now, since it has nothing to do. It didn't.", landlord.pickAndExecuteAnAction());
		assertEquals("Landlord should have two workers right now. It doesn't.", landlord.myWorkers.size(), 2);
		assertEquals("Landlord should have called the same worker for both residents, but he didn't.", landlord.myWorkers.get(0).myWorker, landlord.myWorkers.get(1).myWorker);

		
		//giving landlord money and sending message to pay repairman
		landlord.revenue = 100;
		landlord.jobDone(repairman, 30);
				
		//calling the scheduler and checking postconditions
		assertTrue("Landlord's scheduler should have returned true now, since it has to do something. It didn't.", landlord.pickAndExecuteAnAction());
		assertTrue("MockRepairMan should have logged an event for receiving payment but instead it's: " + repairman.log.getLastLoggedEvent().toString(), repairman.log.containsString("Received a payment for job for 30.0"));
		assertFalse("Landlord's scheduler should have returned false now, since it has nothing to do. It didn't.", landlord.pickAndExecuteAnAction());

		assertEquals("Landlord should have less money now, but it doesnt.", landlord.revenue, 70.0);
		assertEquals("Landlord should have no workers right now. It doesn't.", landlord.myWorkers.size(), 0);

		
	}

}
