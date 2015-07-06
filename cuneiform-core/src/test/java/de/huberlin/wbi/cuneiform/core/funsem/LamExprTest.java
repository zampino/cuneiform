package de.huberlin.wbi.cuneiform.core.funsem;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LamExprTest implements DefaultTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorShouldStoreSign() {
		
		LamExpr lamExpr;
		NatBody body;
		Sign sign;
		
		sign = mock( Sign.class );
		body = mock( NatBody.class );
		
		lamExpr = new LamExpr( LOC, sign, body );
		assertEquals( sign, lamExpr.getSign() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullSign() {		
		new LamExpr( LOC, null, mock( NatBody.class ) );
	}
}
