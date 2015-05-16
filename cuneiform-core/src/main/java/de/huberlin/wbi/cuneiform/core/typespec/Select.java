package de.huberlin.wbi.cuneiform.core.typespec;

import static de.huberlin.wbi.cuneiform.core.libfun.Constant.constantFrom;
import de.huberlin.wbi.cuneiform.core.libfun.Record;

public class Select extends Record {
	
	private static final String SYMBOL_SELECT = "select";
	
	public Select( int channel, Ticket ticket ) {
		super( SYMBOL_SELECT, constantFrom( channel ), ticket );
	}

}
