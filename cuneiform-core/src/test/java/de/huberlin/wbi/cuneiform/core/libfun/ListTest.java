package de.huberlin.wbi.cuneiform.core.libfun;

import static de.huberlin.wbi.cuneiform.core.libfun.Constant.*;
import static de.huberlin.wbi.cuneiform.core.libfun.List.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ListTest {

	@SuppressWarnings("static-method")
	@Test
	public void usortRemovesDuplicateElements() {
		
		List template, unique;
		
		template = list( constantFrom( "A" ), constantFrom( "B" ), constantFrom( "B" ), constantFrom( "C" ) );
		assertEquals( 4, template.size() );

		unique = template.usort();
		
		assertNotSame( template, unique );
		assertEquals( 4, template.size() );
		assertEquals( 3, unique.size() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void reverseListWorks() {
		
		List template, expected;
		
		template = list( constantFrom( "A" ), constantFrom( "B" ), constantFrom( "C" ) );
		expected = list( constantFrom( "C" ), constantFrom( "B" ), constantFrom( "A" ) );
		
		assertEquals( expected, template.reverse() );
		
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void mapWorks() {
		
		Fun fn;
		List l1, l2;
		
		fn = new Fun() {
			
			@Override
			public Term apply( Term... x ) {
				
				Constant<?> c;
				Object obj;
				Integer i;
				
				if( x.length != 1 )
					throw new IllegalArgumentException( "Argument vector must have size 1 but was "+x.length+"." );
				
				if( !( x[ 0 ] instanceof Constant ) )
					throw new IllegalArgumentException( "Argument is expected to be a constant but was "+x[ 0 ] );
				
				c = ( ( Constant<?> )x[ 0 ] );
				obj = c.getContent();
				
				if( !( obj instanceof Integer ) )
					throw new IllegalArgumentException( "Argument is expected to be an integer constant but was "+obj );
				
				i = ( Integer )obj;
				
				return constantFrom( i+1 );
			}
		};
		
		l1 = list( constantFrom( 1 ), constantFrom( 2 ), constantFrom( 3 ) );
		l2 = list( constantFrom( 2 ), constantFrom( 3 ), constantFrom( 4 ) );
		
		assertEquals( l2, l1.map( fn ) );
	}
}
