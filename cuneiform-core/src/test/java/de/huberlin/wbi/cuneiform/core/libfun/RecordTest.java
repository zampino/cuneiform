package de.huberlin.wbi.cuneiform.core.libfun;

import static org.junit.Assert.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( JUnitParamsRunner.class )
public class RecordTest {
	
	/*
	 * Parameter initialization
	 */
	
	@SuppressWarnings("static-method")
	public Object[] getValidSymbol() {
		return new Object[][] {{"bla"}, {"blub"}, {"x"}};
	}
	
	@SuppressWarnings("static-method")
	public Object[] getIllegalSymbol() {
		return new Object[][] {{null}, {""}, {"X"}, {"`"}, {"{"}, {"0"}};
	}
	
	/*
	 * Actual tests
	 */


	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	@Parameters( method="getIllegalSymbol" )
	public void constructorShouldThrowIaeOnIllegalSymbol( String symbol ) {
		new Record( symbol );
	}
	
	@SuppressWarnings("static-method")
	@Test
	@Parameters( method="getValidSymbol" )
	public void symbolShouldBeRetrievable( String symbol ) {
		
		Record r;
		
		r = new Record( symbol );
		assertEquals( symbol, r.getSymbol() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void lengthShouldBeDerivable() {
		
		Record r;
		String symbol;
		Constant<String> c;
		
		symbol = "blub";
		
		c = new Constant<>( "my new content" );
		
		r = new Record( symbol, c );
		assertEquals( 1, r.length() );
	}
}
