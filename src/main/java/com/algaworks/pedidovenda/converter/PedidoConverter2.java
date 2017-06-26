package com.algaworks.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.model.Pedido2;
import com.algaworks.pedidovenda.repository.PedidoDAO;
import com.algaworks.pedidovenda.repository.PedidoDAO2;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Pedido2.class)
public class PedidoConverter2 implements Converter {

	//@Inject
	private PedidoDAO2 pedidoDAO;
	
	public PedidoConverter2() {
		pedidoDAO = CDIServiceLocator.getBean(PedidoDAO2.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pedido2 retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = pedidoDAO.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Pedido2 pedido = (Pedido2) value;
			return  pedido.getId() == null ?  null :  pedido.getId().toString();
		}
		
		return "";
	}

}
