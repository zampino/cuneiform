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
public class VarTest {

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
	@Test( expected=UnexpectedPlaceholderException.class )
	@Parameters( method="getConcreteTerm" )
	public void unifyAnythingWithVarShouldThrowUve( Term concreteTerm ) {
		concreteTerm.unify( new Placeholder( "Bla" ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	@Parameters( method="getConcreteTerm" )
	public void unifyVarWithAnythingShouldReturnTrue( Term concreteTerm ) {
		
		Placeholder ph;
		
		ph = new Placeholder( "X" );
		assertTrue( ph.unify( concreteTerm ) );
	}
	

	
	// TODO:
	// - disallow superseding of specialization
	// - implement unspecializing of term
}
