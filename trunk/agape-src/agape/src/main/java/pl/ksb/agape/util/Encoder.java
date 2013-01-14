package pl.ksb.agape.util;

/**
 * klasa szyfrujca id przekazaywane przez GET
 * @author kunos
 *
 */
public class Encoder {
	
	private static final Long NUMBER=121313l; 
	
	public static Long encode(Long id){
		return NUMBER*id;
	}
	
	public static Long decode(Long id){
		return id/NUMBER;
	}

}
