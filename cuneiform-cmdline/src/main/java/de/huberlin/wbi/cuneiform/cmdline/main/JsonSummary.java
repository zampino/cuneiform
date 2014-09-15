package de.huberlin.wbi.cuneiform.cmdline.main;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.huberlin.wbi.cuneiform.core.semanticmodel.CompoundExpr;
import de.huberlin.wbi.cuneiform.core.semanticmodel.NotDerivableException;

public class JsonSummary {

	private final UUID runId;
	private final Path buildDir;
	private final List<String> output;
	
	public JsonSummary( UUID runId, Path buildDir, CompoundExpr output )
	throws NotDerivableException {
		
		if( runId == null )
			throw new NullPointerException( "Run Id must not be null." );
		
		if( buildDir == null )
			throw new NullPointerException( "Build directory must not be null." );
		
		if( output == null )
			this.output = new ArrayList<>();
		else
			this.output = output.normalize();
						
		this.runId = runId;
		this.buildDir = buildDir;
	}
	
	@Override
	public String toString() {
		
		StringBuffer buf;
		String first, s, r;
		int i, n;
		boolean res, comma;
		
		buf = new StringBuffer();
		
		buf.append( "{\n" );
		
		buf.append( "  runId:\"" ).append( runId ).append( "\",\n" );
		
		buf.append( "  output:[" );
		res = false;
		if( !output.isEmpty() ) {
			
			n = output.size();
			first = output.get( 0 );
			res = first.matches( "\\d+_\\d+_.+" );
			comma = false;
			
			for( i = 0; i < n; i++ ) {
			
				if( comma )
					buf.append( ", " );
				comma = true;
				
				s = output.get( i );
				r = s.substring( 0, s.indexOf( '_' ) );
				if( res )
					s = buildDir.resolve( r ).resolve( s ).toString();
				
				buf.append( '"' ).append( s ).append( '"' );
			}
		}
		buf.append( "],\n" );
		
		buf.append( "  type:\"" );
		if( res )
			buf.append( "File" );
		else
			buf.append( "String" );
		buf.append( "\"\n" );
		
		
		buf.append( "}\n" );
		
		return buf.toString();
	}
}