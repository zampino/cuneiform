package de.huberlin.wbi.cuneiform.core.libfun;

import static org.junit.Assert.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( JUnitParamsRunner.class )
public class ConstantTest {
	
	/*
	 * Parameter initialization
	 */
	
	@SuppressWarnings("static-method")
	public Object[] getValidStringConstantContent() {
		return new Object[][] { {""}, {"1"}, {"bla"}, {"Bla"}, {"BLA"}};
	}


	@SuppressWarnings("static-method")
	public Object[] getValidIntegerConstantContent() {
		return new Object[][]
		{ {0}, {1873465}, {-3124}, {Integer.MAX_VALUE}, {Integer.MIN_VALUE}};
	}

	
	/*
	 * Actual tests
	 */

	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullArg() {
		new Constant<String>( null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	@Parameters( method="getValidStringConstantContent" )
	public void contentShouldBeRetrievable( String content ) {
		
		Constant<String> c;

		c = new Constant<>( content );
		
		assertEquals( content, c.getContent() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	@Parameters( method="getValidIntegerConstantContent" )
	public void contentShouldBeRetrievable( Integer content ) {
		
		Constant<Integer> c;

		c = new Constant<>( content );
		
		assertEquals( content, c.getContent() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	@Parameters( method="getValidStringConstantContent" )
	public void constantShouldBeEqualIfContentIsEqual( String content ) {
		
		Constant<String> c1;
		Constant<String> c2;
		
		c1 = new Constant<>( content );
		c2 = new Constant<>( content );
		
		assertTrue( c1.equals( c2 ) );
		assertTrue( c2.equals( c1 ) );
	}
}
