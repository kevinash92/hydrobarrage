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

import app.polytechnique.hydrobarrage.domain.DataModel;
import app.polytechnique.hydrobarrage.domain.RowData;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.util.VetoException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

@ViewController("/views/donnees.fxml")
public class DonneesController extends AbstractController {

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
				TableColumn<RowData, String> columnHeader[] = new TableColumn[13];
				
				double COL_WIDTH = tableView.getPrefWidth()*1.05/13.0;
				
				for (int i = 0, n = columnHeader.length; i < n; i++) {
					
					final int j = i;
					
					if(i == 0) {
						columnHeader[i] = new TableColumn<>("jours\\mois");
					} else {
						columnHeader[i] = new TableColumn<>(new DateFormatSymbols().getMonths()[i-1]);
					}
					columnHeader[i].setPrefWidth(COL_WIDTH);
					columnHeader[i].setCellValueFactory((param) -> {
						System.out.println("a"+j);
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
						Number[] data = new Number[13];
						data[0] = (int)i;
						for (int j = 1, m=data.length; j < m; j++) {
							try {
								data[j] = row.getCell(j-1).getNumericCellValue();
							}catch (Exception ex) {
								data[j] = 0;
							}
						}
						rowData.setRow(data);
						datas.add(rowData);
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				
				tableView.setItems(datas);
				DataModel.setPluvioTableview(tableView);
				
			} else {
				System.out.println("Aucun fichier sélectionné!");
				Button okBtn = new JFXButton("Ok");
				okBtn.setOnAction((evt) ->{
					dialog.close();
				});
				content.getActions().setAll(okBtn);
				showAlert("Information", "Aucun fichier sélectionné");
			}
		});
	}


	@Override
	public void onNext() throws VetoException, FlowException {
		if (tableView.getColumns().isEmpty()) {
			System.out.println("Aucun fichier sélectionné!");
			Button okBtn = new JFXButton("Ok");
			okBtn.setOnAction((evt) ->{
				dialog.close();
			});
			content.getActions().setAll(okBtn);
			showAlert("Avertissement", "Aucune donnée n'est présente dans la table\n"
					+ "Effectuez une collecte des débits sur un an avant de continuer.");
			return;
		}
		step++;
		flowHandler.navigate(GraphController.class);
	}

}
