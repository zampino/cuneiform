package de.huberlin.wbi.cuneiform.core.funsem;


public class VarExpr implements Expr {

	private final String name;
	private final Location loc;

	public VarExpr(Location loc, String name) {
		this.loc = loc;
		this.name = name;
	}

	@Override
	public Alist<Expr> visit( Sem sem, Amap<String, Alist<Expr>> rho ) {
		return sem.accept( this, rho );
	}

	public String getName() {
		return name;
	}

	public Location getLoc() {
		return loc;
	}

	@Override
	public String toString() {
		return "{var,\""+name+"\"}";
	}
}
