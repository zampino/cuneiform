package de.huberlin.wbi.cuneiform.core.funsem;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LamExprTest implements DefaultTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorShouldStoreSign() {
		
		LamExpr lamExpr;
		NatBody body;
		Sign sign;
		
		sign = mock( Sign.class );
		body = mock( NatBody.class );
		
		lamExpr = new LamExpr( LOC, sign, body );
		assertEquals( sign, lamExpr.getSign() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullSign() {		
		new LamExpr( LOC, null, mock( NatBody.class ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void constructorStoresBody() {
		
		Sign sign;
		Body body;
		LamExpr lamExpr;
		
		sign = mock( Sign.class );
		body = mock( Body.class );
		
		lamExpr = new LamExpr( LOC, sign, body );
		assertEquals( body, lamExpr.getBody() );
	}
	
	@SuppressWarnings({ "unused", "static-method" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorThrowsIaeOnNullBody() {
		
		Sign sign;

		sign = mock( Sign.class );
		
		new LamExpr( LOC, sign, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void constructorShouldStoreLocation() {
		
		Sign sign;
		Body body;
		LamExpr lamExpr;
		Location loc;
		
		sign = mock( Sign.class );
		body = mock( Body.class );
		loc = mock( Location.class );
		
		lamExpr = new LamExpr( loc, sign, body );
		assertEquals( loc, lamExpr.getLocation() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullLocation() {

		Sign sign;
		Body body;
		
		sign = mock( Sign.class );
		body = mock( Body.class );
		
		new LamExpr( null, sign, body );

	}
}
