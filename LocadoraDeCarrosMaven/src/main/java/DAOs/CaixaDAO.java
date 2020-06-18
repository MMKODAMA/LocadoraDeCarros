package DAOs;

import Models.Caixa;
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

public class CaixaDAO {

    public CaixaDAO() {

    }

    public static double calculaCaixa() {

        String sql = "SELECT SUM(op_valor) FROM operacoes;";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            double valorFinal;
            while (rs.next()) {
                Caixa caixa = new Caixa();
                caixa.getGastos();
                caixa.setGastos(rs.getDouble("op_valor"));
                System.out.println(caixa.getGastos());
                return caixa.getGastos();
            }

        } catch (SQLException ex) {
            throw new RuntimeException("ERRO SOMA GASTOS");

        }
        return -1;
    }


}
