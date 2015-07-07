package de.huberlin.wbi.cuneiform.core.funsem;

import org.junit.Test;

import static de.huberlin.wbi.cuneiform.core.funsem.Enumerator.*;
import static org.junit.Assert.*;
import static de.huberlin.wbi.cuneiform.core.funsem.Util.*;

public class EnumeratorTest implements DefaultTest {

	@SuppressWarnings("static-method")
	@Test(expected = IllegalArgumentException.class)
	public void enumerateThrowsIaeOnTaskCorrelatedSignature() {

		Sign sign;

		sign = new Sign( PARAMLIST.add( new Param( "out", false, false ) ),
				PARAMLIST.add( new Param( "correl", false, false ) ), PARAMLIST );

		enumerate( sign, EMPTY_MAP );
	}

	@SuppressWarnings("static-method")
	@Test
	public void enumerateReturnsSingleEmptyBlockForEmptyInputParameterVec() {

		Sign sign;
		Alist<Amap<String, Alist<Expr>>> result;
		Amap<String, Alist<Expr>> map;

		sign = new Sign( PARAMLIST.add( new Param( "out", false, false ) ),
				PARAMLIST, PARAMLIST );

		result = enumerate( sign, EMPTY_MAP );
		assertEquals( 1, result.size() );

		map = result.hd();
		assertEquals( 0, map.size() );
	}

	@SuppressWarnings("static-method")
	@Test
	public void enumerateEnumeratesSingleInputParam() {

		Sign sign;
		Alist<Amap<String, Alist<Expr>>> result;
		Amap<String, Alist<Expr>> bindingMap, map;
		Alist<Expr> e, x;

		sign = new Sign( PARAMLIST.add( new Param( "out", false, false ) ),
				PARAMLIST, PARAMLIST.add( new Param( "p", false, false ) ) );

		e = NIL.add( new StrExpr( "d" ) ).add( new StrExpr( "c" ) )
				.add( new StrExpr( "b" ) ).add( new StrExpr( "a" ) );

		bindingMap = EMPTY_MAP.put( "p", e );

		result = enumerate( sign, bindingMap );
		assertEquals( 4, result.size() );

		map = result.hd();
		assertEquals( 1, map.size() );
		assertTrue( map.isKey( "p" ) );
		x = NIL.add( new StrExpr( "a" ) );
		assertEquals( x, map.get( "p" ) );

		result = result.tl();
		map = result.hd();
		assertEquals( 1, map.size() );
		assertTrue( map.isKey( "p" ) );
		x = NIL.add( new StrExpr( "b" ) );
		assertEquals( x, map.get( "p" ) );

		result = result.tl();
		map = result.hd();
		assertEquals( 1, map.size() );
		assertTrue( map.isKey( "p" ) );
		x = NIL.add( new StrExpr( "c" ) );
		assertEquals( x, map.get( "p" ) );

		result = result.tl();
		map = result.hd();
		assertEquals( 1, map.size() );
		assertTrue( map.isKey( "p" ) );
		x = NIL.add( new StrExpr( "d" ) );
		assertEquals( x, map.get( "p" ) );
	}
}
