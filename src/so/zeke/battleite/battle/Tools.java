package so.zeke.battleite.battle;

public abstract class Tools {

	public static String combine(String[] s, String glue) {
	  int k = s.length;
	  if ( k == 0 ) {
	    return null;
	  }
	  StringBuilder out = new StringBuilder();
	  out.append( s[0] );
	  for ( int x=1; x < k; ++x ) {
	    out.append(glue).append(s[x]);
	  }
	  return out.toString();
	}

}
