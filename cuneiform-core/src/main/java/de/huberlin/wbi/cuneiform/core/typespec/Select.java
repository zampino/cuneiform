package de.huberlin.wbi.cuneiform.core.typespec;

import static de.huberlin.wbi.cuneiform.core.libfun.Constant.*;
import static de.huberlin.wbi.cuneiform.core.libfun.Atom.*;

import de.huberlin.wbi.cuneiform.core.libfun.Record;

public class Select extends Record {
	
	public static final String SYMBOL = "select";
	
	public Select( int channel, Ticket ticket ) {
		super( atomFrom( SYMBOL ), constantFrom( channel ), ticket );
	}

}
