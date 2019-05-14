package tree;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import application.Alerts;
import forms.Circle;

/*********************************************************************
 * Die Klasse für die Umsetzung von einem binären Suchbaumes.
 * 
 * @author Sven Böhrnsen
 *********************************************************************/

public final class BinaryTree {

	// Wurzelknoten
	private Node root;
	// Liste für die Traversierung
	private LinkedList<Node> output = new LinkedList<>();

	/*********************************************************************
	 * Konstruktor Methode für die Erstellung eines neuen Suchbaumes.
	 *********************************************************************/
	public BinaryTree() {
		this.root = null;
	}

	/*********************************************************************
	 * Methode für die Operation Suchen. Übergibt der Methode searchKey den
	 * Schlüsselwert und den Knoten, ab dem die Suche beginnen soll.
	 * 
	 * 
	 * @param searchKey - Schlüsselwert von einem Knoten
	 * @return Den gesuchten Schlüsselwert
	 *********************************************************************/
	public int searchNode(int searchKey) {
		return searchNode(searchKey, root);
	}

	/*********************************************************************
	 * Hilfsmethode, um einen Wert im Suchbaum zu finden. Geht alle Knoten im Baum
	 * rekursiv durch.
	 * 
	 * @param key - Schlüsselwert von einem Knoten
	 * @param focusNode - Startknoten im Suchbaum
	 * @return Den Schlüsselwert des betrachteten Knotens
	 *********************************************************************/
	public int searchNode(int key, Node focusNode) {

		// Wenn nicht vorhanden
		if (focusNode == null) {
			return (Integer) null;
		}

		// Wenn gefunden
		focusNode.setHighlight(true);
		Circle node = focusNode.getKey();
		if (key == node.getContent()) {
			return focusNode.getKey().getContent();
		}

		// Suche nach Schlüsselwert
		if (key < node.getContent()) {
			focusNode.getLeftChild().setHighlight(true);
			return searchNode(key, focusNode.getLeftChild());
		} else {
			focusNode.getRightChild().setHighlight(true);
			return searchNode(key, focusNode.getRightChild());
		}
	}

	/*********************************************************************
	 * Die Operation "Einfügen" eines Knoten in einen Suchbaum. Übergibt der Methode
	 * addCircle den Wurzelknoten ab den die Suche beginnen soll und den neuen Knoten.
	 * 
	 * @param newCircle - Neuer Knoten für den Suchbaum
	 *********************************************************************/
	public void addNode(Circle newCircle) {
		root = addNode(root, newCircle);
	}

	/*********************************************************************
	 * Hilfsmethode, geht den erhalten Baum rekursiv durch, bis eine passende Stelle
	 * für den neuen Knoten gefunden worden ist und fügt diesen dann ein.
	 * 
	 * @param focusNode - Startknoten im Suchbaum
	 * @param newCircle - Der neue Knoten
	 * @return Den Wurzelknoten
	 *********************************************************************/
	public Node addNode(Node focusNode, Circle newCircle) {
		Alerts alert = new Alerts();
		// Ist die Passende Stelle
		if (focusNode == null) {
			focusNode = new Node(newCircle);
			focusNode.setHighlight(true);
			return focusNode;
		}

		Circle node = focusNode.getKey();
		// Wenn der Schlüsselwert schon vorhanden ist
		if (newCircle.getContent() == node.getContent()) {
			alert.DoubleKey();
			focusNode.setHighlight(true);
			return focusNode;
		}
		// Suche nach einer Stelle im Suchbaum
		if (newCircle.getContent() < node.getContent()) {
			focusNode.setHighlight(true);
			focusNode.setLeftChild(addNode(focusNode.getLeftChild(), newCircle));
			focusNode.setHighlight(true);
			return focusNode;
		} else {
			focusNode.setHighlight(true);
			focusNode.setRightChild(addNode(focusNode.getRightChild(), newCircle));
			focusNode.setHighlight(true);
			return focusNode;
		}
	}

	/*********************************************************************
	 * Methode um die Operation Entfernen auszuführen. Sie Ruft die Helfermethode 
	 * Methode removeNode auf und übergibt den Schlüssel und den Knoten, ab den, 
	 * die Suche beginnen soll.
	 * 
	 * @param searchKey - Schlüsselwert im Suchbaum
	 * @return false für nicht vorhanden; true für OK
	 *********************************************************************/
	public boolean removeNode(int searchKey) {
		root = removeNode(root, searchKey);
		if (root == null) {
			return false;
		}
		return true;
	}

