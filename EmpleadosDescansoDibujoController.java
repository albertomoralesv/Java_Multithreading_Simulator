import java.awt.Color;

public class EmpleadosDescansoDibujoController {
    private static DibujoZonaDescanso dibujo;
    private static int empleados = -1;

    public static synchronized void setDibujo(DibujoZonaDescanso d) {
        dibujo = d;
    }

    public synchronized static void addEmpleado() {
        empleados++;
        if (empleados >= dibujo.getMax()) {
            dibujo.addExtraColumn(empleados / 10);
            dibujo.setMax();
        }
        dibujo.setColor(empleados, Color.ORANGE);
    }

    public synchronized static void removeEmpleado() {
        dibujo.setColor(empleados, dibujo.getBackground());
        empleados--;
    }

    public synchronized static void paint(DibujoZonaDescanso d) {
        if (empleados != -1) {
            for (int i = 0; i <= empleados; i++) {
                if (i >= d.getMax()) {
                    d.addExtraColumn(i / 10);
                    d.setMax();
                }
                dibujo.setColor(i, Color.ORANGE);
            }
        }
    }
}
