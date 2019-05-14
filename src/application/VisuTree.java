package application;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import tree.*;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import forms.*;

/*********************************************************************
 * Klasse, um einen Suchbaum und dessen Operationen visuell darzustellen.
 * Darüber hinaus werden hier auch die Operationen und Traversierungen
 * gesteuert.
 * 
 * @author Sven Böhrnsen
 *********************************************************************/
public final class VisuTree extends Canvas {

	// Ein Suchbaum erstellen
	private BinaryTree tree;

	/*********************************************************************
	 * Konstruktor für die Erstellung eines Baumes
	 *********************************************************************/
	public VisuTree() {
		this.tree = new BinaryTree();

		drawTree();

	}

	/*********************************************************************
	 * Diese Methode zeichnet die Knoten und Katen in das Canvas Element.
	 *********************************************************************/
	public void drawTree() {

		GraphicsContext gc = getGraphicsContext2D();

		// Leert die Canvas
		gc.clearRect(0, 0, getWidth(), getHeight());

		// Zeichnet alle Knoten und Kanten im Baum
		if (tree.getRoot() != null) {

			// Zeichne die Kanten
			drawLines(gc, tree.getRoot(), 0, this.getWidth(), 0, this.getHeight() / 5);
			// Zeichne die Knoten
			drawCircles(gc, tree.getRoot(), 0, this.getWidth(), 0, this.getHeight() / 5);
		}
	}

	/*********************************************************************
	 * Zeichnet alle Knoten in den visuellen Suchbaum. 
	 * 
	 * @param gc - Grafikelemente für die Kreise und Linien
	 * @param node - Startknoten im Suchbaum
	 * @param xFrom - Startpunkt auf der X-Koordinate
	 * @param xTo - Endpunkt auf der X-Koordinate
	 * @param yFrom - Startpunkt auf der Y-Koordinate
	 * @param yTo E- ndpunkt auf der Y-Koordinate
	 *********************************************************************/
	protected void drawCircles(GraphicsContext gc, Node node, double xFrom, double xTo, double yFrom, double yTo) {

		Circle newNode = null;
		Point2D position = new Point2D((xFrom + xTo) / 2, yFrom + yTo / 2);

		// hervorgehobene Knoten
		if (node.getHighlight() || node.getKey() == newNode) {
			newNode = null; 
			node.setHighlight(false); 
			node.getKey().setHighlight(true);
			node.getKey().setPosition(position);

			// Keine Hervorhebung
		} else {
			node.getKey().setHighlight(false);
			node.getKey().setPosition(position);
		}

		// Zeichnet den Wurzelknoten
		node.getKey().drawOnCanvas(gc);

		// Zeichnet die Knoten aus dem Linken Teilbaum
		if (node.getLeftChild() != null) {
			drawCircles(gc, node.getLeftChild(), xFrom, (xFrom + xTo) / 2, yFrom + yTo, yTo);
		}
		// Zeichnet die Knoten aus dem rechten Teilbaum
		if (node.getRightChild() != null) {
			drawCircles(gc, node.getRightChild(), (xFrom + xTo) / 2, xTo, yFrom + yTo, yTo);
		}
	}

	/*********************************************************************
	 * Zeichnet für alle Knoten im Suchbaum die entsprechende Kante.
	 * 
	 * @param gc - Grafikelemente für die Kreise und Linien
	 * @param node - Startknoten im Suchbaum
	 * @param xFrom - Startpunkt auf der X-Koordinate
	 * @param xTo - Endpunkt auf der X-Koordinate
	 * @param yFrom - Startpunkt auf der Y-Koordinate
	 * @param yTo E- ndpunkt auf der Y-Koordinate
	 *********************************************************************/
	protected void drawLines(GraphicsContext gc, Node node, double xFrom, double xTo, double yFrom, double yTo) {

		Point2D fromNode;
		Point2D toNode;
		Line newLine = new Line();

		// Wenn ein linker Kindsknoten existiert
		if (node.getLeftChild() != null) {
			newLine.setHighlight(false);

			// überprüft, ob die Kante hervorgehoben werden soll.
			if (node.getLeftChild().getHighlight()) {
				newLine.setHighlight(true);
			}

			// Bestimmung des Starts und Ende der Linie
			fromNode = new Point2D(((xFrom + xTo) / 2), yFrom + yTo / 2);
			toNode = new Point2D(((xFrom + (xFrom + xTo) / 2) / 2), yFrom + yTo + yTo / 2);
			newLine.setPosition(fromNode, toNode);
			newLine.drawOnCanvas(gc);// Zeichne Linie

			// Wiederhole das für alle Knoten im linken Teilbaum
			drawLines(gc, node.getLeftChild(), xFrom, (xFrom + xTo) / 2, yFrom + yTo, yTo);
		}

		// Wenn ein rechter Kindsknoten existiert
		if (node.getRightChild() != null) {
			newLine.setHighlight(false);

			// überprüft, ob die Kante hervorgehoben werden soll.
			if (node.getRightChild().getHighlight()) {
				newLine.setHighlight(true);
			}

			// Bestimmung des Starts und Ende der Linie
			fromNode = new Point2D((xFrom + xTo) / 2, yFrom + yTo / 2);
			toNode = new Point2D((xTo + (xFrom + xTo) / 2) / 2, yFrom + yTo + yTo / 2);
			newLine.setPosition(fromNode, toNode);
			newLine.drawOnCanvas(gc);// Zeichne Linie

			// Wiederhole das für alle Knoten im rechten Teilbaum
			drawLines(gc, node.getRightChild(), (xFrom + xTo) / 2, xTo, yFrom + yTo, yTo);
		}
	}

