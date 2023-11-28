import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class TablaTiempos {
    private static int tiempoZonaDeJuegos = 10000;
    private static int tiempoOrdenando = 5000;
    private static int tiempoComiendo = 20000;
    private static int tiempoDescansoCajero = 10000;
    private static int tiempoCocinando = 15000;
    private static int tiempoDescansoCocinero = 10000;

    private static JTextField tiempoZonaDeJuegosField;
    private static JTextField tiempoOrdenandoField;
    private static JTextField tiempoComiendoField;
    private static JTextField tiempoDescansoCajeroField;
    private static JTextField tiempoCocinandoField;
    private static JTextField tiempoDescansoCocineroField;

    public static void showTable() {
        JFrame frame = new JFrame("Times");

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create title row
        JLabel titleLabel = new JLabel("Times", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Add margin to the top
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create table panel with GridBagLayout
        JPanel tablePanel = new JPanel(new GridBagLayout());

        // Column titles
        addBorderedLabel(tablePanel, "Zona de Juegos", 0, 0);
        addBorderedLabel(tablePanel, "Ordenando", 0, 1);
        addBorderedLabel(tablePanel, "Comiendo", 0, 2);
        addBorderedLabel(tablePanel, "Descanso Cajero", 0, 3);
        addBorderedLabel(tablePanel, "Cocinando", 0, 4);
        addBorderedLabel(tablePanel, "Descanso Cocinero", 0, 5);

        // Input fields
        tiempoZonaDeJuegosField = new JTextField(String.valueOf(tiempoZonaDeJuegos));
        tiempoOrdenandoField = new JTextField(String.valueOf(tiempoOrdenando));
        tiempoComiendoField = new JTextField(String.valueOf(tiempoComiendo));
        tiempoDescansoCajeroField = new JTextField(String.valueOf(tiempoDescansoCajero));
        tiempoCocinandoField = new JTextField(String.valueOf(tiempoCocinando));
        tiempoDescansoCocineroField = new JTextField(String.valueOf(tiempoDescansoCocinero));
        addBorderedTextField(tablePanel, tiempoZonaDeJuegosField, 1, 0);
        addBorderedTextField(tablePanel, tiempoOrdenandoField, 1, 1);
        addBorderedTextField(tablePanel, tiempoComiendoField, 1, 2);
        addBorderedTextField(tablePanel, tiempoDescansoCajeroField, 1, 3);
        addBorderedTextField(tablePanel, tiempoCocinandoField, 1, 4);
        addBorderedTextField(tablePanel, tiempoDescansoCocineroField, 1, 5);

        // Buttons
        JButton setTiempoZonaDeJuegosButton = new JButton("save");
        setTiempoZonaDeJuegosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int newTime = Integer.parseInt(tiempoZonaDeJuegosField.getText());
                    setTiempoZonaDeJuegos(newTime);
                } catch (NumberFormatException err) {
                }
            }
        });
        JButton setTiempoOrdenandoButton = new JButton("save");
        setTiempoOrdenandoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int newTime = Integer.parseInt(tiempoOrdenandoField.getText());
                    setTiempoOrdenando(newTime);
                } catch (NumberFormatException err) {
                }
            }
        });
        JButton setTiempoComiendoButton = new JButton("save");
        setTiempoComiendoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int newTime = Integer.parseInt(tiempoComiendoField.getText());
                    setTiempoComiendo(newTime);
                } catch (NumberFormatException err) {
                }
            }
        });
        JButton setTiempoDescansoCajeroButton = new JButton("save");
        setTiempoDescansoCajeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int newTime = Integer.parseInt(tiempoDescansoCajeroField.getText());
                    setTiempoDescansoCajero(newTime);
                } catch (NumberFormatException err) {
                }
            }
        });
        JButton setTiempoCocinandoButton = new JButton("save");
        setTiempoCocinandoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int newTime = Integer.parseInt(tiempoCocinandoField.getText());
                    setTiempoCocinando(newTime);
                } catch (NumberFormatException err) {
                }
            }
        });
        JButton setTiempoDescansoCocineroButton = new JButton("save");
        setTiempoDescansoCocineroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int newTime = Integer.parseInt(tiempoDescansoCocineroField.getText());
                    setTiempoDescansoCocinero(newTime);
                } catch (NumberFormatException err) {
                }
            }
        });
        addBorderedButton(tablePanel, setTiempoZonaDeJuegosButton, 2, 0);
        addBorderedButton(tablePanel, setTiempoOrdenandoButton, 2, 1);
        addBorderedButton(tablePanel, setTiempoComiendoButton, 2, 2);
        addBorderedButton(tablePanel, setTiempoDescansoCajeroButton, 2, 3);
        addBorderedButton(tablePanel, setTiempoCocinandoButton, 2, 4);
        addBorderedButton(tablePanel, setTiempoDescansoCocineroButton, 2, 5);

        mainPanel.add(tablePanel, BorderLayout.CENTER);

        // Set up the frame
        frame.getContentPane().add(mainPanel);
        frame.setSize(600, 500); // Adjusted the frame height
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void addBorderedLabel(JPanel panel, String text, int gridx, int gridy) {
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add padding

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(label, gbc);
    }

    private static void addBorderedTextField(JPanel panel, JTextField textField, int gridx, int gridy) {
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setPreferredSize(new Dimension(100, 25)); // Set a preferred size

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5); // Add padding
        panel.add(textField, gbc);
    }

    private static void addBorderedButton(JPanel panel, JButton button, int gridx, int gridy) {
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setBorder(new LineBorder(Color.BLACK));
        button.setPreferredSize(new Dimension(60, 25)); // Set a preferred size

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5); // Add padding
        panel.add(button, gbc);
    }

    public static void setTiempoZonaDeJuegos(int tiempo) {
        tiempoZonaDeJuegos = tiempo;
        tiempoZonaDeJuegosField.setText(String.valueOf(tiempo));
    }

    public static void setTiempoOrdenando(int tiempo) {
        tiempoOrdenando = tiempo;
        tiempoOrdenandoField.setText(String.valueOf(tiempo));
    }

    public static void setTiempoComiendo(int tiempo) {
        tiempoComiendo = tiempo;
        tiempoComiendoField.setText(String.valueOf(tiempo));
    }

    public static void setTiempoDescansoCajero(int tiempo) {
        tiempoDescansoCajero = tiempo;
        tiempoDescansoCajeroField.setText(String.valueOf(tiempo));
    }

    public static void setTiempoCocinando(int tiempo) {
        tiempoCocinando = tiempo;
        tiempoCocinandoField.setText(String.valueOf(tiempo));
    }

    public static void setTiempoDescansoCocinero(int tiempo) {
        tiempoDescansoCocinero = tiempo;
        tiempoDescansoCocineroField.setText(String.valueOf(tiempo));
    }

    public static synchronized int getTiempoZonaDeJuegos() {
        return tiempoZonaDeJuegos;
    }

    public static synchronized int getTiempoOrdenando() {
        return tiempoOrdenando;
    }

    public static synchronized int getTiempoComiendo() {
        return tiempoComiendo;
    }

    public static synchronized int getTiempoDescansoCajero() {
        return tiempoDescansoCajero;
    }

    public static synchronized int getTiempoCocinando() {
        return tiempoCocinando;
    }

    public static synchronized int getTiempoDescansoCocinero() {
        return tiempoDescansoCocinero;
    }

}
