package de.huberlin.wbi.cuneiform.core.eval;

import de.huberlin.wbi.cuneiform.core.libfun.List;
import de.huberlin.wbi.cuneiform.core.libfun.Map;
import de.huberlin.wbi.cuneiform.core.typespec.Ticket;

public abstract class Rho extends Map {
	
	private final TicketSrc ticketSrc;
	
	public Rho( TicketSrc ticketSrc ) {
		this.ticketSrc = ticketSrc;
	}
	
	public List eval( List compoundExpr ) {
		return null;
	}

	private Ticket createTicket() {
		return ticketSrc.createTicket();
	}
}
