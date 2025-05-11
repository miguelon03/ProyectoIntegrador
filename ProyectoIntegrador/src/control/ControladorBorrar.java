package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ControladorBorrar implements ActionListener {

	 private JButton botonBorrar;

	    public ControladorBorrar(JButton botonBorrar) {
	        this.botonBorrar = botonBorrar;
	        this.botonBorrar.addActionListener(this);
	    }

	    @Override
	    public void actionPerformed(ActionEvent e) {
	        JOptionPane.showMessageDialog(null, "Has pulsado el botón BORRAR");
	    }
	

}
