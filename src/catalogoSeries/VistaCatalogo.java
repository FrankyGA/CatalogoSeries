package catalogoSeries;

import java.awt.*;


public class VistaCatalogo extends Frame {
    private static final long serialVersionUID = 1L;
    
    TextArea txaDescripcion = new TextArea(180, 210);
    Button btnNext = new Button("NEXT");
    Button btnBack = new Button("BACK");
    
    Font font2 = new Font("Forte", Font.BOLD, 15);
    
    Image fondo;
    Toolkit tool;
    
    Canvas canvasPortada = new Canvas();
    int serieActual = 1; // Inicializamos en la primera serie
    
    public VistaCatalogo() {
    	
        setLayout(null);
        
        //Ventana
        setTitle("Catálogo de series");
        setSize(730, 665);
        setLocationRelativeTo(null);
        setResizable(false);
    
        tool = getToolkit();
        //Imagen de fondo
        fondo = tool.getImage("img/catalogo.jpg");
        
        //Configuración de Text area
        txaDescripcion.setBackground(new Color(255, 204, 204)); // Rojo claro
        txaDescripcion.setBounds(360, 200, 360, 410); // Alineado a la derecha
        txaDescripcion.setEditable(false);
        
        //Configuración de botones
        btnNext.setBounds(580, 620, 60, 40);
        btnNext.setBackground(new Color(0, 102, 102));
        btnNext.setForeground(new Color(255, 255, 255));
        btnNext.setFont(new Font("Arial", Font.BOLD, 16));
        btnBack.setBounds(110, 620, 60, 40);
        btnBack.setBackground(new Color(0, 102, 102));
        btnBack.setForeground(new Color(255, 255, 255));
        btnBack.setFont(new Font("Arial", Font.BOLD, 16));
        
        //Lienzo para poner las fotos
        canvasPortada.setBounds(50, 200, 300, 410);
        add(canvasPortada);
        
        txaDescripcion.setFont(font2);
        
        add(txaDescripcion);
        add(btnNext);
        add(btnBack);
        
        setVisible(true);
    }
    //Pinta fondo de la ventana
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(fondo, 2, 5, this);
    }
    
    // Método para actualizar la imagen de la portada
    public void actualizarImagenPortada(String imagePath) {
    	//Carga la imagen de la portada
        Image imagenPortada = Toolkit.getDefaultToolkit().getImage(imagePath);
        MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(imagenPortada, 10);
        
        //Control de errores
        try {
            mediaTracker.waitForAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        //Dibuja la imagen en el lienzo
        Graphics g = canvasPortada.getGraphics();
        g.drawImage(imagenPortada, 0, 0, 300, 410, this);
    }
}
