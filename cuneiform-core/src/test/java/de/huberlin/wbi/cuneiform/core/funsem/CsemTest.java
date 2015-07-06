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
		Alist<Expr> value;
		
		key = "bla";
		
		value = new Alist<>();
		value = value.add( mock( Expr.class ) );
		
		defaultSem = new Csem( CREATE_TICKET );
		assertEquals( 0, defaultSem.getGlobal().size() );
		
		defaultSem.putGlobal( key, value );
		assertEquals( 1, defaultSem.getGlobal().size() );
		assertTrue( defaultSem.getGlobal().isKey( key ) );
		assertEquals( value, defaultSem.getGlobal().get( key ) );
	}
	
	/*
	 * REFERENCE TESTS
	 */

	@Test
	public void nilShouldEvalItself() {

		Alist<Expr> nil, x;

		nil = new Alist<>();

		x = csem.eval( nil, EMPTY_MAP );

		assertEquals( nil, x );
	}

	@Test
	public void strShouldEvalItself() {

		Alist<Expr> a, x;

		a = new Alist<>();
		a = a.add( new StrExpr( "A" ) );

		x = csem.eval( a, EMPTY_MAP );

		assertEquals( a, x );
	}
	
	@Test
	public void lamShouldEvalItself() {
		
		Sign sign;
		NatBody body;
		Amap<String,Alist<Expr>> bodyMap;
		Alist<Expr> lam, x, e;
		
		sign = new Sign(
			new Param[] { new Param( "out", false, false ) },
			new Param[] {},
			new Param[] {} );
		
		e = new Alist<>();
		e = e.add( new StrExpr( "blub" ) );
		
		bodyMap = EMPTY_MAP.put( "out", e );
		body = new NatBody( Lang.BASH, bodyMap );
		
		lam = new Alist<>();
		lam = lam.add( new LamExpr( LOC, sign, body ) );
		
		x = csem.eval( lam, EMPTY_MAP );
		assertEquals( lam, x );
	}

	@Test(expected = CfRuntimeException.class)
	public void undefVarShouldFail() {

		Alist<Expr> e;

		e = new Alist<>();
		e = e.add( new VarExpr( LOC, "x" ) );

		csem.eval( e, EMPTY_MAP );
	}

	@Test
	public void varDefInRhoShouldEvalToBoundValue() {

		Alist<Expr> e, f, x;
		Amap<String,Alist<Expr>> rho;

		e = new Alist<>();
		e = e.add( new StrExpr( "blub" ) );
		rho = EMPTY_MAP.put( "x", e );

		f = new Alist<>();
		f = f.add( new VarExpr( loc, "x" ) );		
		
		x = csem.eval( f, rho );

		assertEquals( e, x );
	}
		
	@Test
	public void varDefInGlobalShouldEvalToBoundValue() {
		
		Alist<Expr> e, f, x;
		
		e = new Alist<>();
		e = e.add( new StrExpr( "blub" ) );
		
		csem.putGlobal( "x", e );
		
		f = new Alist<>();
		f = f.add( new VarExpr( loc, "x" ) );

		x = csem.eval( f, EMPTY_MAP );
		
		assertEquals( e, x );
	}
	
	@Test
	public void overrideShouldBindCloserThanRho() {
		
		Alist<Expr> e, f, g, x;
		Amap<String,Alist<Expr>> rho;
		
		e = new Alist<>();
		e = e.add( new StrExpr( "bla" ) );
		
		f = new Alist<>();
		f = f.add( new StrExpr( "blub" ) );
		
		csem.putGlobal( "x", e );
		rho = EMPTY_MAP.put(  "x", f );
		
		g = new Alist<>();
		g = g.add( new VarExpr( loc, "x" ) );
		
		x = csem.eval( g, rho );
		
		assertEquals( e, x );
	}
	
	@Test
	public void defVarShouldCascadeBinding() {
		
		Alist<Expr> e, f, w, x;
		Amap<String,Alist<Expr>> rho;
		
		e = new Alist<>();
		e = e.add( new StrExpr( "blub" ) );
		
		f = new Alist<>();
		f = f.add( new VarExpr( loc, "y" ) );
		
		rho = EMPTY_MAP
				.put( "x", f )
				.put( "y", e );
		
		w = new Alist<>();
		w = w.add( new VarExpr( loc, "x" ) );
		
		x = csem.eval( w, rho );
		
		assertEquals( e, x );
	}
	  
	@Test
	public void defVarShouldCascadeBindingTwice() {
		
		Alist<Expr> a, w, x, y, z;
		Amap<String,Alist<Expr>> rho;
		
		a = new Alist<>();
		a = a.add( new StrExpr( "A" ) );
		
		y = new Alist<>();
		y = y.add( new VarExpr( loc, "y" ) );
		
		z = new Alist<>();
		z = z.add( new VarExpr( loc, "z" ) );
		
		rho = EMPTY_MAP
				.put( "x", y )
				.put( "y", z )
				.put( "z", a );
		
		w = new Alist<>();
		w = w.add( new VarExpr( loc, "x" ) );
		
		x = csem.eval( w, rho );
		
		assertEquals( a, x );
	}

	
	@Test
	public void unfinishedTicketShouldEvalToItself() {
		
		Ticket ticket;
		Alist<Expr> e, x;
		
		ticket = CREATE_TICKET.get();
		
		
		e = new Alist<>();
		e = e.add( new SelectExpr( loc, 1, ticket ) );
		
		x = csem.eval( e, EMPTY_MAP );
		
		assertEquals( e, x );
	}
	  

	@Test
	public void finishedTicketShouldEvalToValue() {
		 
		Ticket ticket;
		UUID ref;
		Alist<Expr> e, f, x;
		 
		ticket = CREATE_TICKET.get();
		ref = ticket.getRef();
		 
		e = new Alist<>();
		e = e.add( new SelectExpr( loc, 1, ticket ) );
		 
		f = new Alist<>();
		f = f.add( new StrExpr( "blub" ) );
		 
		csem.putFin( 1, ref, f );
		 
		x = csem.eval( e, EMPTY_MAP );
		 
		assertEquals( f, x );
	}
	 
	@Test
	public void emptyLamExprShouldEvalNil() {
		 
		Alist<Expr> e, f, x;
		Amap<String,Alist<Expr>> bindingMap;
		 
		e = new Alist<>();
		e = e.add( new StrExpr( "bla" ) );
		 
		bindingMap = EMPTY_MAP.put( "inp", e );

		f = new Alist<>();
		f = f.add( new AppExpr( LOC, 1, new Alist<Expr>(), bindingMap ) );
		 
		x = csem.eval( f, EMPTY_MAP );
		assertEquals( new Alist<Expr>(), x );
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
		 
		Alist<Expr> e, f, x, inp;
		Sign sign;
		NatBody body;
		Alist<Expr> lam;
		Amap<String,Alist<Expr>> bodyMap, bindingMap;
		
		e = new Alist<>();
		e = e.add( new StrExpr( "bla" ) );
		
		sign = new Sign(
				new Param[] { new Param( "out", false, false ) },
				new Param[] {},
				new Param[] { new Param( "inp", false, false ) } );
		
		inp = new Alist<>();
		inp = inp.add( new VarExpr( LOC, "inp" ) );
		
		bodyMap = EMPTY_MAP.put( "out", inp );
		body = new NatBody( Lang.BASH, bodyMap );
		
		lam = new Alist<>();
		lam = lam.add( new LamExpr( LOC, sign, body ) );
		 
		bindingMap = EMPTY_MAP.put( "inp", e );
		
		f = new Alist<>();
		f = f.add( new AppExpr( LOC, 1, lam, bindingMap ) );
		 
		x = csem.eval( f, EMPTY_MAP );
		 
		assertEquals( e, x );
	}
}
