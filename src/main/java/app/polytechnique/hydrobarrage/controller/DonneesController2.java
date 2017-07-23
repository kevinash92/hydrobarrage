package app.polytechnique.hydrobarrage.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormatSymbols;
import javax.annotation.PostConstruct;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jfoenix.controls.JFXButton;

import app.polytechnique.hydrobarrage.domain.RowData;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.util.VetoException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

@ViewController("/views/donnees2.fxml")
public class DonneesController2 extends AbstractController {

	@FXML
	private TableView<RowData> tableView;

	@FXML
	private JFXButton chooseBtn;

	@FXML
	private Label fileLabel;
	

	@PostConstruct
	public void init() {
		
		chooseBtn.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(
					new ExtensionFilter("EXCEL Files", "*.xlsx"));

			File selectedFile = fileChooser.showOpenDialog(null);

			if (selectedFile != null ) {
				String filepath = selectedFile.getAbsolutePath();
				fileLabel.setText(filepath);
				
				@SuppressWarnings("unchecked")
				TableColumn<RowData, String> columnHeader[] = new TableColumn[12];
				
				double COL_WIDTH = tableView.getPrefWidth()*1.05/12.0;
				
				for (int i = 0, n = columnHeader.length; i < n; i++) {
					final int j = i;
					columnHeader[i] = new TableColumn<>(new DateFormatSymbols().getMonths()[i]);
					columnHeader[i].setPrefWidth(COL_WIDTH);
					columnHeader[i].setCellValueFactory((param) -> {
						return new SimpleObjectProperty<>(param.getValue().getRow()[j]+"");
					});
				}
				
				tableView.getColumns().setAll(columnHeader);
				
				ObservableList<RowData> datas = FXCollections.observableArrayList();
				
				try {
					
					XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(filepath));
					XSSFSheet sheet = workbook.getSheetAt(0);
					Row row;
					for (int i = 1, n = sheet.getLastRowNum(); i <= n; i++) {
						RowData rowData = new RowData();
						row = sheet.getRow(i); 
						Double[] data = new Double[12];
						for (int j = 0; j < 12; j++) {
							try {
								data[j] = row.getCell(j).getNumericCellValue();
								System.out.print(data[j]+" ");
							}catch (Exception ex) {
								data[j] = (double) 0;
							}
						}
						rowData.setRow(data);
						datas.add(rowData);
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				
				System.out.println();
				datas.forEach(data -> {
					System.out.println(data.getRow()[0]);
				});
				
				tableView.setItems(datas);

			} else {
				fileLabel.setText("Aucun fichier sélectionné!");
			}
		});
	}


	@Override
	public void onNext() throws VetoException, FlowException {
		
	}

}
