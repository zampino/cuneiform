package de.huberlin.wbi.cuneiform.core.libfun;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static de.huberlin.wbi.cuneiform.core.libfun.LibFun.*;

import java.util.Map;

import junitparams.Parameters;
import junitparams.JUnitParamsRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( JUnitParamsRunner.class )
public class LibFunTest {
	
	/*
	 * Parameter initialization
	 */
	
	@SuppressWarnings("static-method")
	public Object[] getConcreteTerm() {
		return new Object[][] {
			{constantFrom( "bla" )},
			{new Record( "a", constantFrom( "b" ), constantFrom( 4 ) )},
			{null},
			{list( constantFrom( 1 ), constantFrom( 2 ), constantFrom( 3 ) )}};
	}
	
	/*
	 * Actual tests
	 */
	
	@SuppressWarnings("static-method")
	@Test
	public void consShouldConstructList() {
		
		Term term;
		Cons l;
		
		term = mock( Term.class );
		
		l = cons( term, null );
		
		assertEquals( term, l.getHead() );
		assertNull( l.getTail() );		
	}
			


	@SuppressWarnings("static-method")
	@Test
	public void listShouldConstructList() {
		
		Term term;
		Cons l;
		
		term = mock( Term.class );
		l = list( term );
		
		assertEquals( term, l.getHead() );
		assertNull( l.getTail() );
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
	public void printStringConstantShouldQuoteContent() {
		
		Term t;
		String content;
		
		content = "bla";
		
		t = new Constant<>( content );
		assertEquals( "\""+content+"\"", printTerm( t ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void printIntegerConstantShouldReturnStringValue() {
		
		Term t;
		int content;
		
		content = 4;
		t = new Constant<>( content );
		assertEquals( String.valueOf( content ), printTerm( t ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void printRecordShouldReturnProperRecord() {
		
		Term record, member;
		String symbol;
		
		symbol = "bla";
		member = constantFrom( "blub" );
		record = new Record( symbol, member );
		
		assertEquals( "{bla,\"blub\"}", printTerm( record ) );
		
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void printVarShouldReturnName() {
		
		Var v;
		String name;
		
		name = "X";
		v = new Var( name );
		assertEquals( name, printTerm( v ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void constantFromStringShouldRememberContent() {
		
		Constant<String> c;
		String content;
		
		content = "bla";
		
		c = constantFrom( content );
		assertEquals( content, c.getContent() );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void constantFromStringShouldThrowIaeOnNullContent() {
		constantFrom( null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void constantFromIntShouldRememberContent() {
		
		Constant<Integer> c;
		Integer content;
		
		content = 4;
		c = constantFrom( content );
		
		assertEquals( content, c.getContent() );
		
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void nullShouldBeEqual() {
		assertTrue( eq( null, null ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void nullNonNullShouldNotBeEqual() {
		
		Term t;
		
		t = mock( Term.class );
		
		assertFalse( eq( null, t ) );
		assertFalse( eq( t, null ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void EqShouldReturnOnStringConstantWithEqualContent() {
		
		String content;
		Term t1, t2;
		
		content = "bla";
		
		t1 = new Constant<>( content );
		t2 = new Constant<>( content );
		
		assertTrue( eq( t1, t2 ) );
		assertTrue( eq( t2, t1 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void EqShouldReturnFalseOnDifferingStringConstants() {
		
		Term t1, t2;
		
		
		t1 = new Constant<>( "bla" );
		t2 = new Constant<>( "blub" );
		
		assertFalse( eq( t1, t2 ) );
		assertFalse( eq( t2, t1 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unifyEmptyListShouldReturnTrue() {
		assertTrue( unify( null, null ) );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=UnexpectedVarException.class )
	@Parameters( method="getConcreteTerm" )
	public void unifyAnythingWithVarShouldThrowUve( Term concreteTerm ) {
		unify( concreteTerm, new Var( "Bla" ) );
	}
	
	
	@SuppressWarnings("static-method")
	@Test
	@Parameters( method="getConcreteTerm" )
	public void unifyVarWithAnythingShouldReturnTrue( Term concreteTerm ) {
		assertTrue( unify( new Var( "X" ), concreteTerm ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unifyMultipleTimesWithEqualValueShouldReturnTrue() {
		
		Term t;
		Var x;
		Cons abstractList, concreteList;
		
		t = mock( Term.class );
		x = new Var( "X" );
		
		abstractList = list( x, x );
		concreteList = list( t, t );
	
		assertFalse( x.isSpecialized() );
		assertTrue( unify( abstractList, concreteList ) );
		assertTrue( x.isSpecialized() );
		assertEquals( t, x.getSpecializedValue() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unifyMultipleTimesWithDifferentValueShouldReturnFalse() {
		
		Term t1, t2;
		Var x;
		Cons abstractList, concreteList;
		
		t1 = mock( Term.class );
		t2 = mock( Term.class );
		x = new Var( "X" );
		
		abstractList = list( x, x );
		concreteList = list( t1, t2 );
	
		assertFalse( x.isSpecialized() );
		assertFalse( unify( abstractList, concreteList ) );
	}

	@SuppressWarnings("static-method")
	@Test
	public void unspecializingVarAllowsNewUnification() {
		
		Term t1, t2;
		Var x;
		
		t1 = mock( Term.class );
		t2 = mock( Term.class );
		
		x = new Var( "X" );
		
		assertFalse( x.isSpecialized() );
		assertTrue( unify( x, t1 ) );
		assertTrue( x.isSpecialized() );
		
		x.unspecialize();
		
		assertFalse( x.isSpecialized() );
		assertTrue( unify( x, t2 ) );
		assertTrue( x.isSpecialized() );
		
		assertFalse( unify( x, t1 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unspecializeIsHandedDownToVar() {
		
		Var var;
		
		var = mock( Var.class );
		
		unspecialize( var );
		verify( var ).unspecialize();
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unspecializeDoesNotAffectEmptyList() {
		unspecialize( null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unspecializeDoesNotAffectConstant() {
		unspecialize( new Constant<>( "bla" ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unspecializeIsHandedDownThroughCons() {
		
		Term t;
		Cons c1, c2;
		
		t = mock( Term.class );
		c1 = mock( Cons.class );
		
		c2 = cons( t, c1 );
		unspecialize( c2 );
		
		verify( t ).unspecialize();
		verify( c1 ).unspecialize();
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unspecializeIsHandedDownThroughRecord() {
		
		Term t;
		Record r;
		
		t = mock( Term.class );
		r = new Record( "bla", t );
		
		unspecialize( r );
		verify( t ).unspecialize();
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void mapReturnsEmptyMap() {
		
		Map<Term,Term> m;
		
		m = map();
		assertTrue( m.isEmpty() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void putReturnsNewMapWithNewEntry() throws UnboundVarException {
		
		Map<Term,Term> m1, m2;
		Term key;
		Term value;
		
		key = mock( Term.class );
		value = mock( Term.class );
		
		
		m1 = map();
		assertTrue( m1.isEmpty() );
		
		m2 = put( key, value, m1 );
		assertTrue( m1.isEmpty() );
		assertEquals( 1, m2.size() );
		assertEquals( value, get( key, m2 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void mapReturnsMapWithSingleEntry() throws UnboundVarException {
		
		Map<Term,Term> m;
		Term key;
		Term value;
		
		key = mock( Term.class );
		value = mock( Term.class );
		
		m = map( key, value );
		assertEquals( 1, m.size() );
		assertEquals( value, get( key, m ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void mergeCombinesTwoMaps() throws UnboundVarException {
		
		Map<Term,Term> m1, m2, m3;
		Term key1, key2, value1, value2;
		
		key1 = mock( Term.class );
		key2 = mock( Term.class );
		value1 = mock( Term.class );
		value2 = mock( Term.class );
		
		m1 = map( key1, value1 );
		m2 = map( key2, value2 );
		
		m3 = merge( m1, m2 );
		assertEquals( 2, m3.size() );
		assertEquals( value1, get( key1, m3 ) );
		assertEquals( value2, get( key2, m3 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void mergeFirstShouldSupersedeSecond() throws UnboundVarException {
		
		Map<Term,Term> m1, m2, m3;
		Term key, value1, value2;
		
		key = mock( Term.class );
		value1 = mock( Term.class );
		value2 = mock( Term.class );
		
		m1 = map( key, value1 );
		m2 = map( key, value2 );
		
		m3 = merge( m1, m2 );
		assertEquals( 1, m3.size() );
		assertEquals( value1, get( key, m3 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=UnboundVarException.class )
	public void getUnboundVarShouldThrowUve() throws UnboundVarException {
		
		Map<Term,Term> m;
		
		m = map();
		get( mock( Term.class ), m );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void getUnboundVarWithDefaultShouldReturnDefault() {
		
		Map<Term,Term> m;
		Term def;
		
		m = map();
		def = mock( Term.class );
		
		assertEquals( def, get( mock( Term.class ), m, def ) );
	}
}
