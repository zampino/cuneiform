package de.huberlin.wbi.cuneiform.core.libfun;

import static org.junit.Assert.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( JUnitParamsRunner.class )
public class VarTest {

	@SuppressWarnings("static-method")
	public Object[] getValidName() {
		return new Object[][] {{"Bla"}, {"Blub"}};
	}
	
	@SuppressWarnings("static-method")
	public Object[] getIllegalName() {
		return new Object[][] {{null}, {""}, {"bla"}, {"0"}, {"@"}, {"["}};
	}
	
	@SuppressWarnings({ "unused", "static-method" })
	@Test
	@Parameters( method="getValidName" )
	public void constructorShouldAcceptValidName( String name ) {
		new Var( name );
	}
	

	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	@Parameters( method="getIllegalName" )
	public void constructorShouldThrowIaeOnIllegalName( String name ) {
		new Var( name );
	}
	
	@SuppressWarnings("static-method")
	@Test
	@Parameters( method="getValidName" )
	public void nameShouldBeRetrievable( String name ) {
		
		Var var;
		
		var = new Var( name );
		
		assertEquals( name, var.getName() );
	}
}
