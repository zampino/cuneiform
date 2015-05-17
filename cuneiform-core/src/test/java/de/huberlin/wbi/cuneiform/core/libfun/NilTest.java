package de.huberlin.wbi.cuneiform.core.libfun;

import static de.huberlin.wbi.cuneiform.core.libfun.Nil.NIL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class NilTest {

	@SuppressWarnings("static-method")
	@Test
	public void printOnNilShouldReturnEmptyListString() {
		
		String s;
		
		s = NIL.print();
		assertEquals( "[]", s );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void NilShouldEqualItself() {
		assertTrue( NIL.equals( NIL ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void nilShouldNotEqualNonNil() {
		
		Term t;
		
		t = mock( Term.class );
		
		assertFalse( NIL.equals( t ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unspecializeDoesNotAffectEmptyList() {
		NIL.unspecialize();
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=InvalidOpOnNilException.class )
	public void getHeadOnNilShouldThrowIoone() {
		NIL.getHead();
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=InvalidOpOnNilException.class )
	public void getTailOnNilShouldThrowIoone() {
		NIL.getTail();
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unifyEmptyListShouldReturnTrue() {
		assertTrue( NIL.unify( NIL ) );
	}
}
