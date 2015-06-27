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
	public Stream<Expr> visit(Map<String, Expr[]> rho, Sem sem) {
		
		if( !rho.containsKey( name ) )
			throw new CfRuntimeException( loc, "The variable "+name+" is unbound." );
		
		return null;
	}

}
