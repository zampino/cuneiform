package de.huberlin.wbi.cuneiform.core.typespec;

import static de.huberlin.wbi.cuneiform.core.libfun.Constant.*;
import static de.huberlin.wbi.cuneiform.core.libfun.Atom.*;
import de.huberlin.wbi.cuneiform.core.libfun.Record;

public class For extends Record implements Body {

	public static final String SYMBOL = "for";
	
	public For( String langLabel, String bodyStr ) {
		super( atomFrom( SYMBOL ), atomFrom( langLabel ), constantFrom( bodyStr ) );
	}

}
