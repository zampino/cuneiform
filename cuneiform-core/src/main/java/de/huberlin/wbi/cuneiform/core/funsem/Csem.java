package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Csem extends DefaultSem {

	public Csem(Map<String, Expr[]> global, Supplier<Ticket> createTicket,
			Map<RefChannel, Expr[]> fin) {
		super(global, createTicket, fin);
	}

	@Override
	public Expr[] eval(Expr[] compoundExpr, Map<String, Expr[]> rho) {

		Stream<Expr> stream, result;

		stream = Stream.of(compoundExpr);

		result = stream.flatMap((Expr e) -> evalSingle(e, rho));

		return toExprVec(result);

	}

	private Stream<Expr> evalSingle(Expr expr, Map<String, Expr[]> rho) {
		return expr.visit(rho, this);
	}


}
