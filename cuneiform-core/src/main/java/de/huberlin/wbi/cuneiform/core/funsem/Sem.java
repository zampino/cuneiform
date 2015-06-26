package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface Sem {

	public Expr[] eval(Expr[] compoundExpr, Map<String, Expr[]> rho,
			Map<String, Expr[]> global, Supplier<Ticket> createTicket,
			Map<RefChannel, Expr[]> fin);

	public Stream<Expr> accept(StrExpr strExpr, Map<String, Expr[]> rho,
			Map<String, Expr[]> global, Supplier<Ticket> createTicket,
			Map<RefChannel, Expr[]> fin);
}
