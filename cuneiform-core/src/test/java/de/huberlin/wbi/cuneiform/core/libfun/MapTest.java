package de.huberlin.wbi.cuneiform.core.libfun;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static de.huberlin.wbi.cuneiform.core.libfun.Nil.NIL;

import org.junit.Test;

public class MapTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorReturnsEmptyMap() {
		
		Map m;
		
		m = new Map();
		assertTrue( m.isEmpty() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void constructorReturnsMapWithSingleEntry() {
		
		Map m;
		Term key;
		Term value;
		
		key = mock( Term.class );
		value = mock( Term.class );
		
		m = new Map( key, value );
		assertEquals( 1, m.size() );
		assertEquals( value, m.get( key ) );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructThrowsIaeOnNullKey() {
		new Map( null, mock( Term.class ) );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructThrowsIaeOnNullValue() {
		new Map( mock( Term.class ), null );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=PhAsKeyInTermMapException.class )
	public void constructThrowsUpeOnPhKey() {
		new Map( new Placeholder(), mock( Term.class ) );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void getNullShouldThrowIae() throws UnboundKeyException {
		
		Map m;
		
		m = new Map();
		m.get( null );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=UnboundKeyException.class )
	public void getUnboundKeyShouldThrowUve() {
		
		Map m;
		
		m = new Map();
		m.get( mock( Term.class ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void getUnboundKeyWithDefaultShouldReturnDefault() {
		
		Map m;
		Term def;
		
		m = new Map();
		def = mock( Term.class );
		
		assertEquals( def, m.get( mock( Term.class ), def ) );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void getNullWithDefaultShouldThrowIae() {
		
		Map m;
		Term def;
		
		m = new Map();
		def = mock( Term.class );
		
		m.get( null, def );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=PhAsKeyInTermMapException.class )
	public void getPlaceholderWithDefaultShouldThrowUpe() {
		
		Map m;
		Term def;
		
		m = new Map();
		def = mock( Term.class );
		
		m.get( new Placeholder(), def );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void getWithNullDefaultShouldThrowIae() {
		
		Map m;
		
		m = new Map();
		m.get( mock( Term.class ), null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void mergeCombinesTwoMaps() {
		
		Map m1, m2, m3;
		Term key1, key2, value1, value2;
		
		key1 = mock( Term.class );
		key2 = mock( Term.class );
		value1 = mock( Term.class );
		value2 = mock( Term.class );
		
		m1 = new Map( key1, value1 );
		m2 = new Map( key2, value2 );
		
		m3 = m1.merge( m2 );
		assertEquals( 2, m3.size() );
		assertEquals( value1, m3.get( key1 ) );
		assertEquals( value2, m3.get( key2 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void mergeSecondShouldSupersedeFirst() {
		
		Map m1, m2, m3;
		Term key, value1, value2;
		
		key = mock( Term.class );
		value1 = mock( Term.class );
		value2 = mock( Term.class );
		
		m1 = new Map( key, value1 );
		m2 = new Map( key, value2 );
		
		m3 = m1.merge( m2 );
		assertEquals( 1, m3.size() );
		assertEquals( value2, m3.get( key ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void mergeLeavesBothOriginalMapsUnchanged() {
		
		Map tm1, tm2, tm3;
		
		tm1 = new Map( mock( Term.class ), mock( Term.class ) );
		assertEquals( 1, tm1.size() );
		
		tm2 = new Map( mock( Term.class ), mock( Term.class ) );
		assertEquals( 1, tm2.size() );
		
		tm3 = tm1.merge( tm2 );
		assertEquals( 1, tm1.size() );
		assertEquals( 1, tm2.size() );
		assertEquals( 2, tm3.size() );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void mergeWithNullShouldThrowIae() {
		
		Map tm;
		
		tm = new Map();
		tm.merge( null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void putLeavesOriginalMapUnchanged() {
		
		Map tm1, tm2;
		
		tm1 = new Map( mock( Term.class ), mock( Term.class ) );
		assertEquals( 1, tm1.size() );
		
		tm2 = tm1.put( mock( Term.class ),  mock( Term.class ) );
		assertEquals( 1, tm1.size() );
		assertEquals( 2, tm2.size() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void putReturnsNewMapWithNewEntry() {
		
		Map m1, m2;
		Term key;
		Term value;
		
		key = mock( Term.class );
		value = mock( Term.class );
		
		
		m1 = new Map();
		assertTrue( m1.isEmpty() );
		
		m2 = m1.put( key, value );
		assertTrue( m1.isEmpty() );
		assertEquals( 1, m2.size() );
		assertEquals( value, m2.get( key ) );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void putThrowsIaeOnNullKey() {
		
		Map tm;
		
		tm = new Map();
		tm.put( null, mock( Term.class ) );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void putThrowsIaeOnNullValue() {
		
		Map tm;
		
		tm = new Map();
		tm.put( mock( Term.class ),  null );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=PhAsKeyInTermMapException.class )
	public void putThrowsUpeOnPhKey() {
		
		Map tm;
		
		tm = new Map();
		tm.put( new Placeholder(), mock( Term.class ) );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=PhOnRightHandSideException.class )
	public void unifyRecordWithPhShouldThrowUpe() {
		
		Map tm;
		Placeholder ph;
		
		tm = new Map( mock( Term.class ), mock( Term.class ) );
		ph = new Placeholder();
		tm.unify( ph );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void unifyTmWithNullShouldThrowIae() {
		
		Map tm;
		
		tm = new Map( mock( Term.class ), mock( Term.class ) );
		tm.unify( null );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=PhAsKeyInTermMapException.class )
	public void getPlaceholderShouldThrowUpe() {
		
		Map tm;
		
		tm = new Map( mock( Term.class ), mock( Term.class ) );
		tm.get( new Placeholder() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unifySelfReturnsTrue() {
		
		Map tm;
		Term key;
		Term value;
		
		key = mock( Term.class );
		when( key.unify( key ) ).thenReturn( true );
		
		value = mock( Term.class );
		when( value.unify( value ) ).thenReturn( true );
		
		tm = new Map( key, value );
		assertTrue( tm.unify( tm ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unifyWithNonTermMapShouldReturnFalse() {
		
		Map tm;
		
		tm = new Map( mock( Term.class ), mock( Term.class ) );
		assertFalse( tm.unify( NIL ) );
		assertFalse( NIL.unify( tm ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unificationOfTermMapsWithDifferingSizesShouldReturnFalse() {
		
		Map tm1, tm2;
		
		tm1 = new Map( mock( Term.class ), mock( Term.class ) );
		tm2 = new Map();
		assertFalse( tm1.unify( tm2 ) );
		assertFalse( tm2.unify( tm1 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unificationOfTermMapsWithDifferingKeysShouldReturnFalse() {
		
		Map tm1, tm2;
		
		tm1 = new Map( mock( Term.class ), mock( Term.class ) );
		tm2 = new Map( mock( Term.class ), mock( Term.class ) );
		assertFalse( tm1.unify( tm2 ) );
		assertFalse( tm2.unify( tm1 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void hasKeyReturnsTrueOnExistingKey() {
		
		Map tm;
		Term key;
		
		key = mock( Term.class );
		
		tm = new Map( key, mock( Term.class ) );
		assertTrue( tm.isKey( key ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void hasKeyReturnsFalseOnNonExistingKey() {
		
		Map tm;
		tm = new Map( mock( Term.class ), mock( Term.class ) );
		assertFalse( tm.isKey( mock( Term.class ) ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unificationIsDeferredToValues() {
		
		Term value;
		Constant<String> key;
		Map tm1, tm2;
		
		key = new Constant<>( "blub" );
		
		value = mock( Term.class );
		when( value.unify( value ) ).thenReturn( true ); 
		
		tm1 = new Map( key, value );
		tm2 = new Map( key, value );
		
		assertTrue( tm1.unify( tm2 ) );
		verify( value ).unify( value );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void unspecializeIsDeferredToValues() {
		
		Term value;
		Constant<String> key;
		Map tm;
		
		key = new Constant<>( "blub" );
		value = mock( Term.class );
		
		tm = new Map( key, value );
		tm.unspecialize();
		
		verify( value ).unspecialize();
	}
}
