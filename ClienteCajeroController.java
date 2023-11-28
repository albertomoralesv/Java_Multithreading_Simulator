public class ClienteCajeroController extends Thread {
    private boolean operando = true;

    @Override
    public void run() {
        while (operando) {
            if (RestaurantController.getClientesEnFilaParaOrdenar() > 0
                    && RestaurantController.getCajerosEnCaja() > 0) {
                RestaurantController.getFirstClienteFormado()
                        .entrarOrdenando(RestaurantController.getFirstCajeroEnCaja());
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
