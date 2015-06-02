package de.huberlin.wbi.cuneiform.core.libfun;

import static de.huberlin.wbi.cuneiform.core.libfun.List.list;
import static de.huberlin.wbi.cuneiform.core.libfun.Nil.NIL;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class ConsTest {
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullHead() {
		
		List l;
		
		l = mock( List.class );
		new Cons( null, l );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldTrhowIaeOnNullTail() {
		
		Term t;
		
		t = mock( Term.class );
		new Cons( t, null );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void unifyWithNullShouldThrowIae() {
		
		Term t;
		List l;
		Cons c;
		
		t = mock( Term.class );
		l = mock( List.class );
		
		c = new Cons( t, l );
		c.unify( null );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=PhOnRightHandSideException.class )
	public void unifyConsWithPhShouldThrowUpe() {
		
		Cons c;
		Placeholder ph;
		
		c = new Cons( mock( Term.class ), mock( List.class ) );
		ph = new Placeholder();
		c.unify( ph );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsShouldReturnFalseOnNull() {
		
		Term t;
		List l;
		Cons c;
		
		t = mock( Term.class );
		l = mock( List.class );
		
		c = new Cons( t, l );
		
		assertNotEquals( null, c );
		assertNotEquals( c, null );
	}

	@SuppressWarnings("static-method")
	@Test
	public void hdShouldRetrieveHead() {
		
		Cons c;
		Term t;
		
		t = mock( Term.class );
		c = new Cons( t, NIL );
		
		assertEquals( t, c.hd() );
		assertEquals( c.hd(), t );
	}

	@SuppressWarnings("static-method")
	@Test
	public void tlShouldRetrieveTail() {
		
		Cons c, t;
		Term h;
		
		h = mock( Term.class );
		t = mock( Cons.class );
		c = new Cons( h, t );
		
		assertEquals( t, c.tl() );
		assertEquals( c.tl(), t );
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
	public void equalsShouldReturnFalseOnNilNonNilHead() {
		
		Term h1, h2;
		Cons t;
		Cons c1, c2;
		
		h1 = NIL;
		h2 = mock( Term.class );
		t = mock( Cons.class );
		
		c1 = new Cons( h1, t );
		c2 = new Cons( h2, t );
		
		assertNotEquals( c1, c2 );
		assertNotEquals( c2, c1 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsShouldReturnFalseOnNilNonNilTail() {
		
		Term h;
		List t1, t2;
		Cons c1, c2;
		
		h = mock( Term.class );
		t1 = NIL;
		t2 = mock( Cons.class );
		
		c1 = new Cons( h, t1 );
		c2 = new Cons( h, t2 );
		
		assertNotEquals( c1, c2 );
		assertNotEquals( c2, c1 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsShouldReturnTrueOnEverythingNil() {
		
		Cons c1, c2;
		
		c1 = new Cons( NIL, NIL );
		c2 = new Cons( NIL, NIL );
		
		assertEquals( c1, c2 );
		assertEquals( c2, c1 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsNullShouldReturnFalse() {
		
		Cons c;
		
		c = new Cons( mock( Term.class ), mock( List.class ) );

		assertNotEquals( c, null );
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
	public void listShouldConstructList() {
		
		Term term;
		List l;
		
		term = mock( Term.class );
		l = list( term );
		
		assertEquals( term, l.hd() );
		assertEquals( NIL, l.tl() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void printTermNonEmptyListShouldReturnItsElementsInList() {
		
		Term term1, term2;
		Cons l;
		
		term1 = new Constant<>( "bla" );
		term2 = new Constant<>( "blub" );
		
		l = new Cons( term1, list( term2 ) );
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
		Cons c;
		List l;
		
		t = mock( Term.class );
		l = mock( List.class );
		
		c = new Cons( t, l );
		c.unspecialize();
		
		verify( t ).unspecialize();
		verify( l ).unspecialize();
	}
}
