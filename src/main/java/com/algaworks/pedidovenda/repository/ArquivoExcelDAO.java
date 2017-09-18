package com.algaworks.pedidovenda.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.pedidovenda.model.ArquivoExcel;

public class ArquivoExcelDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public ArquivoExcel salvar(ArquivoExcel arquivoExcel) {
		
		try {
			return this.manager.merge(arquivoExcel);
		} catch (Exception e) {
			return null;
		}
		
	}
}
