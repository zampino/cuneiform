package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static java.util.UUID.randomUUID;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CsemTest {
	
	private static final Supplier<Ticket> CREATE_TICKET = () -> new Ticket( randomUUID() );

	private DefaultSem csem;
	private Map<RefChannel,Expr[]> fin;
	private Map<String,Expr[]> global;

	@Before
	public void setup() {
		
		global = new HashMap<>();
		fin = new HashMap<>();
		csem = new Csem( global, CREATE_TICKET, fin );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void constructorShouldSetAndGetGlobalCreateTicketAndFin() {
		
		DefaultSem defaultSem;
		HashMap<String,Expr[]> g;
		HashMap<RefChannel,Expr[]> f;
		Supplier<Ticket> createTicket;
		
		g = new HashMap<>();
		f = new HashMap<>();
		createTicket = () -> new Ticket( randomUUID() );
		
		defaultSem = new Csem( g, createTicket, f );
		assertSame( g, defaultSem.getGlobal() );
		assertSame( createTicket, defaultSem.getCreateTicket() );
		assertSame( f, defaultSem.getFin() );
	}

	@Test
	public void nilShouldEvalItself() {
		
		Expr[] nil, x;
		Map<String,Expr[]> rho;
		
		nil = new Expr[] {};
		rho = new HashMap<>();
		
		x = csem.eval( nil, rho );
		
		assertArrayEquals( nil, x );
	}
	
	@Test
	public void strShouldEvalItself() {
		
		Expr[] a, x;
		Map<String,Expr[]> rho;
		
		a = new Expr[] { new StrExpr( "A" ) };
		rho = new HashMap<>();
		
		x = csem.eval( a, rho );
		
		assertArrayEquals( a, x );
	}
}
