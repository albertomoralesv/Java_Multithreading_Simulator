import javax.swing.*;

public class DibujoGeneral {
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Restaurant");
        frame.setSize(800, 600);

        // Create an instance of MainPanel
        VentanaDibujo mainPanel = new VentanaDibujo();

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
