package de.huberlin.wbi.cuneiform.core.eval;

import org.junit.Test;

public class RhoTest {

	@SuppressWarnings({ "unused", "static-method" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullTicketSrc() {
		new Rho( null );
	}
}
