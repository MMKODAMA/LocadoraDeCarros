package DAOs;

import Models.Carro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.RuntimeErrorException;
import locadora.locadoradecarrosmaven.ConnectionFactory;

public class CarroDAO {

    public CarroDAO()  {
    }
    public static ArrayList<Carro> selectAll() throws SQLException {

        String sql = "SELECT * FROM carro WHERE carro_vendido = false";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            ArrayList<Carro> carros = new ArrayList<>();
            while (rs.next()) {
                Carro carro = new Carro();
                carro.setId(rs.getInt("carro_id"));
                carro.setMarca(rs.getString("carro_marca"));
                carro.setModelo(rs.getString("carro_modelo"));
                carro.setAno(rs.getInt("carro_ano"));
                carro.setPlaca(rs.getString("carro_placa"));
                carro.setTipo(rs.getString("carro_tipo"));
                carro.setKilometragem(rs.getFloat("carro_kilometragem"));
                carro.setTanque(rs.getInt("carro_tanque"));
                carro.setDisponibilidade(rs.getBoolean("carro_disponibilidade"));
                carro.setVendido(rs.getBoolean("carro_vendido"));
                carro.setPreco(rs.getDouble("carro_preco"));
                carros.add(carro);
                System.out.println(carros);
            }
//            ConnectionFactory.closeConnection(conn, stmt, rs);

            conn.close();

//            tableModel.setList(carros);
//            tableModel.atualiza();
            return carros;
        } catch (SQLException ex) {
            throw new RuntimeException("ERRO LISTAR");
        }

    }

    public static Carro pesquisaVenda(String placa) throws Exception {

        Carro carro = new Carro();
        String sql = "SELECT * FROM carro WHERE carro_placa = ? and carro_vendido = false";
        try (Connection conn = ConnectionFactory.getConnection();) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                carro.setId(rs.getInt("carro_id"));
                carro.setMarca(rs.getString("carro_marca"));
                carro.setModelo(rs.getString("carro_modelo"));
                carro.setAno(rs.getInt("carro_ano"));
                carro.setPlaca(rs.getString("carro_placa"));
                carro.setTipo(rs.getString("carro_tipo"));
                carro.setKilometragem(rs.getFloat("carro_kilometragem"));
                carro.setTanque(rs.getInt("carro_tanque"));
                carro.setDisponibilidade(rs.getBoolean("carro_disponibilidade"));
                carro.setVendido(rs.getBoolean("carro_vendido"));
                carro.setPreco(rs.getDouble("carro_preco"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("ERRO PESQUISA VENDA");
        }
        return carro;
    }

    public static void vender(String placa) throws Exception {
        String sql = "UPDATE carro SET carro_vendido = true WHERE carro_placa = ? ";
        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, placa);
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

    public static void comprar(Carro novoCarro) throws Exception {
        String sql = "INSERT INTO carro(carro_marca,carro_modelo,carro_ano,carro_placa,carro_tipo,carro_kilometragem,carro_tanque,carro_disponibilidade,carro_vendido,carro_preco) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, novoCarro.getMarca());
                stmt.setString(2, novoCarro.getModelo());
                stmt.setInt(3, novoCarro.getAno());
                stmt.setString(4, novoCarro.getPlaca());
                stmt.setString(5, novoCarro.getTipo());
                stmt.setFloat(6, novoCarro.getKilometragem());
                stmt.setInt(7, novoCarro.getTanque());
                stmt.setBoolean(8, novoCarro.isDisponibilidade());
                stmt.setBoolean(9, false);
                stmt.setDouble(10, novoCarro.getPreco());
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

    public static void retirar(String placa) throws Exception {

        String sql = "UPDATE carro SET carro_disponibilidade = false WHERE carro_placa = ?;";
        try (Connection conn = ConnectionFactory.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, placa);
                stmt.execute();
            } catch (SQLException ex) {
                throw new RuntimeException("ERRO CATCH UPDATE 1");
            }
        }
    }

    public static boolean checaDisponibilidade(String placa) throws SQLException, Exception {
        Carro carro = new Carro();
        String sql = "SELECT * FROM carro WHERE carro_placa = ? and carro_vendido = false";
        try (Connection conn = ConnectionFactory.getConnection();) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                carro.setId(rs.getInt("carro_id"));
                carro.setMarca(rs.getString("carro_marca"));
                carro.setModelo(rs.getString("carro_modelo"));
                carro.setAno(rs.getInt("carro_ano"));
                carro.setPlaca(rs.getString("carro_placa"));
                carro.setTipo(rs.getString("carro_tipo"));
                carro.setKilometragem(rs.getFloat("carro_kilometragem"));
                carro.setTanque(rs.getInt("carro_tanque"));
                carro.setDisponibilidade(rs.getBoolean("carro_disponibilidade"));
                carro.setVendido(rs.getBoolean("carro_vendido"));
                carro.setPreco(rs.getDouble("carro_preco"));
            }

            if (carro.isDisponibilidade()) {
                System.out.println(placa);
                retirar(placa);

                return true;
            } else {

                return false;
            }

        } catch (SQLException ex) {
            throw new RuntimeException("ERRO ChecaDisponibilidade");
        }
    }

    public static void devolver(String placa) throws Exception {

        String sql = "UPDATE carro SET carro_disponibilidade = true WHERE carro_placa = ? and carro_vendido = false;";
        try (Connection conn = ConnectionFactory.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, placa);
                stmt.execute();
            } catch (SQLException ex) {
                throw new RuntimeException("ERRO CATCH UPDATE 2");
            }
        }
    }

    public static boolean checaIndisponibilidade(String placa) throws SQLException, Exception {
        Carro carro = new Carro();
        String sql = "SELECT * FROM carro WHERE carro_placa = ? and carro_vendido = false";
        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                carro.setId(rs.getInt("carro_id"));
                carro.setMarca(rs.getString("carro_marca"));
                carro.setModelo(rs.getString("carro_modelo"));
                carro.setAno(rs.getInt("carro_ano"));
                carro.setPlaca(rs.getString("carro_placa"));
                carro.setTipo(rs.getString("carro_tipo"));
                carro.setKilometragem(rs.getFloat("carro_kilometragem"));
                carro.setTanque(rs.getInt("carro_tanque"));
                carro.setDisponibilidade(rs.getBoolean("carro_disponibilidade"));
                carro.setVendido(rs.getBoolean("carro_vendido"));
                carro.setPreco(rs.getDouble("carro_preco"));
            }

            if (!carro.isDisponibilidade()) {
                System.out.println(placa);
                devolver(placa);

                return true;
            } else {

                return false;
            }

        } catch (SQLException ex) {
            throw new RuntimeException("ERRO ChecaIndisponibilidade");
        }
    }

    public static ArrayList<Carro> selectTipo(String tipo) throws SQLException {
        String sql = "Select * from carro WHERE carro_tipo = " + "'" + tipo + "' and carro_vendido = false";
        System.out.println("entrou tipo");

        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Carro> carros = new ArrayList<>();
            while (rs.next()) {
                Carro carro = new Carro();
                carro.setId(rs.getInt("carro_id"));
                carro.setMarca(rs.getString("carro_marca"));
                carro.setModelo(rs.getString("carro_modelo"));
                carro.setAno(rs.getInt("carro_ano"));
                carro.setPlaca(rs.getString("carro_placa"));
                carro.setTipo(rs.getString("carro_tipo"));
                carro.setKilometragem(rs.getFloat("carro_kilometragem"));
                carro.setTanque(rs.getInt("carro_tanque"));
                carro.setDisponibilidade(rs.getBoolean("carro_disponibilidade"));
                carro.setVendido(rs.getBoolean("carro_vendido"));
                carro.setPreco(rs.getDouble("carro_preco"));
                carros.add(carro);
                System.out.println(carros);
            }

            conn.close();

            return carros;
        } catch (SQLException ex) {
            throw new RuntimeException("ERRO LISTAR " + ex);

        }

    }

    public static ArrayList<Carro> selectModelo(String modelo) throws SQLException {
        String sql = "Select * from carro WHERE carro_modelo = " + "'" + modelo + "' and carro_vendido = false";
        System.out.println("entrou modelo");

        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Carro> carros = new ArrayList<>();
            while (rs.next()) {
                Carro carro = new Carro();
                carro.setId(rs.getInt("carro_id"));
                carro.setMarca(rs.getString("carro_marca"));
                carro.setModelo(rs.getString("carro_modelo"));
                carro.setAno(rs.getInt("carro_ano"));
                carro.setPlaca(rs.getString("carro_placa"));
                carro.setTipo(rs.getString("carro_tipo"));
                carro.setKilometragem(rs.getFloat("carro_kilometragem"));
                carro.setTanque(rs.getInt("carro_tanque"));
                carro.setDisponibilidade(rs.getBoolean("carro_disponibilidade"));
                carro.setVendido(rs.getBoolean("carro_vendido"));
                carro.setPreco(rs.getDouble("carro_preco"));
                carros.add(carro);
                System.out.println(carros);
            }

            conn.close();

            return carros;
        } catch (SQLException ex) {
            throw new RuntimeException("ERRO LISTAR MODELO" + ex);

        }

    }

    public static ArrayList<Carro> selectMarca(String marca) throws SQLException {
        String sql = "Select * from carro WHERE carro_marca = " + "'" + marca + "' and carro_vendido = false";
        System.out.println("entrou modelo");

        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Carro> carros = new ArrayList<>();
            while (rs.next()) {
                Carro carro = new Carro();
                carro.setId(rs.getInt("carro_id"));
                carro.setMarca(rs.getString("carro_marca"));
                carro.setModelo(rs.getString("carro_modelo"));
                carro.setAno(rs.getInt("carro_ano"));
                carro.setPlaca(rs.getString("carro_placa"));
                carro.setTipo(rs.getString("carro_tipo"));
                carro.setKilometragem(rs.getFloat("carro_kilometragem"));
                carro.setTanque(rs.getInt("carro_tanque"));
                carro.setDisponibilidade(rs.getBoolean("carro_disponibilidade"));
                carro.setVendido(rs.getBoolean("carro_vendido"));
                carro.setPreco(rs.getDouble("carro_preco"));
                carros.add(carro);
                System.out.println(carros);
            }

            conn.close();

            return carros;
        } catch (SQLException ex) {
            throw new RuntimeException("ERRO LISTAR MARCA" + ex);

        }

    }

    public static int buscaMaiorId() {
        String sql = "select carro_id from carro where carro_id = (select max(carro_id) from carro);";
        Carro carro = new Carro();
        try (Connection conn = ConnectionFactory.getConnection();) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int maiorId;
                maiorId = (rs.getInt("carro_id"));
                return maiorId;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("ERRO PESQUISA VENDA");
        }

        return -1;
    }
    public static ArrayList<Carro> selectValor(double valMin, double valMax) throws SQLException {
        String sql = "Select * from carro WHERE carro_vendido = false and carro_preco between "+valMin +" and "+valMax+"";

        System.out.println("entrou modelo");

        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Carro> carros = new ArrayList<>();
            while (rs.next()) {
                Carro carro = new Carro();
                carro.setId(rs.getInt("carro_id"));
                carro.setMarca(rs.getString("carro_marca"));
                carro.setModelo(rs.getString("carro_modelo"));
                carro.setAno(rs.getInt("carro_ano"));
                carro.setPlaca(rs.getString("carro_placa"));
                carro.setTipo(rs.getString("carro_tipo"));
                carro.setKilometragem(rs.getFloat("carro_kilometragem"));
                carro.setTanque(rs.getInt("carro_tanque"));
                carro.setDisponibilidade(rs.getBoolean("carro_disponibilidade"));
                carro.setVendido(rs.getBoolean("carro_vendido"));
                carro.setPreco(rs.getDouble("carro_preco"));
                carros.add(carro);
                System.out.println(carros);
            }

            conn.close();

            return carros;
        } catch (SQLException ex) {
            throw new RuntimeException("ERRO LISTAR MARCA" + ex);

        }

    }

}
