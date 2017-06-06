CREATE OR REPLACE FUNCTION gerarparcela()
  RETURNS trigger AS
$BODY$
DECLARE
pedido RECORD;   

BEGIN
    FOR pedido in SELECT * FROM pedidos WHERE id = NEW.id LOOP   
        FOR i IN 1..pedido.numero_parcela LOOP
            INSERT INTO parcelas(id_pedido,valor_parcela,descricao,data_vencimento) VALUES
               (pedido.id,(pedido.valor_total/pedido.numero_parcela),('Parcela '|| i || ' de '|| pedido.numero_parcela),cast(pedido.data_emissao as date) + i*30);
            END LOOP;

        return NEW;

    END LOOP;

END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION gerarparcela()
  OWNER TO postgres;