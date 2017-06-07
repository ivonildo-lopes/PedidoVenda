package com.algaworks.pedidovenda.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao", nullable = false)
	private Date dataCriacao;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_emissao")
	private Date dataEmissao;

	@Column(columnDefinition = "text")
	private String observacao;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_entrega", nullable = false)
	private Date dataEntrega;

	@NotNull
	@Column(name = "valor_frete", nullable = false, precision = 10, scale = 2)
	private BigDecimal valorFrete = BigDecimal.ZERO;

	@NotNull
	@Column(name = "valor_desconto", nullable = false, precision = 10, scale = 2)
	private BigDecimal valorDesconto = BigDecimal.ZERO;

	@NotNull
	@Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
	private BigDecimal valorTotal = BigDecimal.ZERO;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20, name = "status_pedido")
	private StatusPedido status = StatusPedido.ORCAMENTO;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "forma_pagamento", nullable = false, length = 20)
	private FormaPagamento formaPagamento;
	
	@Column(name="numero_parcela")
	private int numeroParcela = 0;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_usuario_vendedor", nullable = false)
	private Usuario vendedor;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;

	@Embedded
	private EnderecoEntrega enderecoEntrega;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ItemPedido> itens = new ArrayList<>();
	
	@OneToMany(mappedBy = "pedido")
	private List<Parcela> parcelas = new ArrayList<>();

	@Transient
	public boolean isNovo() {
		return getId() == null;
	}

	@Transient
	public boolean isExistente() {
		return !isNovo();
	}


	public void recalcularValorTotal() {
		BigDecimal total = BigDecimal.ZERO;

		// total = frete - desconto
		total = total.add(this.getValorFrete()).subtract(
				this.getValorDesconto());

		for (ItemPedido item : this.getItens()) {
			if (item.getProduto() != null && item.getProduto().getId() != null) {
				total = total.add(item.getValorTotal());
			}
		}

		this.setValorTotal(total);

	}

	@Transient
	public BigDecimal getValorSubTotal() {
		// sub total = valor total - frete + desconto
		return this.getValorTotal().subtract(this.getValorFrete())
				.add(this.getValorDesconto());
	}

	public void adicionaItemVazio() {

		if (this.isOrcamento()) {
			Produto produto = new Produto();
			//produto.setQuantidadeEstoque(1);

			ItemPedido item = new ItemPedido();
			item.setProduto(produto);
			item.setQuantidade(1);
			item.setPedido(this);

			this.getItens().add(0, item);

		}
	}

	@Transient
	public boolean isOrcamento() {
		return StatusPedido.ORCAMENTO.equals(this.getStatus());
	}

	public void removerItemVazio() {
		ItemPedido primeiroItem = this.getItens().get(0);
		
		if(primeiroItem != null && primeiroItem.getProduto().getId() == null){
			this.getItens().remove(0);
		}
		
	}
	
	public void removerItem(ItemPedido item){
		this.getItens().remove(item);
		adicionaItemVazio();
	}

	@Transient
	public boolean isValorTotalNegativo() {
		// TODO Auto-generated method stub
		return this.valorTotal.compareTo(BigDecimal.ZERO) < 0;
	}

	@Transient
	public boolean isEmitido() {
		return StatusPedido.EMITIDO.equals(this.getStatus());
	}

	@Transient
	public boolean isEmissivel() {
		return this.isExistente() && this.isOrcamento();
	}
	
	
	@Transient
	public boolean isNaoEmissivel() {
		return !isEmissivel();
	}

	@Transient
	public boolean isCancelavel() {
		return this.isExistente() && !this.isCancelado();
	}
	

	@Transient
	public boolean isNaoCancelavel() {
		return !isCancelavel();
	}

	@Transient
	private boolean isCancelado() {
		return StatusPedido.CANCELADO.equals(this.getStatus());
	}

	@Transient
	public boolean isAlteravel() {
		return this.isOrcamento();
	}
	
	@Transient
	public boolean isNaoAlteravel() {
		return !isAlteravel();
	}
	
	@Transient
	public boolean isNaoEnviavelPorEmail() {
		return isNovo() || isCancelado();
	}
	
	// get and set
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Date getDataCriacao() {
			return dataCriacao;
		}

		public void setDataCriacao(Date dataCriacao) {
			this.dataCriacao = dataCriacao;
		}

		public String getObservacao() {
			return observacao;
		}

		public void setObservacao(String observacao) {
			this.observacao = observacao;
		}

		public Date getDataEntrega() {
			return dataEntrega;
		}

		public void setDataEntrega(Date dataEntrega) {
			this.dataEntrega = dataEntrega;
		}

		public Date getDataEmissao() {
			return dataEmissao;
		}

		public void setDataEmissao(Date dataEmissao) {
			this.dataEmissao = dataEmissao;
		}

		public BigDecimal getValorFrete() {
			return valorFrete;
		}

		public void setValorFrete(BigDecimal valorFrete) {
			this.valorFrete = valorFrete;
		}

		public BigDecimal getValorDesconto() {
			return valorDesconto;
		}

		public void setValorDesconto(BigDecimal valorDesconto) {
			this.valorDesconto = valorDesconto;
		}

		public BigDecimal getValorTotal() {
			return valorTotal;
		}

		public void setValorTotal(BigDecimal valorTotal) {
			this.valorTotal = valorTotal;
		}

		public StatusPedido getStatus() {
			return status;
		}

		public void setStatus(StatusPedido status) {
			this.status = status;
		}

		public FormaPagamento getFormaPagamento() {
			return formaPagamento;
		}

		public void setFormaPagamento(FormaPagamento formaPagamento) {
			this.formaPagamento = formaPagamento;
		}

		public Usuario getVendedor() {
			return vendedor;
		}

		public void setVendedor(Usuario vendedor) {
			this.vendedor = vendedor;
		}

		public Cliente getCliente() {
			return cliente;
		}

		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}

		public EnderecoEntrega getEnderecoEntrega() {
			return enderecoEntrega;
		}

		public void setEnderecoEntrega(EnderecoEntrega enderecoEntrega) {
			this.enderecoEntrega = enderecoEntrega;
		}

		public List<ItemPedido> getItens() {
			return itens;
		}

		public void setItens(List<ItemPedido> itens) {
			this.itens = itens;
		}
		
		public int getNumeroParcela() {
			return numeroParcela;
		}

		public void setNumeroParcela(int numeroParcela) {
			this.numeroParcela = numeroParcela;
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
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}