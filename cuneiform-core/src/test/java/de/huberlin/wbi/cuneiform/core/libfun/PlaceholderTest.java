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
	
	@SuppressWarnings("static-method")
	public Object[] getValidName() {
		return new Object[][] {{"Bla"}, {"Blub"}};
	}
	
	@SuppressWarnings("static-method")
	public Object[] getIllegalName() {
		return new Object[][] {{null}, {""}, {"bla"}, {"0"}, {"@"}, {"["}};
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
		x = new Placeholder( "X" );
		
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
		x = new Placeholder( "X" );
		
		abstractList = list( x, x );
		concreteList = list( t, t );
	
		assertFalse( x.isSpecialized() );
		assertTrue( abstractList.unify( concreteList ) );
		assertTrue( x.isSpecialized() );
		assertEquals( t, x.getSpecializedValue() );
	}

	@SuppressWarnings("static-method")
	@Test
	public void unspecializingVarAllowsNewUnification() {
		
		Term t1, t2;
		Placeholder x;
		
		t1 = mock( Term.class );
		t2 = mock( Term.class );
		
		x = new Placeholder( "X" );
		
		assertFalse( x.isSpecialized() );
		assertTrue( x.unify( t1 ) );
		assertTrue( x.isSpecialized() );
		
		x.unspecialize();
		
		assertFalse( x.isSpecialized() );
		assertTrue( x.unify( t2 ) );
		assertTrue( x.isSpecialized() );
		
		assertFalse( x.unify( t1 ) );
	}
			

	
	@SuppressWarnings({ "static-method", "unused" })
	@Test
	@Parameters( method="getValidName" )
	public void constructorShouldAcceptValidName( String name ) {
		new Placeholder( name );
	}
	

	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	@Parameters( method="getIllegalName" )
	public void constructorShouldThrowIaeOnIllegalName( String name ) {
		new Placeholder( name );
	}
	
	@SuppressWarnings("static-method")
	@Test
	@Parameters( method="getValidName" )
	public void nameShouldBeRetrievable( String name ) {
		
		Placeholder var;
		
		var = new Placeholder( name );
		
		assertEquals( name, var.getName() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unifyShouldMemorizeBoundValue() {
		
		Term t;
		Placeholder var;
		
		t = mock( Term.class );

		var = new Placeholder( "X" );
		assertTrue( var.unify( t ) );
		assertEquals( t, var.getSpecializedValue() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void printVarShouldReturnName() {
		
		Placeholder v;
		String name;
		
		name = "X";
		v = new Placeholder( name );
		assertEquals( name, v.print() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	@Parameters( method="getConcreteTerm" )
	public void unifyPhWithAnythingShouldReturnTrue( Term concreteTerm ) {
		
		Placeholder ph;
		
		ph = new Placeholder( "X" );
		assertTrue( ph.unify( concreteTerm ) );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void unifyPhWithNullShouldThrowIae() {
		
		Placeholder ph;
		
		ph = new Placeholder( "X" );
		ph.unify( null );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=UnexpectedPlaceholderException.class )
	public void unifyPhWithPhShouldThrowUpe() {
		
		
		Placeholder ph1, ph2;
		
		ph1 = new Placeholder( "X" );
		ph2 = new Placeholder( "Y" );
		ph1.unify( ph2 );
	}
	
	// TODO:
	// - disallow superseding of specialization
	// - implement unspecializing of term
}
