package de.huberlin.wbi.cuneiform.core.typespec;

import static de.huberlin.wbi.cuneiform.core.libfun.Constant.*;
import static de.huberlin.wbi.cuneiform.core.libfun.Atom.*;
import de.huberlin.wbi.cuneiform.core.libfun.Record;

public class Param extends Record {

	public static final String SYMBOL = "param";
	
	public Param( String paramName, boolean isFile, boolean isList ) {
		super( atomFrom( SYMBOL ), constantFrom( paramName ), constantFrom( isFile ), constantFrom( isList ) );
	}

}
