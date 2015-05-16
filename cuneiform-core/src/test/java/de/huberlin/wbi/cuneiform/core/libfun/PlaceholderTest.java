package de.huberlin.wbi.cuneiform.core.libfun;

import static de.huberlin.wbi.cuneiform.core.libfun.List.list;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class PlaceholderTest {

	@SuppressWarnings("static-method")
	@Test
	public void unifyMultipleTimesWithDifferentValueShouldReturnFalse() {
		
		Term t1, t2;
		Placeholder x;
		List abstractList, concreteList;
		
		t1 = mock( Term.class );
		t2 = mock( Term.class );
		x = new Placeholder( "X" );
		
		abstractList = list( x, x );
		concreteList = list( t1, t2 );
	
		assertFalse( x.isSpecialized() );
		assertFalse( abstractList.unify( concreteList ) );
	}

	@SuppressWarnings("static-method")
	@Test
	public void unifyMultipleTimesWithEqualValueShouldReturnTrue() {
		
		Term t;
		Placeholder x;
		List abstractList, concreteList;
		
		t = mock( Term.class );
		x = new Placeholder( "X" );
		
		abstractList = list( x, x );
		concreteList = list( t, t );
	
		assertFalse( x.isSpecialized() );
		assertTrue( abstractList.unify( concreteList ) );
		assertTrue( x.isSpecialized() );
		assertEquals( t, x.getSpecializedValue() );
	}

	@SuppressWarnings("static-method")
	@Test
	public void unspecializingVarAllowsNewUnification() {
		
		Term t1, t2;
		Placeholder x;
		
		t1 = mock( Term.class );
		t2 = mock( Term.class );
		
		x = new Placeholder( "X" );
		
		assertFalse( x.isSpecialized() );
		assertTrue( x.unify( t1 ) );
		assertTrue( x.isSpecialized() );
		
		x.unspecialize();
		
		assertFalse( x.isSpecialized() );
		assertTrue( x.unify( t2 ) );
		assertTrue( x.isSpecialized() );
		
		assertFalse( x.unify( t1 ) );
	}
			

	

}
