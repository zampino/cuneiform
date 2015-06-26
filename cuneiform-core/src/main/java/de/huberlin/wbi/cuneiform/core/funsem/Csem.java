package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Csem implements Sem {

	@Override
	public Expr[] eval(Expr[] compoundExpr,
			Map<String, Expr[]> rho, Map<String, Expr[]> global,
			Supplier<Ticket> createTicket, Map<RefChannel, Expr[]> fin) {

		Stream<Expr> stream, result;
		
		stream = Stream.of( compoundExpr );
		
		result = stream.flatMap((Expr e) -> evalSingle(e, rho, global,
				createTicket, fin));
		
		return toExprVec( result );
		
	}

	private static Stream<Expr> evalSingle(Expr expr,
			Map<String, Expr[]> rho, Map<String, Expr[]> global,
			Supplier<Ticket> createTicket, Map<RefChannel, Expr[]> fin) {
		return expr.step( rho, global, createTicket, fin );
	}
	
	private static Expr[] toExprVec( Stream<Expr> stream ) {
		
		Object[] resultObj;
		Expr[] resultExpr;
		int n;

		resultObj = stream.toArray();
		
		n = resultObj.length;
		resultExpr = new Expr[ n ];
		
		System.arraycopy( resultObj, 0, resultExpr, 0, n );
		
		return resultExpr;
	}
	
}
