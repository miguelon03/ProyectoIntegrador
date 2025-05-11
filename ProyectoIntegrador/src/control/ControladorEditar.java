package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ControladorEditar implements ActionListener {
	
	 private JButton botonEditar;

	    public ControladorEditar(JButton botonEditar) {
	        this.botonEditar = botonEditar;
	        this.botonEditar.addActionListener(this);
	    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 JOptionPane.showMessageDialog(null, "Has pulsado el botón EDITAR");
	}



}
