package catalogoSeries;

public class Principal {
	public static void main(String[] args) {
		
		VistaMenu vistaMenu = new VistaMenu();
		Modelo modelo = new Modelo();
		new Controlador(vistaMenu, modelo);
		//VistaCatalogo vistaCatalogo = new VistaCatalogo();
	}
}
