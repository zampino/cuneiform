package de.huberlin.wbi.cuneiform.core.funsem;

import static org.junit.Assert.*;

import org.junit.Test;

public class ImmutableMapTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorReturnsEmptyMap() {
		
		ImmutableMap<Integer,String> m;
		
		m = new ImmutableMap<>();
		assertTrue( m.isEmpty() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void constructorReturnsMapWithSingleEntry() {
		
		ImmutableMap<Integer,String> m;
		int key;
		String value;
		
		key = 1;
		value = "bla";
		
		m = new ImmutableMap<>( key, value );
		assertEquals( 1, m.size() );
		assertEquals( value, m.get( key ) );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructThrowsIaeOnNullKey() {
		new ImmutableMap<Integer,String>( null, "bla" );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructThrowsIaeOnNullValue() {
		new ImmutableMap<Integer,String>( 2, null );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void getNullShouldThrowIae() {
		
		ImmutableMap<Integer,String> m;
		
		m = new ImmutableMap<>();
		m.get( null );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=RuntimeException.class )
	public void getUnboundKeyShouldThrowUve() {
		
		ImmutableMap<Integer,String> m;
		
		m = new ImmutableMap<>();
		m.get( 2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void getUnboundKeyWithDefaultShouldReturnDefault() {
		
		ImmutableMap<Integer,String> m;
		String def;
		
		m = new ImmutableMap<>();
		def = "blub";
		
		assertEquals( def, m.get( 5, def ) );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void getNullWithDefaultShouldThrowIae() {
		
		ImmutableMap<Integer,String> m;
		String def;
		
		m = new ImmutableMap<>();
		def = "blub";
		
		m.get( null, def );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void getWithNullDefaultShouldThrowIae() {
		
		ImmutableMap<Integer,String> m;
		
		m = new ImmutableMap<>();
		m.get( 1, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void mergeCombinesTwoMaps() {
		
		ImmutableMap<Integer,String> m1, m2, m3;
		int key1, key2;
		String value1, value2;
		
		key1 = 1;
		key2 = 2;
		value1 = "bla";
		value2 = "blub";
		
		m1 = new ImmutableMap<>( key1, value1 );
		m2 = new ImmutableMap<>( key2, value2 );
		
		m3 = m1.merge( m2 );
		assertEquals( 2, m3.size() );
		assertEquals( value1, m3.get( key1 ) );
		assertEquals( value2, m3.get( key2 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void mergeSecondShouldSupersedeFirst() {
		
		ImmutableMap<Integer,String> m1, m2, m3;
		int key;
		String value1, value2;
		
		key = 1;
		value1 = "bla";
		value2 = "blub";
		
		m1 = new ImmutableMap<>( key, value1 );
		m2 = new ImmutableMap<>( key, value2 );
		
		m3 = m1.merge( m2 );
		assertEquals( 1, m3.size() );
		assertEquals( value2, m3.get( key ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void mergeLeavesBothOriginalMapsUnchanged() {
		
		ImmutableMap<Integer,String> tm1, tm2, tm3;
		
		tm1 = new ImmutableMap<>( 1, "bla" );
		assertEquals( 1, tm1.size() );
		
		tm2 = new ImmutableMap<>( 2, "blub" );
		assertEquals( 1, tm2.size() );
		
		tm3 = tm1.merge( tm2 );
		assertEquals( 1, tm1.size() );
		assertEquals( 1, tm2.size() );
		assertEquals( 2, tm3.size() );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void mergeWithNullShouldThrowIae() {
		
		ImmutableMap<Integer,String> tm;
		
		tm = new ImmutableMap<>();
		tm.merge( null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void putLeavesOriginalMapUnchanged() {
		
		ImmutableMap<Integer,String> tm1, tm2;
		
		tm1 = new ImmutableMap<>( 1, "bla" );
		assertEquals( 1, tm1.size() );
		
		tm2 = tm1.put( 2, "blub" );
		assertEquals( 1, tm1.size() );
		assertEquals( 2, tm2.size() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void putReturnsNewMapWithNewEntry() {
		
		ImmutableMap<Integer,String> m1, m2;
		int key;
		String value;
		
		key = 1;
		value = "bla";
		
		
		m1 = new ImmutableMap<>();
		assertTrue( m1.isEmpty() );
		
		m2 = m1.put( key, value );
		assertTrue( m1.isEmpty() );
		assertEquals( 1, m2.size() );
		assertEquals( value, m2.get( key ) );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void putThrowsIaeOnNullKey() {
		
		ImmutableMap<Integer,String> tm;
		
		tm = new ImmutableMap<>();
		tm.put( null, "blub" );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void putThrowsIaeOnNullValue() {
		
		ImmutableMap<Integer,String> tm;
		
		tm = new ImmutableMap<>();
		tm.put( 2, null );
	}
	
	
	
	
	
	@SuppressWarnings("static-method")
	@Test
	public void hasKeyReturnsTrueOnExistingKey() {
		
		ImmutableMap<Integer,String> tm;
		int key;
		
		key = 2;
		
		tm = new ImmutableMap<>( key, "blub" );
		assertTrue( tm.isKey( key ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void hasKeyReturnsFalseOnNonExistingKey() {
		
		ImmutableMap<Integer,String> tm;
		tm = new ImmutableMap<>( 4, "bla" );
		assertFalse( tm.isKey( 3 ) );
	}
	
}
