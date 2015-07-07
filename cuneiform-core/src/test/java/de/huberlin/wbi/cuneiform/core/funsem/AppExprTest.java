package de.huberlin.wbi.cuneiform.core.funsem;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static de.huberlin.wbi.cuneiform.core.funsem.Util.*;

public class AppExprTest implements DefaultTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorShouldStoreLam() {

		AppExpr app;
		Alist<Expr> lam;

		lam = new Alist<>();
		lam = lam.add( mock( LamExpr.class ) );

		app = new AppExpr( LOC, 1, lam, EMPTY_MAP );
		assertEquals( lam, app.getLam() );
	}

	@SuppressWarnings({ "static-method", "unused" })
	@Test(expected = IllegalArgumentException.class)
	public void constructorShouldThrowIaeOnNullLam() {

		new AppExpr( LOC, 1, null, EMPTY_MAP );
	}

	@SuppressWarnings("static-method")
	@Test
	public void constructorShouldStoreBindingMap() {

		AppExpr app;
		Alist<Expr> lam;
		Amap<String, Alist<Expr>> bindingMap;
		Alist<Expr> e;

		lam = new Alist<>();
		lam = lam.add( mock( LamExpr.class ) );

		e = new Alist<>();
		e = e.add( new StrExpr( "blub" ) );

		bindingMap = EMPTY_MAP.put( "bla", e );

		app = new AppExpr( LOC, 1, lam, bindingMap );
		assertEquals( bindingMap, app.getBindingMap() );
	}

	@SuppressWarnings({ "static-method", "unused" })
	@Test(expected = IllegalArgumentException.class)
	public void constructorShouldThrowIaeOnNullBindingMap() {

		Alist<Expr> lam;

		lam = new Alist<>();
		lam = lam.add( mock( LamExpr.class ) );

		new AppExpr( LOC, 1, lam, null );
	}

	@SuppressWarnings("static-method")
	@Test
	public void constructorStoresChannel() {
		
		AppExpr appExpr;
		int channel;
		
		channel = 5;
		
		appExpr = new AppExpr( LOC, channel, NIL, EMPTY_MAP );
		
		assertEquals( 5, appExpr.getChannel() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorThrowsIaeOnInvalidChannel() {
		
		new AppExpr( LOC, 0, NIL, EMPTY_MAP );		
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void constructorStoresLocation() {
		
		AppExpr appExpr;
		Location loc;
		
		loc = mock( Location.class );
		
		appExpr = new AppExpr( loc, 5, NIL, EMPTY_MAP );
		
		assertEquals( loc, appExpr.getLocation() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorThrowsIaeOnNullLocation() {
		new AppExpr( null, 5, NIL, EMPTY_MAP );
	}
}
