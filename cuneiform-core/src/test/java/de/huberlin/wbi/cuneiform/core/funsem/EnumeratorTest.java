package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.List;

import org.junit.Test;

import static de.huberlin.wbi.cuneiform.core.funsem.Enumerator.*;
import static org.junit.Assert.*;

public class EnumeratorTest implements DefaultTest {


	@SuppressWarnings("static-method")
	@Test(expected = IllegalArgumentException.class)
	public void enumerateThrowsIaeOnTaskCorrelatedSignature() {

		Sign sign;

		sign = new Sign( new Param[] { new Param( "out", false, false ) },
				new Param[] { new Param( "correl", false, false ) },
				new Param[] {} );

		enumerate( sign, EMPTY_MAP );
	}

	@SuppressWarnings("static-method")
	@Test
	public void enumerateReturnsSingleEmptyBlockForEmptyInputParameterVec() {

		Sign sign;
		List<ImmutableMap<String, Expr[]>> result;
		ImmutableMap<String, Expr[]> map;

		sign = new Sign( new Param[] { new Param( "out", false, false ) },
				new Param[] {}, new Param[] {} );

		result = enumerate( sign, EMPTY_MAP );
		assertEquals( 1, result.size() );

		map = result.get( 0 );
		assertEquals( 0, map.size() );
	}

	@SuppressWarnings("static-method")
	@Test
	public void enumerateEnumeratesSingleInputParam() {

		Sign sign;
		List<ImmutableMap<String, Expr[]>> result;
		ImmutableMap<String, Expr[]> bindingMap, map;

		sign = new Sign( new Param[] { new Param( "out", false, false ) },
				new Param[] {}, new Param[] { new Param( "p", false, false ) } );

		bindingMap = EMPTY_MAP.put( "p", new Expr[] { new StrExpr( "a" ),
				new StrExpr( "b" ), new StrExpr( "c" ), new StrExpr( "d" ) } );

		result = enumerate( sign, bindingMap );
		assertEquals( 4, result.size() );

		map = result.get( 0 );
		assertEquals( 1, map.size() );
		assertTrue( map.isKey( "p" ) );
		assertArrayEquals( new Expr[] { new StrExpr( "a" ) }, map.get( "p" ) );

		map = result.get( 1 );
		assertEquals( 1, map.size() );
		assertTrue( map.isKey( "p" ) );
		assertArrayEquals( new Expr[] { new StrExpr( "b" ) }, map.get( "p" ) );

		map = result.get( 2 );
		assertEquals( 1, map.size() );
		assertTrue( map.isKey( "p" ) );
		assertArrayEquals( new Expr[] { new StrExpr( "c" ) }, map.get( "p" ) );

		map = result.get( 3 );
		assertEquals( 1, map.size() );
		assertTrue( map.isKey( "p" ) );
		assertArrayEquals( new Expr[] { new StrExpr( "d" ) }, map.get( "p" ) );
	}
}
