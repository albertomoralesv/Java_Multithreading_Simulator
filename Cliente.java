import java.util.Random;

public class Cliente extends Thread {
    private final String clientName;
    private final int id;
    private String currentState;
    private Cajero cajeroActual;
    private boolean activo = true;
    private Random random = new Random();

    public Cliente(String name, int id) {
        this.clientName = name;
        this.id = id;
        RestaurantController.addNewClient();
    }

    @Override
    public void run() {
        entrarCliente();
        while (activo) {
            if (currentState == "zonaDeJuegos") {
                zonaDeJuegos();
            }
            if (currentState == "filaParaOrdenar") {
                filaParaOrdenar();
            }
            if (currentState == "ordenando") {
                ordenando();
            }
            if (currentState == "esperandoMesa") {
                esperandoMesa();
            }
            if (currentState == "comiendo") {
                comiendo();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getClientName() {
        return clientName;
    }

    public int getClientId() {
        return id;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void entrarCliente() {
        currentState = "entrando";
        int tiempo = 500;
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int d = random.nextInt(2);
        if (d == 0) {
            if (RestaurantController.getJuegoDisponible()) {
                entrarZonaDeJuegos();
            } else {
                d = random.nextInt(2);
                if (d == 0) {
                    entrarFilaParaOrdenar();
                } else {
                    salirRestaurante();
                }
            }
        } else {
            entrarFilaParaOrdenar();
        }
    }

    public void entrarZonaDeJuegos() {
        currentState = "zonaDeJuegos";
    }

    public void zonaDeJuegos() {
        currentState = "juegos";
        RestaurantController.addClienteEnZonaDeJuegos();
        TablaClientes.updateRow(id, 1, "X");
        try {
            Thread.sleep(TablaTiempos.getTiempoZonaDeJuegos());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int d = random.nextInt(3);
        RestaurantController.subtractClienteEnZonaDeJuegos();
        if (d != 0) {
            RestaurantController.addJuegoDisponible();
        }
        if (d == 0) {
            entrarZonaDeJuegos();
        } else if (d == 1) {
            entrarFilaParaOrdenar();
        } else {
            salirRestaurante();
        }
    }

    public void entrarFilaParaOrdenar() {
        currentState = "filaParaOrdenar";
    }

    public void filaParaOrdenar() {
        currentState = "enFila";
        RestaurantController.addClienteEnFilaParaOrdenar(this);
        TablaClientes.updateRow(id, 2, "X");
        Cajero cajero;
        while (true) {
            cajero = RestaurantController.getFirstCajeroEnCaja();
            if (cajero != null) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        entrarOrdenando(cajero);
    }

    public void entrarOrdenando(Cajero cajero) {
        cajeroActual = cajero;
        currentState = "ordenando";
    }

    public void ordenando() {
        currentState = "enOrden";
        RestaurantController.subtractClienteEnFilaParaOrdenar(this);
        RestaurantController.addClienteOrdenando();
        TablaClientes.updateRow(id, 3, cajeroActual.getCajeroName());
        cajeroActual.entrarTomandoOrden(this);
    }

    public void entrarEsperandoComida() {
        RestaurantController.subtractClienteOrdenando();
        RestaurantController.addClienteEsperandoComida();
        TablaClientes.updateRow(id, 4, cajeroActual.getCajeroName());
        currentState = "esperandoComida";
    }

    public void comidaEntregada() {
        RestaurantController.subtractClienteEsperandoComida();
        entrarEsperandoMesa();
    }

    public void entrarEsperandoMesa() {
        currentState = "esperandoMesa";
    }

    public void esperandoMesa() {
        currentState = "mesa";
        RestaurantController.addClienteEsperandoMesa();
        TablaClientes.updateRow(id, 5, "X");
        while (true) {
            boolean mesaDisponible = RestaurantController.getMesaDisponible();
            if (mesaDisponible == true) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        RestaurantController.subtractClienteEsperandoMesa();
        entrarComiendo();
    }

    public void entrarComiendo() {
        currentState = "comiendo";
    }

    public void comiendo() {
        currentState = "comer";
        RestaurantController.addClienteComiendo();
        TablaClientes.updateRow(id, 6, "X");
        try {
            Thread.sleep(TablaTiempos.getTiempoComiendo());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RestaurantController.subtractClienteComiendo();
        RestaurantController.addMesaDisponible();
        int d = random.nextInt(3);
        if (d == 0) {
            entrarFilaParaOrdenar();
        } else if (d == 1) {
            entrarZonaDeJuegos();
        } else {
            salirRestaurante();
        }
    }

    public void salirRestaurante() {
        currentState = "inactivo";
        activo = false;
        TablaClientes.updateRow(id, 0, "X");
        System.out.println("Cliente " + id + " Salio del Restaurante");
        RestaurantController.sacarCliente(this);
    }

}
