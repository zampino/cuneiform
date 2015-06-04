package de.huberlin.wbi.cuneiform.core.eval;

import static org.mockito.Mockito.*;

import org.junit.Test;

public class RhoTest {

	@SuppressWarnings({ "unused", "static-method" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullTicketSrc() {
		new Rho( null );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void evalNullShouldThrowIae() {
		
		Rho rho;
		
		rho = new Rho( mock( TicketSrc.class ) );
		rho.eval( null );
	}
}
