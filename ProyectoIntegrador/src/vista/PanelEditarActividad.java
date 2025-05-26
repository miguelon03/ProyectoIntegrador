package vista;

import modelo.Actividad;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Panel que permite a un monitor editar la fecha y la hora de una actividad previamente creada.
 * 
 * Muestra el nombre de la actividad, los campos de selección de nueva fecha y hora, y un botón para guardar los cambios.
 * Incluye validaciones para evitar que el monitor asigne una actividad a una fecha u hora pasada o solapada con otra propia.
 * 
 * @author Antonio Alonso
 * @author Miguel De Pablo
 * @author Juan José González
 */
public class PanelEditarActividad extends JPanel {

    private JComboBox<String> comboDia, comboMes, comboAnio, comboHora;
    private JButton btnGuardar;
    private Actividad actividad;

    public PanelEditarActividad(Actividad actividad) {
        this.actividad = actividad;
        inicializarComponentes();
    }

    public void inicializarComponentes() {
        setLayout(new BorderLayout());
        setBackground(new Color(230, 210, 210));

        //En el título añadimos el nombre de la activida que se va a editar
        JLabel titulo = new JLabel("Editar actividad: " + actividad.getNombreActividad(), SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(titulo, BorderLayout.NORTH);

       //Panel general que incluye el resto de paneles
        JPanel formPanel = new JPanel(new GridLayout(2, 1));
        formPanel.setBackground(new Color(230, 210, 210));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        // Panel con GridBagLayout para la fecha
        JPanel panelFecha = new JPanel(new GridBagLayout());
        panelFecha.setBackground(new Color(230, 210, 210));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Día
        gbc.gridx = 0; gbc.gridy = 0;
        panelFecha.add(new JLabel("Día:"), gbc);
        comboDia = new JComboBox<>();
        gbc.gridx = 1;
        panelFecha.add(comboDia, gbc);

        // Mes
        gbc.gridx = 0; gbc.gridy = 1;
        panelFecha.add(new JLabel("Mes:"), gbc);
        comboMes = new JComboBox<>();
        for (int i = 1; i <= 12; i++) comboMes.addItem(String.format("%02d", i));
        gbc.gridx = 1;
        panelFecha.add(comboMes, gbc);

        // Año
        gbc.gridx = 0; gbc.gridy = 2;
        panelFecha.add(new JLabel("Año:"), gbc);
        comboAnio = new JComboBox<>();
        for (int i = 2025; i <= 2030; i++) comboAnio.addItem(String.valueOf(i));
        gbc.gridx = 1;
        panelFecha.add(comboAnio, gbc);

        // Hora
        gbc.gridx = 0; gbc.gridy = 3;
        panelFecha.add(new JLabel("Hora:"), gbc);
        comboHora = new JComboBox<>();
        for (int h = 9; h <= 20; h++) comboHora.addItem(String.format("%02d:00", h));
        gbc.gridx = 1;
        panelFecha.add(comboHora, gbc);

        formPanel.add(panelFecha);

        // Botón
        btnGuardar = new JButton("Guardar cambios");
        btnGuardar.setBackground(new Color(204, 0, 0));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 14));
        btnGuardar.setFocusPainted(false);

        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(230, 210, 210));
        panelBoton.add(btnGuardar);

        formPanel.add(panelBoton);

        add(formPanel, BorderLayout.CENTER);

        //Añadimos los action listeners a los combobox de mes y año
        comboMes.addActionListener(e -> actualizarDias());
        comboAnio.addActionListener(e -> actualizarDias());

        cargarDatos();
    }

    //MÉTODO PARA CARGAR LOS DATOS DENTRO DE LOS COMBO BOX
    private void cargarDatos() {
        LocalDate fecha = LocalDate.parse(actividad.getFecha(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        comboMes.setSelectedItem(String.format("%02d", fecha.getMonthValue()));
        comboAnio.setSelectedItem(String.valueOf(fecha.getYear()));
        actualizarDias();
        comboDia.setSelectedItem(String.format("%02d", fecha.getDayOfMonth()));
        comboHora.setSelectedItem(actividad.getHora());
    }

 // Método para ajustar los días del mes dinámicamente
    private void actualizarDias() {
        if (comboMes.getSelectedItem() == null || comboAnio.getSelectedItem() == null) return;

        int mes = Integer.parseInt((String) comboMes.getSelectedItem());
        int anio = Integer.parseInt((String) comboAnio.getSelectedItem());

        int diasEnMes;
        switch (mes) {
            case 2:
            	// Año bisiesto
                diasEnMes = ((anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0)) ? 29 : 28;
                /*hago esta operacion porque se sabe si un año es bisiesto si es divisible por 4 per no divisible entre
                 * 100 y si además es divisible por 400. Entonces dependiendo si esa condicion se cumple febrero,
                 * el mes 2, tendrá 28 o 29 días.
                 */
                break;
            case 4: case 6: case 9: case 11://abril, junio, agosto y noviembre tienen 30 días
                diasEnMes = 30;
                break;
            default:
                diasEnMes = 31;//los demás meses tienen 31 días
        }

        comboDia.removeAllItems();
        for (int i = 1; i <= diasEnMes; i++) {
            comboDia.addItem(String.format("%02d", i));
        }
    }

    //Método que devuelve la fecha completa
    public String getFechaCompleta() {
        return comboAnio.getSelectedItem() + "-" +
               comboMes.getSelectedItem() + "-" +
               comboDia.getSelectedItem();
    }

  
    public String getHora() {
        return (String) comboHora.getSelectedItem();
    }

  
    public JButton getBtnGuardar() {
        return btnGuardar;
    }

   
    public Actividad getActividad() {
        return actividad;
    }
    
    //Método para comprobar si la fecha es válida (la fecha debe ser futura)
    public boolean fechaEsValida() {
        String fechaStr = getFechaCompleta();
        LocalDate fechaSeleccionada = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return !fechaSeleccionada.isBefore(LocalDate.now());
    }
    

}
