package application;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

/*********************************************************************
 * Die Klasse beinhaltet mehrere Rückmeldungen für das Programm Yggdrasil.
 * 
 * @author Sven Böhrnsen
 *********************************************************************/
public class Alerts {

	/*********************************************************************
	 * Rückmeldung, wenn die Höhe des Suchbaumes überschritten wird.
	 *********************************************************************/
	public void MaxHeight() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Maximale Baumhöhe erreicht!");
		alert.setContentText("Die Darstellung von einem Suchbaum kann die Höhe von 4 nicht überschreiten!");

		alert.showAndWait();

	}

	/*********************************************************************
	 * Rückmeldung, wenn die Eingabe im Textfeld was anderes als ganze Zahlen ist.
	 *********************************************************************/
	public void WrongValue() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Die Eingabe muss ein Wert zwischen -999 und 999 betragen!");

		alert.showAndWait();

	}

	/*********************************************************************
	 * Rückmeldung, wenn der Schlüsselwert sich nicht im Suchbaum befindet.
	 *********************************************************************/
	public void NoKey() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Der Schlüsselwert ist nicht im Baum enthalten!");

		alert.showAndWait();

	}

	/*********************************************************************
	 * Rückmeldung, wenn der Schlüsselwert schon im Baum vorhanden ist.
	 *********************************************************************/
	public void DoubleKey() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Der Schlüsselwert ist schon im Baum enthalten!");

		alert.showAndWait();

	}

	/*********************************************************************
	 * Rückmeldung, wenn die Zeichenfläche geleert werden soll.
	 *
	 * @return 
	 *********************************************************************/
	public boolean ClearAlert() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Baum leeren");
		alert.setHeaderText(null);
		alert.setContentText(
				"Möchten Sie wirklich den Baum leeren?\nDie Entscheidung kann nicht rückgängig gemacht werden!");

		ButtonType buttonTypeOne = new ButtonType("JA");
		ButtonType buttonTypeCancel = new ButtonType("NEIN", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			return true;
		}
		return false;
	}

}
