package catalogoSeries;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class VistaMenu extends Frame {
	
	private static final long serialVersionUID = 1L;
	Button btnEntrar = new Button("Entrar");
	//Fondo
	Image backgroundImage;
	Toolkit tool;
	
	public VistaMenu() {
		
		setLayout(null);
		//Añadir botón entrar
		btnEntrar.setBounds(210, 400, 310, 40);
		btnEntrar.setFont(new Font("Arial", Font.BOLD, 24));
		btnEntrar.setBackground(new Color(0, 102, 102));
		btnEntrar.setForeground(new Color(255, 255, 255));
		
		add(btnEntrar);
		
		setTitle("Menu catálogo");
		setSize(730, 670);
		setLocationRelativeTo(null);
		setResizable(false);
		
		tool = getToolkit();
		backgroundImage = tool.getImage("img/catalogodeseries.jpg");
		
		setVisible(true);
	}
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(backgroundImage, 5, 15,this);
	}
}
