package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

import static java.util.UUID.randomUUID;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CsemTest {

	private static final Supplier<Ticket> CREATE_TICKET = ( ) -> new Ticket(
			randomUUID() );
	private static final Location LOC = new Location();

	private DefaultSem csem;
	private Map<ChannelRef, Expr[]> fin;
	private Map<String, Expr[]> global;
	private Map<String, Expr[]> rho;
	private Location loc;

	/*
	 * SETUP AND TEAR DOWN
	 */

	@Before
	public void setup() {

		global = new HashMap<>();
		fin = new HashMap<>();
		csem = new Csem( global, CREATE_TICKET, fin );
		rho = new HashMap<>();
		loc = new Location();
	}

	/*
	 * STANDARD JAVA RELATED TESTS
	 */

	@SuppressWarnings("static-method")
	@Test
	public void constructorShouldSetAndGetGlobalCreateTicketAndFin() {

		DefaultSem defaultSem;
		HashMap<String, Expr[]> g;
		HashMap<ChannelRef, Expr[]> f;

		g = new HashMap<>();
		f = new HashMap<>();

		defaultSem = new Csem( g, CREATE_TICKET, f );
		assertSame( g, defaultSem.getGlobal() );
		assertSame( CREATE_TICKET, defaultSem.getCreateTicket() );
		assertSame( f, defaultSem.getFin() );
	}

	/*
	 * REFERENCE TESTS
	 */

	@Test
	public void nilShouldEvalItself() {

		Expr[] nil, x;

		nil = new Expr[] {};

		x = csem.eval( nil, rho );

		assertArrayEquals( nil, x );
	}

	@Test
	public void strShouldEvalItself() {

		Expr[] a, x;

		a = new Expr[] { new StrExpr( "A" ) };

		x = csem.eval( a, rho );

		assertArrayEquals( a, x );
	}

	@Test(expected = CfRuntimeException.class)
	public void undefVarShouldFail() {

		Expr[] e;

		e = new Expr[] { new VarExpr( LOC, "x" ) };

		csem.eval( e, rho );
	}

	@Test
	public void varDefInRhoShouldEvalToBoundValue() {

		Expr[] e, x;

		e = new Expr[] { new StrExpr( "blub" ) };
		rho.put( "x", e );

		x = csem.eval( new Expr[] { new VarExpr( loc, "x" ) }, rho );

		assertArrayEquals( e, x );
	}
		
	@Test
	public void varDefInGlobalShouldEvalToBoundValue() {
		
		Expr[] e, x;
		
		e = new Expr[] { new StrExpr( "blub" ) };
		global.put(  "x", e );
		
		x = csem.eval(  new Expr[] { new VarExpr( loc, "x" ) }, rho );
		
		assertArrayEquals( e, x );
	}
	
	@Test
	public void overrideShouldBindCloserThanRho() {
		
		Expr[] e, f, x;
		
		e = new Expr[] { new StrExpr( "bla" ) };
		f = new Expr[] { new StrExpr( "blub" ) };
		
		global.put(  "x", e );
		rho.put(  "x", f );
		
		x = csem.eval( new Expr[] { new VarExpr( loc, "x" ) }, rho );
		
		assertArrayEquals( e, x );
	}
	
	@Test
	public void defVarShouldCascadeBinding() {
		
		Expr[] e, w, x;
		
		e = new Expr[] { new StrExpr( "blub" ) };
		rho.put( "x", new Expr[] { new VarExpr( loc, "y" ) } );
		rho.put(  "y", e );
		
		w = new Expr[] { new VarExpr( loc, "x" ) };
		x = csem.eval( w, rho );
		
		assertArrayEquals( e, x );
	}
	  
	@Test
	public void defVarShouldCascadeBindingTwice() {
		
		Expr[] a, w, x;
		
		a = new Expr[] { new StrExpr( "A" ) };
		rho.put( "x", new Expr[] { new VarExpr( loc, "y" ) } );
		rho.put( "y", new Expr[] { new VarExpr( loc, "z" ) } );
		rho.put(  "z", a );
		
		w = new Expr[] { new VarExpr( loc, "x" ) };
		x = csem.eval( w, rho );
		
		assertArrayEquals( a, x );
	}

	/*unfinished_ticket_should_eval_to_itself( {Eval, CreateTicket} ) ->
	  Ticket = apply( CreateTicket, [] ),
	  E = [{select, ?LOC, 1, Ticket}],
	  X = apply( Eval, [E, #{}, CreateTicket, #{}] ),
	  ?_assertEqual( E, X ).*/
	
	@Test
	public void unfinishedTicketShouldEvalToItself() {
		
		Ticket ticket;
		Expr[] e, x;
		
		ticket = CREATE_TICKET.get();
		
		e = new Expr[] { new SelectExpr( loc, 1, ticket ) };
		x = csem.eval( e, rho );
		
		assertArrayEquals( e, x );
	}
	  
	/*finished_ticket_should_eval_to_value( {Eval, CreateTicket} ) ->
  Ticket = apply( CreateTicket, [] ),
  {ticket, Ref} = Ticket,
  E = [{select, ?LOC, 1, Ticket}],
  F = [{str, "blub"}],
  X = apply( Eval, [E, #{}, CreateTicket, put( {1, Ref}, F, #{} )] ),
  ?_assertEqual( F, X ).*/

	 @Test
	 public void finishedTicketShouldEvalToValue() {
		 
		 Ticket ticket;
		 UUID ref;
		 Expr[] e, f, x;
		 
		 ticket = CREATE_TICKET.get();
		 ref = ticket.getRef();
		 
		 e = new Expr[] { new SelectExpr( loc, 1, ticket ) };
		 f = new Expr[] { new StrExpr( "blub" ) };
		 
		 fin.put( new ChannelRef( 1, ref ), f );
		 
		 x = csem.eval( e, rho );
		 
		 assertArrayEquals( f, x );
	 }
}
