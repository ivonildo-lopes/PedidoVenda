CREATE TRIGGER tggeraparcela
  AFTER UPDATE
  ON pedidos
  FOR EACH ROW
  EXECUTE PROCEDURE gerarparcela();