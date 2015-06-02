package de.huberlin.wbi.cuneiform.core.typespec;

import static de.huberlin.wbi.cuneiform.core.libfun.Atom.*;

import de.huberlin.wbi.cuneiform.core.libfun.Cons;
import de.huberlin.wbi.cuneiform.core.libfun.List;
import de.huberlin.wbi.cuneiform.core.libfun.Record;

public class Sign extends Record {

	public static final String SYMBOL = "sign";
	
	public Sign( Cons outVarList, List correlList, List paramList ) {
		super( atomFrom( SYMBOL ), outVarList, correlList, paramList );
	}
}
