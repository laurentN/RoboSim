package Utils;

public class StringUtils {

	/**
	 * Fonction qui nous indique si une chaine de caract�re contient que des chiffres
	 * @param s chaine de caract�re � tester
	 * @return retourne vrai si une chaine est exclusivement compos�e de chiffre, faux sinon
	 */
	public static boolean isANumber(String s){
		char[] c = s.toCharArray();
		for(int i=0;i<c.length;i++){
			if(!Character.isDigit(c[i])){
				return false;
			}
		}
		return true;
	}
}
