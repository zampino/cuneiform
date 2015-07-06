package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.UUID;
import java.util.function.Supplier;

import static java.util.UUID.randomUUID;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class CsemTest implements DefaultTest {

	private static final Supplier<Ticket> CREATE_TICKET = ( ) -> new Ticket(
			randomUUID() );

	private DefaultSem csem;
	private Location loc;

	/*
	 * SETUP AND TEAR DOWN
	 */

	@Before
	public void setup() {

		csem = new Csem( CREATE_TICKET );
		loc = new Location();
	}

	/*
	 * STANDARD JAVA RELATED TESTS
	 */

	@SuppressWarnings("static-method")
	@Test
	public void constructorShouldSetAndGetCreateTicket() {

		DefaultSem defaultSem;

		defaultSem = new Csem( CREATE_TICKET );
		assertSame( CREATE_TICKET, defaultSem.getCreateTicket() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void putGlobalShouldPutValue() {
		
		DefaultSem defaultSem;
		String key;
		Expr[] value;
		
		key = "bla";
		value = new Expr[] { mock( Expr.class ) };
		
		defaultSem = new Csem( CREATE_TICKET );
		assertEquals( 0, defaultSem.getGlobal().size() );
		
		defaultSem.putGlobal( key, value );
		assertEquals( 1, defaultSem.getGlobal().size() );
		assertTrue( defaultSem.getGlobal().isKey( key ) );
		assertArrayEquals( value, defaultSem.getGlobal().get( key ) );
	}
	
	/*
	 * REFERENCE TESTS
	 */

	@Test
	public void nilShouldEvalItself() {

		Expr[] nil, x;

		nil = new Expr[] {};

		x = csem.eval( nil, EMPTY_MAP );

		assertArrayEquals( nil, x );
	}

	@Test
	public void strShouldEvalItself() {

		Expr[] a, x;

		a = new Expr[] { new StrExpr( "A" ) };

		x = csem.eval( a, EMPTY_MAP );

		assertArrayEquals( a, x );
	}
	
	@Test
	public void lamShouldEvalItself() {
		
		Sign sign;
		NatBody body;
		ImmutableMap<String,Expr[]> bodyMap;
		Expr[] lam, x;
		
		sign = new Sign(
			new Param[] { new Param( "out", false, false ) },
			new Param[] {},
			new Param[] {} );
		
		bodyMap = EMPTY_MAP.put( "out", new Expr[] { new StrExpr( "blub" ) } );
		body = new NatBody( Lang.BASH, bodyMap );
		lam = new Expr[] { new LamExpr( LOC, sign, body ) };
		
		x = csem.eval( lam, EMPTY_MAP );
		assertArrayEquals( lam, x );
	}

	@Test(expected = CfRuntimeException.class)
	public void undefVarShouldFail() {

		Expr[] e;

		e = new Expr[] { new VarExpr( LOC, "x" ) };

		csem.eval( e, EMPTY_MAP );
	}

	@Test
	public void varDefInRhoShouldEvalToBoundValue() {

		Expr[] e, x;
		ImmutableMap<String,Expr[]> rho;

		e = new Expr[] { new StrExpr( "blub" ) };
		rho = EMPTY_MAP.put( "x", e );

		x = csem.eval( new Expr[] { new VarExpr( loc, "x" ) }, rho );

		assertArrayEquals( e, x );
	}
		
	@Test
	public void varDefInGlobalShouldEvalToBoundValue() {
		
		Expr[] e, x;
		
		e = new Expr[] { new StrExpr( "blub" ) };
		csem.putGlobal( "x", e );
		
		x = csem.eval(  new Expr[] { new VarExpr( loc, "x" ) }, EMPTY_MAP );
		
		assertArrayEquals( e, x );
	}
	
	@Test
	public void overrideShouldBindCloserThanRho() {
		
		Expr[] e, f, x;
		ImmutableMap<String,Expr[]> rho;
		
		e = new Expr[] { new StrExpr( "bla" ) };
		f = new Expr[] { new StrExpr( "blub" ) };
		
		csem.putGlobal( "x", e );
		rho = EMPTY_MAP.put(  "x", f );
		
		x = csem.eval( new Expr[] { new VarExpr( loc, "x" ) }, rho );
		
		assertArrayEquals( e, x );
	}
	
	@Test
	public void defVarShouldCascadeBinding() {
		
		Expr[] e, w, x;
		ImmutableMap<String,Expr[]> rho;
		
		e = new Expr[] { new StrExpr( "blub" ) };
		rho = EMPTY_MAP
				.put( "x", new Expr[] { new VarExpr( loc, "y" ) } )
				.put( "y", e );
		
		w = new Expr[] { new VarExpr( loc, "x" ) };
		x = csem.eval( w, rho );
		
		assertArrayEquals( e, x );
	}
	  
	@Test
	public void defVarShouldCascadeBindingTwice() {
		
		Expr[] a, w, x;
		ImmutableMap<String,Expr[]> rho;
		
		a = new Expr[] { new StrExpr( "A" ) };
		rho = EMPTY_MAP
				.put( "x", new Expr[] { new VarExpr( loc, "y" ) } )
				.put( "y", new Expr[] { new VarExpr( loc, "z" ) } )
				.put( "z", a );
		
		w = new Expr[] { new VarExpr( loc, "x" ) };
		x = csem.eval( w, rho );
		
		assertArrayEquals( a, x );
	}

	
	@Test
	public void unfinishedTicketShouldEvalToItself() {
		
		Ticket ticket;
		Expr[] e, x;
		
		ticket = CREATE_TICKET.get();
		
		e = new Expr[] { new SelectExpr( loc, 1, ticket ) };
		x = csem.eval( e, EMPTY_MAP );
		
		assertArrayEquals( e, x );
	}
	  

	 @Test
	 public void finishedTicketShouldEvalToValue() {
		 
		 Ticket ticket;
		 UUID ref;
		 Expr[] e, f, x;
		 
		 ticket = CREATE_TICKET.get();
		 ref = ticket.getRef();
		 
		 e = new Expr[] { new SelectExpr( loc, 1, ticket ) };
		 f = new Expr[] { new StrExpr( "blub" ) };
		 
		 csem.putFin( 1, ref, f );
		 
		 x = csem.eval( e, EMPTY_MAP );
		 
		 assertArrayEquals( f, x );
	 }
	 
	 /* empty_lam_expr_should_eval_nil( {Eval, CreateTicket} ) ->
  E = [{str, "bla"}],
  F = [{app, ?LOC, 1, [], #{"inp" => E}}],
  ?_assertEqual( [], apply( Eval, [F, #{}, CreateTicket, #{}] ) ). */
	 
	 @Test
	 public void emptyLamExprShouldEvalNil() {
		 
		 Expr[] e, f, x;
		 ImmutableMap<String,Expr[]> bindingMap;
		 
		 e = new Expr[] { new StrExpr( "bla" ) };
		 bindingMap = EMPTY_MAP.put(  "inp", e );
		 
		 e = new Expr[] { new StrExpr( "bla" ) };
		 f = new Expr[] { new AppExpr( LOC, 1, new Expr[] {}, bindingMap ) };
		 
		 x = csem.eval( f, EMPTY_MAP );
		 assertArrayEquals( new Expr[] {}, x );
	 }


	 
	 /* identity_fn_should_eval_arg( {Eval, CreateTicket} ) ->
  E = [{str, "bla"}],
  Sign = {sign, [{param, "out", false, false}],
                [], [{param, "inp", false, false}]},
  Body = {natbody, #{"out" => [{var, ?LOC, "inp"}]}},
  LamList = [{lam, ?LOC, Sign, Body}],
  F = [{app, ?LOC, 1, LamList, #{"inp" => E}}],
  ?_assertEqual( E, apply( Eval, [F, #{}, CreateTicket, #{}] ) ). */
	 
	 @Test
	 public void identityFnShouldEvalArg() {
		 
		 Expr[] e, f, x;
		 Sign sign;
		 NatBody body;
		 Expr[] lam;
		 ImmutableMap<String,Expr[]> bodyMap, bindingMap;
		  
		 e = new Expr[] { new StrExpr( "bla" ) };
		 sign = new Sign(
				 new Param[] { new Param( "out", false, false ) },
				 new Param[] {},
				 new Param[] { new Param( "inp", false, false ) } );
		 
		 bodyMap = EMPTY_MAP.put( "out", new Expr[] { new VarExpr( LOC, "inp" ) } );
		 body = new NatBody( Lang.BASH, bodyMap );
		 lam = new Expr[] { new LamExpr( LOC, sign, body ) };
		 
		 bindingMap = EMPTY_MAP.put( "inp", e );
		 f = new Expr[] { new AppExpr( LOC, 1, lam, bindingMap ) };
		 
		 x = csem.eval( f, EMPTY_MAP );
		 
		 assertArrayEquals( e, x );
	 }
}
