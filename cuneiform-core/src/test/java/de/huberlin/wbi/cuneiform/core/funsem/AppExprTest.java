package de.huberlin.wbi.cuneiform.core.funsem;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AppExprTest implements DefaultTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorShouldStoreLam() {
		
		AppExpr app;
		Expr[] lam;
		
		lam = new Expr[] { mock( LamExpr.class ) };
		
		app = new AppExpr( LOC, 1, lam, EMPTY_MAP );
		assertArrayEquals( lam, app.getLam() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullLam() {
		
		new AppExpr( LOC, 1, null, EMPTY_MAP );
	}

	@SuppressWarnings("static-method")
	@Test
	public void constructorShouldStoreBindingMap() {
		
		AppExpr app;
		Expr[] lam;
		ImmutableMap<String,Expr[]> bindingMap;
		
		lam = new Expr[] { mock( LamExpr.class ) };
		bindingMap = EMPTY_MAP.put( "bla", new Expr[] { new StrExpr( "blub" ) } );
		
		app = new AppExpr( LOC, 1, lam, bindingMap );
		assertEquals( bindingMap, app.getBindingMap() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullBindingMap() {
		
		Expr[] lam;
		
		lam = new Expr[] { mock( LamExpr.class ) };
		
		new AppExpr( LOC, 1, lam, null );
	}
	
}
