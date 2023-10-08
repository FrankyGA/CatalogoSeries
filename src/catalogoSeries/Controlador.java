package catalogoSeries;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Controlador implements WindowListener, ActionListener {
	
	Modelo modelo;
	VistaMenu vistaMenu;
	VistaCatalogo vistaCatalogo;
	
	int serieActual = 1; //Inicializamos en la primera serie
	
	public Controlador(VistaMenu vistaMenu, Modelo modelo){
		
		this.vistaMenu = vistaMenu;
		this.modelo = modelo;
		//Agregar controladores
		this.vistaMenu.addWindowListener(this);
		this.vistaMenu.btnEntrar.addActionListener(this);
			
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	    if (vistaMenu != null && vistaMenu.isActive()) {
	    	
	        if (e.getSource().equals(vistaMenu.btnEntrar)) {
	        	
	            vistaMenu.setVisible(false); //Oculta VistaMenu
	            vistaCatalogo = new VistaCatalogo();
	            //Agregar controladores
	            vistaCatalogo.addWindowListener(this);
	            vistaCatalogo.btnNext.addActionListener(this);
	            vistaCatalogo.btnBack.addActionListener(this);
	            mostrarSerie(serieActual); //Mostrar la serie actual
	            //Muestra la imagen de la primera serie al acceder al catálogo
	            //String imagenPrimera = "img/" + serieActual + ".jpg";
	            //vistaCatalogo.actualizarImagenPortada(imagenPrimera);
	            String imagePath = modelo.obtenerPortadaSerie1();
	            vistaCatalogo.actualizarImagenPortada(imagePath);
	          
	            vistaCatalogo.actualizarImagenPortada("img/1.jpg");
	            System.out.println("Clicked catalogo");//Para comprobar funcionalidad correcta del botón
	        } 
	    } 
	    else if (vistaCatalogo != null && vistaCatalogo.isActive()) {
	    	//Si pulsamos en el botón Next accede a la siguiente serie del catálogo
	        if (e.getSource().equals(vistaCatalogo.btnNext)) {
	        	
	            serieActual++;
	            mostrarSerie(serieActual);
	      
	        } 
	        //Si pulsamos en el botón Back accede a la serie anterior del catálogo
	        else if (e.getSource().equals(vistaCatalogo.btnBack)) {
	        	
	            serieActual--;
	            mostrarSerie(serieActual);
	            
	        }
	    }
	}
	//Método privado de controlador para insertar los datos de la serie que se va a mostrar en la vista
	//Usa el método del modelo para sacar los datos de la base de datos
    private void mostrarSerie(int id) {
        String serieTexto = modelo.mostrarSerieDatos(id);

        //Actualiza la imagen de la portada
        String imagePath = "img/" + id + ".jpg";
        vistaCatalogo.actualizarImagenPortada(imagePath);
            
        if (serieTexto != null) {
            vistaCatalogo.txaDescripcion.setText(serieTexto);

            // Verifica si es la primera o última serie para controlar el catálogo
            if (serieActual == 1) {
                vistaCatalogo.txaDescripcion.append("\n\nEsta es la primera serie.");
            } else {
                String siguienteSerie = modelo.mostrarSerieDatos(id + 1);
                if (siguienteSerie == null) {
                    vistaCatalogo.txaDescripcion.append("\n\nEsta es la última serie.");
                }
            }

            
        }
    }
	@Override
	public void windowOpened(WindowEvent e){}
	@Override
	/*public void windowClosing(WindowEvent e) {
        if (vistaMenu != null && vistaMenu.isActive()) {
            vistaMenu.setVisible(false);
        } else if (vistaCatalogo != null && vistaCatalogo.isActive()) {
            vistaCatalogo.setVisible(false);
        } else {
            System.exit(0);
        }
    }*/
	//instanceof para verificar si se genero el evento
	public void windowClosing(WindowEvent e) {
	    if (e.getSource() instanceof VistaMenu) {
	        vistaMenu.setVisible(false);
	    } else if (e.getSource() instanceof VistaCatalogo) {
	        vistaCatalogo.setVisible(false);
	    } else {
	        System.exit(0);
	    }
	}

	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowDeactivated(WindowEvent e) {}
}
