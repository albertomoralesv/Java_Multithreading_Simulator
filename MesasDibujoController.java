import java.awt.Color;

public class MesasDibujoController {
    private static DibujoMesas dibujo;
    private static int clientes = -1;

    public static synchronized void setDibujo(DibujoMesas d) {
        dibujo = d;
    }

    public synchronized static void addCliente() {
        clientes++;
        if (clientes >= dibujo.getMax()) {
            dibujo.addExtraRow(clientes / 10);
            dibujo.setMax();
        }
        dibujo.setColor(clientes, Color.BLUE);
    }

    public synchronized static void removeCliente() {
        dibujo.setColor(clientes, dibujo.getBackground());
        clientes--;
    }

    public synchronized static void paint(DibujoMesas d) {
        if (clientes != -1) {
            for (int i = 0; i <= clientes; i++) {
                if (i >= d.getMax()) {
                    d.addExtraRow(i / 10);
                    d.setMax();
                }
                dibujo.setColor(i, Color.BLUE);
            }
        }
    }
}
