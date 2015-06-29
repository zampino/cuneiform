package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.UUID;

public class Ticket {

	private final UUID ref;
	
	public Ticket( UUID ref ) {
		this.ref = ref;
	}

	public UUID getRef() {
		return ref;
	}
}