	/*********************************************************************
	 * Methode um die Suchen Operation aufzurufen.
	 * 
	 * @param key - Schlüsselwert im Suchbaum
	 * @return false: Suche erfolglos; true: OK
	 *********************************************************************/
	public boolean search(int key) {

		try {
			tree.searchNode(key);
		} catch (NullPointerException e) {
			// Farbe zurücksetzen
			tree.setResetColor(tree.getRoot());
			return false;
		}

		drawTree();
		return true;
	}

	/*********************************************************************
	 * Methode um die Einfügen Operation aufzurufen. Beim ausführen wird der Knoten
	 * direkt als neuer Kreis gezeichnet.
	 * 
	 * @param key - Neuer Schlüsselwert
	 *********************************************************************/
	public void add(int key) {
		Circle newNode = new Circle(key);
		tree.addNode(newNode);

		drawTree();
	}

	/*********************************************************************
	 * Methode um die Entfernen Operation aufzurufen. Beim ausführen wird der
	 * Kreis des Knotens entfernt.
	 * 
	 * @param key - Schlüsselwert im Suchbaum
	 * @return false: Entfernen erfolglos; true: OK
	 *********************************************************************/
	public boolean delete(int key) {
		if (tree.removeNode(key)) {
			drawTree();
			return true;
		}
		return false;
	}

	/*********************************************************************
	 * Methode um die Pre-Order Traversierung aufzurufen
	 *********************************************************************/
	public void setPreorder() {
		tree.setPreorder(tree.getRoot());
	}

	/*********************************************************************
	 * Methode um die In-Order Traversierung aufzurufen
	 *********************************************************************/
	public void setInorder() {
		tree.setInorder(tree.getRoot());
	}

	/*********************************************************************
	 * Methode um die Post-Prder Traversierung aufzurufen
	 *********************************************************************/
	public void setPostorder() {
		tree.setPostorder(tree.getRoot());
	}

	/*********************************************************************
	 * Methode um den Minimum Methode mit dem übergebenden Baum aufzurufen
	 *********************************************************************/
	public void getMin() {
		tree.minimumNode(tree.getRoot());
		drawTree();
	}

	/*********************************************************************
	 * Methode um den Maximum Methode mit dem übergebenden Baum aufzurufen
	 *********************************************************************/
	public void getMax() {
		tree.maximumNode(tree.getRoot());
		drawTree();
	}

	/*********************************************************************
	 * Methode, um die Traversierung in Textform auszugeben.
	 * 
	 * @return Zahlen-Reihenfolge als String
	 *********************************************************************/
	public String printTree() {

		StringBuilder outputString = new StringBuilder();
		while (tree.hasNext()) {
			outputString.append(tree.next()).append(" ");
		}

		return outputString.toString();
	}

	/*********************************************************************
	 * Diese Methode leert das Canvas Element.
	 *********************************************************************/
	public void clear() {
		tree.clearCanvas();
		getGraphicsContext2D().clearRect(0, 0, getWidth(), getHeight());
	}

	/*********************************************************************
	 * Getter Methode um die Höhe eines Baumes zu bestimmen
	 * 
	 * @return Die Höhe des Suchbaumes
	 *********************************************************************/
	public int getTreeHeight() {
		return tree.getHeight(tree.getRoot());
	}

	/*********************************************************************
	 * Methode, um den grafischen Bereich als Bild zu speichern
	 *********************************************************************/
	public void saveImage() {
		FileChooser fileChooser = new FileChooser();

		// Bestimmung der Endung
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG-Bild", "*.png"));

		File file = fileChooser.showSaveDialog(null);

		if (file != null) {
			try {
				// Bestimmung der Zeichenfläche
				WritableImage writableImage = new WritableImage((int) getWidth(), (int) getHeight());
				snapshot(null, writableImage);
				RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
				// speichert die Zeichenfläche als Bilddatei
				ImageIO.write(renderedImage, "png", file);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
