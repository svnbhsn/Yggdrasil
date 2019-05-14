package tree;

import forms.Circle;

/*********************************************************************
 * Klasse für die Definition von Knoten in einem Baum. Erstellt Knoten als
 * Circle Objekte.
 * 
 * @author Sven Böhrnsen
 *********************************************************************/

public class Node {
	private Circle key;
	private Node leftChild;
	private Node rightChild;
	private boolean highlight;

	/*********************************************************************
	 * Konstruktor, um einen Knoten als Circle Objekt zu erstellen.
	 * 
	 * @param key - Schlüsselwert eines Knotens 
	 *********************************************************************/
	public Node(Circle key) {
		this.key = key;
	}

	/*********************************************************************
	 * Getter Methode für den Schlüsselwert.
	 *
	 * @return Den Schlüsselwert aus dem Knoten
	 *********************************************************************/
	public Circle getKey() {
		return key;
	}

	/*********************************************************************
	 * Setter Methode für den Schlüsselwert.
	 *
	 * @param key - Schlüsselwert eines Knotens
	 *********************************************************************/
	public void setKey(Circle key) {
		this.key = key;
	}

	/*********************************************************************
	 * Getter Methode für den linken Kindsknoten.
	 *
	 * @return Den linken Kindsknoten
	 *********************************************************************/
	public Node getLeftChild() {
		return leftChild;
	}

	/*********************************************************************
	 * Setter Methode für den linken Kindsknoten.
	 *
	 * @param leftChild - Den linken Kindsknoten
	 *********************************************************************/
	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}

	/*********************************************************************
	 * Getter Methode für den rechten Kindsknoten.
	 *
	 * @return Den rechten Kindsknoten
	 *********************************************************************/
	public Node getRightChild() {
		return rightChild;
	}

	/*********************************************************************
	 * Setter Methode für den rechten Kindsknoten.XX
	 *
	 * @param rightChild - Den rechten Kindsknoten
	 *********************************************************************/
	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}

	/*********************************************************************
	 * Getter Methode für die Hervorhebung eines Knotens.
	 *
	 * @return true: Hervorgehoben; false: normal
	 *********************************************************************/
	public boolean getHighlight() {
		return highlight;
	}

	/*********************************************************************
	 * Setter Methode um einen Knoten hervorzuheben. Wenn true, dann wird eine
	 * andere Farbe gewählt.
	 *
	 * @param highlight - Hervorhebung des Knotens
	 *********************************************************************/
	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
	}

}
