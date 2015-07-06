package de.huberlin.wbi.cuneiform.core.funsem;

import org.junit.Test;
import static org.junit.Assert.*;

public class SignTest {

	@SuppressWarnings({ "static-method", "unused" })
	@Test(expected = IllegalArgumentException.class)
	public void constructorThrowsIaeOnNullOutputParamList() {
		new Sign( null, new Param[] {}, new Param[] {} );
	}

	@SuppressWarnings({ "static-method", "unused" })
	@Test(expected = IllegalArgumentException.class)
	public void contstructorThrowsIaeOnEmptyOutputParamList() {
		new Sign( new Param[] {}, new Param[] {}, new Param[] {} );
	}

	@SuppressWarnings({ "static-method", "unused" })
	@Test(expected = IllegalArgumentException.class)
	public void constructorThrowsIaeOnNullCorrelParamList() {
		new Sign( new Param[] { new Param( "out", false, false ) }, null,
				new Param[] {} );
	}

	@SuppressWarnings("static-method")
	@Test
	public void constructorStoresCorrelParamVec() {

		Sign sign;
		Param[] correlParamVec;

		correlParamVec = new Param[] { new Param( "correl", false, false ) };

		sign = new Sign( new Param[] { new Param( "out", false, false ) },
				correlParamVec, new Param[] {} );

		assertArrayEquals( correlParamVec, sign.getCorrelParamVec() );
	}

	@SuppressWarnings({ "static-method", "unused" })
	@Test(expected = IllegalArgumentException.class)
	public void constructorThrowsIaeOnNullInputParamList() {
		new Sign( new Param[] { new Param( "out", false, false ) },
				new Param[] {}, null );
	}

	@SuppressWarnings("static-method")
	@Test
	public void constructorStoresInParamVec() {

		Sign sign;
		Param[] inParamVec;

		inParamVec = new Param[] { new Param( "p", false, false ) };

		sign = new Sign( new Param[] { new Param( "out", false, false ) },
				new Param[] {}, inParamVec );

		assertArrayEquals( inParamVec, sign.getInParamVec() );
	}
}
