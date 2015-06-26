package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.Map;
import java.util.stream.Stream;

public interface Expr {

	public Stream<Expr> visit(Map<String, Expr[]> rho, Sem sem);
}
