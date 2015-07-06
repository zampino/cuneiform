package de.huberlin.wbi.cuneiform.core.funsem;


public interface Sem {

	public Expr[] eval( Expr[] compoundExpr, ImmutableMap<String, Expr[]> rho );

	public Expr[] accept( StrExpr strExpr, ImmutableMap<String, Expr[]> rho );

	public Expr[] accept( VarExpr varExpr, ImmutableMap<String, Expr[]> rho );

	public Expr[] accept( SelectExpr selectExpr, ImmutableMap<String, Expr[]> rho );

	public Expr[] accept(AppExpr appExpr, ImmutableMap<String, Expr[]> rho);

	public Expr[] accept(LamExpr lamExpr, ImmutableMap<String, Expr[]> rho);
}
