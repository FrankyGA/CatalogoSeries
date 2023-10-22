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
    int serieActual = 1;

    public VistaCatalogo() {

        setLayout(null);

        setTitle("Catálogo de series");
        setSize(730, 665);
        setLocationRelativeTo(null);
        setResizable(false);

        tool = getToolkit();
        fondo = tool.getImage("img/catalogo.jpg");

        txaDescripcion.setBackground(new Color(255, 204, 204));
        txaDescripcion.setBounds(360, 200, 360, 410);
        txaDescripcion.setEditable(false);

        btnNext.setBounds(580, 620, 60, 40);
        btnNext.setBackground(new Color(0, 102, 102));
        btnNext.setForeground(new Color(255, 255, 255));
        btnNext.setFont(new Font("Arial", Font.BOLD, 16));
        btnBack.setBounds(110, 620, 60, 40);
        btnBack.setBackground(new Color(0, 102, 102));
        btnBack.setForeground(new Color(255, 255, 255));
        btnBack.setFont(new Font("Arial", Font.BOLD, 16));

        canvasPortada.setBounds(50, 200, 300, 410);
        add(canvasPortada);

        txaDescripcion.setFont(font2);

        add(txaDescripcion);
        add(btnNext);
        add(btnBack);

        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(fondo, 2, 5, this);
    }
    //Método para cargar las imagenes en el lienzo
    public void actualizarImagenPortada(Image imagen) {
        Graphics g = canvasPortada.getGraphics();
        g.drawImage(imagen, 0, 0, canvasPortada.getWidth(), canvasPortada.getHeight(), this);
    }
}

