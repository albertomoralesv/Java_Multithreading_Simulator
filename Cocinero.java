import java.util.Random;

public class Cocinero extends Thread {
    private final String cocineroName;
    private final int id;
    private String currentState = "";
    private Cajero cajeroActual;
    private int comidasCocinadas = 0;
    private static Random random = new Random();

    public Cocinero(String name, int id) {
        this.cocineroName = name;
        this.id = id;
    }

    @Override
    public void run() {
        entrarCocinero();
        while (true) {
            if (currentState == "cocinando") {
                cocinando();
            }
            if (currentState == "descansando") {
                descansando();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getCocineroName() {
        return cocineroName;
    }

    public int getCocineroId() {
        return id;
    }

    public void setCurrentState(String state) {
        currentState = state;
    }

    public String getCurrentState() {
        return currentState;
    }

    public int getComidasCocinadas() {
        return comidasCocinadas;
    }

    public void entrarCocinero() {
        currentState = "entrando";
        int tiempo = 500;
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        entrarEnCocina();
    }

    public void entrarEnCocina() {
        currentState = "enCocina";
        RestaurantController.addCocineroEnCoina();
        TablaCocineros.updateRow(id, 1, "X");
    }

    public void entrarCocinando(Cajero cajero) {
        currentState = "cocinando";
        cajeroActual = cajero;
    }

    public void cocinando() {
        currentState = "cocina";
        RestaurantController.subtractCocineroEnCoina();
        RestaurantController.addCocineroCocinando();
        TablaCocineros.updateRow(id, 2, cajeroActual.getCajeroName());
        try {
            Thread.sleep(TablaTiempos.getTiempoCocinando());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RestaurantController.subtractCocineroCocinando();
        cajeroActual.comidaEntregada();
        comidasCocinadas += 1;
        int d = random.nextInt(2);
        if (d == 0) {
            entrarEnCocina();
        } else {
            entrarDescansando();
        }
    }

    public void entrarDescansando() {
        currentState = "descansando";
    }

    public void descansando() {
        currentState = "descanso";
        RestaurantController.addCocineroEnDescanso();
        TablaCocineros.updateRow(id, 3, "X");
        try {
            Thread.sleep(TablaTiempos.getTiempoDescansoCocinero());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RestaurantController.subtractCocineroEnDescanso();
        entrarEnCocina();
    }
}
