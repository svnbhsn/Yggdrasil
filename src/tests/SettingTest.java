package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.VisuTree;

/*********************************************************************
 * Testklasse, um die Eigenschaften von einem Suchbaum zu testen.
 * 
 * @author Sven Böhrnsen
 *********************************************************************/
class SettingTest {
	VisuTree testTree = new VisuTree();

	/*********************************************************************
	 * Testbaum erstellen. Dieser Baum steht für jedem Test zur Verfügung.
	 *
	 * @throws java.lang.Exception
	 *********************************************************************/
	@BeforeEach
	void setUp() throws Exception {
		int[] treeNodes = new int[] { 50, 30, 70, 20, 40, 60, 80, 25, 35, 45, 65, 90 };

		for (int i = 0; i < treeNodes.length; i++) {
			testTree.add(treeNodes[i]);
		}
	}

	/*********************************************************************
	 * Prüft, ob die getMin Methode aus der Klasse VisuTree funktioniert.
	 *********************************************************************/
	@Test
	void minTest() {
		testTree.getMin();

		String output = testTree.printTree();
		System.out.println("Minimum Wert im Baum: " + output);

		// Vergleicht die Ausgabe mit der erwarteten minimalen Wert im Baum
		assertEquals(output, "20 ");
	}

	/*********************************************************************
	 * Prüft, ob die getMax Methode aus der Klasse VisuTree funktioniert.
	 *********************************************************************/
	@Test
	void maxTest() {
		testTree.getMax();

		String output = testTree.printTree();
		System.out.println("Maximum Wert im Baum: " + output);

		// Vergleicht die Ausgabe mit der erwarteten maximalen Wert im Baum
		assertEquals(output, "90 ");
	}

	/*********************************************************************
	 * Prüft, ob die getTreeHeight Methode aus der Klasse VisuTree funktioniert.
	 *********************************************************************/
	@Test
	void heightTest() {
		System.out.println("Höhe des Baumes: " + (testTree.getTreeHeight() - 1));

		// Vergleicht die Ausgabe mit der erwarteten Höhe des Suchbaumes
		assertEquals((testTree.getTreeHeight() - 1), 3);

	}
}
