package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.Map;

public interface Expr {

	public Expr[] visit(Map<String, Expr[]> rho, Sem sem);
}
