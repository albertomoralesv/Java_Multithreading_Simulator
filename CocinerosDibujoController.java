public class CocinerosDibujoController {
    private static DibujoRestauranteCCC dibujo;
    private static int cocineros = -1;

    public static synchronized void setDibujo(DibujoRestauranteCCC d) {
        dibujo = d;
    }

    public synchronized static void addCocinero() {
        cocineros++;
        if (cocineros >= dibujo.getMax()) {
            dibujo.addExtraRow(dibujo.getMax());
            dibujo.setMax();
        }
        dibujo.setColor(20 * cocineros + 18, Colors.cocinerosEnCocinaColor);
    }

    public synchronized static void removeCocinero() {
        dibujo.setColor(20 * cocineros + 18, dibujo.getBackground());
        cocineros--;
    }

    public synchronized static void paint(DibujoRestauranteCCC d) {
        if (cocineros != -1) {
            for (int i = 0; i <= cocineros; i++) {
                if (i >= d.getMax()) {
                    d.addExtraRow(i);
                    d.setMax();
                }
                dibujo.setColor(20 * i + 18, Colors.cocinerosEnCocinaColor);
            }
        }
    }
}
