package tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.VisuTree;

/*********************************************************************
 * Testklasse, um die Entfernenoperation und ihre Fälle zu testen.
 * 
 * @author Sven Böhrnsen
 *********************************************************************/
class RemoveTest {

	VisuTree testTree = new VisuTree();

	/*********************************************************************
	 * Testbaum erstellen. Dieser Baum steht für jedem Test zur Verfügung.
	 *
	 * @throws Exception
	 *********************************************************************/
	@BeforeEach
	public void treeSetup() throws Exception {

		int[] treeNodes = new int[] { 50, 30, 70, 20, 40, 60, 80, 25, 35, 45, 65, 90 };

		for (int i = 0; i < treeNodes.length; i++) {
			testTree.add(treeNodes[i]);
		}
	}

	/*********************************************************************
	 * Fall 1: Der zu entfernende Knoten ist ein Blattknoten.
	 *********************************************************************/
	@Test
	public void removeCase1() {
		String output;

		System.out.println("Fall 1: Der zu entfernende Knoten ist ein Blattknoten");

		// Entfernen vom Knoten mit dem Wert 25
		testTree.delete(25);

		// Inorder Traversierung zum vergleichen
		testTree.setInorder();
		output = testTree.printTree();
		System.out.println(output);
		System.out.println("----------------------------");

		// Vergleicht die Inroder Ausgabe mit der erwarteten Reihenfolge
		assertEquals(output, "20 30 35 40 45 50 60 65 70 80 90 ");
		// assertThat(output, CoreMatchers.containsString("30"));

	}

	/*********************************************************************
	 * Fall 2: Der zu entfernende Knoten hat genau einen Nachfolger.
	 *********************************************************************/
	@Test
	public void removeCase2() {
		String output;

		System.out.println("Fall 2: Der zu entfernende Knoten hat genau einen Nachfolger");

		// Entfernen vom Knoten mit dem Wert 60
		testTree.delete(60);

		// Inorder Traversierung zum vergleichen
		testTree.setInorder();
		output = testTree.printTree();
		System.out.println(output);
		System.out.println("----------------------------");

		// Vergleicht die Inroder Ausgabe mit der erwarteten Reihenfolge
		assertEquals(output, "20 25 30 35 40 45 50 65 70 80 90 ");
		// assertThat(output, CoreMatchers.containsString("30"));

	}

	/*********************************************************************
	 * Fall 3: Der zu entfernende Knoten hat zwei Nachfolger.
	 *********************************************************************/
	@Test
	public void removeCase3() {
		String output;

		System.out.println("Fall 3: Der zu entfernende Knoten hat zwei Nachfolger.");

		// Entfernen vom Knoten mit dem Wert 50
		testTree.delete(50);

		// In-Order Traversierung zum vergleichen
		testTree.setInorder();
		output = testTree.printTree();
		System.out.println(output);
		System.out.println("----------------------------");

		// Vergleicht die In-Order Ausgabe mit der erwarteten Reihenfolge
		assertEquals(output, "20 25 30 35 40 45 60 65 70 80 90 ");
	}
}
