package de.huberlin.wbi.cuneiform.core.funsem;

import org.junit.Test;

import static org.junit.Assert.*;
import static de.huberlin.wbi.cuneiform.core.funsem.Util.*;

public class SignTest {

	@SuppressWarnings({ "static-method", "unused" })
	@Test(expected = IllegalArgumentException.class)
	public void constructorThrowsIaeOnNullOutputParamList() {
		new Sign( null, PARAMLIST, PARAMLIST );
	}

	@SuppressWarnings({ "static-method", "unused" })
	@Test(expected = IllegalArgumentException.class)
	public void contstructorThrowsIaeOnEmptyOutputParamList() {
		new Sign( PARAMLIST, PARAMLIST, PARAMLIST );
	}

	@SuppressWarnings({ "static-method", "unused" })
	@Test(expected = IllegalArgumentException.class)
	public void constructorThrowsIaeOnNullCorrelParamList() {
		new Sign( PARAMLIST.add( new Param( "out", false, false ) ), null,
				PARAMLIST );
	}

	@SuppressWarnings("static-method")
	@Test
	public void constructorStoresCorrelParamVec() {

		Sign sign;
		Alist<Param> correlParamVec;

		correlParamVec = PARAMLIST.add( new Param( "correl", false, false ) );

		sign = new Sign( PARAMLIST.add( new Param( "out", false, false ) ),
				correlParamVec, PARAMLIST );

		assertEquals( correlParamVec, sign.getCorrelParamVec() );
	}

	@SuppressWarnings({ "static-method", "unused" })
	@Test(expected = IllegalArgumentException.class)
	public void constructorThrowsIaeOnNullInputParamList() {
		new Sign( PARAMLIST.add( new Param( "out", false, false ) ),
				PARAMLIST, null );
	}

	@SuppressWarnings("static-method")
	@Test
	public void constructorStoresInParamVec() {

		Sign sign;
		Alist<Param> inParamVec;

		inParamVec = PARAMLIST.add( new Param( "p", false, false ) );

		sign = new Sign( PARAMLIST.add( new Param( "out", false, false ) ),
				PARAMLIST, inParamVec );

		assertEquals( inParamVec, sign.getInParamVec() );
	}

	@SuppressWarnings("static-method")
	@Test
	public void constructorStoresOutvarVec() {

		Sign sign;
		Alist<Param> outParamVec;

		outParamVec = PARAMLIST.add( new Param( "out", false, false ) );

		sign = new Sign( outParamVec, PARAMLIST, PARAMLIST );

		assertEquals( outParamVec, sign.getOutParamVec() );

	}
}
