package forms;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/*********************************************************************
 * Klasse, um die Kanten zwischen den Knoten darzustellen.
 * 
 * @author Sven Böhrnsen
 *********************************************************************/
public final class Line {

	public final int LINEWIDTH = 3;
	// Farbe der Linie im normalen und hervorgehobenen Zustand
	private Color color;
	private final Color NORMAL = Color.rgb(0, 0, 0);
	private final Color HIGH = Color.rgb(255, 0, 0);

	// Position der Linien
	private Point2D pFrom, pTo;

	/*********************************************************************
	 * Konstruktor, um ein neues Linienobjekt zu erstellen.
	 * 
	 *********************************************************************/
	public Line() {
		this.color = NORMAL;
	}

	/*********************************************************************
	 * Methode, um eine Linie an einer übergebende Position zu zeichnen.
	 * 
	 * @param gc - Grafikelement für die Zeichung der Linien
	 *********************************************************************/
	public void drawOnCanvas(GraphicsContext gc) {

		// Farbe der Linie
		gc.setStroke(color);

		// Strichstärke
		gc.setLineWidth(LINEWIDTH);

		// Setzen der Linie
		gc.strokeLine(pFrom.getX(), pFrom.getY(), pTo.getX(), pTo.getY());
	}

	/*********************************************************************
	 * Setter Methode, um die Position der Linie zu definieren.
	 * 
	 * @param from - Startpunkt der Linie
	 * @param to - Endpunkt der Linie
	 *********************************************************************/
	public void setPosition(Point2D from, Point2D to) {
		this.pFrom = from;
		this.pTo = to;
	}

	/*********************************************************************
	 * Setter Methode, um eine Kante hervorzuheben. Definiert die Farben der Kante.
	 * true = Hervorhebung; false = normale Farbe.
	 * 
	 * @param highlight
	 *********************************************************************/
	public void setHighlight(boolean highlight) {
		if (highlight) {
			this.color = HIGH;
		} else {
			this.color = NORMAL;
		}
	}
}
