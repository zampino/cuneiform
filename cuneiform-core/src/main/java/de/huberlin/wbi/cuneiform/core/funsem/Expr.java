package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.Map;

public interface Expr {

	public Expr[] visit(Sem sem, Map<String, Expr[]> rho );
}
