package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.Map;
import java.util.stream.Stream;

public class StrExpr implements Expr {

	public StrExpr(String content) {
	}

	@Override
	public Stream<Expr> visit(Map<String, Expr[]> rho, Sem sem) {
		return sem.accept(this, rho);
	}

}
