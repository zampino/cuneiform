package de.huberlin.wbi.cuneiform.core.funsem;


public class StrExpr implements Expr {

	public StrExpr(String content) {
	}

	@Override
	public Expr[] visit( Sem sem, ImmutableMap<String, Expr[]> rho ) {
		return sem.accept( this, rho );
	}

}
