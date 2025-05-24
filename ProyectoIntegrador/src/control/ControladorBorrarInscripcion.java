package control;

import bbdd.AccesoBBDDLogin;
import modelo.Actividad;
import modelo.Usuario;
import vista.PanelMisActividadesUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class ControladorBorrarInscripcion implements ActionListener {
	
	
	    //Atributos
	    private PanelMisActividadesUsuario panel;
	    private Usuario usuario;
	    
	    //Constructor
	    public ControladorBorrarInscripcion(PanelMisActividadesUsuario panel, Usuario usuario) {
	    	this.panel=panel;
	    	this.usuario=usuario;
	    	panel.getBtnBorrarInscripcion().addActionListener(this);
	    }

		@Override
		public void actionPerformed(ActionEvent e) {
			int index= panel.getListaActividades().getSelectedIndex();
			List<Actividad> actividades = panel.getActividades();
			
			if(index ==-1) {
				JOptionPane.showMessageDialog(panel, "Selecciona una actividad para borrar tu inscripción");
				return;
			}
			
			Actividad act = actividades.get(index);
			
			//Mostramos un JOptionPane para confirmar si el usuario quiere borrar la inscripcion
			int confirm = JOptionPane.showConfirmDialog(panel, "¿Seguro que quieres borrar tu inscripcion en:\n"+ act.getNombreActividad()+"?",
					"Confirmar",JOptionPane.YES_NO_OPTION);
			
			
			//Si el usuario confirma en ese JOption que quiere borrar la inscripcion, se procede con el borrado
			if(confirm == JOptionPane.YES_OPTION) {
				AccesoBBDDLogin acceso = new AccesoBBDDLogin();
				Connection conn = acceso.getConexion();
				
				if(conn != null) {
					boolean borrado = acceso.borrarInscripcionUsuario(conn, usuario.getIdUsuario(), act.getIdActividad());
					acceso.terminarConexion(conn);
				
				
				//Si se ha borrado correctamente, eliminamos la actividad de las actividades del usuario
				if(borrado) {
					panel.getModeloLista().remove(index);
					actividades.remove(index);
					JOptionPane.showMessageDialog(panel, "La inscripción fue cancelada con éxito.");
					
				}else {
					JOptionPane.showMessageDialog(panel, "No se pudo cancelar la inscripción");
				}
			}
		}
	}
}
