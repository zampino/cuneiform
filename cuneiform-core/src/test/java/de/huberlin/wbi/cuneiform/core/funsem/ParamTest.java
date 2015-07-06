package de.huberlin.wbi.cuneiform.core.funsem;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParamTest {

	@SuppressWarnings({ "unused", "static-method" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullName() {
		new Param( null, false, false );
	}

	@SuppressWarnings({ "unused", "static-method" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnEmptyName() {
		new Param( "", false, false );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void constructorShouldStoreName() {
		
		String name;
		Param param;
		
		name = "blub";
		param = new Param( name, false, false );
		
		assertEquals( name, param.getName() );
	}

}
