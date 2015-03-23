package de.huberlin.wbi.cuneiform.core.libfun;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static de.huberlin.wbi.cuneiform.core.libfun.LibFun.*;

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
				
		c1 = cons( h1, t );
		c2 = cons( h2, t );
		
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
		
		c1 = cons( h, t1 );
		c2 = cons( h, t2 );
		
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
		
		c1 = cons( h1, t );
		c2 = cons( h2, t );
		
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
		
		c1 = cons( h, t1 );
		c2 = cons( h, t2 );
		
		assertNotEquals( c1, c2 );
		assertNotEquals( c2, c1 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsShouldReturnTrueOnEverythingNull() {
		
		Cons c1, c2;
		
		c1 = cons( null, null );
		c2 = cons( null, null );
		
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
		
		c1 = cons( h, t );
		c2 = cons( h, t );
		
		assertEquals( c1, c2 );
		assertEquals( c2, c1 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void compareToNullShouldReturnFalse() {
		
		Cons c;
		
		c = cons( mock( Term.class ), mock( Cons.class ) );
		
		assertNotEquals( c, null );
		assertNotEquals( null, c );
	}
}
