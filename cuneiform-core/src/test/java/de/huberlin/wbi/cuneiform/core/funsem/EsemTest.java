package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static java.util.UUID.randomUUID;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EsemTest {
	
	private static final Supplier<Ticket> CREATE_TICKET = () -> new Ticket( randomUUID() );

	private Csem esem;
	private Map<String, Expr[]> rho, global;
	private Map<RefChannel,Expr[]> fin;

	@Before
	public void setup() {
		
		esem = new DefaultCsem();
		rho = new HashMap<>();
		global = new HashMap<>();
		fin = new HashMap<>();
	}

	@Test
	public void nilShouldEvalItself() {
		
		Expr[] nil, x;
		
		nil = new Expr[] {};
		
		x = esem.eval( nil, rho, global, CREATE_TICKET, fin );
		
		assertArrayEquals( nil, x );
	}
	
	@Test
	public void strShouldEvalItself() {
		
		Expr[] a, x;
		
		a = new Expr[] { new StrExpr( "A" ) };
		
		x = esem.eval( a, rho, global, CREATE_TICKET, fin );
		
		assertArrayEquals( a, x );
	}
}
