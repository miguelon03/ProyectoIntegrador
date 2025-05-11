package vista;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PanelDatosPersonales extends JPanel {

    private JLabel nombreLabel;
    private JLabel apellidosLabel;
    private JLabel cicloLabel;
    private JLabel matriculaLabel;

    public PanelDatosPersonales() {
    	inicializarComponentes();
    }
    
    public void inicializarComponentes() {
        setLayout(null); // Layout absoluto
        setBackground(new Color(220, 200, 200)); // Fondo similar al de la imagen
        setPreferredSize(new Dimension(350, 250));

        // Borde con título
        TitledBorder border = BorderFactory.createTitledBorder(
                new LineBorder(Color.DARK_GRAY),
                "Datos personales",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 16)
        );
        setBorder(border);

        // Nombre
        JLabel nombreTexto = new JLabel("Nombre:");
        nombreTexto.setFont(new Font("SansSerif", Font.PLAIN, 14));
        nombreTexto.setBounds(30, 50, 80, 25);
        add(nombreTexto);

        nombreLabel = new JLabel(" ");
        nombreLabel.setOpaque(true);
        nombreLabel.setBackground(Color.WHITE);
        nombreLabel.setBounds(120, 50, 160, 25);
        nombreLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));
        add(nombreLabel);

        // Apellidos
        JLabel apellidosTexto = new JLabel("Apellidos:");
        apellidosTexto.setFont(new Font("SansSerif", Font.PLAIN, 14));
        apellidosTexto.setBounds(30, 90, 80, 25);
        add(apellidosTexto);

        apellidosLabel = new JLabel(" ");
        apellidosLabel.setOpaque(true);
        apellidosLabel.setBackground(Color.WHITE);
        apellidosLabel.setBounds(120, 90, 160, 25);
        apellidosLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));
        add(apellidosLabel);

        // Ciclo
        JLabel cicloTexto = new JLabel("Ciclo:");
        cicloTexto.setFont(new Font("SansSerif", Font.PLAIN, 14));
        cicloTexto.setBounds(30, 130, 80, 25);
        add(cicloTexto);

        cicloLabel = new JLabel(" ");
        cicloLabel.setOpaque(true);
        cicloLabel.setBackground(Color.WHITE);
        cicloLabel.setBounds(120, 130, 160, 25);
        cicloLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));
        add(cicloLabel);

        // Matrícula
        JLabel matriculaTexto = new JLabel("Matrícula:");
        matriculaTexto.setFont(new Font("SansSerif", Font.PLAIN, 14));
        matriculaTexto.setBounds(30, 170, 80, 25);
        add(matriculaTexto);

        matriculaLabel = new JLabel(" ");
        matriculaLabel.setOpaque(true);
        matriculaLabel.setBackground(Color.WHITE);
        matriculaLabel.setBounds(120, 170, 160, 25);
        matriculaLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));
        add(matriculaLabel);
    }

    // Setters
    public void setNombre(String nombre) { nombreLabel.setText(nombre); }
    public void setApellidos(String apellidos) { apellidosLabel.setText(apellidos); }
    public void setCiclo(String ciclo) { cicloLabel.setText(ciclo); }
    public void setMatricula(String matricula) { matriculaLabel.setText(matricula); }
}
