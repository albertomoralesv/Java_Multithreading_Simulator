public class CajerosDibujoController {
    private static DibujoRestauranteCCC dibujo;
    private static int cajeros = -1;

    public static synchronized void setDibujo(DibujoRestauranteCCC d) {
        dibujo = d;
    }

    public synchronized static void addCajero() {
        cajeros++;
        if (cajeros >= dibujo.getMax()) {
            dibujo.addExtraRow(dibujo.getMax());
            dibujo.setMax();
        }
        dibujo.setColor(20 * cajeros + 15, Colors.cajerosEnCajaColor);
    }

    public synchronized static void removeCajero() {
        dibujo.setColor(20 * cajeros + 15, dibujo.getBackground());
        cajeros--;
    }

    public synchronized static void paint(DibujoRestauranteCCC d) {
        if (cajeros != -1) {
            for (int i = 0; i <= cajeros; i++) {
                if (i >= d.getMax()) {
                    d.addExtraRow(i);
                    d.setMax();
                }
                dibujo.setColor(20 * i + 15, Colors.cajerosEnCajaColor);
            }
        }
    }
}
