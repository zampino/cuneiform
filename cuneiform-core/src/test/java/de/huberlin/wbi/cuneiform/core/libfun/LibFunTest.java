package de.huberlin.wbi.cuneiform.core.libfun;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static de.huberlin.wbi.cuneiform.core.libfun.LibFun.*;

import org.junit.Test;


public class LibFunTest {
	
	/*
	 * Parameter initialization
	 */
	
	/*
	 * Actual tests
	 */
	
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
		assertEquals( null, l.getTail() );		
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
	public void printSeqShouldQuoteContent() {
		
		Term t;
		String content;
		
		content = "bla";
		
		t = new Constant<>( content );
		assertEquals( "\""+content+"\"", printTerm( t ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unifyingEmptyListShouldWork() {
		assertTrue( unify( null, null ) );
	}
}
