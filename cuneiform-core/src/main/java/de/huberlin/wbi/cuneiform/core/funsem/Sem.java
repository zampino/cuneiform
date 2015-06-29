package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.Map;

public interface Sem {

	public Expr[] eval( Expr[] compoundExpr, Map<String, Expr[]> rho );

	public Expr[] accept( StrExpr strExpr, Map<String, Expr[]> rho );

	public Expr[] accept( VarExpr varExpr, Map<String, Expr[]> rho );

	public Expr[] accept( SelectExpr selectExpr, Map<String, Expr[]> rho );
}
