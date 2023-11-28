import javax.swing.SwingUtilities;

public class Main {
    private static int restaurantCapacity;
    private static int numberOfTables;
    private static int gameZoneCapacity;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Call the static method to show the initial table
            StartTable.showTable(new StartTable.TableClosedListener() {
                @Override
                public void onTableClosed() {
                    SwingUtilities.invokeLater(() -> {
                        // Perform actions after the table has been disposed
                        restaurantCapacity = StartTable.getRestaurantCapacity();
                        numberOfTables = StartTable.getNumberOfTables();
                        gameZoneCapacity = StartTable.getGameZoneCapacity();

                        RestaurantController.setMesasTotales(numberOfTables);
                        RestaurantController.setJuegosDisponibles(gameZoneCapacity);

                        GeneralTable.showTable();
                    });

                }
            });
        });
    }

    public static int getRestaurantCapacity() {
        return restaurantCapacity;
    }

    public static int getNumberOfTables() {
        return numberOfTables;
    }

    public static int getGameZoneCapacity() {
        return gameZoneCapacity;
    }
}