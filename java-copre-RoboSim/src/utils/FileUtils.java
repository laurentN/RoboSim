package utils;

import java.io.File;
import java.util.ArrayList;

public class FileUtils 
{
	/**
	 * Retourne une arrayList qui contient  les fichiers txt du dossier path
	 * Les Strings retournés ne contiennent pas l'extension ".txt"
	 * 
	 * @param path : chemin d'un dossier
	 * @return une arrayList qui contient les fichiers txt du dossier path
	 */
	public static ArrayList<String> findFiles(String path) {
		
		ArrayList<String> listeNom = new ArrayList<String>();
		File directory = new File(path);
		
		File[] subfiles = directory.listFiles();
		if(subfiles!=null){
			for(int i=0 ; i < subfiles.length; i++)
			{
				if(!subfiles[i].getName().equals(".svn"))
					listeNom.add(subfiles[i].getName());
	           
			}
		}
		
		return listeNom;
	}
}
