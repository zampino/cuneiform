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
	public void constantFromStringShouldRememberContent() {
		
		Constant<String> c;
		String content;
		
		content = "bla";
		
		c = constantFrom( content );
		assertEquals( content, c.getContent() );
	}

	
	/*
	 * Actual tests
	 */

	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void constantFromStringShouldThrowIaeOnNullContent() {
		constantFrom( null );
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
	@Parameters( method="getValidIntegerConstantContent" )
	public void constantsWithEqualIntContentShouldHaveEqualHashcodes( Integer validInt ) {
		
		Constant<Integer> c1;
		Constant<Integer> c2;
		
		c1 = new Constant<>( validInt );
		c2 = new Constant<>( validInt );
		
		assertEquals( c1.hashCode(), c2.hashCode() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	@Parameters( method="getValidStringConstantContent" )
	public void constantsWithEqualStringContentShouldHaveEqualHashcodes( String validString ) {
		
		Constant<String> c1;
		Constant<String> c2;
		
		c1 = new Constant<>( validString );
		c2 = new Constant<>( validString );
		
		assertEquals( c1.hashCode(), c2.hashCode() );
	}
	


	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullArg() {
		new Constant<String>( null );
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
	public void contentShouldBeRetrievable( String content ) {
		
		Constant<String> c;

		c = new Constant<>( content );
		
		assertEquals( content, c.getContent() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	@Parameters( method="getValidIntegerConstantContent" )
	public void equalsConstantWithEqualIntValueShouldReturnTrue( Integer validInt ) {
		
		Constant<Integer> c1;
		Constant<Integer> c2;
		
		c1 = new Constant<>( validInt );
		c2 = new Constant<>( validInt );
		
		assertEquals( c1, c2 );
		assertEquals( c2, c1 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	@Parameters( method="getValidStringConstantContent" )
	public void equalsConstantWithEqualStringValueShouldReturnTrue( String validString ) {
		
		Constant<String> c1;
		Constant<String> c2;
		
		c1 = new Constant<>( validString );
		c2 = new Constant<>( validString );
		
		assertEquals( c1, c2 );
		assertEquals( c2, c1 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsConstantWithNonEqualValueShouldReturnFalse() {
		
		Constant<String> c1;
		Constant<String> c2;
		
		c1 = new Constant<>( "blub" );
		c2 = new Constant<>( "bla" );
		
		assertNotEquals( c1, c2 );
		assertNotEquals( c2, c1 );
	}
	

	
	@SuppressWarnings("static-method")
	@Test
	public void equalsDifferentClassShouldReturnFalse() {
		
		Constant<String> c;
		
		c = new Constant<>( "blub" );
		assertNotEquals( c, 5 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsNullClassShouldReturnFalse() {
		
		Constant<String> c;
		
		c = new Constant<>( "blub" );
		assertNotEquals( c, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsNullShouldReturnFalse() {
		
		Constant<String> c;
		
		c = new Constant<>( "blub" );
		assertNotEquals( c, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	@Parameters( method="getValidStringConstantContent" )
	public void equalsSelfShouldReturnTrue( String validString ) {
		
		Term t1;

		t1 = new Constant<>( validString );
		
		assertEquals( t1, t1 );
	}
	
	@SuppressWarnings("static-method")
	public Object[] getValidIntegerConstantContent() {
		return new Object[][]
		{ {0}, {1873465}, {-3124}, {Integer.MAX_VALUE}, {Integer.MIN_VALUE}};
	}
	
	// public void unifyPlaceholderShouldThrowUpe
	
	@SuppressWarnings("static-method")
	public Object[] getValidStringConstantContent() {
		return new Object[][] { {""}, {"1"}, {"bla"}, {"Bla"}, {"BLA"}};
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
	public void printStringConstantShouldQuoteContent() {
		
		Term t;
		String content;
		
		content = "bla";
		
		t = new Constant<>( content );
		assertEquals( "\""+content+"\"", t.print() );
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
	@Parameters( method="getValidStringConstantContent" )
	public void unifyConstantWithSelfShouldReturnTrue( String content ) {
		
		Constant<String> c;
		
		c = new Constant<>( content );
		
		assertTrue( c.unify( c ) );		
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
	@Test( expected=IllegalArgumentException.class )
	public void unifyConstantWithNullShouldThrowIae() {
		
		Constant<String> c;
		
		c = new Constant<>( "blub" );
		c.unify( null );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=UnexpectedPlaceholderException.class )
	public void unifyConstantWithPhShouldThrowUpe() {
		
		Constant<String> c;
		Placeholder ph;
		
		c = new Constant<>( "blub" );
		ph = new Placeholder( "X" );
		c.unify( ph );
	}

	@SuppressWarnings("static-method")
	@Test
	public void unspecializeDoesNotAffectConstant() {
		
		Constant<String> c;
		
		c = new Constant<>( "bla" );
		c.unspecialize();
	}
}
