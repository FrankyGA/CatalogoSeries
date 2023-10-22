package catalogoSeries;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Controlador implements WindowListener, ActionListener {

    Modelo modelo;
    VistaMenu vistaMenu;
    VistaCatalogo vistaCatalogo;
    //Iniciamos la serie actual al entrar en el catálogo
    int serieActual = 1;

    public Controlador(VistaMenu vistaMenu, Modelo modelo) {
        this.vistaMenu = vistaMenu;
        this.modelo = modelo;
        this.vistaMenu.addWindowListener(this);
        this.vistaMenu.btnEntrar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (vistaMenu != null && vistaMenu.isActive()) {
            if (e.getSource().equals(vistaMenu.btnEntrar)) {
            	//Al pulsar en el bóton entrar en el menú principal accedemos a la vista del catálogo
                vistaMenu.setVisible(false);
                vistaCatalogo = new VistaCatalogo();
                vistaCatalogo.addWindowListener(this);
                vistaCatalogo.btnNext.addActionListener(this);
                vistaCatalogo.btnBack.addActionListener(this);
                mostrarSerie(serieActual);
            }
        } else if (vistaCatalogo != null && vistaCatalogo.isActive()) {
        	//Control de botones para movernos por el catálogo
            if (e.getSource().equals(vistaCatalogo.btnNext)) {
                serieActual++;
                if (!mostrarSerie(serieActual)) {
                    vistaCatalogo.txaDescripcion.setText("No hay más registros.");
                    vistaCatalogo.btnNext.setEnabled(false);
                    vistaCatalogo.btnBack.setEnabled(true);
                }
            } else if (e.getSource().equals(vistaCatalogo.btnBack)) {
                serieActual--;
                mostrarSerie(serieActual);
                vistaCatalogo.btnNext.setEnabled(true);
                vistaCatalogo.btnBack.setEnabled(true);
                if(serieActual==1) {
                	vistaCatalogo.btnBack.setEnabled(false);
                }
            }
        }
    }
    /*Método para mostrar las series en el catálogo
    Usa los métodos del Modelo para mostrar los datos y la portada de cada serie y que estan guardados en
    la base de datos*/
    private boolean mostrarSerie(int id) {
        String serieTexto = modelo.mostrarSerieDatos(id);
        String imagePath = modelo.obtenerRutaImagen(id);

        if (serieTexto != null) {
            vistaCatalogo.txaDescripcion.setText(serieTexto);

            if (imagePath != null) {
                Image imagenPortada = Toolkit.getDefaultToolkit().getImage(imagePath);
                MediaTracker mediaTracker = new MediaTracker(vistaCatalogo);
                mediaTracker.addImage(imagenPortada, 0);

                try {
                    mediaTracker.waitForAll();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                vistaCatalogo.actualizarImagenPortada(imagenPortada);
            } else {
                vistaCatalogo.actualizarImagenPortada(null); // Puedes establecer una imagen por defecto
            }

            if (id == 1) {
                vistaCatalogo.btnBack.setEnabled(false);
            }
        }
        return serieTexto != null;
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    //Añade funcionalidad a las ventanas para cerrarlas
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
