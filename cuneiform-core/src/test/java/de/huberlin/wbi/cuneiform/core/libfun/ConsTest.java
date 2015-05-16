package de.huberlin.wbi.cuneiform.core.libfun;

import static de.huberlin.wbi.cuneiform.core.libfun.List.list;
import static de.huberlin.wbi.cuneiform.core.libfun.Nil.NIL;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class ConsTest {

	@SuppressWarnings("static-method")
	@Test
	public void hdShouldRetrieveHead() {
		
		Cons c;
		Term t;
		
		t = mock( Term.class );
		c = new Cons( t, null );
		
		assertEquals( t, c.getHead() );
	}

	@SuppressWarnings("static-method")
	@Test
	public void tlShouldRetrieveTail() {
		
		Cons c, t;
		Term h;
		
		h = mock( Term.class );
		t = mock( Cons.class );
		c = new Cons( h, t );
		
		assertEquals( t, c.getTail() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsShouldReturnFalseOnDifferingHead() {
		
		Term h1, h2;
		Cons t;
		Cons c1, c2;
		
		t = mock( Cons.class );
		h1 = mock( Term.class );
		h2 = mock( Term.class );
				
		c1 = new Cons( h1, t );
		c2 = new Cons( h2, t );
		
		assertNotEquals( c1, c2 );
		assertNotEquals( c2, c1 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsShouldReturnFalseOnDifferingTail() {
		
		Term h;
		Cons t1, t2;
		Cons c1, c2;
		
		h = mock( Term.class );
		t1 = mock( Cons.class );
		t2 = mock( Cons.class );
		
		c1 = new Cons( h, t1 );
		c2 = new Cons( h, t2 );
		
		assertNotEquals( c1, c2 );
		assertNotEquals( c2, c1 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsShouldReturnFalseOnNullNonNullHead() {
		
		Term h1, h2;
		Cons t;
		Cons c1, c2;
		
		h1 = null;
		h2 = mock( Term.class );
		t = mock( Cons.class );
		
		c1 = new Cons( h1, t );
		c2 = new Cons( h2, t );
		
		assertNotEquals( c1, c2 );
		assertNotEquals( c2, c1 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsShouldReturnFalseOnNullNonNullTail() {
		
		Term h;
		Cons t1, t2;
		Cons c1, c2;
		
		h = mock( Term.class );
		t1 = null;
		t2 = mock( Cons.class );
		
		c1 = new Cons( h, t1 );
		c2 = new Cons( h, t2 );
		
		assertNotEquals( c1, c2 );
		assertNotEquals( c2, c1 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsShouldReturnTrueOnEverythingNull() {
		
		Cons c1, c2;
		
		c1 = new Cons( null, null );
		c2 = new Cons( null, null );
		
		assertEquals( c1, c2 );
		assertEquals( c2, c1 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsShouldReturnTrueOnEverythingEqual() {
		
		Cons c1, c2;
		Term h;
		Cons t;
		
		h = mock( Term.class );
		t = mock( Cons.class );
		
		c1 = new Cons( h, t );
		c2 = new Cons( h, t );
		
		assertEquals( c1, c2 );
		assertEquals( c2, c1 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void compareToNullShouldReturnFalse() {
		
		Cons c;
		
		c = new Cons( mock( Term.class ), mock( Cons.class ) );
		
		assertNotEquals( c, null );
		assertNotEquals( null, c );
	}
	

	
	@SuppressWarnings("static-method")
	@Test
	public void listShouldConstructList() {
		
		Term term;
		List l;
		
		term = mock( Term.class );
		l = list( term );
		
		assertEquals( term, l.getHead() );
		assertNull( l.getTail() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void printTermNonEmptyListShouldReturnItsElementsInList() {
		
		Term term1, term2;
		Cons l;
		
		term1 = new Constant<>( "bla" );
		term2 = new Constant<>( "blub" );
		
		l = new Cons( term1, new Cons( term2, null ) );
		assertEquals( "[\"bla\",\"blub\"]", l.print() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unifyConsWithNonConsShouldReturnFalse() {
		
		Cons cons;
		Constant<String> constant;
		
		cons = new Cons( mock( Term.class ), mock( Cons.class ) );
		constant = new Constant<>( "bla" );
		
		assertFalse( cons.unify( constant ) );
	}

	@SuppressWarnings("static-method")
	@Test
	public void unifyConsShouldDeferTestToHeadAndTail() {
		
		Term h1, h2;
		Cons t1, t2;
		Cons c1, c2;
		
		h1 = mock( Term.class );
		h2 = mock( Term.class );
		t1 = mock( Cons.class );
		t2 = mock( Cons.class );
		
		c1 = new Cons( h1, t1 );
		c2 = new Cons( h2, t2 );
		
		when( h1.unify( h2 ) ).thenReturn( true );
		when( t1.unify( t2 ) ).thenReturn( true );
		
		assertTrue( c1.unify( c2 ) );
		
		verify( h1 ).unify( h2 );
		verify( t1 ).unify( t2 );
	}
	

	
	@SuppressWarnings("static-method")
	@Test
	public void unspecializeIsHandedDownThroughCons() {
		
		Term t;
		Cons c1, c2;
		
		t = mock( Term.class );
		c1 = mock( Cons.class );
		
		c2 = new Cons( t, c1 );
		c2.unspecialize();
		
		verify( t ).unspecialize();
		verify( c1 ).unspecialize();
	}
}
