package de.huberlin.wbi.cuneiform.core.funsem;

import static java.util.UUID.randomUUID;
import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

public class ChannelRefTest {

	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorFailsOnZeroChannel() {
		new ChannelRef( 0, randomUUID() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorFailsOnNegativeChannel() {
		new ChannelRef( -1, randomUUID() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorFailsOnNullRef() {
		new ChannelRef( 1, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsShouldReturnTrueOnEqualChannelAndRef() {
		
		int channel;
		UUID ref;
		ChannelRef cr1, cr2;
		
		channel = 13;
		ref = randomUUID();

		cr1 = new ChannelRef( channel, ref );
		cr2 = new ChannelRef( channel, ref );
		
		assertEquals( cr1, cr2 );
		assertEquals( cr2, cr1 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsShouldReturnFalseOnDifferingChannel() {
		
		UUID ref;
		ChannelRef cr1, cr2;
		
		ref = randomUUID();

		cr1 = new ChannelRef( 12, ref );
		cr2 = new ChannelRef( 3, ref );
		
		assertNotEquals( cr1, cr2 );
		assertNotEquals( cr2, cr1 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsShouldReturnFalseOnDifferingRef() {
		
		int channel;
		ChannelRef cr1, cr2;
		
		channel = 13;

		cr1 = new ChannelRef( channel, randomUUID() );
		cr2 = new ChannelRef( channel, randomUUID() );
		
		assertNotEquals( cr1, cr2 );
		assertNotEquals( cr2, cr1 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsShouldReturnFalseOnNonChannelRef() {
		
		ChannelRef cr1;
		
		cr1 = new ChannelRef( 43, randomUUID() );
		
		assertNotEquals( cr1, "blub" );
		assertNotEquals( "blub", cr1 );
	}
}
