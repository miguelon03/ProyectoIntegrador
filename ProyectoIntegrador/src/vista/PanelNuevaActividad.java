package vista;

import javax.swing.*;

import bbdd.AccesoBBDDLogin;

import java.awt.*;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import modelo.Sala;
import modelo.Usuario;

/**
 * Panel que permite al monitor crear una nueva actividad. Incluye selección 
 * de tipo de actividad, fecha, hora y sala correspondiente (asociada a la actividad).
 * 
 * Valida que la fecha no sea anterior a la actual y que no se duplique la actividad
 * en el mismo horario. También evita solapamientos para el mismo monitor.
 * 
 * Se muestra la sala asignada automáticamente en función del tipo de actividad seleccionada
 * @author Antonio Alonso
 * @author Miguel De Pablo
 * @author Juan José González
 */
public class PanelNuevaActividad extends JPanel {
	
	private JList<String> listaActividades;
    private JComboBox<String> comboDia, comboMes, comboAnio, comboHora;
    private JLabel lblSalaAsignada;
    private JButton btnAnadir;

    private Usuario monitor;
    private AccesoBBDDLogin acceso;
    
    //LISTA DE ACTIVIDADES FIJAS
    private final List<String> actividadesDisponibles = Arrays.asList(
            "Sesión de entrenamiento",
            "Partido de baloncesto",
            "Partido de pádel",
            "Clase de natación",
            "Clase de pilates",
            "Partido de fútbol",
            "Entrenamiento de atletismo",
            "Partido de tenis"
        );

    public PanelNuevaActividad(Usuario monitor) {
        this.monitor = monitor;
        this.acceso = new AccesoBBDDLogin();
        inicializarComponentes();
    }

    

