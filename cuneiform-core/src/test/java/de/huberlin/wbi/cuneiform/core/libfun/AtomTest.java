package de.huberlin.wbi.cuneiform.core.libfun;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import static de.huberlin.wbi.cuneiform.core.libfun.Nil.NIL;
import static de.huberlin.wbi.cuneiform.core.libfun.Atom.*;
import static org.junit.Assert.*;

@RunWith( JUnitParamsRunner.class )
public class AtomTest {
	
	@SuppressWarnings("static-method")
	public Object[] getIllegalName() {
		return new Object[][] {{null}, {""}, {"X"}, {"`"}, {"{"}, {"0"}};
	}
	
	@SuppressWarnings("static-method")
	public Object[] getValidName() {
		return new Object[][] {{"bla"}, {"blub"}, {"x"}, {"a"}, {"z"}, {"aA"} };
	}

	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructNullShouldThrowIae() {
		new Atom( null );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructEmptyNameShouldThrowIae() {
		new Atom( "" );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void unifyAtomWithNullShouldThrowIae() {
		
		Atom a;
		
		a = new Atom( "blub" );
		a.unify( null );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=PhOnRightHandSideException.class )
	public void unifyAtomWithPlaceholderShouldThrowUpe() {
		
		Atom a;
		
		a = new Atom( "blub" );
		a.unify( new Placeholder() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unifyAtomWithNonAtomShouldReturnFalse() {
		
		Atom a;
		
		a = new Atom( "blub" );
		assertFalse( a.unify( NIL ) );
		assertFalse( NIL.unify( a ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	@Parameters( method="getValidName" )
	public void unifyAtomWithSelfShouldReturnTrue( String validName ) {
		
		Atom a;
		
		a = new Atom( validName );
		assertTrue( a.unify( a ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	@Parameters( method="getValidName" )
	public void unifyAtomsWithEqualNameShouldReturnTrue( String validName ) {
		
		Atom a1, a2;
		
		a1 = new Atom( validName );
		a2 = new Atom( validName );
		
		assertTrue( a1.unify( a2 ) );
		assertTrue( a2.unify( a1 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unifyAtomsWithDifferentNameShouldReturnFalse() {
		
		Atom a1, a2;
		
		a1 = new Atom( "blub" );
		a2 = new Atom( "bla" );
		
		assertFalse( a1.unify( a2 ) );
		assertFalse( a2.unify( a1 ) );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	@Parameters( method="getIllegalName" )
	public void constructorShouldThrowIaeOnIllegalSymbol( String symbol ) {
		new Atom( symbol );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsNullShouldReturnFalse() {		
		assertNotEquals( atomFrom( "blub" ), null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsNonAtomShouldReturnFalse() {
		
		assertNotEquals( atomFrom( "blub" ), NIL );
		assertNotEquals( NIL, atomFrom( "blub" ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsSelfShouldReturnTrue() {
		
		Atom a;
		
		a = atomFrom( "blub" );
		assertEquals( a, a );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsNonEqualNameShouldReturnFalse() {
		
		Atom a1, a2;
		
		a1 = atomFrom( "bla" );
		a2 = atomFrom( "blub" );
		
		assertNotEquals( a1, a2 );
		assertNotEquals( a2, a1 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsEqualNameShouldReturnTrue() {
		
		Atom a1, a2;
		
		a1 = atomFrom( "bla" );
		a2 = atomFrom( "bla" );
		
		assertEquals( a1, a2 );
		assertEquals( a2, a1 );
	}
	
}
