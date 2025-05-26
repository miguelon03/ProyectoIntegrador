package vista;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import modelo.Usuario;

import java.awt.*;

/**
 * Panel que muestra los datos personales del usuario, incluyendo nombre, apellidos, ciclo y número de matrícula.
 * Permite además cambiar la contraseña mediante verificación.
 * 
 * @author Antonio Alonso
 * @author Miguel De Pablo
 * @author Juan José González
 */
public class PanelDatosPersonales extends JPanel {

    private JLabel nombreLabel;
    private JLabel apellidosLabel;
    private JLabel cicloLabel;
    private JLabel matriculaLabel;
    
    private JPasswordField campoActual;
    private JPasswordField campoNueva;
    private JPasswordField campoConfirmar;
    private JButton btnCambiar;
    private Usuario usuario;

    public PanelDatosPersonales(Usuario usuario) {
    	this.usuario = usuario;
    	inicializarComponentes();
    }
    
    public void inicializarComponentes() {
        setLayout(null); // Layout absoluto
        setBackground(new Color(220, 200, 200)); // Fondo similar al de la imagen
        setPreferredSize(new Dimension(350, 400));

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
        
        JLabel lblActual = new JLabel("Contraseña actual:");
        lblActual.setBounds(30, 210, 140, 25);
        add(lblActual);

        campoActual = new JPasswordField();
        campoActual.setBounds(170, 210, 120, 25);
        add(campoActual);

        JLabel lblNueva = new JLabel("Nueva contraseña:");
        lblNueva.setBounds(30, 250, 140, 25);
        add(lblNueva);

        campoNueva = new JPasswordField();
        campoNueva.setBounds(170, 250, 120, 25);
        add(campoNueva);

        JLabel lblConfirmar = new JLabel("Confirmar nueva:");
        lblConfirmar.setBounds(30, 290, 140, 25);
        add(lblConfirmar);

        campoConfirmar = new JPasswordField();
        campoConfirmar.setBounds(170, 290, 120, 25);
        add(campoConfirmar);

        btnCambiar = new JButton("Cambiar contraseña");
        btnCambiar.setBounds(90, 330, 170, 30);
        btnCambiar.setBackground(new Color(150, 0, 0));
        btnCambiar.setForeground(Color.WHITE);
        add(btnCambiar);
    }

    // Setters y getters
    public void setNombre(String nombre) { 
    	nombreLabel.setText(nombre); 
    	}
    public void setApellidos(String apellidos) {
    	apellidosLabel.setText(apellidos); 
    	}
    public void setCiclo(String ciclo) {
    	cicloLabel.setText(ciclo); 
    	}
    public void setMatricula(String matricula) { 
    	matriculaLabel.setText(matricula); 
    	}
    
    public JButton getBtnCambiar() {
    	return btnCambiar; 
    	}
    
    public JPasswordField getCampoActual() {
    	return campoActual; 
    	}
    
    public JPasswordField getCampoNueva() {
    	return campoNueva; 
    	}
    
    public JPasswordField getCampoConfirmar() {
    	return campoConfirmar;
    	}
    
    //Método para mostrar los datos del usuario
    public void mostrarDatosUsuario(modelo.Usuario usuario) {
        setNombre(usuario.getNombre());
        setApellidos(usuario.getApellidos());
        setCiclo(usuario.getCiclo());
        setMatricula(usuario.getNumMatricula());
    }
    
    //Método para limpiar los campos de contraseña una vez cambiada
    public void limpiarCamposContrasena() {
        campoActual.setText("");
        campoNueva.setText("");
        campoConfirmar.setText("");
    }
    
}
