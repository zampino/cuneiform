package de.huberlin.wbi.cuneiform.core.funsem;

import org.junit.Test;

import static de.huberlin.wbi.cuneiform.core.funsem.Util.*;
import static org.junit.Assert.*;

public class NatBodyTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorStoresBodyMap() {
		
		Amap<String,Alist<Expr>> bodyMap;
		NatBody natBody;
		
		bodyMap = EMPTY_MAP.put( "bla", NIL.add( new StrExpr( "blub" ) ) );
		natBody = new NatBody( bodyMap );
		
		assertEquals( bodyMap, natBody.getBodyMap() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullBodyMap() {
		new NatBody( null );
	}
}
