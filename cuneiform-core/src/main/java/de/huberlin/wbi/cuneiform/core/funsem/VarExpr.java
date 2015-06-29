package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.Map;
import java.util.stream.Stream;

public class VarExpr implements Expr {

	private final String name;
	private final Location loc;

	public VarExpr(Location loc, String name) {
		this.loc = loc;
		this.name = name;
	}

	@Override
	public Expr[] visit( Sem sem, Map<String, Expr[]> rho ) {
		return sem.accept( this, rho );
	}

	public String getName() {
		return name;
	}

	public Location getLoc() {
		return loc;
	}

}
