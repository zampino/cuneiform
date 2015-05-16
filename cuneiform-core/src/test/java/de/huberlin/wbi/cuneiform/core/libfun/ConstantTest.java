package de.huberlin.wbi.cuneiform.core.libfun;

import static de.huberlin.wbi.cuneiform.core.libfun.Constant.constantFrom;
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

	@SuppressWarnings("static-method")
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
	


	@SuppressWarnings("static-method")
	@Test
	public void printStringConstantShouldQuoteContent() {
		
		Term t;
		String content;
		
		content = "bla";
		
		t = new Constant<>( content );
		assertEquals( "\""+content+"\"", t.print() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void printIntegerConstantShouldReturnStringValue() {
		
		Term t;
		int content;
		
		content = 4;
		t = new Constant<>( content );
		assertEquals( String.valueOf( content ), t.print() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void constantFromStringShouldRememberContent() {
		
		Constant<String> c;
		String content;
		
		content = "bla";
		
		c = constantFrom( content );
		assertEquals( content, c.getContent() );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void constantFromStringShouldThrowIaeOnNullContent() {
		constantFrom( null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void constantFromIntShouldRememberContent() {
		
		Constant<Integer> c;
		Integer content;
		
		content = 4;
		c = constantFrom( content );
		
		assertEquals( content, c.getContent() );
		
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsShouldReturnTrueOnSelf() {
		
		String content;
		Term t1;
		
		content = "bla";
		
		t1 = new Constant<>( content );
		
		assertTrue( t1.equals( t1 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsShouldReturnTrueOnStringConstantsWithEqualContent() {
		
		String content;
		Term t1, t2;
		
		content = "bla";
		
		t1 = new Constant<>( content );
		t2 = new Constant<>( content );
		
		assertTrue( t1.equals( t2 ) );
		assertTrue( t2.equals( t1 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsShouldReturnFalseOnDifferingStringConstants() {
		
		Term t1, t2;
		
		
		t1 = new Constant<>( "bla" );
		t2 = new Constant<>( "blub" );
		
		assertFalse( t1.equals( t2 ) );
		assertFalse( t2.equals( t1 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	@Parameters( method="getValidStringConstantContent" )
	public void unifyConstantWithEqualContentShouldReturnTrue( String content ) {
		
		Constant<String> c1, c2;
		
		c1 = new Constant<>( content );
		c2 = new Constant<>( content );
		
		assertTrue( c1.unify( c2 ) );
		assertTrue( c2.unify( c1 ) );
		
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unifyConstantWithNonEqualContentShouldReturnFalse() {
		
		Constant<String> c1, c2;
		
		c1 = new Constant<>( "bla" );
		c2 = new Constant<>( "blub" );
		
		assertFalse( c1.unify( c2 ) );
		assertFalse( c2.unify( c1 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unspecializeDoesNotAffectConstant() {
		
		Constant<String> c;
		
		c = new Constant<>( "bla" );
		c.unspecialize();
	}
}
