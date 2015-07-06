package de.huberlin.wbi.cuneiform.core.funsem;

public class NatBody {

	private final Amap<String, Alist<Expr>> bodyMap;

	public NatBody( Lang bash, Amap<String, Alist<Expr>> bodyMap ) {
		this.bodyMap = bodyMap;
	}

}
