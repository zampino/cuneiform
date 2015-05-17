package de.huberlin.wbi.cuneiform.core.libfun;

import static de.huberlin.wbi.cuneiform.core.libfun.Constant.constantFrom;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( JUnitParamsRunner.class )
public class RecordTest {
	
	/*
	 * Parameter initialization
	 */
	
	@SuppressWarnings("static-method")
	public Object[] getValidSymbol() {
		return new Object[][] {{"bla"}, {"blub"}, {"x"}, {"a"}, {"z"}, {"aA"} };
	}
	
	@SuppressWarnings("static-method")
	public Object[] getIllegalSymbol() {
		return new Object[][] {{null}, {""}, {"X"}, {"`"}, {"{"}, {"0"}};
	}
	
	/*
	 * Actual tests
	 */


	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	@Parameters( method="getIllegalSymbol" )
	public void constructorShouldThrowIaeOnIllegalSymbol( String symbol ) {
		new Record( symbol );
	}
	
	@SuppressWarnings("static-method")
	@Test
	@Parameters( method="getValidSymbol" )
	public void symbolShouldBeRetrievable( String symbol ) {
		
		Record r;
		
		r = new Record( symbol );
		assertEquals( symbol, r.getSymbol() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void lengthShouldBeDerivable() {
		
		Record r;
		String symbol;
		Constant<String> c;
		
		symbol = "blub";
		
		c = new Constant<>( "my new content" );
		
		r = new Record( symbol, c );
		assertEquals( 1, r.length() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void EmptyRecordWithEqualSymbolShouldBeEqual() {
		
		Record r1, r2;
		String s;
		
		s = "bla";
		
		r1 = new Record( s );
		r2 = new Record( s );
		
		assertEquals( r1, r2 );
		assertEquals( r2, r1 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void recordsOfDifferingSizeShouldNotBeEqual() {
		
		Record r1, r2;
		String s;
		
		s = "bla";
		
		r1 = new Record( s );
		r2 = new Record( s, mock( Term.class ) );
		
		assertNotEquals( r1, r2 );
		assertNotEquals( r2, r1 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void recordsWithEqualTermVecShouldBeEqual() {
		
		Record r1, r2;
		String s;
		Term t;
		
		s = "bla";
		t = mock( Term.class );
		
		r1 = new Record( s, t );
		r2 = new Record( s, t );
		
		assertEquals( r1, r2 );
		assertEquals( r2, r1 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void recordsWithUnequalTermVecShouldNotBeEqual() {
		
		Record r1, r2;
		String s;
		Term t1, t2;
		
		s = "bla";
		t1 = mock( Term.class );
		t2 = mock( Term.class );
		
		r1 = new Record( s, t1 );
		r2 = new Record( s, t2 );
		
		assertNotEquals( r1, r2 );
		assertNotEquals( r2, r1 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unifyWithNonRecordShouldReturnFalse() {
		
		Record r;
		Cons c;
		
		r = new Record( "bla" );
		c = mock( Cons.class );
		assertFalse( r.unify( c ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	@Parameters( method="getValidSymbol" )
	public void unifyOfEmptyRecordsWithEqualNameShouldReturnTrue( String symbol ) {
		
		Record r1, r2;
		
		r1 = new Record( symbol );
		r2 = new Record( symbol );
		
		assertTrue( r1.unify( r2 ) );
		assertTrue( r2.unify( r1 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unifyRecordWithDifferingLengthShouldReturnFalse() {
		
		Record r1, r2;
		String symbol;
		
		symbol = "bla";
		
		r1 = new Record( symbol );
		r2 = new Record( symbol, mock( Cons.class ) );
		
		assertFalse( r1.unify( r2 ) );
		assertFalse( r2.unify( r1 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unifyRecordWithDifferingFieldsShouldReturnFalse() {
		
		Record r1, r2;
		String symbol;
		Term c1, c2;
		
		symbol = "bla";
		c1 = mock( Term.class );
		c2 = mock( Term.class );
		
		r1 = new Record( symbol, c1 );
		r2 = new Record( symbol, c2 );
		
		assertFalse( r1.unify( r2 ) );
		assertFalse( r2.unify( r1 ) );

		verify( c1 ).unify( c2 );
		verify( c2 ).unify( c1 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void printRecordShouldReturnProperRecord() {
		
		Term record, member;
		String symbol;
		
		symbol = "bla";
		member = constantFrom( "blub" );
		record = new Record( symbol, member );
		
		assertEquals( "{bla,\"blub\"}", record.print() );
		
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unspecializeIsHandedDownThroughRecord() {
		
		Term t;
		Record r;
		
		t = mock( Term.class );
		r = new Record( "bla", t );
		
		r.unspecialize();
		verify( t ).unspecialize();
	}
}
