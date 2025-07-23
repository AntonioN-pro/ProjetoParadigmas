
import java.io.*;
import java.util.ArrayList;

public class MidiaController {
    private static final String ARQUIVO = "src/data/dados.dat";

    public static void salvarLista(ArrayList<Midia> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Midia> carregarLista() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            return (ArrayList<Midia>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
