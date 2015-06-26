package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.Map;
import java.util.function.Supplier;

public class Csem extends DefaultSem {

	public Csem(Map<String, Expr[]> global, Supplier<Ticket> createTicket,
			Map<RefChannel, Expr[]> fin) {
		super(global, createTicket, fin);
	}

}
