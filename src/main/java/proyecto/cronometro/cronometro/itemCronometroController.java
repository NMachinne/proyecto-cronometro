package proyecto.cronometro.cronometro;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import proyecto.cronometro.cronometro.model.Entity.Cronometro;

public class itemCronometroController {
	@FXML
    private Label centesimasSegundos;

    @FXML
    private Label horas;

    @FXML
    private Label minutos;

    @FXML
    private Label segundos;

    @FXML
    private Label vuelta;
    
    public void setData(Cronometro crono) {
    	try {
    		vuelta.setText("Vuelta  " + crono.getVuelta()+"");
    		horas.setText(crono.getHoras()+"");
    		minutos.setText(crono.getMinutos() + "");
    		segundos.setText(crono.getSegundos() + "");
    		centesimasSegundos.setText(crono.getMiliSegundos()+"");
		} catch (Exception e) {
			// TODO: handle exception
		}
		

	}
}
