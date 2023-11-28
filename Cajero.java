import java.util.Random;

public class Cajero extends Thread {
    private final String cajeroName;
    private final int id;
    private String currentState;
    private int ordenesTomadas = 0;
    private Cliente clienteAtendido;
    private static Random random = new Random();

    public Cajero(String name, int id) {
        this.cajeroName = name;
        this.id = id;
    }

    @Override
    public void run() {
        entrarCajero();
        while (true) {
            if (currentState == "tomandoOrden") {
                tomandoOrden();
            }
            if (currentState == "esperandoCocinero") {
                esperandoCocinero();
            }
            if (currentState == "comidaEntregada") {
                entregada();
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

    public String getCajeroName() {
        return cajeroName;
    }

    public int getCajeroId() {
        return id;
    }

    public void setCurrentState(String state) {
        currentState = state;
    }

    public String getCurrentState() {
        return currentState;
    }

    public int getOrdenesTomadas() {
        return ordenesTomadas;
    }

    public void entrarCajero() {
        currentState = "entrando";
        int tiempo = 500;
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        entrarEnCaja();
    }

    public void entrarEnCaja() {
        currentState = "enCaja";
        RestaurantController.addCajeroEnCaja();
        TablaCajeros.updateRow(id, 1, "X");
    }

    public void entrarTomandoOrden(Cliente cliente) {
        clienteAtendido = cliente;
        currentState = "tomandoOrden";
    }

    public void tomandoOrden() {
        currentState = "orden";
        RestaurantController.subtractCajeroEnCaja();
        RestaurantController.addCajeroTomandoOrden();
        TablaCajeros.updateRow(id, 2, clienteAtendido.getClientName());
        int tiempo = TablaTiempos.getTiempoOrdenando();
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RestaurantController.subtractCajeroTomandoOrden();
        clienteAtendido.entrarEsperandoComida();
        entrarEsperandoCocinero();
    }

    public void entrarEsperandoCocinero() {
        currentState = "esperandoCocinero";
    }

    public void esperandoCocinero() {
        currentState = "cocinero";
        RestaurantController.addCajeroEsperandoCocinero();
        Cocinero cocinero;
        TablaCajeros.updateRow(id, 3, "X");
        while (true) {
            cocinero = RestaurantController.getFirstCocineroEnCocina();
            if (cocinero != null) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        RestaurantController.subtractCajeroEsperandoCocinero();
        RestaurantController.addCajeroEsperandoComida();
        TablaCajeros.updateRow(id, 4, cocinero.getCocineroName());
        cocinero.entrarCocinando(this);
        entrarEsperandoComida();
    }

    public void entrarEsperandoComida() {
        currentState = "esperandoComida";
    }

    public void comidaEntregada() {
        currentState = "comidaEntregada";
    }

    public void entregada() {
        currentState = "entrega";
        RestaurantController.subtractCajeroEsperandoComida();
        ordenesTomadas += 1;
        int d = random.nextInt(2);
        clienteAtendido.comidaEntregada();
        if (d == 0) {
            entrarEnCaja();
        } else {
            entrarDescansando();
        }
    }

    public void entrarDescansando() {
        currentState = "descansando";
    }

    public void descansando() {
        currentState = "descanso";
        RestaurantController.addCajeroEnDescanso();
        TablaCajeros.updateRow(id, 5, "X");
        try {
            Thread.sleep(TablaTiempos.getTiempoDescansoCajero());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RestaurantController.subtractCajeroEnDescanso();
        entrarEnCaja();
    }
}
