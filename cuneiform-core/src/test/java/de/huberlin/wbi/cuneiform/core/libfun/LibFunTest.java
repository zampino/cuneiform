package de.huberlin.wbi.cuneiform.core.libfun;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static de.huberlin.wbi.cuneiform.core.libfun.LibFun.*;
import junitparams.Parameters;
import junitparams.JUnitParamsRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( JUnitParamsRunner.class )
public class LibFunTest {
	
	/*
	 * Parameter initialization
	 */
	
	@SuppressWarnings("static-method")
	public Object[] getConcreteTerm() {
		return new Object[][] {
			{constantFrom( "bla" )},
			{new Record( "a", constantFrom( "b" ), constantFrom( 4 ) )},
			{null},
			{list( constantFrom( 1 ), constantFrom( 2 ), constantFrom( 3 ) )}};
	}
	
	/*
	 * Actual tests
	 */
	
	// TODO
	// head returns car of list
	// tail returns cdr of list
	
	@SuppressWarnings("static-method")
	@Test
	public void nullShouldBeList() {
		assertTrue( isList( null ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void recordShouldNotBeList() {
		
		Record r;
		
		r = mock( Record.class );
		assertFalse( isList( r ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void consShouldBeList() {
		
		Cons c;
		
		c = mock( Cons.class );
		assertTrue( isList( c ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void constantShouldNotBeList() {
		
		Constant<String> s;
		
		s = new Constant<>( "bla" );

		assertFalse( isList( s ) );
	}
		
	@SuppressWarnings("static-method")
	@Test
	public void consShouldConstructList() {
		
		Term term;
		Cons l;
		
		term = mock( Term.class );
		
		l = cons( term, null );
		
		assertTrue( isList( l ) );
		assertEquals( term, l.getHead() );
		assertNull( l.getTail() );		
	}
			


	@SuppressWarnings("static-method")
	@Test
	public void listShouldConstructList() {
		
		Term term;
		Cons l;
		
		term = mock( Term.class );
		l = list( term );
		
		assertTrue( isList( l ) );
		assertEquals( term, l.getHead() );
		assertNull( l.getTail() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void printTermNullShouldReturnEmptyList() {
		
		String s;
		
		s = printTerm( null );
		assertEquals( "[]", s );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void printTermNonEmptyListShouldReturnItsElementsInList() {
		
		Term term1, term2;
		Cons l;
		
		term1 = new Constant<>( "bla" );
		term2 = new Constant<>( "blub" );
		
		l = new Cons( term1, new Cons( term2, null ) );
		assertEquals( "[\"bla\",\"blub\"]", printTerm( l ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void printStringConstantShouldQuoteContent() {
		
		Term t;
		String content;
		
		content = "bla";
		
		t = new Constant<>( content );
		assertEquals( "\""+content+"\"", printTerm( t ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void printIntegerConstantShouldReturnStringValue() {
		
		Term t;
		int content;
		
		content = 4;
		t = new Constant<>( content );
		assertEquals( String.valueOf( content ), printTerm( t ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void printRecordShouldReturnProperRecord() {
		
		Term record, member;
		String symbol;
		
		symbol = "bla";
		member = constantFrom( "blub" );
		record = new Record( symbol, member );
		
		assertEquals( "{bla,\"blub\"}", printTerm( record ) );
		
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void printVarShouldReturnName() {
		
		Var v;
		String name;
		
		name = "X";
		v = new Var( name );
		assertEquals( name, printTerm( v ) );
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
	public void unifyEmptyListShouldWork() {
		assertTrue( unify( null, null ) );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=UnexpectedVarException.class )
	@Parameters( method="getConcreteTerm" )
	public void unifyAnythingWithVarShouldThrowUve( Term concreteTerm ) {
		unify( concreteTerm, new Var( "Bla" ) );
	}
	
	
	@SuppressWarnings("static-method")
	@Test
	@Parameters( method="getConcreteTerm" )
	public void unifyVarWithAnythingShouldReturnTrue( Term concreteTerm ) {
		assertTrue( unify( new Var( "X" ), concreteTerm ) );
	}
}
