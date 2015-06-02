package de.huberlin.wbi.cuneiform.core.libfun;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class TermMapTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorReturnsEmptyMap() {
		
		TermMap m;
		
		m = new TermMap();
		assertTrue( m.isEmpty() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void constructorReturnsMapWithSingleEntry() throws UnboundKeyException {
		
		TermMap m;
		Term key;
		Term value;
		
		key = mock( Term.class );
		value = mock( Term.class );
		
		m = new TermMap( key, value );
		assertEquals( 1, m.size() );
		assertEquals( value, m.get( key ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void putReturnsNewMapWithNewEntry() throws UnboundKeyException {
		
		TermMap m1, m2;
		Term key;
		Term value;
		
		key = mock( Term.class );
		value = mock( Term.class );
		
		
		m1 = new TermMap();
		assertTrue( m1.isEmpty() );
		
		m2 = m1.put( key, value );
		assertTrue( m1.isEmpty() );
		assertEquals( 1, m2.size() );
		assertEquals( value, m2.get( key ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void mergeCombinesTwoMaps() throws UnboundKeyException {
		
		TermMap m1, m2, m3;
		Term key1, key2, value1, value2;
		
		key1 = mock( Term.class );
		key2 = mock( Term.class );
		value1 = mock( Term.class );
		value2 = mock( Term.class );
		
		m1 = new TermMap( key1, value1 );
		m2 = new TermMap( key2, value2 );
		
		m3 = m1.merge( m2 );
		assertEquals( 2, m3.size() );
		assertEquals( value1, m3.get( key1 ) );
		assertEquals( value2, m3.get( key2 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void mergeFirstShouldSupersedeSecond() throws UnboundKeyException {
		
		TermMap m1, m2, m3;
		Term key, value1, value2;
		
		key = mock( Term.class );
		value1 = mock( Term.class );
		value2 = mock( Term.class );
		
		m1 = new TermMap( key, value1 );
		m2 = new TermMap( key, value2 );
		
		m3 = m1.merge( m2 );
		assertEquals( 1, m3.size() );
		assertEquals( value1, m3.get( key ) );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=UnboundKeyException.class )
	public void getUnboundVarShouldThrowUve() throws UnboundKeyException {
		
		TermMap m;
		
		m = new TermMap();
		m.get( mock( Term.class ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void getUnboundVarWithDefaultShouldReturnDefault() {
		
		TermMap m;
		Term def;
		
		m = new TermMap();
		def = mock( Term.class );
		
		assertEquals( def, m.get( mock( Term.class ), def ) );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void getNullShouldThrowIae() throws UnboundKeyException {
		
		TermMap m;
		
		m = new TermMap();
		m.get( null );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void unifyTmWithNullShouldThrowIae() {
		
		TermMap tm;
		
		tm = new TermMap( mock( Term.class ), mock( Term.class ) );
		tm.unify( null );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=UnexpectedPlaceholderException.class )
	public void unifyRecordWithPhShouldThrowUpe() {
		
		TermMap tm;
		Placeholder ph;
		
		tm = new TermMap( mock( Term.class ), mock( Term.class ) );
		ph = new Placeholder( "X" );
		tm.unify( ph );
	}
}
