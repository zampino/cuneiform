package de.huberlin.wbi.cuneiform.core.libfun;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

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
}
