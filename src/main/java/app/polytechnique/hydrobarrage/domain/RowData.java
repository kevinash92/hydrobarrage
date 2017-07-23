package app.polytechnique.hydrobarrage.domain;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class RowData {
	private ObjectProperty<Number[]> row;
	
	public RowData() {
		row = new SimpleObjectProperty<>();
	}
	
	public final ObjectProperty<Number[]> rowProperty() {
		return this.row;
	}
	

	public final Number[] getRow() {
		return this.rowProperty().get();
	}
	

	public final void setRow(final Number[] row) {
		this.rowProperty().set(row);
	}
}
