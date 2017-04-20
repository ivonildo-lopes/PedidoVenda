package com.algaworks.pedidovenda.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="parcelas")
public class Parcela implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private long id;
	
//
//	private Long id_pedido;
	
	@ManyToOne
	@JoinColumn(name="id_pedido",referencedColumnName="id")
	private Pedido pedido;
	
	@Column(name="valor_parcela")
	private double valorParcela;
	
	private String descricao;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_vencimento")
	private Date dataVencimento;
	
	private Boolean paga;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public double getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(double valorParcela) {
		this.valorParcela = valorParcela;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Boolean getPaga() {
		return paga;
	}

	public void setPaga(Boolean paga) {
		this.paga = paga;
	}

//	public Long getId_pedido() {
//		return id_pedido;
//	}
//
//	public void setId_pedido(Long id_pedido) {
//		this.id_pedido = id_pedido;
//	}
	
	
	
	
}
