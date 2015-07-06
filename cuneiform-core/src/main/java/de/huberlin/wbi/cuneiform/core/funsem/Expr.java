package de.huberlin.wbi.cuneiform.core.funsem;


public interface Expr {

	public Expr[] visit(Sem sem, ImmutableMap<String, Expr[]> rho );
}
