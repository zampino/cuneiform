package de.huberlin.wbi.cuneiform.core.libfun;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static de.huberlin.wbi.cuneiform.core.libfun.Nil.NIL;

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
	public void constructorReturnsMapWithSingleEntry() {
		
		TermMap m;
		Term key;
		Term value;
		
		key = mock( Term.class );
		value = mock( Term.class );
		
		m = new TermMap( key, value );
		assertEquals( 1, m.size() );
		assertEquals( value, m.get( key ) );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructThrowsIaeOnNullKey() {
		new TermMap( null, mock( Term.class ) );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructThrowsIaeOnNullValue() {
		new TermMap( mock( Term.class ), null );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=PhAsKeyInTermMapException.class )
	public void constructThrowsUpeOnPhKey() {
		new TermMap( new Placeholder(), mock( Term.class ) );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void getNullShouldThrowIae() throws UnboundKeyException {
		
		TermMap m;
		
		m = new TermMap();
		m.get( null );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=UnboundKeyException.class )
	public void getUnboundKeyShouldThrowUve() {
		
		TermMap m;
		
		m = new TermMap();
		m.get( mock( Term.class ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void getUnboundKeyWithDefaultShouldReturnDefault() {
		
		TermMap m;
		Term def;
		
		m = new TermMap();
		def = mock( Term.class );
		
		assertEquals( def, m.get( mock( Term.class ), def ) );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void getNullWithDefaultShouldThrowIae() {
		
		TermMap m;
		Term def;
		
		m = new TermMap();
		def = mock( Term.class );
		
		m.get( null, def );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=PhAsKeyInTermMapException.class )
	public void getPlaceholderWithDefaultShouldThrowUpe() {
		
		TermMap m;
		Term def;
		
		m = new TermMap();
		def = mock( Term.class );
		
		m.get( new Placeholder(), def );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void getWithNullDefaultShouldThrowIae() {
		
		TermMap m;
		
		m = new TermMap();
		m.get( mock( Term.class ), null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void mergeCombinesTwoMaps() {
		
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
	public void mergeFirstShouldSupersedeSecond() {
		
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
	@Test
	public void mergeLeavesBothOriginalMapsUnchanged() {
		
		TermMap tm1, tm2, tm3;
		
		tm1 = new TermMap( mock( Term.class ), mock( Term.class ) );
		assertEquals( 1, tm1.size() );
		
		tm2 = new TermMap( mock( Term.class ), mock( Term.class ) );
		assertEquals( 1, tm2.size() );
		
		tm3 = tm1.merge( tm2 );
		assertEquals( 1, tm1.size() );
		assertEquals( 1, tm2.size() );
		assertEquals( 2, tm3.size() );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void mergeWithNullShouldThrowIae() {
		
		TermMap tm;
		
		tm = new TermMap();
		tm.merge( null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void putLeavesOriginalMapUnchanged() {
		
		TermMap tm1, tm2;
		
		tm1 = new TermMap( mock( Term.class ), mock( Term.class ) );
		assertEquals( 1, tm1.size() );
		
		tm2 = tm1.put( mock( Term.class ),  mock( Term.class ) );
		assertEquals( 1, tm1.size() );
		assertEquals( 2, tm2.size() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void putReturnsNewMapWithNewEntry() {
		
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
	@Test( expected=IllegalArgumentException.class )
	public void putThrowsIaeOnNullKey() {
		
		TermMap tm;
		
		tm = new TermMap();
		tm.put( null, mock( Term.class ) );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void putThrowsIaeOnNullValue() {
		
		TermMap tm;
		
		tm = new TermMap();
		tm.put( mock( Term.class ),  null );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=PhAsKeyInTermMapException.class )
	public void putThrowsUpeOnPhKey() {
		
		TermMap tm;
		
		tm = new TermMap();
		tm.put( new Placeholder(), mock( Term.class ) );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=PhOnRightHandSideException.class )
	public void unifyRecordWithPhShouldThrowUpe() {
		
		TermMap tm;
		Placeholder ph;
		
		tm = new TermMap( mock( Term.class ), mock( Term.class ) );
		ph = new Placeholder();
		tm.unify( ph );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void unifyTmWithNullShouldThrowIae() {
		
		TermMap tm;
		
		tm = new TermMap( mock( Term.class ), mock( Term.class ) );
		tm.unify( null );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=PhAsKeyInTermMapException.class )
	public void getPlaceholderShouldThrowUpe() {
		
		TermMap tm;
		
		tm = new TermMap( mock( Term.class ), mock( Term.class ) );
		tm.get( new Placeholder() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unifySelfReturnsTrue() {
		
		TermMap tm;
		Term key;
		Term value;
		
		key = mock( Term.class );
		when( key.unify( key ) ).thenReturn( true );
		
		value = mock( Term.class );
		when( value.unify( value ) ).thenReturn( true );
		
		tm = new TermMap( key, value );
		assertTrue( tm.unify( tm ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unifyWithNonTermMapShouldReturnFalse() {
		
		TermMap tm;
		
		tm = new TermMap( mock( Term.class ), mock( Term.class ) );
		assertFalse( tm.unify( NIL ) );
		assertFalse( NIL.unify( tm ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unificationOfTermMapsWithDifferingSizesShouldReturnFalse() {
		
		TermMap tm1, tm2;
		
		tm1 = new TermMap( mock( Term.class ), mock( Term.class ) );
		tm2 = new TermMap();
		assertFalse( tm1.unify( tm2 ) );
		assertFalse( tm2.unify( tm1 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unificationOfTermMapsWithDifferingKeysShouldReturnFalse() {
		
		TermMap tm1, tm2;
		
		tm1 = new TermMap( mock( Term.class ), mock( Term.class ) );
		tm2 = new TermMap( mock( Term.class ), mock( Term.class ) );
		assertFalse( tm1.unify( tm2 ) );
		assertFalse( tm2.unify( tm1 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void hasKeyReturnsTrueOnExistingKey() {
		
		TermMap tm;
		Term key;
		
		key = mock( Term.class );
		
		tm = new TermMap( key, mock( Term.class ) );
		assertTrue( tm.isKey( key ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void hasKeyReturnsFalseOnNonExistingKey() {
		
		TermMap tm;
		tm = new TermMap( mock( Term.class ), mock( Term.class ) );
		assertFalse( tm.isKey( mock( Term.class ) ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unificationIsDeferredToValues() {
		
		Term value;
		Constant<String> key;
		TermMap tm1, tm2;
		
		key = new Constant<>( "blub" );
		
		value = mock( Term.class );
		when( value.unify( value ) ).thenReturn( true ); 
		
		tm1 = new TermMap( key, value );
		tm2 = new TermMap( key, value );
		
		assertTrue( tm1.unify( tm2 ) );
		verify( value ).unify( value );
		
	}
}
