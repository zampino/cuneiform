package de.huberlin.wbi.cuneiform.core.funsem;

import static org.junit.Assert.*;

import org.junit.Test;

public class StrExprTest {

	@SuppressWarnings("static-method")
	@Test
	public void equalsNullShouldReturnFalse() {
		
		StrExpr strExpr;
		
		strExpr = new StrExpr( "blub" );
		
		assertFalse( strExpr.equals( null ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsOtherClassShouldEqualFalse() {
		
		StrExpr strExpr;
		
		strExpr = new StrExpr( "blub" );
		
		assertFalse( strExpr.equals( 3 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void strWithDifferentContentShouldNotBeEqual() {
		
		StrExpr strExpr1, strExpr2;
		
		strExpr1 = new StrExpr( "blub" );
		strExpr2 = new StrExpr( "bla" );
		
		assertNotEquals( strExpr1, strExpr2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void strWithSameContentShouldBeEqual() {
		
		StrExpr strExpr1, strExpr2;
		String s;
		
		s = "blub";
		
		strExpr1 = new StrExpr( s );
		strExpr2 = new StrExpr( s );
		
		assertEquals( strExpr1, strExpr2 );
	}
}
