/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.cronometro.cronometro;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import proyecto.cronometro.cronometro.model.DAO.cronometroDAO;
import proyecto.cronometro.cronometro.model.Entity.Cronometro;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CronometroController implements Runnable, Initializable {

	@FXML
	private ScrollPane ScrollPane;
	@FXML
	private Button btn_iniciar;
	@FXML
	private Button addVuelta;
	@FXML
	private Button btn_reset;
	@FXML
	private Text txtHora;
	@FXML
	private Text txtMilisegundos;
	@FXML
	private Text txtMinutos;
	@FXML
	private Text txtSegundos;
	@FXML
	private VBox vueltasLayout;

	private Cronometro cr;
	private Cronometro ListaVueltas;
	private cronometroDAO crDao;
	private List<Cronometro> listvuelta = new ArrayList<Cronometro>();
	private ObservableList<Cronometro> listVueltCrono = FXCollections.observableArrayList(listvuelta);
	private boolean pause;
	private boolean suspend;
	Thread ini = new Thread(this);
	int thora;
	int tmin;
	int tsec;
	int tmsec;

	@FXML
	public void iniciar() {
		isRunning(true);
		try {
			if (cr == null) {
				if (isRunning(true)) {
					cr = new Cronometro();
					ini = new Thread(this);
					incrementTime();
					isRunning(true);
				} else {
					pausaTime();
				}
			} else {
				renaudTime();
				cr = null;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@FXML
	public void reset() {
		cr = null;
		isRunning(false);
		ini.interrupt();
		txtHora.setText("00");
		txtMinutos.setText("00");
		txtSegundos.setText("00");
		txtMilisegundos.setText("000");
	}

	public void incrementTime() {
		cr = new Cronometro();
		ini = new Thread(this);
		ini.start();
		isRunning(true);
	}

	public synchronized void pausaTime() {
		pause = true;
		suspend = false;
		isRunning(false);
		int hora = Integer.parseInt(txtHora.getText());
		int min = Integer.parseInt(txtMinutos.getText());
		int sec = Integer.parseInt(txtSegundos.getText());
		int msec = Integer.parseInt(txtMilisegundos.getText());
		btn_iniciar.setText("Parar");
		cr = new Cronometro(hora, min, sec, msec);
		notify();
	}

	public synchronized void renaudTime() {
		suspend = false;
		btn_iniciar.setText("Reanudar");
		thora = cr.getHoras();
		tmin = cr.getMinutos();
		tsec = cr.getSegundos();
		tmsec = cr.getMiliSegundos();
		cr = new Cronometro(thora, tmin, tsec, tmsec);
		ini = new Thread(this);
		ini.start();
		notify();
	}

	/**
	 * devuelve un booleano para comprobar si esta corriendo
	 * 
	 * @param iscorriendo true o false
	 * @return
	 */
	public boolean isRunning(boolean iscorriendo) {
		return iscorriendo;
	}

	@FXML
	void addvueltacronometro() {
		thora = Integer.parseInt(txtHora.getText());
		tmin = Integer.parseInt(txtMinutos.getText());
		tsec = Integer.parseInt(txtSegundos.getText());
		tmsec = Integer.parseInt(txtMilisegundos.getText());
		ListaVueltas = new Cronometro(thora, tmin, tsec, tmsec);
		crDao = new cronometroDAO(thora, tmin, tsec, tmsec);
		listvuelta.add(ListaVueltas);
		crDao.insertTime(ListaVueltas);
		listVueltCrono.addAll(listvuelta);
		Cronometro caux = new Cronometro();
		for (int i = 0; i < listVueltCrono.size(); i++) {
			FXMLLoader fxmloader = new FXMLLoader();

			fxmloader.setLocation(getClass().getResource("itemCronometro.fxml"));
			try {
				HBox apane = fxmloader.load();
				itemCronometroController icc = fxmloader.getController();
				caux = ((Cronometro) listVueltCrono.toArray()[i]);
				icc.setData(caux);
				vueltasLayout.getChildren().add(apane);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		try {
			while (isRunning(true)) {
				Thread.sleep(4);
				int mili;
				mili = cr.getMiliSegundos();
				mili += 4;
				cr.setMiliSegundos(mili);
				if (cr.getMiliSegundos() > 999) {
					cr.setMiliSegundos(000);
					int sec;
					sec = cr.getSegundos();
					sec++;
					cr.setSegundos(sec);
					if (cr.getSegundos() > 59) {
						cr.setSegundos(00);
						int min;
						min = cr.getMinutos();
						min++;
						cr.setMinutos(min);
						if (cr.getMinutos() > 59) {
							cr.setMinutos(00);
							int hor;
							hor = cr.getHoras();
							hor++;
							cr.setHoras(hor);
							if (cr.getHoras() > 24) {
								cr.setMiliSegundos(000);
								cr.setSegundos(00);
								cr.setMinutos(00);
								cr.setHoras(00);
							}
						}
					}
				}

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						if (cr.getMiliSegundos() < 100) {
							txtMilisegundos.setText("0" + cr.getMiliSegundos());

							if (cr.getMiliSegundos() < 10) {
								txtMilisegundos.setText("00" + cr.getMiliSegundos());
							}
						} else {
							txtMilisegundos.setText(String.valueOf(cr.getMiliSegundos()));
						}
						if (cr.getSegundos() < 10) {
							txtSegundos.setText("0" + cr.getSegundos());
						} else {
							txtSegundos.setText(String.valueOf(cr.getSegundos()));
						}

						if (cr.getMinutos() < 10) {
							txtMinutos.setText("0" + cr.getMinutos());
						} else {
							txtMinutos.setText(String.valueOf(cr.getMinutos()));
						}

						if (cr.getHoras() < 10) {
							txtHora.setText("0" + cr.getHoras());
						} else {
							txtHora.setText(String.valueOf(cr.getHoras()));
						}
					}
				});
				System.out.println("horas: " + cr.horas + "minutos: " + cr.minutos + "segunos: " + cr.segundos
						+ "milisec: " + cr.miliSegundos);
			}
		} catch (Exception e) {

		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listVueltCrono.addAll(listvuelta);
	}

}
