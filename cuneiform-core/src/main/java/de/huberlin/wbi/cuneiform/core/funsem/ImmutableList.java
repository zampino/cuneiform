package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class ImmutableList<T> {
	
	private class Cons extends ImmutableList<T> {
		
		private final T hd;
		private final ImmutableList<T> tl;
		
		Cons( T hd, ImmutableList<T> tl ) {
			
			this.hd = hd;
			this.tl = tl;
		}
		
		@Override
		public T hd() {
			return hd;
		}
		
		@Override
		public boolean isEmpty() {
			return false;
		}
		
		@Override
		public int size() {
			return 1+tl.size();
		}
		
		@Override
		public ImmutableList<T> tl() {
			return tl;
		}
		
		@Override
		public String toString() {
			
			StringBuffer buf;
			
			buf = new StringBuffer();
					
			buf.append( '[' ).append( hd );
			
			if( tl.isEmpty() )
				buf.append( ']' );
			else
				buf
					.append( ',' )
					.append( tl.toString().substring( 1 ) );
			
			return buf.toString();
		}
	}
	
	public ImmutableList() {}

	public ImmutableList<T> cons( T hd ) {
		return new Cons( hd, this );
	}

	@Override
	public boolean equals( Object other ) {
		return false;
	}

	public T hd() {
		throw new RuntimeException( "Cannot retrieve head from nil." );
	}

	@SuppressWarnings("static-method")
	public boolean isEmpty() {
		return true;
	}

	public ImmutableList<T> map( Function<T,T> fun ) {
		
		ImmutableList<T> trav, result;
		
		trav = this;
		result = new ImmutableList<>();
		
		while( !trav.isEmpty() ) {
			result = result.cons( fun.apply( trav.hd() ) );
			trav = trav.tl();
		}
		
		return result.reverse();
	}
	
	public ImmutableList<T> reverse() {
		
		ImmutableList<T> result;
		ImmutableList<T> trav;
		
		trav = this;
		result = new ImmutableList<>();
		
		while( !trav.isEmpty() ) {
			result = result.cons( trav.hd() );
			trav = trav.tl();
		}
		
		return result;
	}
	
	@SuppressWarnings("static-method")
	public int size() {
		return 0;
	}
		
	public ImmutableList<T> tl() {
		throw new RuntimeException( "Cannot retrieve tail from nil." );
	}
	
	@Override
	public String toString() {
		return "[]";
	}
	
	public ImmutableList<T> usort() {
		
		ImmutableList<T> result;
		ImmutableList<T> trav;
		Set<T> elementSet;
		
		elementSet = new HashSet<>();
		trav = this;
		
		while( !trav.isEmpty() ) {	
			elementSet.add( trav.hd() );
			trav = trav.tl();
		}
		
		result = new ImmutableList<>();
		
		for( T element : elementSet )
			result = result.cons( element );
		
		return result;
	}
	
}
