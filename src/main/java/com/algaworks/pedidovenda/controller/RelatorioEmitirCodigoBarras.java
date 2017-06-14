package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
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
public class RelatorioEmitirCodigoBarras implements Serializable {

	private static final long serialVersionUID = 1L;

	private String sku;

	private FacesContext facesContext = FacesContext.getCurrentInstance();
	private ExternalContext externalContext = getFacesContext()
			.getExternalContext();
	private HttpServletResponse response = (HttpServletResponse) getExternalContext()
			.getResponse();

	@Inject
	private EntityManager manager;

	public void emitir() {
			Map<String, Object> parametros = new HashMap<>();
			parametros.put("sku", this.sku);

			ExecutorRelatorio executor = new ExecutorRelatorio(
					"/relatorios/produto_codigo_barra.jasper",
					this.response, parametros, "codigo_barra.pdf");

			Session session = manager.unwrap(Session.class);

			session.doWork(executor);
			if (executor.isRelatorioGerado()) {
				facesContext.responseComplete();
			} else {
				FacesUtil
						.AvisoMessage("Não existe produto com esse SKU");
			}

	}


	// get and set

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


	public String getSku() {
		return sku;
	}


	public void setSku(String sku) {
		this.sku = sku;
	}

}
