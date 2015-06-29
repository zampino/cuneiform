package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.Map;

public class SelectExpr implements Expr {

	public SelectExpr(Location loc, int i, Ticket ticket) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Expr[] visit( Sem sem, Map<String, Expr[]> rho ) {
		return sem.accept(  this, rho );
	}

}
