package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.algaworks.pedidovenda.util.jsf.FacesUtil;
import com.algaworks.pedidovenda.util.report.ExecutorRelatorio;

@Named
@RequestScoped
public class RelatorioPedidosEmitidosBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date dataInicio;
	private Date fim;

	private FacesContext facesContext = FacesContext.getCurrentInstance();
	private ExternalContext externalContext = getFacesContext()
			.getExternalContext();
	private HttpServletResponse response = (HttpServletResponse) getExternalContext()
			.getResponse();

	@Inject
	private EntityManager manager;

	public void emitir() {
		if (verificaDataInicialAnteriorDataFim()) {
			Map<String, Object> parametros = new HashMap<>();
			parametros.put("data_inicio", this.dataInicio);
			parametros.put("fim", this.fim);

			ExecutorRelatorio executor = new ExecutorRelatorio(
					"/relatorios/relatorio_pedidos_emitidos.jasper",
					this.response, parametros, "pedidos_emitidos.pdf");

			Session session = manager.unwrap(Session.class);

			// session.doWork(new Work() {
			//
			// @Override
			// public void execute(Connection arg0) throws SQLException {
			// // TODO Auto-generated method stub
			//
			// }
			// });

			session.doWork(executor);
			if (executor.isRelatorioGerado()) {
				facesContext.responseComplete();
			} else {
				FacesUtil
						.AvisoMessage("Não existe pedido emitido no intervalor dessas datas");
			}
		}else{
			FacesUtil.ErrorMessage("A data Inicial não pode ser depois da data final");
		}

	}

	public boolean verificaDataInicialAnteriorDataFim() {
		if (dataInicio.before(fim)) {
			return true;
		}
		return false;
	}

	// get and set
	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public FacesContext getFacesContext() {
		return facesContext;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public ExternalContext getExternalContext() {
		return externalContext;
	}

	public void setExternalContext(ExternalContext externalContext) {
		this.externalContext = externalContext;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

}
