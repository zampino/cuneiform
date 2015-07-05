package de.huberlin.wbi.cuneiform.core.funsem;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class ImmutableMapTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorReturnsEmptyMap() {
		
		ImmutableMap m;
		
		m = new ImmutableMap();
		assertTrue( m.isEmpty() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void constructorReturnsMapWithSingleEntry() {
		
		ImmutableMap m;
		String key;
		Expr[] value;
		
		key = "bla";
		value = new Expr[] { mock( Expr.class ) };
		
		m = new ImmutableMap( key, value );
		assertEquals( 1, m.size() );
		assertArrayEquals( value, m.get( key ) );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructThrowsIaeOnNullKey() {
		new ImmutableMap( null, new Expr[] { mock( Expr.class ) } );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructThrowsIaeOnNullValue() {
		new ImmutableMap( "bla", null );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void getNullShouldThrowIae() {
		
		ImmutableMap m;
		
		m = new ImmutableMap();
		m.get( null );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=RuntimeException.class )
	public void getUnboundKeyShouldThrowUve() {
		
		ImmutableMap m;
		
		m = new ImmutableMap();
		m.get( mock( String.class ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void getUnboundKeyWithDefaultShouldReturnDefault() {
		
		ImmutableMap m;
		Expr[] def;
		
		m = new ImmutableMap();
		def = new Expr[] { mock( Expr.class ) };
		
		assertArrayEquals( def, m.get( "bla", def ) );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void getNullWithDefaultShouldThrowIae() {
		
		ImmutableMap m;
		Expr[] def;
		
		m = new ImmutableMap();
		def = new Expr[] { mock( Expr.class ) };
		
		m.get( null, def );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void getWithNullDefaultShouldThrowIae() {
		
		ImmutableMap m;
		
		m = new ImmutableMap();
		m.get( "bla", null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void mergeCombinesTwoMaps() {
		
		ImmutableMap m1, m2, m3;
		String key1, key2;
		Expr[] value1, value2;
		
		key1 = "bla";
		key2 = "blub";
		value1 = new Expr[] { mock( Expr.class ) };
		value2 = new Expr[] { mock( Expr.class ) };
		
		m1 = new ImmutableMap( key1, value1 );
		m2 = new ImmutableMap( key2, value2 );
		
		m3 = m1.merge( m2 );
		assertEquals( 2, m3.size() );
		assertArrayEquals( value1, m3.get( key1 ) );
		assertArrayEquals( value2, m3.get( key2 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void mergeSecondShouldSupersedeFirst() {
		
		ImmutableMap m1, m2, m3;
		String key;
		Expr[] value1, value2;
		
		key = "bla";
		value1 = new Expr[] { mock( Expr.class ) };
		value2 = new Expr[] { mock( Expr.class ) };
		
		m1 = new ImmutableMap( key, value1 );
		m2 = new ImmutableMap( key, value2 );
		
		m3 = m1.merge( m2 );
		assertEquals( 1, m3.size() );
		assertArrayEquals( value2, m3.get( key ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void mergeLeavesBothOriginalMapsUnchanged() {
		
		ImmutableMap tm1, tm2, tm3;
		
		tm1 = new ImmutableMap( "bla", new Expr[] { mock( Expr.class ) } );
		assertEquals( 1, tm1.size() );
		
		tm2 = new ImmutableMap( "blub", new Expr[] { mock( Expr.class ) } );
		assertEquals( 1, tm2.size() );
		
		tm3 = tm1.merge( tm2 );
		assertEquals( 1, tm1.size() );
		assertEquals( 1, tm2.size() );
		assertEquals( 2, tm3.size() );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void mergeWithNullShouldThrowIae() {
		
		ImmutableMap tm;
		
		tm = new ImmutableMap();
		tm.merge( null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void putLeavesOriginalMapUnchanged() {
		
		ImmutableMap tm1, tm2;
		
		tm1 = new ImmutableMap( "bla", new Expr[] { mock( Expr.class ) } );
		assertEquals( 1, tm1.size() );
		
		tm2 = tm1.put( "blub", new Expr[] { mock( Expr.class ) } );
		assertEquals( 1, tm1.size() );
		assertEquals( 2, tm2.size() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void putReturnsNewMapWithNewEntry() {
		
		ImmutableMap m1, m2;
		String key;
		Expr[] value;
		
		key = "bla";
		value = new Expr[] { mock( Expr.class ) };
		
		
		m1 = new ImmutableMap();
		assertTrue( m1.isEmpty() );
		
		m2 = m1.put( key, value );
		assertTrue( m1.isEmpty() );
		assertEquals( 1, m2.size() );
		assertArrayEquals( value, m2.get( key ) );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void putThrowsIaeOnNullKey() {
		
		ImmutableMap tm;
		
		tm = new ImmutableMap();
		tm.put( null, new Expr[] { mock( Expr.class ) } );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void putThrowsIaeOnNullValue() {
		
		ImmutableMap tm;
		
		tm = new ImmutableMap();
		tm.put( "bla", null );
	}
	
	
	
	
	
	@SuppressWarnings("static-method")
	@Test
	public void hasKeyReturnsTrueOnExistingKey() {
		
		ImmutableMap tm;
		String key;
		
		key = "bla";
		
		tm = new ImmutableMap( key, new Expr[] { mock( Expr.class ) } );
		assertTrue( tm.isKey( key ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void hasKeyReturnsFalseOnNonExistingKey() {
		
		ImmutableMap tm;
		tm = new ImmutableMap( "bla", new Expr[] { mock( Expr.class ) } );
		assertFalse( tm.isKey( "blub" ) );
	}
	
}
