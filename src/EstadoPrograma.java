import java.io.*;

public class EstadoPrograma implements Serializable {
    public static Vintage carregaEstado (String nomeFichiero) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(nomeFichiero);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Vintage v = (Vintage) ois.readObject();
        ois.close();
        return v;
    }

    public static void guardaEstado(String nomeFicheiro, Vintage v) throws  FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(nomeFicheiro);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(v);
        oos.flush();
        oos.close();
    }
}
