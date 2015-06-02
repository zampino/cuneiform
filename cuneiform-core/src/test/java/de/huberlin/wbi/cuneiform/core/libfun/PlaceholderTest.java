package de.huberlin.wbi.cuneiform.core.libfun;

import static de.huberlin.wbi.cuneiform.core.libfun.Constant.constantFrom;
import static de.huberlin.wbi.cuneiform.core.libfun.List.list;
import static de.huberlin.wbi.cuneiform.core.libfun.Nil.NIL;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( JUnitParamsRunner.class )
public class PlaceholderTest {

	@SuppressWarnings( "static-method" )
	public Object[] getConcreteTerm() {
		return new Object[][] {
			{constantFrom( "bla" )},
			{new Record( "a", constantFrom( "b" ), constantFrom( 4 ) )},
			{NIL},
			{list( constantFrom( 1 ), constantFrom( 2 ), constantFrom( 3 ) )}};
	}
	
	/*
	 * Actual Tests
	 */
	
	@SuppressWarnings("static-method")
	@Test
	public void unifyMultipleTimesWithDifferentValueShouldReturnFalse() {
		
		Term t1, t2;
		Placeholder x;
		List abstractList, concreteList;
		
		t1 = mock( Term.class );
		t2 = mock( Term.class );
		x = new Placeholder();
		
		abstractList = list( x, x );
		concreteList = list( t1, t2 );
	
		assertFalse( x.isSpecialized() );
		assertFalse( abstractList.unify( concreteList ) );
	}

	@SuppressWarnings("static-method")
	@Test
	public void unifyMultipleTimesWithEqualValueShouldReturnTrue() {
		
		Term t;
		Placeholder x;
		List abstractList, concreteList;
		
		t = mock( Term.class );
		x = new Placeholder();
		
		abstractList = list( x, x );
		concreteList = list( t, t );
	
		assertTrue( abstractList.unify( concreteList ) );
		assertTrue( x.isSpecialized() );
	}

	@SuppressWarnings("static-method")
	@Test
	public void unspecializingPhAllowsNewUnification() {
		
		Term t1, t2;
		Placeholder x;
		
		t1 = mock( Term.class );
		t2 = mock( Term.class );
		
		x = new Placeholder();
		
		assertFalse( x.isSpecialized() );
		assertTrue( x.unify( t1 ) );
		assertTrue( x.isSpecialized() );
		
		x.unspecialize();
		
		assertFalse( x.isSpecialized() );
		assertTrue( x.unify( t2 ) );
		assertTrue( x.isSpecialized() );
		
		assertFalse( x.unify( t1 ) );
	}
			

	

	
	@SuppressWarnings("static-method")
	@Test
	public void unifyShouldMemorizeSpecializedValue() {
		
		Term t;
		Placeholder var;
		
		t = mock( Term.class );
		var = new Placeholder();
		
		assertFalse( var.isSpecialized() );
		assertTrue( var.unify( t ) );
		assertEquals( t, var.getSpecializedValue() );
		assertTrue( var.isSpecialized() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	@Parameters( method="getConcreteTerm" )
	public void unifyPhWithAnythingShouldReturnTrue( Term concreteTerm ) {
		
		Placeholder ph;
		
		ph = new Placeholder();
		assertTrue( ph.unify( concreteTerm ) );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void unifyPhWithNullShouldThrowIae() {
		
		Placeholder ph;
		
		ph = new Placeholder();
		ph.unify( null );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=UnexpectedPlaceholderException.class )
	public void unifyPhWithPhShouldThrowUpe() {
		
		
		Placeholder ph1, ph2;
		
		ph1 = new Placeholder();
		ph2 = new Placeholder();
		ph1.unify( ph2 );
	}
}
