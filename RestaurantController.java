import java.util.ArrayList;

public class RestaurantController {
    private static int total = 0;

    private static ArrayList<Cliente> clientesEsperandoEntrar = new ArrayList<>();
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static ArrayList<Cocinero> cocineros = new ArrayList<>();
    private static ArrayList<Cajero> cajeros = new ArrayList<>();
    private static ArrayList<Cliente> clientesFormadosEnCaja = new ArrayList<>();

    private static int clientesEnZonaDeJuegos = 0;
    private static int clientesEnFilaParaOrdenar = 0;
    private static int clientesOrdenando = 0;
    private static int clientesEsperandoComida = 0;
    private static int clientesEsperandoMesa = 0;
    private static int clientesComiendo = 0;

    private static int cajerosEnCaja = 0;
    private static int cajerosTomandoOrden = 0;
    private static int cajerosEsperandoCocinero = 0;
    private static int cajerosEsperandoComida = 0;
    private static int cajerosEnDescanso = 0;

    private static int cocinerosEnCocina = 0;
    private static int cocinerosCocinando = 0;
    private static int cocinerosEnDescanso = 0;

    private static int mesasDisponibles;

    private static int juegosDisponibles;

    private static int totalClientes = 0;

    public synchronized static void setMesasTotales(int mesas) {
        mesasDisponibles = mesas;
    }

    public synchronized static void addMesaDisponible() {
        mesasDisponibles++;
    }

    public synchronized static boolean getMesaDisponible() {
        if (mesasDisponibles > 0) {
            mesasDisponibles--;
            return true;
        }
        return false;
    }

    public synchronized static void setJuegosDisponibles(int juegos) {
        juegosDisponibles = juegos;
    }

    public synchronized static boolean getJuegoDisponible() {
        if (juegosDisponibles > 0) {
            juegosDisponibles--;
            return true;
        }
        return false;
    }

    public synchronized static void addJuegoDisponible() {
        juegosDisponibles++;
    }

    public synchronized static void createClient(Cliente cliente) {
        total++;
        GeneralTable.updateRow(18, String.valueOf(total));
        if (clientes.size() >= Main.getRestaurantCapacity()) {
            clientesEsperandoEntrar.add(cliente);
            FilaParaEntrarDibujoController.addCliente();
            GeneralTable.updateRow(0, String.valueOf(clientesEsperandoEntrar.size()));
        } else {
            cliente.start();
            clientes.add(cliente);
            GeneralTable.updateRow(1, String.valueOf(clientes.size()));
            TablaClientes.addRow(cliente.getClientName());
        }
    }

    public synchronized static void createCajero(Cajero cajero) {
        total++;
        GeneralTable.updateRow(18, String.valueOf(total));
        cajero.start();
        cajeros.add(cajero);
        GeneralTable.updateRow(8, String.valueOf(cajeros.size()));
        TablaCajeros.addRow(cajero.getCajeroName());
    }

    public synchronized static void createCocinero(Cocinero cocinero) {
        total++;
        GeneralTable.updateRow(18, String.valueOf(total));
        cocinero.start();
        cocineros.add(cocinero);
        GeneralTable.updateRow(14, String.valueOf(cocineros.size()));
        TablaCocineros.addRow(cocinero.getCocineroName());
    }

    public synchronized static int getClientsNumber() {
        return clientes.size();
    }

    public synchronized static int getCajerosNumber() {
        return cajeros.size();
    }

    public synchronized static int getCocinerosNumber() {
        return cocineros.size();
    }

    public synchronized static void clienteRetirado(int id) {
        for (int i = 0; i < clientesEsperandoEntrar.size(); i++) {
            if (clientesEsperandoEntrar.get(i).getClientId() == id) {
                clientesEsperandoEntrar.remove(i);
                FilaParaEntrarDibujoController.removeClient();
                break;
            }
        }
    }

