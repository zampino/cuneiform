package de.huberlin.wbi.cuneiform.core.libfun;

import static de.huberlin.wbi.cuneiform.core.libfun.Constant.constantFrom;
import static de.huberlin.wbi.cuneiform.core.libfun.Atom.atomFrom;
import static de.huberlin.wbi.cuneiform.core.libfun.Nil.NIL;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import junitparams.JUnitParamsRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( JUnitParamsRunner.class )
public class RecordTest {
	

	
	@SuppressWarnings("static-method")
	@Test
	public void lengthShouldBeDerivable() {
		
		Record r;
		Constant<String> c;
		
		
		c = new Constant<>( "my new content" );
		
		r = new Record( c );
		assertEquals( 1, r.length() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void printRecordShouldReturnProperRecord() {
		
		Term record, member, symbol;
		
		symbol = atomFrom( "bla" );
		member = constantFrom( "blub" );
		record = new Record( symbol, member );
		
		assertEquals( "{bla,\"blub\"}", record.print() );
		
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void recordEqualsNullShouldReturnFalse() {
		
		Record r;
		r = new Record( mock( Term.class ) );
		assertNotEquals( r, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void recordEqualsNonRecordReturnFalse() {
		
		Record r;
		r = new Record( mock( Term.class ) );
		assertNotEquals( r, NIL );
		assertNotEquals( NIL, r );
	}

	@SuppressWarnings("static-method")
	@Test
	public void recordsOfDifferingSizeShouldNotBeEqual() {
		
		Record r1, r2;
		
		r1 = new Record();
		r2 = new Record( mock( Term.class ) );
		
		assertNotEquals( r1, r2 );
		assertNotEquals( r2, r1 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void recordsWithEqualTermVecShouldBeEqual() {
		
		Record r1, r2;
		Term t;
		
		t = mock( Term.class );
		
		r1 = new Record( t );
		r2 = new Record( t );
		
		assertEquals( r1, r2 );
		assertEquals( r2, r1 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void recordsWithUnequalTermVecShouldNotBeEqual() {
		
		Record r1, r2;
		Term t1, t2;
		
		t1 = mock( Term.class );
		t2 = mock( Term.class );
		
		r1 = new Record( t1 );
		r2 = new Record( t2 );
		
		assertNotEquals( r1, r2 );
		assertNotEquals( r2, r1 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unifyRecordsWithEqualContentShouldReturnTrue() {
		
		Record r1, r2;
		
		r1 = new Record( new Constant<>( "blub" ) );
		r2 = new Record( new Constant<>( "blub" ) );
		assertTrue( r1.unify( r2 ) );
		assertTrue( r2.unify( r1 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unifyRecordsWithGoodPlaceholderShouldReturnTrue() {
		
		Record r1, r2;
		
		r1 = new Record( new Placeholder() );
		r2 = new Record( new Constant<>( "blub" ) );
		assertTrue( r1.unify( r2 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unifyRecordWithDifferingFieldsShouldReturnFalse() {
		
		Record r1, r2;
		Term c1, c2;
		
		c1 = mock( Term.class );
		c2 = mock( Term.class );
		
		r1 = new Record( c1 );
		r2 = new Record( c2 );
		
		assertFalse( r1.unify( r2 ) );
		assertFalse( r2.unify( r1 ) );

		verify( c1 ).unify( c2 );
		verify( c2 ).unify( c1 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unifyRecordWithDifferingLengthShouldReturnFalse() {
		
		Record r1, r2;
		
		r1 = new Record();
		r2 = new Record( mock( Term.class ) );
		
		assertFalse( r1.unify( r2 ) );
		assertFalse( r2.unify( r1 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unifyRecordWithNonRecordShouldReturnFalse() {
		
		Record r;
		Cons c;
		
		r = new Record();
		c = mock( Cons.class );
		assertFalse( r.unify( c ) );
	}
	
	
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void unifyRecordWithNullShouldThrowIae() {
		
		Record r;
		Term t;
		
		t = mock( Term.class );
		
		r = new Record( t );
		r.unify( null );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=PhOnRightHandSideException.class )
	public void unifyRecordWithPhShouldThrowUpe() {
		
		Record r;
		Placeholder ph;
		
		r = new Record( mock( Term.class ) );
		ph = new Placeholder();
		r.unify( ph );
	}

	@SuppressWarnings("static-method")
	@Test
	public void unifyRecordWithSelfShouldReturnTrue() {
		
		Record r;
		
		r = new Record( new Constant<>( "blub" ) );
		assertTrue( r.unify( r ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unificationShouldRetrieveFieldsInRecord() {
		
		Record concrete, pattern;
		Constant<String> value1;
		Constant<Integer> value2;
		Placeholder x, y;
		
		value1 = constantFrom( "blub" );
		value2 = constantFrom( 54 );
		
		concrete = new Record( value1, value2 );
		
		x = new Placeholder();
		y = new Placeholder();
		pattern = new Record( x, y );
		
		assertTrue( pattern.unify( concrete ) );
		
		assertEquals( value1, x.getSpecializedValue() );
		assertEquals( value2, y.getSpecializedValue() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unspecializeIsHandedDownThroughRecord() {
		
		Term t;
		Record r;
		
		t = mock( Term.class );
		r = new Record( t );
		
		r.unspecialize();
		verify( t ).unspecialize();
	}
}
