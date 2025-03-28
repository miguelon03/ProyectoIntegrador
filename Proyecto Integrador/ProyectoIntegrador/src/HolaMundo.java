import javax.swing.*;
import java.awt.*;

public class HolaMundo extends JFrame {

    public HolaMundo() {
        // Ponemos el titulo
        setTitle("Hola Mundo - WindowBuilder");
        //Ponemos el tamaño
        setSize(400, 200);
        //indicar que se hace cuando se cierra la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //centramos la ventana 
        setLocationRelativeTo(null);
        //establecemos el diseño del layout
        getContentPane().setLayout(new FlowLayout());

        // Escribrimos el mensaje hola mundo
        JLabel lblMensaje = new JLabel("¡Hola Mundo!");
        //establecemos el font
        lblMensaje.setFont(new Font("Arial", Font.BOLD, 18));
        //añadimos el mensaje 
        getContentPane().add(lblMensaje);
    }

    public static void main(String[] args) {
            HolaMundo ventana = new HolaMundo();
            //hacemos visible la ventana
            ventana.setVisible(true);
        
    }
}
