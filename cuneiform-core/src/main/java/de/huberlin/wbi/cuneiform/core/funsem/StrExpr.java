package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.Map;
import java.util.stream.Stream;

public class StrExpr implements Expr {

	public StrExpr(String content) {
	}

	@Override
	public Expr[] visit( Sem sem, Map<String, Expr[]> rho ) {
		return sem.accept( this, rho );
	}

}
