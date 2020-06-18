package DAOs;

import Models.Operacoes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import locadora.locadoradecarrosmaven.ConnectionFactory;
public class OperacoesDAO {
    public static ArrayList<Operacoes> selectAll() throws SQLException {

        String sql = "SELECT * FROM operacoes";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            ArrayList<Operacoes> operacoess = new ArrayList<>();
            while (rs.next()) {
                Operacoes operacoes = new Operacoes();
                operacoes.setId(rs.getInt("op_id"));
                operacoes.setTipo(rs.getString("op_tipo"));
                operacoes.setCarro(rs.getInt("op_carro"));
                operacoes.setValor(rs.getDouble("op_valor"));
                operacoes.setKilometragem(rs.getFloat("op_kilometragem"));
                operacoes.setTanque(rs.getInt("op_tanque"));
                operacoess.add(operacoes);
                System.out.println(operacoess);
            }
//            ConnectionFactory.closeConnection(conn, stmt, rs);

            conn.close();

//            tableModel.setList(carros);
//            tableModel.atualiza();
            return operacoess;
        } catch (SQLException ex) {
            throw new RuntimeException("ERRO LISTAR");
        }

    }
    public static void insert(Operacoes novaOperacao) throws Exception {
        String sql = "INSERT INTO operacoes(op_tipo,op_carro,op_valor,op_kilometragem,op_tanque) VALUES (?,?,?,?,?)";
        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, novaOperacao.getTipo());
                stmt.setInt(2, novaOperacao.getCarro());
                stmt.setDouble(3, novaOperacao.getValor());
                stmt.setFloat(4, novaOperacao.getKilometragem());
                stmt.setInt(5, novaOperacao.getTanque());
                
                stmt.execute();
                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                System.out.println(e.getMessage());
                throw new RuntimeException("ERRO CATCH 1");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException("ERRO CATCH 2");
        }
    }
     public static ArrayList<Operacoes> selectData(String dataIni,String dataFin) throws SQLException {
        String sql = "Select * from operacoes WHERE op_data between "+"'"+dataIni+"' and "+"'"+dataFin+"'";
        System.out.println("entrou modelo");

        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Operacoes> operacoes = new ArrayList<>();
            while (rs.next()) {
                Operacoes operacoess = new Operacoes();//OBJ
                operacoess.setId(rs.getInt("op_id"));
                operacoess.setTipo(rs.getString("op_tipo"));
                operacoess.setCarro(rs.getInt("op_carro"));
                operacoess.setValor(rs.getDouble("op_valor"));
                operacoess.setKilometragem(rs.getFloat("op_kilometragem"));
                operacoess.setTanque(rs.getInt("op_tanque"));
                operacoes.add(operacoess);//lista.add(OBJ)
                System.out.println(operacoes);
            }

            conn.close();

            return operacoes;
        } catch (SQLException ex) {
            throw new RuntimeException("ERRO LISTAR OPERACOES" + ex);

        }


     }

}