	/*********************************************************************
	 * Helfermethode, um den zu entfernenden Knoten zu bestimmen. Prüft welcher Fall
	 * eintrifft und ermittelt ggf. den Ersatzknoten.
	 * 
	 * @param focusNode - Startknoten im Suchbaum
	 * @param key - Schlüsselwert im Suchbaum
	 * @return Den betrachteten Wurzelknoten
	 *********************************************************************/
	protected Node removeNode(Node focusNode, int key) {
		Alerts alert = new Alerts();

		// Wenn der Schlüsselwert nicht vorhanden ist
		if (focusNode == null) {
			alert.NoKey();
			return null;
		}
		// Wenn der Schlüssel gefunden ist
		if ((key == focusNode.getKey().getContent())) {
			// Fall 1: Knoten hat keine Nachfolger
			if (focusNode.getLeftChild() == null && focusNode.getRightChild() == null) {
				// Kein Ersatz notwendig
				return null;
			}
			// Fall 2: Knoten hat keinen linken Kindsknoten
			else if (focusNode.getLeftChild() == null) {
				return focusNode.getRightChild();
			}
			// Fall 2: Knoten hat keinen rechten Kindsknoten
			else if (focusNode.getRightChild() == null) {
				return focusNode.getLeftChild();
			} else {
				// Fall 3: Hat zwei Kindsknoten
				// Ermittelt den Ersatzknoten im rechten Teilbaum
				Circle replacement = getReplacementNode(focusNode.getRightChild());
				// Überschreibe den Wert vom betrachteten Knoten mit dem vom Ersatzknoten 
				focusNode.setKey(replacement);
				// Lösche den Ersatzknoten aus der Struktur
				focusNode.setRightChild(deleteReplacementNode(focusNode.getRightChild()));
			}
			// Suche nach dem zu entfernenden Knoten
		} else if (key < focusNode.getKey().getContent()) {
			focusNode.setLeftChild(removeNode(focusNode.getLeftChild(), key));
		} else {
			focusNode.setRightChild(removeNode(focusNode.getRightChild(), key));
		}
		return focusNode;
	}

	/*********************************************************************
	 * Methode ermittelt einen Ersatzknoten aus den rechten Teilknoten des übergebenen Knotens.
	 * 
	 * @param replacedNode - Der Ersatzknoten
	 * @return Schlüsselwert aus dem Ersatzknoten
	 *********************************************************************/
	protected Circle getReplacementNode(Node replacedNode) {
		replacedNode.setHighlight(true);
		if (replacedNode.getLeftChild() == null) {
			// Übergebe erhaltenen Knotenwert als Ersatz
			return replacedNode.getKey();
		}
		// Wenn der erhaltene Knoten einen linken Kindsknoten hat:
		// Nimmt den kleinsten Wert aus dem linken Teilbaum
		return getReplacementNode(replacedNode.getLeftChild());
	}

	/*********************************************************************
	 * Methode entfernt das Circle Objekt des ursprünglichen Ersatzknotens.
	 * 
	 * @param focusNode - Startknoten im Suchbaum
	 * @return rechten Kindsknoten vom Ersatzknoten
	 *********************************************************************/
	protected Node deleteReplacementNode(Node focusNode) {
		if (focusNode.getLeftChild() == null) {
			return focusNode.getRightChild();
		}
		focusNode.setLeftChild(deleteReplacementNode(focusNode.getLeftChild()));
		return focusNode;
	}

	/*********************************************************************
	 * Die Traversierung Pre-Order Verlauf: Wurzel, Links, Rechts
	 * 
	 * @param focusNode - Startknoten im Suchbaum
	 *********************************************************************/
	public void preorder(Node focusNode) {
		if (focusNode != null) {
			output.add(focusNode);
			preorder(focusNode.getLeftChild());
			preorder(focusNode.getRightChild());
		}
	}

	/*********************************************************************
	 * Die Traversierung In-Order. Verlauf: Links, Wurzel, Rechts
	 * 
	 * @param focusNode - Startknoten im Suchbaum
	 *********************************************************************/
	public void inorder(Node focusNode) {
		if (focusNode != null) {
			inorder(focusNode.getLeftChild());
			output.add(focusNode);
			inorder(focusNode.getRightChild());
		}
	}

