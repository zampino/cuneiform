package de.huberlin.wbi.cuneiform.core.funsem;


public interface Sem {

	public Alist<Expr> eval( Alist<Expr> compoundExpr, Amap<String, Alist<Expr>> rho );

	public Alist<Expr> accept( StrExpr strExpr, Amap<String, Alist<Expr>> rho );

	public Alist<Expr> accept( VarExpr varExpr, Amap<String, Alist<Expr>> rho );

	public Alist<Expr> accept( SelectExpr selectExpr, Amap<String, Alist<Expr>> rho );

	public Alist<Expr> accept(AppExpr appExpr, Amap<String, Alist<Expr>> rho);

	public Alist<Expr> accept(LamExpr lamExpr, Amap<String, Alist<Expr>> rho);
}
