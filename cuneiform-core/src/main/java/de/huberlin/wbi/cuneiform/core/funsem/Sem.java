package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.Map;
import java.util.stream.Stream;

public interface Sem {
	
	public Expr[] eval(Expr[] compoundExpr, Map<String, Expr[]> rho);
	public Stream<Expr> accept(StrExpr strExpr, Map<String, Expr[]> rho);
}
