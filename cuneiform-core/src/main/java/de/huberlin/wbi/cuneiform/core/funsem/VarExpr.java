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
	public Expr[] visit( Map<String, Expr[]> rho, Sem sem ) {
		return sem.accept( this, rho );
	}

	public String getName() {
		return name;
	}

	public Location getLoc() {
		return loc;
	}

}
