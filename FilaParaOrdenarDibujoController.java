import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

public class FilaParaOrdenarDibujoController {
    private static DibujoRestauranteCCC dibujo;
    private static int clientes = -1;
    private static ArrayList<Integer> posiciones = new ArrayList<>(
            Arrays.asList(12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22,
                    21, 20, 52, 51, 50, 49, 48, 47, 46, 45, 44, 43, 42, 41, 40, 72, 71, 70, 69, 68, 67, 66, 65,
                    64, 63, 62, 61, 60, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83, 82, 81, 80));

    public static synchronized void setDibujo(DibujoRestauranteCCC d) {
        dibujo = d;
    }

    public synchronized static void addCliente() {
        clientes++;
        if (clientes >= (dibujo.getMax() - 1) * 20) {
            dibujo.addExtraRow(dibujo.getMax());
            dibujo.setMax();
        }
        dibujo.setColor(posiciones.get(clientes), Color.BLUE);
    }

    public synchronized static void removeCliente() {
        dibujo.setColor(posiciones.get(clientes), dibujo.getBackground());
        clientes--;
    }

    public synchronized static void paint(DibujoRestauranteCCC d) {
        if (clientes != -1) {
            for (int i = 0; i <= clientes; i++) {
                if (i >= d.getMax()) {
                    d.addExtraRow(d.getMax());
                    d.setMax();
                }
                dibujo.setColor(posiciones.get(i), Color.BLUE);
            }
        }
    }
}
