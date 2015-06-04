package de.huberlin.wbi.cuneiform.core.eval;

import de.huberlin.wbi.cuneiform.core.libfun.List;
import de.huberlin.wbi.cuneiform.core.libfun.Map;
import de.huberlin.wbi.cuneiform.core.typespec.Ticket;

public class Rho extends Map {
	
	private final TicketSrc ticketSrc;
	
	public Rho( TicketSrc ticketSrc ) {
		
		if( ticketSrc == null )
			throw new IllegalArgumentException( "Ticket source must not be null." );
		
		this.ticketSrc = ticketSrc;
	}
	
	public List eval( List compoundExpr ) {
		
		if( compoundExpr == null )
			throw new IllegalArgumentException( "Compound expression must not be null." );
		
		return null;
	}

	private Ticket createTicket() {
		return ticketSrc.createTicket();
	}
}
