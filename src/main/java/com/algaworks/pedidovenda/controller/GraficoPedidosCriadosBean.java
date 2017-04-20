package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.repository.PedidoDAO;
import com.algaworks.pedidovenda.repository.UsuarioDAO;

@Named
@RequestScoped
public class GraficoPedidosCriadosBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	private static DateFormat DATE_FORMATE = new SimpleDateFormat("dd/MM");
	private LineChartModel model;

	@Inject
	private PedidoDAO pedidoDAO;

	@Inject
	UsuarioDAO dao;
	@Inject
	Usuario usuario;// =dao.porId(3L);
	
	/**
	 * opção 1
	 */

	// public void preRender() {
	// this.model = new LineChartModel();
	//
	// definirParametros();
	// }

	// estatico PRIMEFACES 6.0
	// private void adicionarSerie(String rotulo) {
	// LineChartSeries series = new LineChartSeries();
	//
	// series.set("1", Math.random() * 1000);
	// series.set("2", Math.random() * 1000);
	// series.set("3", Math.random() * 1000);
	// series.set("4", Math.random() * 1000);
	// series.set("5", Math.random() * 1000);
	// series.setLabel(rotulo);
	//
	//
	// this.model.addSeries(series);
	//
	// }

	// private void adicionarSerie(String rotulo, Usuario criadoPor) {
	//
	// LineChartSeries series = new LineChartSeries();
	//
	// Map<Date, BigDecimal> valoresPorData = this.pedidoDAO
	// .valoresTotaisPorData(20, criadoPor);
	//
	// for (Date data : valoresPorData.keySet()) {
	// series.set(data , valoresPorData.get(data));
	// // System.out.println(DATE_FORMATE.format(data) + " : " +
	// valoresPorData.get(data));
	// }
	//
	// series.setLabel(rotulo);
	//
	// this.model.addSeries(series);
	//
	// }

	// public void definirParametros() {
	// this.model = new LineChartModel();
	// model.setTitle("Comparação de Pedidos");
	// model.setLegendPosition("e");
	// model.setAnimate(true);
	//
	// // // todos os usuarios > id = null
	// adicionarSerie("todos os pedidos", null);
	//
	// // Usuario usuario = new Usuario();
	// // //usuario.setId(3L);
	// // UsuarioDAO dao = new UsuarioDAO();
	// // usuario = dao.porId(3L);
	//
	// usuario = dao.porId(3L);
	// adicionarSerie("todos os pedidos: " , usuario);
	//
	// // adicionarSerie("Meus Pedidos");
	// // adicionarSerie("Todos Pedidos");
	//
	//
	// Axis yAxis = model.getAxis(AxisType.Y);
	// yAxis.setMin(0);
	//
	//
	// }
	
	/**
	 * opção 2
	 */

//	@PostConstruct
//	public void init() {
//
//		this.model = new LineChartModel();
//		this.model.setTitle("Pedidos");
//		this.model.setLegendPosition("e");
//		this.model.setShowPointLabels(true);
//		
//		createAreaModel("Nivel 1",null);
//		createAreaModel("Nivel 2",null);
//		
//		Axis yAxis = this.model.getAxis(AxisType.Y);
//		yAxis.setMin(0);
//		
//		Axis xAxis = this.model.getAxis(AxisType.X);
//		xAxis.setMax(2017);
//		xAxis.setMin(2012);
//		
//	}
//
//	
//	private void createAreaModel(String rotulo,Usuario criadoPor) {
//		
//		LineChartSeries series = new LineChartSeries();
//		
////		series.set("2013",50 * Math.random());
////		series.set("2014",60 * Math.random());
////		series.set("2015",45 * Math.random());
////		series.set("2016",65 * Math.random());
////		series.set("2017",100 * Math.random());
//		
//		Map<Date, BigDecimal> valoresPorData = this.pedidoDAO
//		.valoresTotaisPorData(20, criadoPor);
//		
//		 for (Date data : valoresPorData.keySet()) {
//		 series.set(data , valoresPorData.get(data));
//		 }
//		
//		series.setLabel(rotulo);
//		
//		this.model.addSeries(series);
//	}
//
//	public LineChartModel getModel() {
//		return model;
//	}

}
