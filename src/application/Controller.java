package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.VisuTree;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/*********************************************************************
 * Controller Klasse für die GUI
 * 
 * @author Sven Böhrnsen
 *********************************************************************/

public class Controller {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML
	private AnchorPane apDraw;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="taHistory"
	private TextArea taHistory; // Value injected by FXMLLoader

	@FXML // fx:id="bSearch"
	private Button bSearch; // Value injected by FXMLLoader

	@FXML // fx:id="bInsert"
	private Button bInsert; // Value injected by FXMLLoader

	@FXML // fx:id="bDelete"
	private Button bDelete; // Value injected by FXMLLoader

	@FXML // fx:id="tfInput"
	private TextField tfInput; // Value injected by FXMLLoader

	@FXML // fx:id="bPreorder"
	private Button bPreorder; // Value injected by FXMLLoader

	@FXML // fx:id="bInorder"
	private Button bInorder; // Value injected by FXMLLoader

	@FXML // fx:id="bPostorder"
	private Button bPostorder; // Value injected by FXMLLoader

	@FXML // fx:id="bTreeHigh"
	private Button bTreeHigh; // Value injected by FXMLLoader

	@FXML // fx:id="bClearTree"
	private Button bClearTree; // Value injected by FXMLLoader

	@FXML // fx:id="tOutput"
	private Text tOutput; // Value injected by FXMLLoader

	// ein neu erstellten Baum beim Start
	private VisuTree bsTree;

	private Alerts alert = new Alerts();

	/*********************************************************************
	 * Suchen Operation auf der GUI aufrufen.
	 * 
	 * @param event
	 *********************************************************************/
	@FXML
	void searchNode(ActionEvent event) {
		if (!tfInput.getText().matches("^-?[1-9][0-9]{0,2}")) {
			alert.WrongValue();
			tfInput.clear();
		} else if (bsTree.search(Integer.parseInt(tfInput.getText()))) {
			taHistory.appendText("- Wert " + tfInput.getText() + " gefunden.\n");
			tfInput.clear();
		} else {
			taHistory.appendText("- Wert " + tfInput.getText() + " nicht im Baum enthalten.\n");
			tfInput.clear();
		}

	}

	/*********************************************************************
	 * Einfügen Operation auf der GUI aufrufen.
	 * 
	 * @param event
	 *********************************************************************/
	@FXML
	void addNode(ActionEvent event) {
		if (!tfInput.getText().matches("^-?[1-9][0-9]{0,2}")) {
			alert.WrongValue();
			tfInput.clear();
		} else if (bsTree.getTreeHeight() <= 5) { // TODO: Fehler finden
			bsTree.add(Integer.parseInt(tfInput.getText()));
			taHistory.appendText("- Wert " + tfInput.getText() + " hinzugefügt.\n");
			tfInput.clear();
		} else {
			alert.MaxHeight();
			tfInput.clear();
		}
	}

	/*********************************************************************
	 * Entfernen Operation auf der GUI aufrufen.
	 * 
	 * @param event
	 *********************************************************************/
	@FXML
	void removeNode(ActionEvent event) {
		if (!tfInput.getText().matches("^-?[1-9][0-9]{0,2}")) {
			alert.WrongValue();
			tfInput.clear();
		} else if (bsTree.delete(Integer.parseInt(tfInput.getText()))) {
			taHistory.appendText("- Wert " + tfInput.getText() + " entfernt.\n");
			tfInput.clear();

		}

	}

	/*********************************************************************
	 * Ausgabe der Höhe auf der GUI ausgeben.
	 * 
	 * @param event
	 *********************************************************************/
	@FXML
	void highTree(ActionEvent event) {
		taHistory.appendText("- Die Höhe des Baumes: " + (bsTree.getTreeHeight() -1) + "\n");
	}

	/*********************************************************************
	 * Ausgabe der In-Order auf der GUI ausgeben.
	 * 
	 * @param event
	 *********************************************************************/
	@FXML
	void inorder(ActionEvent event) {
		bsTree.setInorder();
		taHistory.appendText("- Ausgabe der Inorder\n");
		tOutput.setText("In-Order: [ " + bsTree.printTree() + "]");
	}

	/*********************************************************************
	 * Ausgabe der Post-Order auf der GUI ausgeben.
	 * 
	 * @param event
	 *********************************************************************/
	@FXML
	void postorder(ActionEvent event) {
		bsTree.setPostorder();
		taHistory.appendText("- Ausgabe der Postorder\n");
		tOutput.setText("Post-Order: [ " + bsTree.printTree() + "]");
	}

	/*********************************************************************
	 * Ausgabe der Pre-Order auf der GUI ausgeben.
	 * 
	 * @param event
	 *********************************************************************/
	@FXML
	void preorder(ActionEvent event) {
		bsTree.setPreorder();
		taHistory.appendText("- Ausgabe der Preorder\n");
		tOutput.setText("Pre-Order: [ " + bsTree.printTree() + "]");

	}

	/*********************************************************************
	 * Bestimmen des minimalen Wertes im Baum. Ausgabe auf der GUI.
	 *
	 * @param event
	 *********************************************************************/
	@FXML
	void MinValue(ActionEvent event) {
		bsTree.getMin();
		taHistory.appendText("- Minimum: " + bsTree.printTree() + "\n");
	}

	/*********************************************************************
	 * Bestimmen des maximalen Wertes im Baum. Ausgabe auf der GUI.
	 *
	 * @param event
	 *********************************************************************/
	@FXML
	void maxValue(ActionEvent event) {
		bsTree.getMax();
		taHistory.appendText("- Maximum: " + bsTree.printTree() + "\n");
	}

	/*********************************************************************
	 * Leert das Canvas Element in der AnchorPane.
	 * 
	 * @param event
	 *********************************************************************/
	@FXML
	void clearTree(ActionEvent event) {
		if (alert.ClearAlert()) {
			bsTree.clear();
			taHistory.clear();
			taHistory.appendText("- Baum geleert!\n");
		}

	}

	/*********************************************************************
	 * Grafikgläche als Bild gespeichern.
	 * 
	 * @param event
	 *********************************************************************/
	@FXML
	void saveAsPicture(ActionEvent event) {
		bsTree.saveImage();
	}

	@FXML
	void initialize() {

		// Erstellung des Suchbaumes
		bsTree = new VisuTree();
		// Zuweisung der Canvas an das AnchorPane Element
		apDraw.getChildren().add(bsTree);

		// Festlegung der Grenzen der Canvas
		bsTree.widthProperty().bind(apDraw.widthProperty());
		bsTree.heightProperty().bind(apDraw.heightProperty());
	}

}