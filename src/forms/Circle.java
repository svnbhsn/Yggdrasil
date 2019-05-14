package forms;

import com.sun.javafx.tk.FontMetrics;
import com.sun.javafx.tk.Toolkit;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/*********************************************************************
 * Klasse um einen Knoten in Kreisform darzustellen.
 * 
 * @author Sven Böhrnsen
 *********************************************************************/
public final class Circle {

	// Eigenschaften eines Kreises
	public final int SIZE = 24;
	public final int LINEWIDTH = 3;
	private Point2D position;
	private Color background;
	private Color border;
	private Color fontColor;

	// Eigenschaften der Schrift im Kreis
	final Font font = Font.font("Verdana", FontWeight.BOLD, 16);
	final FontMetrics fmKey = Toolkit.getToolkit().getFontLoader().getFontMetrics(font);
	private int content;

	/*********************************************************************
	 * Konstruktor, um ein neues Kreisobjekt mit erhaltenen Schlüsselwert zu
	 * erstellen.
	 * 
	 * @param key - Neuer Schlüsselwert
	 *********************************************************************/
	public Circle(int key) {
		this.content = key;
	}

	/*********************************************************************
	 * Methode, um einen Kreis an einer übergebenden Position zu zeichnen.
	 * 
	 * @param gc - Grafikelment für die Kreise
	 *********************************************************************/
	public void drawOnCanvas(GraphicsContext gc) {
		// Kreis
		gc.setFill(background);
		gc.fillOval(position.getX() - SIZE, position.getY() - SIZE, 2 * SIZE, 2 * SIZE);

		// Rand eines Kreises
		gc.setStroke(border);
		gc.setLineWidth(LINEWIDTH); // Linienstärke
		gc.strokeOval(position.getX() - SIZE, position.getY() - SIZE, 2 * SIZE, 2 * SIZE);

		// Schlüsselwert innerhalb eines Kreises
		gc.setFont(font);
		gc.setFill(getFontColor());
		gc.fillText(getValue(), position.getX() - (fmKey.computeStringWidth(getValue()) / 2),
				position.getY() + (fmKey.getAscent() / 2.5));
	}

	/*********************************************************************
	 * Gibt den Schlüsselwert für die Visualisierung als String zurück.
	 * 
	 * @return Den Schlüsselwert des Kreises als String
	 *********************************************************************/
	private String getValue() {
		return Integer.toString(content);
	}

	/*********************************************************************
	 * Getter Methode, um den Schlüsselwert zu erhalten.
	 * 
	 * @returnen Schlüsselwert des Kreises als int
	 *********************************************************************/
	public int getContent() {
		return this.content;
	}

	/*********************************************************************
	 * Getter Methode, um die Farbe des Kreisrandes zu erhalten.
	 * 
	 * @return Farbe vom Rand des Kreises
	 *********************************************************************/
	public Color getBorderColor() {
		return border;
	}

	/*********************************************************************
	 * Setter Methode, um die Farbe des Kreisrandes zu definieren.
	 * 
	 * @param borderColor - Farbe vom Randes des Kreis
	 *********************************************************************/
	private void setBorderColor(Color borderColor) {
		this.border = borderColor;
	}

	/*********************************************************************
	 * Getter Methode, um die Position des Kreises zu erhalten.
	 * 
	 * @return Position eines Kreises
	 *********************************************************************/
	public Point2D getPoint() {
		return position;
	}

	/*********************************************************************
	 * Setter Methode, um die Position des Kreises zu definieren.
	 * 
	 * @param point - Position vom Kreis
	 *********************************************************************/
	public void setPosition(Point2D point) {
		this.position = point;
	}

	/*********************************************************************
	 * Getter Methode, um die Farbe des Kreises zu erhalten.
	 * 
	 * @return Grundfarbe des Kreises
	 *********************************************************************/
	public Color getBackgroundColor() {
		return background;
	}

	/*********************************************************************
	 * Setter Methode, um die Farbe des Kreises zu definieren.
	 * 
	 * @param bgColor - Grundfarbe des Kreises
	 *********************************************************************/
	private void setBackgroundColor(Color bgColor) {
		this.background = bgColor;
	}

	/*********************************************************************
	 * Getter Methode, um die Farbe der Schrift zu erhalten.
	 * 
	 * @return Farbe der Schrift
	 *********************************************************************/
	public Color getFontColor() {
		return this.fontColor;
	}

	/*********************************************************************
	 * Setter Methode, um die Farbe der Schrift zu definieren.
	 * 
	 * @param fontColor - Farbe der Schrift 
	 *********************************************************************/
	private void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}

	/*********************************************************************
	 * Setter Methode, um einen Kreis hervorzuheben. Definiert die Farben des
	 * Kreises, dessen Rand und der Schrift des Schlüsselwertes.
	 * true = Hervorhebung; false = normale Farbe.
	 * 
	 * @param highlight 
	 *********************************************************************/
	public void setHighlight(boolean highlight) {
		if (highlight) {
			setFontColor(Color.rgb(0, 0, 0));
			setBackgroundColor(Color.rgb(255, 255, 255));
			setBorderColor(Color.rgb(255, 0, 0));

		} else {
			setFontColor(Color.rgb(255, 255, 255));
			setBackgroundColor(Color.rgb(95, 157, 186));
			setBorderColor(Color.rgb(0, 0, 0));
		}
	}
}
