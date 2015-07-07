package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.UUID;

public class SelectExpr implements Expr {

	private final int channel;
	private final Ticket ticket;

	public SelectExpr( Location loc, int channel, Ticket ticket ) {
		this.channel = channel;
		this.ticket = ticket;
	}

	public int getChannel() {
		return channel;
	}

	public UUID getRef() {
		return ticket.getRef();
	}

	public Ticket getTicket() {
		return ticket;
	}

	@Override
	public boolean isFinal() {
		return false;
	}

	@Override
	public Alist<Expr> visit( Sem sem, Amap<String, Alist<Expr>> rho ) {
		return sem.accept( this, rho );
	}

	@Override
	public boolean isLamExpr() {
		return false;
	}

}
