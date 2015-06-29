package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.Map;
import java.util.UUID;

public class SelectExpr implements Expr {
	
	private final int channel;
	private final Ticket ticket;

	public SelectExpr(Location loc, int channel, Ticket ticket) {
		this.channel = channel;
		this.ticket = ticket;
	}

	@Override
	public Expr[] visit( Sem sem, Map<String, Expr[]> rho ) {
		return sem.accept(  this, rho );
	}

	public int getChannel() {
		return channel;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public UUID getRef() {
		return ticket.getRef();
	}

}
