package objetos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;

import ventanas.GameOver;

public class Puntuacion {
	public static int puntos;
	public static TreeMap<String, ArrayList<Integer>> puntuaciones = new TreeMap<String, ArrayList<Integer>>();
	public static int puntos_max;

	public Puntuacion() {
		init_puntos();
	}

	public void init_puntos() {
		puntos = 0;
	}

	public int puntMax() {
		if (puntuaciones.size() != 0) {
			if (puntos_max > puntos) {
				return puntos_max;
			} else {
				return puntos;
			}
		}
		return puntos;
	}

	public static void valorMayor() {
		puntos_max = 0;
		for (ArrayList<Integer> i : puntuaciones.values()) {
			for (int j : i) {
				if (j > puntos_max) {
					puntos_max = j;
				}
			}
		}
	}

	public static void añadir(String nick, int puntos) {
		if (coincidencia(nick)) {
			puntuaciones.get(nick).add(puntos);
		} else {
			ArrayList<Integer> listapuntos = new ArrayList<Integer>();
			listapuntos.add(puntos);
			puntuaciones.put(nick, listapuntos);
		}
	}

	public static boolean coincidencia(String nick) {
		for (String s : puntuaciones.keySet()) {
			if (nick.equals(s)) {
				return true;
			}
		}
		return false;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public static String linea(String nick) {
		String ret = nick;
		for (Integer i : puntuaciones.get(nick)) {
			ret = ret + "," + String.valueOf(i);
		}
		return ret;

	}

	public static void ordenarArray() {
		for (ArrayList<Integer> i : puntuaciones.values()) {
			Comparator<Integer> comparador = Collections.reverseOrder();
			Collections.sort(i, comparador);
		}
	}

	public static String[] ranking() {
		String[] podio = new String[3];
		ArrayList<Integer> mejores_punt = new ArrayList<Integer>();
		for (ArrayList<Integer> i : puntuaciones.values()) {
			mejores_punt.add(i.get(0));
		}
		Comparator<Integer> comparador = Collections.reverseOrder();
		Collections.sort(mejores_punt, comparador);
		int contador = 0;
		for (ArrayList<Integer> i : puntuaciones.values()) {
			if (i.get(0).equals(mejores_punt.get(0))) {
				podio[0] = (String) puntuaciones.keySet().toArray()[contador];
			}
			if (i.get(0).equals(mejores_punt.get(1))) {
				podio[1] = (String) puntuaciones.keySet().toArray()[contador];
			}
			if (i.get(0).equals(mejores_punt.get(2))) {
				podio[2] = (String) puntuaciones.keySet().toArray()[contador];
			}
			contador++;
		}
		return podio;
	}
}
