import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        Vintage v = null;
        try {
            v = EstadoPrograma.carregaEstado(args[0]);
        } catch (FileNotFoundException ex) {
            System.out.println ("Ficheiro nao existe: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Não conseguiu aceder o ficheiro: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Não encontrou a classe :" + ex.getMessage());
        }

        if (v == null)
            v = new Vintage (LocalDate.now());

        UtilizadorController user = new UtilizadorController(v);
        AdminController admin = new AdminController(v);
        VintageController mainController = new VintageController(v,user,admin);
        MainView client = new MainView(mainController);
        client.run();

        try {
            EstadoPrograma.guardaEstado(args[0], v);
        } catch (FileNotFoundException ex) {
            System.out.println ("Ficheiro nao existe: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Não conseguiu aceder o ficheiro: " + ex.getMessage());
        }
    }
}

