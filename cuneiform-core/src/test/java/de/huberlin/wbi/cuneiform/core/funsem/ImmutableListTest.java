package de.huberlin.wbi.cuneiform.core.funsem;

import static org.junit.Assert.*;

import org.junit.Test;

public class ImmutableListTest {

	@SuppressWarnings("static-method")
	@Test
	public void usortRemovesDuplicateElements() {

		ImmutableList<String> template, unique;

		template = new ImmutableList<>();
		template = template.cons( "A" ).cons( "B" ).cons( "B" ).cons( "C" );
		assertEquals( 4, template.size() );

		unique = template.usort();

		assertNotSame( template, unique );
		assertEquals( 4, template.size() );
		assertEquals( 3, unique.size() );
	}

	@SuppressWarnings("static-method")
	@Test
	public void reverseListWorks() {

		ImmutableList<String> template, expected;

		template = new ImmutableList<>();
		template = template.cons( "C" ).cons( "B" ).cons( "A" );

		expected = new ImmutableList<>();
		expected = expected.cons( "A" ).cons( "B" ).cons( "C" );

		assertEquals( expected, template.reverse() );

	}

	@SuppressWarnings("static-method")
	@Test
	public void mapWorks() {

		ImmutableList<Integer> l1, l2;

		l1 = new ImmutableList<>();
		l1 = l1.cons( 1 ).cons( 2 ).cons( 3 );

		l2 = new ImmutableList<>();
		l2 = l2.cons( 2 ).cons( 3 ).cons( 4 );

		assertEquals( l2, l1.map( ( i ) -> i + 1 ) );
	}

	@SuppressWarnings("static-method")
	@Test
	public void emptyListEqualsNullReturnsFalse() {

		ImmutableList<Integer> l;

		l = new ImmutableList<>();
		assertFalse( l.equals( null ) );
	}

	@SuppressWarnings("static-method")
	@Test
	public void nonEmptyListEqualsNullReturnsFalse() {

		ImmutableList<Integer> l;

		l = new ImmutableList<>();
		l.cons( 1 );

		assertFalse( l.equals( null ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void emptyListShouldBeEqualToEmptyList() {
		
		ImmutableList<Integer> a, b;
		
		a = new ImmutableList<>();
		b = new ImmutableList<>();
		
		assertEquals( a, b );
	}
}
