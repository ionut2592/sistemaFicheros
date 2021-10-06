import java.io.File;
import java.text.SimpleDateFormat;

public class Exercicio1 {
  public static int MAX_FILES_BY_COLUMN = 5;

  public static void main(String[] args) throws Exception {
    String directorio = args[0];
    String tipo = args[1];
    File f = new File(directorio);
    String[] listadoD = f.list();
    File[] archivos = f.listFiles();
    
    switch (tipo) {
      case "l":

        Listado(listadoD);
        break;

      case "c":

        ListaColumnas(listadoD);
        break;

      case "t":
        ListadoInfo(archivos);
        break;
    }

  }

  public static void Listado(String[] filesname) {
    if (filesname == null || filesname.length == 0) {
      System.out.println("No hay elementos dentro de la carpeta actual");
      return;
    } else {
      for (int i = 0; i < filesname.length; i++) {
        System.out.println(filesname[i]);
      }
    }

  }

  public static void ListaColumnas(String[] filenames) {
    int columnas = (filenames.length / MAX_FILES_BY_COLUMN) + 1;
    String[][] salida = new String[MAX_FILES_BY_COLUMN][columnas];
    for (int i = 0; i < filenames.length; i++) {
      salida[i % MAX_FILES_BY_COLUMN][i / MAX_FILES_BY_COLUMN] = filenames[i];
    }
    // bucle para mostrar salidas
    for (int i = 0; i < MAX_FILES_BY_COLUMN; i++) {
      for (int j = 0; j < columnas; j++)
        System.out.print(salida[i][j] + " - ");
      System.out.println(" /");
    }
  }

  public static void ListadoInfo(File[] carpeta) {
    if (carpeta == null || carpeta.length == 0) {
      System.out.println("No hay elementos dentro del directorio actual");
      return;
    } else {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
      for (int i = 0; i < carpeta.length; i++) {
        File archivo = carpeta[i];
        System.out.println(String.format("%s %s %s %s - %s - %s - %s",

            archivo.isDirectory() ? "D" : "F", 
            archivo.isFile() ? archivo.canRead() ? "R" : "" : "",
            archivo.isFile() ? archivo.canWrite() ? "W" : "" : "",
            archivo.isFile() ? archivo.isHidden() ? "H" : "" : "", 
            archivo.getName(),
            archivo.isFile() ? archivo.length() : "",
             sdf.format(archivo.lastModified())));
      }

    }

  }
}