    public synchronized static void clienteFueraRestaurante(int id) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getClientId() == id) {
                clientes.remove(i);
                clientes.add(clientesEsperandoEntrar.get(0));
                clientesEsperandoEntrar.remove(0);
                FilaParaEntrarDibujoController.removeClient();
                break;
            }
        }
    }

    public synchronized static void addCocineroEnCoina() {
        cocinerosEnCocina++;
        GeneralTable.updateRow(15, String.valueOf(cocinerosEnCocina));
        CocinerosDibujoController.addCocinero();
    }

    public synchronized static void subtractCocineroEnCoina() {
        cocinerosEnCocina--;
        GeneralTable.updateRow(15, String.valueOf(cocinerosEnCocina));
        CocinerosDibujoController.removeCocinero();
    }

    public synchronized static void addCocineroCocinando() {
        cocinerosCocinando++;
        GeneralTable.updateRow(16, String.valueOf(cocinerosCocinando));
        CocinerosDibujoController.addCocinero();
    }

    public synchronized static void subtractCocineroCocinando() {
        cocinerosCocinando--;
        GeneralTable.updateRow(16, String.valueOf(cocinerosCocinando));
        CocinerosDibujoController.removeCocinero();
    }

    public synchronized static void addCocineroEnDescanso() {
        cocinerosEnDescanso++;
        GeneralTable.updateRow(17, String.valueOf(cocinerosEnDescanso));
        EmpleadosDescansoDibujoController.addEmpleado();
    }

    public synchronized static void subtractCocineroEnDescanso() {
        cocinerosEnDescanso--;
        GeneralTable.updateRow(17, String.valueOf(cocinerosEnDescanso));
        EmpleadosDescansoDibujoController.removeEmpleado();
    }

    public synchronized static void addCajeroEnCaja() {
        cajerosEnCaja++;
        GeneralTable.updateRow(9, String.valueOf(cajerosEnCaja));
        CajerosDibujoController.addCajero();
    }

    public synchronized static void subtractCajeroEnCaja() {
        cajerosEnCaja--;
        GeneralTable.updateRow(9, String.valueOf(cajerosEnCaja));
        CajerosDibujoController.removeCajero();
    }

    public synchronized static void addCajeroTomandoOrden() {
        cajerosTomandoOrden++;
        GeneralTable.updateRow(10, String.valueOf(cajerosTomandoOrden));
        CajerosDibujoController.addCajero();
    }

    public synchronized static void subtractCajeroTomandoOrden() {
        cajerosTomandoOrden--;
        GeneralTable.updateRow(10, String.valueOf(cajerosTomandoOrden));
        CajerosDibujoController.removeCajero();
    }

    public synchronized static void addCajeroEsperandoCocinero() {
        cajerosEsperandoCocinero++;
        GeneralTable.updateRow(11, String.valueOf(cajerosEsperandoCocinero));
        CajerosDibujoController.addCajero();
    }

    public synchronized static void subtractCajeroEsperandoCocinero() {
        cajerosEsperandoCocinero--;
        GeneralTable.updateRow(11, String.valueOf(cajerosEsperandoCocinero));
        CajerosDibujoController.removeCajero();
    }

    public synchronized static void addCajeroEsperandoComida() {
        cajerosEsperandoComida++;
        GeneralTable.updateRow(12, String.valueOf(cajerosEsperandoComida));
        CajerosDibujoController.addCajero();
    }

    public synchronized static void subtractCajeroEsperandoComida() {
        cajerosEsperandoComida--;
        GeneralTable.updateRow(12, String.valueOf(cajerosEsperandoComida));
        CajerosDibujoController.removeCajero();
    }

    public synchronized static void addCajeroEnDescanso() {
        cajerosEnDescanso++;
        GeneralTable.updateRow(13, String.valueOf(cajerosEnDescanso));
        EmpleadosDescansoDibujoController.addEmpleado();
    }

    public synchronized static void subtractCajeroEnDescanso() {
        cajerosEnDescanso--;
        GeneralTable.updateRow(13, String.valueOf(cajerosEnDescanso));
        EmpleadosDescansoDibujoController.removeEmpleado();
    }

    public synchronized static void addClienteEnZonaDeJuegos() {
        clientesEnZonaDeJuegos++;
        GeneralTable.updateRow(2, String.valueOf(clientesEnZonaDeJuegos));
        ZonaDeJuegosDibujoController.addCliente();
    }

    public synchronized static void subtractClienteEnZonaDeJuegos() {
        clientesEnZonaDeJuegos--;
        GeneralTable.updateRow(2, String.valueOf(clientesEnZonaDeJuegos));
        ZonaDeJuegosDibujoController.removeCliente();
    }

    public synchronized static void addClienteEnFilaParaOrdenar(Cliente cliente) {
        clientesEnFilaParaOrdenar++;
        GeneralTable.updateRow(3, String.valueOf(clientesEnFilaParaOrdenar));
        clientesFormadosEnCaja.add(cliente);
        FilaParaOrdenarDibujoController.addCliente();
    }

    public synchronized static void subtractClienteEnFilaParaOrdenar(Cliente cliente) {
        clientesEnFilaParaOrdenar--;
        GeneralTable.updateRow(3, String.valueOf(clientesEnFilaParaOrdenar));
        for (int i = 0; i < clientesFormadosEnCaja.size(); i++) {
            if (clientesFormadosEnCaja.get(i).getClientId() == cliente.getClientId()) {
                clientesFormadosEnCaja.remove(i);
            }
        }
        FilaParaOrdenarDibujoController.removeCliente();
    }

    public synchronized static void addClienteOrdenando() {
        clientesOrdenando++;
        GeneralTable.updateRow(4, String.valueOf(clientesOrdenando));
    }

    public synchronized static void subtractClienteOrdenando() {
        clientesOrdenando--;
        GeneralTable.updateRow(4, String.valueOf(clientesOrdenando));
    }

    public synchronized static void addClienteEsperandoComida() {
        clientesEsperandoComida++;
        GeneralTable.updateRow(5, String.valueOf(clientesEsperandoComida));
    }

    public synchronized static void subtractClienteEsperandoComida() {
        clientesEsperandoComida--;
        GeneralTable.updateRow(5, String.valueOf(clientesEsperandoComida));
    }

    public synchronized static void addClienteEsperandoMesa() {
        clientesEsperandoMesa++;
        GeneralTable.updateRow(6, String.valueOf(clientesEsperandoMesa));
        EsperandoMesaDibujoController.addCliente();
    }

    public synchronized static void subtractClienteEsperandoMesa() {
        clientesEsperandoMesa--;
        GeneralTable.updateRow(6, String.valueOf(clientesEsperandoMesa));
        EsperandoMesaDibujoController.removeCliente();
    }

    public synchronized static void addClienteComiendo() {
        clientesComiendo++;
        GeneralTable.updateRow(7, String.valueOf(clientesComiendo));
        MesasDibujoController.addCliente();
    }

    public synchronized static void subtractClienteComiendo() {
        clientesComiendo--;
        GeneralTable.updateRow(7, String.valueOf(clientesComiendo));
        MesasDibujoController.removeCliente();
    }

    // Getter methods
    public synchronized static int getClientesEnZonaDeJuegos() {
        return clientesEnZonaDeJuegos;
    }

    public synchronized static int getClientesEnFilaParaOrdenar() {
        return clientesEnFilaParaOrdenar;
    }

    public synchronized static int getClientesOrdenando() {
        return clientesOrdenando;
    }

    public synchronized static int getClientesEsperandoComida() {
        return clientesEsperandoComida;
    }

    public synchronized static int getClientesEsperandoMesa() {
        return clientesEsperandoMesa;
    }

    public synchronized static int getClientesComiendo() {
        return clientesComiendo;
    }

    public synchronized static int getCajerosEnCaja() {
        return cajerosEnCaja;
    }

    // Getter for cocinerosEnCocina
    public synchronized static int getCocinerosEnCocina() {
        return cocinerosEnCocina;
    }

    // Getter for cocinerosCocinando
    public synchronized static int getCocinerosCocinando() {
        return cocinerosCocinando;
    }

    // Getter for cocinerosEnDescanso
    public synchronized static int getCocinerosEnDescanso() {
        return cocinerosEnDescanso;
    }

    public synchronized static Cliente getFirstClienteFormado() {
        Cliente cliente = clientesFormadosEnCaja.get(0);
        clientesFormadosEnCaja.remove(0);
        return cliente;
    }

    public synchronized static Cajero getFirstCajeroEnCaja() {
        for (int i = 0; i < cajeros.size(); i++) {
            if (cajeros.get(i).getCurrentState() == "enCaja") {
                cajeros.get(i).setCurrentState("");
                return cajeros.get(i);
            }
        }
        return null;
    }

    public synchronized static Cocinero getFirstCocineroEnCocina() {
        for (int i = 0; i < cocineros.size(); i++) {
            if (cocineros.get(i).getCurrentState() == "enCocina") {
                cocineros.get(i).setCurrentState("");
                return cocineros.get(i);
            }
        }
        return null;
    }

    public synchronized static void sacarCliente(Cliente cliente) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getClientId() == cliente.getClientId()) {
                clientes.remove(i);
                total--;
                GeneralTable.updateRow(18, String.valueOf(total));
                if (clientesEsperandoEntrar.size() > 0) {
                    clientes.add(clientesEsperandoEntrar.get(0));
                    TablaClientes.addRow(clientesEsperandoEntrar.get(0).getClientName());
                    clientesEsperandoEntrar.get(0).start();
                    clientesEsperandoEntrar.remove(0);
                    FilaParaEntrarDibujoController.removeClient();
                    GeneralTable.updateRow(0, String.valueOf(clientesEsperandoEntrar.size()));
                }
                GeneralTable.updateRow(1, String.valueOf(clientes.size()));
                break;
            }
        }
    }

    public synchronized static int getTotalClientes() {
        return totalClientes;
    }

    public synchronized static void addNewClient() {
        totalClientes++;
    }
}
