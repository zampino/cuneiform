package de.huberlin.wbi.cuneiform.core.libfun;

import static de.huberlin.wbi.cuneiform.core.libfun.Nil.NIL;

public class Cons extends List {
	
	private final Term head;
	private final List tail;

	public Cons( Term head, List tail ) {
		
		if( head == null )
			throw new IllegalArgumentException( "Cons head must not be null." );
		
		if( tail == null )
			throw new IllegalArgumentException( "Cons tail must not be null." );
		
		this.head = head;
		this.tail = tail;
	}

	@Override
	public Term getHead() {
		return head;
	}
	
	@Override
	public List getTail() {
		return tail;
	}
	
	@Override
	public boolean unify( Term other ) {

		Cons cons;
		
		if( other == null )
			throw new IllegalArgumentException( "Ohter term must not be null." );
		
		if( other instanceof Placeholder )
			throw new PhOnRightHandSideException();
		
		if( !( other instanceof Cons ) )
			return false;
		
		cons = ( Cons )other;
		
		if( !( head.unify( cons.head ) ) )
			return false;
		
		return tail.unify( cons.tail );
	}

	@Override
	public String print() {
		
		StringBuffer buf;
		
		buf = new StringBuffer();
				
		buf.append( '[' ).append( head.print() );
		
		if( tail.equals( NIL ) )
			buf.append( ']' );
		else
			buf
				.append( ',' )
				.append( tail.print().substring( 1 ) );
		
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

	@Override
	public void unspecialize() {
		head.unspecialize();
		tail.unspecialize();	
	}
	

}
