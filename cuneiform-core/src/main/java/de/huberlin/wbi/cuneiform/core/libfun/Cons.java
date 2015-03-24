package de.huberlin.wbi.cuneiform.core.libfun;

import de.huberlin.wbi.cuneiform.core.libfun.LibFun;

public class Cons extends Term {
	
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
	protected boolean unify( Term other ) {

		Cons cons;
		
		if( !( other instanceof Cons ) )
			return false;
		
		cons = ( Cons )other;
		
		if( !( LibFun.unify( head, cons.head ) ) )
			return false;
		
		return LibFun.unify( tail, cons.tail );
	}

	@Override
	protected String print() {
		
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
	public boolean equals( Object obj ) {
		
		Cons other;
		
		if( !( obj instanceof Cons ) )
			return false;
		
		other = ( Cons )obj;
		
		if( head == null ) {
			if( other.head != null )
				return false;
		}
		else
			if( !head.equals( other.head ) )
				return false;
		
		if( tail == null ) {
			if( other.tail != null )
				return false;
		}
		else
			if( !tail.equals( other.tail ) )
				return false;
		
		return true;
	}

	@Override
	public int hashCode() {
		return 89785674;
	}
}
