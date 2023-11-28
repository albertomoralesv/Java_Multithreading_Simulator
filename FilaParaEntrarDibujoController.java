public class FilaParaEntrarDibujoController {
    private static DibujoFilaParaEntrar dibujo;
    private static int clientes = -1;

    public static synchronized void setDibujo(DibujoFilaParaEntrar d) {
        dibujo = d;
    }

    public synchronized static void addCliente() {
        clientes++;
        if (clientes + 10 >= dibujo.getMax()) {
            dibujo.addExtraColumn();
            dibujo.setMax();
        }
        dibujo.setColor(clientes + 10, Colors.enFilaParaEntrarColor);
    }

    public synchronized static void removeClient() {
        dibujo.setColor(clientes + 10, dibujo.getBackground());
        clientes--;
    }

    public synchronized static void paint(DibujoFilaParaEntrar d) {
        if (clientes != -1) {
            for (int i = 0; i <= clientes; i++) {
                if (i + 10 >= d.getMax()) {
                    d.addExtraColumn();
                    d.setMax();
                }
                d.setColor(i + 10, Colors.enFilaParaEntrarColor);
            }
        }
    }
}