	/*********************************************************************
	 * Die Traversierung Post-Order Verlauf: Links, Rechts, Wurzel
	 * 
	 * @param focusNode - Startknoten im Suchbaum
	 *********************************************************************/
	public void postorder(Node focusNode) {
		if (focusNode != null) {
			postorder(focusNode.getLeftChild());
			postorder(focusNode.getRightChild());
			output.add(focusNode);
		}
	}

	/*********************************************************************
	 * überprüft, ob das LinkedList Objekt weitere Elemente enthält.
	 * 
	 * @return Ob die Liste noch Elemente hat oder nicht
	 *********************************************************************/
	public boolean hasNext() {
		return !output.isEmpty();
	}

	/*********************************************************************
	 * Nimmt sich das nächste Element aus dem LinkedList Objekt
	 * 
	 * @return Das nächste Element aus der Liste
	 * @throws NoSuchElementException
	 *********************************************************************/
	public int next() throws NoSuchElementException {

		try {
			Node iterator = output.remove();
			return iterator.getKey().getContent();
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException();
		}
	}

	/*********************************************************************
	 * Methode, um die Farbe der visuellen Knoten zurückzusetzen.
	 * 
	 * @param node  - Knoten, dessen Farbe zurückgesetzt werden soll
	 *********************************************************************/
	protected void resetColor(Node node) {
		if (node != null) {
			node.setHighlight(false);

			if (node.getLeftChild() != null) {
				node.getLeftChild().setHighlight(false);
			}

			if (node.getRightChild() != null) {
				node.getRightChild().setHighlight(false);
			}
			resetColor(node.getLeftChild());
			resetColor(node.getRightChild());
		}
	}

	/*********************************************************************
	 * Methode um den größten Knoten in einem Baum zu ermitteln
	 * 
	 * @param focusNode - Startknoten im Suchbaum
	 *********************************************************************/
	public void maximumNode(Node focusNode) {
		if (focusNode.getRightChild() != null) {
			focusNode.setHighlight(true);
			maximumNode(focusNode.getRightChild());
		} else {
			focusNode.setHighlight(true);
			output.add(focusNode);
		}

	}

	/*********************************************************************
	 * Methode um den kleinsten Knoten in einem Baum zu ermitteln
	 * 
	 * @param focusNode - Startknoten im Suchbaum
	 *********************************************************************/
	public void minimumNode(Node focusNode) {
		if (focusNode.getLeftChild() != null) {
			focusNode.setHighlight(true);
			minimumNode(focusNode.getLeftChild());
		} else {
			focusNode.setHighlight(true);
			output.add(focusNode);
		}
	}

	/*********************************************************************
	 * Setter Methode, um die Farbe der visuellen Knoten zurückzusetzen.
	 * 
	 * @param node - Knoten, dessen Farbe zurückgesetzt werden soll
	 *********************************************************************/
	public void setResetColor(Node node) {
		resetColor(node);
	}

	/*********************************************************************
	 * Getter Methode, um die Höhe des Baumes zu ermitteln. Vergleicht die Teilbäume
	 * des Wurzelknotens und gibt den größeren zurück.
	 * 
	 * @param root - Wurzelknoten des Suchbaumes
	 * @return Den größeren Teilbaum
	 *********************************************************************/
	public int getHeight(Node root) {
		if (root == null) {
			return 0;
		}
		return Math.max(getHeight(root.getLeftChild()), getHeight(root.getRightChild())) + 1;
	}

	/*********************************************************************
	 * Getter Methode, um den Wurzelknoten zu erhalten.
	 * 
	 * * @return Den Wurzelknoten
	 *********************************************************************/
	public Node getRoot() {
		if (root == null) {
			return null;
		}
		return root;
	}

	/*********************************************************************
	 * Setter Methode für die Pre-Order Traversierung
	 *********************************************************************/
	public void setPreorder(Node node) {
		output.clear();
		preorder(node);
	}

	/*********************************************************************
	 * Setter Methode für die In-Order Traversierung
	 * 
	 * @param node - Startknoten im Suchbaum
	 *********************************************************************/
	public void setInorder(Node node) {
		output.clear();
		inorder(node);
	}

	/*********************************************************************
	 * Setter Methode für die Post-Order Traversierung
	 *
	 * @param node - Startknoten im Suchbaum
	 *********************************************************************/
	public void setPostorder(Node node) {
		output.clear();
		postorder(node);
	}

	/*********************************************************************
	 * Methode, um das Canvas Element zu leeren.
	 *********************************************************************/
	public void clearCanvas() {
		root = null;
	}

}
