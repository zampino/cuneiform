package de.huberlin.wbi.cuneiform.core.typespec;

import static de.huberlin.wbi.cuneiform.core.libfun.Atom.*;

import de.huberlin.wbi.cuneiform.core.libfun.Record;

public class Ticket extends Record {

	public static final String SYMBOL = "ticket";
	
	public Ticket() {
		super( atomFrom( SYMBOL ) );
	}
}
