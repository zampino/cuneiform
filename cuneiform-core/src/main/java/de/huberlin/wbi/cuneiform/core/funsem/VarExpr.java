package de.huberlin.wbi.cuneiform.core.funsem;

public class VarExpr implements Expr {

	private final String name;
	private final Location loc;

	public VarExpr( Location loc, String name ) {
		this.loc = loc;
		this.name = name;
	}

	public Location getLoc() {
		return loc;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean isFinal() {
		return false;
	}

	@Override
	public String toString() {
		return "{var,\"" + name + "\"}";
	}

	@Override
	public Alist<Expr> visit( Sem sem, Amap<String, Alist<Expr>> rho ) {
		return sem.accept( this, rho );
	}

	@Override
	public boolean isLamExpr() {
		return false;
	}
}
