package de.huberlin.wbi.cuneiform.core.funsem;


public interface Expr {

	public Alist<Expr> visit(Sem sem, Amap<String, Alist<Expr>> rho );
	public boolean isFinal();
	public boolean isLamExpr();
}
