package de.huberlin.wbi.cuneiform.core.funsem;

import static java.util.UUID.randomUUID;
import static org.junit.Assert.*;

import java.util.function.Supplier;

import org.junit.Test;

public class SelectExprTest {

	private static final Supplier<Ticket> CREATE_TICKET = ( ) -> new Ticket(
			randomUUID() );
	private static final Location LOC = new Location();

	@SuppressWarnings("static-method")
	@Test
	public void constructorShouldSetChannel() {
		
		SelectExpr selectExpr;
		Ticket ticket;
		int channel;
		
		ticket = CREATE_TICKET.get();
		channel = 25;
		
		selectExpr = new SelectExpr( LOC, channel, ticket );
		
		assertEquals( channel, selectExpr.getChannel() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void constructorShouldSetTicket() {
		
		SelectExpr selectExpr;
		Ticket ticket;
		int channel;
		
		ticket = CREATE_TICKET.get();
		channel = 25;
		
		selectExpr = new SelectExpr( LOC, channel, ticket );
		
		assertEquals( ticket, selectExpr.getTicket() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void ticketRefShouldBeRetrievable() {
		
		SelectExpr selectExpr;
		Ticket ticket;
		int channel;
		
		ticket = CREATE_TICKET.get();
		channel = 25;
		
		selectExpr = new SelectExpr( LOC, channel, ticket );
		
		assertEquals( ticket.getRef(), selectExpr.getRef() );
	}

}
