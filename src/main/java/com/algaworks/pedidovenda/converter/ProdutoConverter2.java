package com.algaworks.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.pedidovenda.model.Produto;
import com.algaworks.pedidovenda.model.Produto2;
import com.algaworks.pedidovenda.repository.ProdutoDAO2;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Produto2.class)
public class ProdutoConverter2 implements Converter {

	//@Inject
	private ProdutoDAO2 produtos;
	
	public ProdutoConverter2() {
		produtos = CDIServiceLocator.getBean(ProdutoDAO2.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Produto2 retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = produtos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Produto2 produto = (Produto2) value;
			return  produto.getId() == null ?  null :  produto.getId().toString();
		}
		
		return "";
	}

}
