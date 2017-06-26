package com.algaworks.pedidovenda.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "item_pedidos2")
public class ItemPedido2 implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, length = 3)
	private double largura = 1;

	@Column(nullable = false, length = 3)
	private double altura = 1;
	
	@Column(nullable = false, length = 3)
	private Integer quantidade = 1;

	@Column(name = "valor_metro_quadrado", nullable = false, precision = 10, scale = 2)
	private BigDecimal valorM2 = BigDecimal.ZERO;

	@ManyToOne
	@JoinColumn(name = "id_produto", nullable = false)
	private Produto2 produto;

	@ManyToOne
	@JoinColumn(name = "id_pedido", nullable = false)
	private Pedido2 pedido;

	// get and set

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorM2() {
		return valorM2;
	}

	public void setValorM2(BigDecimal valorM2) {
		this.valorM2 = valorM2;
	}

	public Produto2 getProduto() {
		return produto;
	}

	public void setProduto(Produto2 produto) {
		this.produto = produto;
	}

	public Pedido2 getPedido() {
		return pedido;
	}

	public void setPedido(Pedido2 pedido) {
		this.pedido = pedido;
	}

	public double getLargura() {
		return largura;
	}

	public void setLargura(double largura) {
		this.largura = largura;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido2 other = (ItemPedido2) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Transient
	public BigDecimal getValorTotal() {
		// total = valor da unidade * quantidade
		return this.getValorM2()
				.multiply(new BigDecimal(this.getAltura()))
				.multiply(new BigDecimal(this.getLargura()))
				.multiply(new BigDecimal(this.quantidade));
	}

	@Transient
	public boolean isProdutoAssociado() {
		return this.getProduto() != null && this.getProduto().getId() != null;
	}

}
