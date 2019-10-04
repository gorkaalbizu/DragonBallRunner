package ficheros;

import java.io.*;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import objetos.Puntuacion;

public class Fichero {

	public File archivo;
	public FileReader lector;
	public BufferedReader bufer;
	public BufferedWriter buferescr;
	public FileWriter escritor;

	public void escribir() {
		try {

			archivo = new File("src/puntuaciones.txt");
			escritor = new FileWriter(archivo);
			buferescr = new BufferedWriter(escritor);

			for (String i : Puntuacion.puntuaciones.keySet()) {
				buferescr.write(Puntuacion.linea(i));
				buferescr.newLine();
			}
			buferescr.close();
			escritor.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void leer() {
		try {
			String cadena;
			archivo = new File("src/puntuaciones.txt");
			lector = new FileReader(archivo);
			bufer = new BufferedReader(lector);
			while ((cadena = bufer.readLine()) != null) {
				String[] partes = cadena.split(",");
				Integer[] array_puntos = new Integer[100];
				int contador = 0;
				for (int i = 1; i <= partes.length - 1; i++) {
					array_puntos[contador] = Integer.valueOf(partes[i]);
					contador++;
				}
				for (Integer i : array_puntos) {
					if (i == null)
						break;
					else
						Puntuacion.añadir(partes[0], i);
				}
			}
			bufer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}