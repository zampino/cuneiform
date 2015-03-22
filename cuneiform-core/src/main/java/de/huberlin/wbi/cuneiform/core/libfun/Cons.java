package de.huberlin.wbi.cuneiform.core.libfun;

public class Cons implements Term {
	
	private final Term head;
	private final Cons tail;

	public Cons( Term head, Cons tail ) {
		this.head = head;
		this.tail = tail;
	}

	public Term getHead() {
		return head;
	}
	
	public Cons getTail() {
		return tail;
	}
	
	@Override
	public String toString() {
		
		StringBuffer buf;
				
		buf = new StringBuffer();
				
		buf.append( '[' ).append( LibFun.printTerm( head ) );
		
		if( tail == null )
			buf.append( ']' );
		else
			buf
				.append( ',' )
				.append( LibFun.printTerm( tail ).substring( 1 ) );
		
		return buf.toString();
	}

	@Override
	public boolean unify(Term other) {
		// TODO Auto-generated method stub
		return false;
	}
}
