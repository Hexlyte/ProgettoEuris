package org.generation.italy.euris.dao;

import org.springframework.stereotype.Repository;

@Repository
public class DaoOperations implements IDaoOperations {
	
	/**
	 * Metodo accessorio che consente la conversione da dato numerico in valuta "pence" a dato stringa
	 * nel formato del sistema monetario del Regno Unito nel 1970 (Xp Ys Zd).
	 * @param penceTot Dato contenente un intero convertito nella valuta "pence".
	 * @return Una stringa contenente un costo convertito nel sistema monetario del Regno Unito nel 1970 (Xp Ys Zd).
	 */
	private String penceToString(int penceTot) {
		String res = "";

		int pence = penceTot % 12;
		int shillingTot = penceTot / 12;
		int shilling = shillingTot % 20;
		int pound = shillingTot / 20;

		res = pound + "p " + shilling + "s " + pence + "d";

		return res;
	}

	/**
	 * Metodo accessorio che consente la conversione da dato stringa nel formato del 
	 * sistema monetario del Regno Unito nel 1970 (Xp Ys Zd) a dato numerico in valuta "pence".
	 * @param cost Dato contenente un costo col sistema monetario del Regno Unito nel 1970 (Xp Ys Zd) in formato String.
	 * @return Un intero convertito nella valuta "pence".
	 */
	private int stringToPence(String cost) {
		int res = 0;
		String money[] = cost.split(" ");
		String temp = "";

		for (int i = 0; i < money.length; i++) {
			for (int j = 0; j < money[i].length(); j++) {
				char ch = money[i].charAt(j);

				if (Character.isDigit(ch)) {
					temp += ch;

				} else {
					switch (ch) {

					case 'p':
						res += Integer.parseInt(temp) * (12 * 20);
						break;

					case 's':
						res += Integer.parseInt(temp) * 12;
						break;

					case 'd':
						res += Integer.parseInt(temp);
						break;
					}
				}
			}

			temp = "";
		}

		return res;
	}

	@Override
	public String sum(String cost1, String cost2) {
		int pence1 = stringToPence(cost1);
		int pence2 = stringToPence(cost2);

		int sumF = pence1 + pence2;

		return penceToString(sumF);
	}

	@Override
	public String sub(String cost1, String cost2) {
		int pence1 = stringToPence(cost1);
		int pence2 = stringToPence(cost2);

		int subF = pence1 - pence2;

		return penceToString(subF);
	}

	@Override
	public String mult(String cost, int multi) {
		int pence = stringToPence(cost);

		int multF = pence * multi;

		return penceToString(multF);
	}

	@Override
	public String div(String cost, int div) {
		int pence = stringToPence(cost);
		int divF = pence / div;
		int restF = pence % div;

		return penceToString(divF) + " (" + penceToString(restF) + ")";
	}

}
