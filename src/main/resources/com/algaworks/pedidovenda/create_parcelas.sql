CREATE TABLE parcelas
(
  id serial NOT NULL,
  data_vencimento date,
  descricao character varying(255),
  paga boolean DEFAULT false,
  valor_parcela double precision,
  id_pedido bigint,
  CONSTRAINT parcelas_pkey PRIMARY KEY (id),
  CONSTRAINT fk_pedido_id FOREIGN KEY (id_pedido)
      REFERENCES pedidos (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)