package utils;

public class StringUtils {

	/**
	 * @param s : string à valider
	 * @return vrai si le String en paramètre ne contient pas de caractères spéciaux, faux sinon
	 */
	public static boolean stringValide(String s)
	{
		return !(s.contains("/") || s.contains("\\") || s.contains(":") || s.contains("*") || s.contains("?") || s.contains("\"") || s.contains("<") || s.contains(">") || s.contains("|")); 
	}
	
	/**
	 * Fonction qui nous indique si une chaine de caractère contient que des chiffres
	 * @param s chaine de caractère à tester
	 * @return retourne vrai si une chaine est exclusivement composée de chiffre, faux sinon
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
