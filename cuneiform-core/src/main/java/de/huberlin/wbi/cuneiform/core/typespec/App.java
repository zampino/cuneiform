package de.huberlin.wbi.cuneiform.core.typespec;

import static de.huberlin.wbi.cuneiform.core.libfun.Atom.atomFrom;
import static de.huberlin.wbi.cuneiform.core.libfun.Constant.constantFrom;
import de.huberlin.wbi.cuneiform.core.libfun.List;
import de.huberlin.wbi.cuneiform.core.libfun.Record;
import de.huberlin.wbi.cuneiform.core.libfun.Map;

public class App extends Record implements Expr {
	
	public static final String SYMBOL = "app";
	
	public App( int channel, List lamList, Map bindingBlock ) {
		super( atomFrom( SYMBOL ), constantFrom( channel ), lamList, bindingBlock );
	}

}
