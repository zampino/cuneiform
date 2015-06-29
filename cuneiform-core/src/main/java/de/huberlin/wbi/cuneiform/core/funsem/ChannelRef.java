package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.UUID;

public class ChannelRef {

	private final int channel;
	private final UUID ref;
	
	public ChannelRef( int channel, UUID ref ) {

		if( channel <= 0 )
			throw new IllegalArgumentException( "Channel must be positive." );
		
		if( ref == null )
			throw new IllegalArgumentException( "Reference UUID must not be null." );
		
		this.channel = channel;
		this.ref = ref;
	}
	
	@Override
	public boolean equals( Object obj ) {
		
		ChannelRef other;
		
		if( !( obj instanceof ChannelRef ) )
			return false;
		
		other = ( ChannelRef )obj;
		
		if( other.channel != channel )
			return false;
		
		if( !other.ref.equals( ref ) )
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "{"+channel+", "+ref+"}";
	}

	@Override
	public int hashCode() {
		return ref.hashCode();
	}
}
