package app.polytechnique.hydrobarrage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.google.common.collect.Lists;

import app.polytechnique.framework.Stat;
import app.polytechnique.hydrobarrage.domain.DataModel;
import app.polytechnique.hydrobarrage.domain.RowData;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

@ViewController("/views/graph.fxml")
public class GraphController extends AbstractController {

//	@FXML
//	private LineChart lineChart;

	@FXML
	private AnchorPane centerPane;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostConstruct
	public void init() {
		
		TableView<RowData> tableView = DataModel.getPluvioTableview();
		
		Stat stat = new Stat();
		
		List<Double> average = new ArrayList<>();
		
		for (int i = 1; i <= 12; i++) {
			TableColumn<RowData, ?> col = tableView.getColumns().get(i);
			List<Double> colData = new ArrayList<>();
			for (int j = 0; j < 31; j++) {
				Double cell = col.getCellData(j) == null ? 0: Double.parseDouble(col.getCellData(j)+"");
				colData.add(cell);
			}
			Double avg = stat.moyenne(colData);
			average.add(avg);
		}
		
		Map<Double, Double> dataMap = stat.tabStat(average);
		List<Double> keys = (Lists.newArrayList(dataMap.keySet()));


		//		Object cell = col.getCellData(2);
		//		
		//		System.out.println(cell);
		
		

		NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("% time is equalled or exceeded");
        xAxis.setAutoRanging(true);
        xAxis.setAnimated(true);

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Discharge (m3/s)");
        yAxis.setAutoRanging(true);
        yAxis.setAnimated(true);
        
        LineChart lineChart = new LineChart(xAxis, yAxis);

        lineChart.setAnimated(true);
        
        lineChart.getData().clear();
        keys.forEach(key -> {
        	XYChart.Series serie = new XYChart.Series();
        	serie.getData().add(new XYChart.Data(dataMap.get(key), key));
        	System.out.println(String.format("{%f, %f}", key, dataMap.get(key)));
        	lineChart.getData().add(serie);
        });
        
        lineChart.setLegendVisible(false);
        lineChart.setTitle("Courbe des débits classés");
        lineChart.setTitleSide(Side.TOP);
        lineChart.setAnimated(true);
        centerPane.getChildren().add(lineChart);

	}

	@Override
	public void onNext() throws VetoException, FlowException {
		step++;
		flowHandler.navigate(AnalyseCtrl.class);
	}
}
