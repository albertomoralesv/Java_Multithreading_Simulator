import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Creditos {
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Credits");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load image from URL (replace URL with the path to your image)
        ImageIcon imageIcon = createResizedImageIcon("logo.jpeg", 200, 100);
        JLabel imageLabel = new JLabel(imageIcon);

        // Create text labels
        JLabel textLabel1 = new JLabel("Raquel Magdalena Ochoa Martinez 0235324");
        JLabel textLabel2 = new JLabel("Jorge Vargas Gonzalez 0237032");
        JLabel textLabel3 = new JLabel("Alberto Morales Vizcarra 0230866");
        JLabel textLabel4 = new JLabel("Fundamentos de Programacion en Paralelo");
        JLabel textLabel5 = new JLabel("Juan Carlos Lopez Pimentel");

        // Create a panel with a GridLayout to arrange components in a grid
        JPanel panel = new JPanel(new GridLayout(0, 1)); // 0 means any number of rows, 1 column
        panel.add(imageLabel);
        panel.add(textLabel1);
        panel.add(textLabel2);
        panel.add(textLabel3);
        panel.add(textLabel4);
        panel.add(textLabel5);

        // Add the panel to the frame
        frame.add(panel);

        // Set frame size based on preferred sizes of components
        frame.pack();

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);
    }

    // Helper method to create a resized ImageIcon from a file path or URL
    private static ImageIcon createResizedImageIcon(String path, int width, int height) {
        URL imgUrl = Creditos.class.getResource(path);
        if (imgUrl != null) {
            ImageIcon originalIcon = new ImageIcon(imgUrl);
            Image resizedImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