    public void inicializarComponentes() {
    	 setLayout(new BorderLayout());
         setBackground(new Color(230, 210, 210));

         JLabel titulo = new JLabel("Añade una nueva actividad", SwingConstants.CENTER);
         titulo.setFont(new Font("Arial", Font.BOLD, 16));
         titulo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
         add(titulo, BorderLayout.NORTH);

        //Panel general 
         JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 15));
         formPanel.setBackground(new Color(230, 210, 210));
         formPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

         JLabel lblMonitor = new JLabel("Monitor:");
         JLabel lblMonitorValor = new JLabel(monitor.getNombre() + " " + monitor.getApellidos());

         // Lista de actividades
         DefaultListModel<String> modeloLista = new DefaultListModel<>();
         for (String act : actividadesDisponibles) modeloLista.addElement(act);
         listaActividades = new JList<>(modeloLista);
         listaActividades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         JScrollPane scrollActividades = new JScrollPane(listaActividades);

         // Fecha (día, mes, año)
         comboDia = new JComboBox<>();
         comboMes = new JComboBox<>();
         comboAnio = new JComboBox<>();
         for (int i = 1; i <= 12; i++) comboMes.addItem(String.format("%02d", i));
         for (int i = 2025; i <= 2030; i++) comboAnio.addItem(String.valueOf(i));

         //Panel para los elementos de la fecha
         JPanel panelFecha = new JPanel(new GridLayout(2, 3));
         panelFecha.add(new JLabel("Día", SwingConstants.CENTER));
         panelFecha.add(new JLabel("Mes", SwingConstants.CENTER));
         panelFecha.add(new JLabel("Año", SwingConstants.CENTER));
         panelFecha.add(comboDia);
         panelFecha.add(comboMes);
         panelFecha.add(comboAnio);
         //Añadimos los action listeners a los combobox de mes y año
         comboMes.addActionListener(e -> actualizarDias());
         comboAnio.addActionListener(e -> actualizarDias());
         actualizarDias();

         // Hora
         comboHora = new JComboBox<>();
         for (int h = 9; h <= 20; h++) comboHora.addItem(String.format("%02d:00", h));

         // Sala asignada
         lblSalaAsignada = new JLabel("—");
         //Añadimos un listener a la lista
         listaActividades.addListSelectionListener(e -> mostrarSalaAsociada());

         // Añadir al formulario general
         formPanel.add(lblMonitor);
         formPanel.add(lblMonitorValor);
         formPanel.add(new JLabel("Actividad:"));
         formPanel.add(scrollActividades);
         formPanel.add(new JLabel("Fecha:"));
         formPanel.add(panelFecha);
         formPanel.add(new JLabel("Hora:"));
         formPanel.add(comboHora);
         formPanel.add(new JLabel("Sala asignada:"));
         formPanel.add(lblSalaAsignada);

         // Botón añadir
         btnAnadir = new JButton("Añadir");
         btnAnadir.setBackground(new Color(204, 0, 0));
         btnAnadir.setForeground(Color.WHITE);
         btnAnadir.setFont(new Font("Arial", Font.BOLD, 14));
         btnAnadir.setFocusPainted(false);

         JPanel botonPanel = new JPanel();
         botonPanel.setBackground(new Color(230, 210, 210));
         botonPanel.add(btnAnadir);

         add(formPanel, BorderLayout.CENTER);
         add(botonPanel, BorderLayout.SOUTH);
    }

    // Método para ajustar los días del mes dinámicamente
    private void actualizarDias() {
        //primero se debe seleccionar mes o año 
    	if (comboMes.getSelectedItem() == null || comboAnio.getSelectedItem() == null) return;

        //recogemos lo seleccioando en los combo box
        int mesSeleccionado = comboMes.getSelectedIndex() + 1; //como el combobox se indexa desde 0, se suma 1 para obtener el día real del mes (no hay día 0 del mes)
        int anioSeleccionado = Integer.parseInt((String) comboAnio.getSelectedItem());

        //Determinar cuantos días hay en cada mes
        int diasEnMes;
        switch (mesSeleccionado) {
            case 2:
                // Año bisiesto
                diasEnMes = ((anioSeleccionado % 4 == 0 && anioSeleccionado % 100 != 0) || (anioSeleccionado % 400 == 0)) ? 29 : 28;
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
                break;
        }
        
        //Obtener la fecha actual
        java.time.LocalDate hoy = java.time.LocalDate.now(); //declaramos hoy como la fecha actual
        int diaMinimo = 1;
        
        if(anioSeleccionado == hoy.getYear() && mesSeleccionado == hoy.getMonthValue()) {//Si el mes y el año corresponden al mes actual
        	diaMinimo = hoy.getDayOfMonth()+1; //el día mínimo para crear una actividad debe ser posterior a hoy 
        }

        comboDia.removeAllItems();//borra cualquier valor anterior
        for (int d = diaMinimo; d <= diasEnMes; d++) {
            comboDia.addItem(String.format("%02d", d));//llenamos el combo día con los días válidos
        }
    }

    // Getters
    public String getActividad() {
    	return listaActividades.getSelectedValue();
    }

    public String getFechaCompleta() {
        return comboAnio.getSelectedItem() + "-" +
               comboMes.getSelectedItem() + "-" +
               comboDia.getSelectedItem();
    }

    public String getHora() {
        return (String) comboHora.getSelectedItem();
    }

    

    public JButton getBtnAnadir() {
        return btnAnadir;
    }

    //método para mostrar la sala asociada a la actividad en el panel
    private void mostrarSalaAsociada() {
        String actividad = listaActividades.getSelectedValue();//obtenemos la actividad seleccionada de la lista
        if (actividad == null) return;

        try (Connection conn = acceso.getConexion()) {
            /*
             * obtenemos la sala asignada a la actividad seleccionada con el método
             *  correspondiente en AccesoBBDDLogin
             */
        	Sala sala = acceso.obtenerSalaPorNombreActividad(conn, actividad);
            if (sala != null) {
                lblSalaAsignada.setText(sala.getTipoSala());
            } else {
                lblSalaAsignada.setText("—");
            }
        } catch (Exception e) {
            lblSalaAsignada.setText("Error al cargar sala");
        }
    }
    
    
    public Sala getSalaAsociada() {
        String actividad = getActividad();
        if (actividad == null) return null;
        try (Connection conn = acceso.getConexion()) {
            return acceso.obtenerSalaPorNombreActividad(conn, actividad);
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean fechaEsValida() {
        String fechaStr = getFechaCompleta();
        LocalDate fechaSeleccionada = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return !fechaSeleccionada.isBefore(LocalDate.now());
    }
}
