package vista;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VistaLogin vista= new VistaLogin("App Paneles");
		VistaMenuPrincipalMonitor vista2= new VistaMenuPrincipalMonitor();
		VistaMenuPrincipalUsuario vista3= new VistaMenuPrincipalUsuario();
		vista3.hacerVisible();
	}

}
