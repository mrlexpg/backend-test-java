package br.com.fcamara.asnpark.util;

public class AsnParkFcmUtils {
	
	public static String getValueMaskFormat(String pMask, String pValue, boolean pReturnValueEmpty){

		/* 
		 * Verifica se se foi configurado para nao retornar a  
		 * mascara se a string for nulo ou vazia se nao 
		 * retorna somente a mascara. 
		 */ 
		if (pReturnValueEmpty == true && (pValue == null || pValue.trim().equals("")))
			return "";

		/* 
		 * Substituir as mascaras passadas como  9, X, * por # para efetuar a formatcao
		 */
		
		if(pMask.contains("*")) {
		   pMask = pMask.replaceAll("*", "#");
		} else if(pMask.contains("9")) {
			pMask = pMask.replaceAll("9", "#");
		} else {
			pMask = pMask.toUpperCase().replaceAll("X", "#");
		}

		/* 
		 * Formata valor com a mascara passada  
		 */
		for(int i = 0; i < pValue.length(); i++){
			pMask = pMask.replaceFirst("#", pValue.substring(i, i + 1));
		}

		/* 
		 * Subistitui por string vazia os digitos restantes da mascara 
		 * quando o valor passado é menor que a mascara   
		 */
		return pMask.replaceAll("#", "");
	}
}
