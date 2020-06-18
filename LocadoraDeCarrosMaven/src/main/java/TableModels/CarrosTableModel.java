package TableModels;

import DAOs.CarroDAO;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import Models.Carro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import locadora.locadoradecarrosmaven.ConnectionFactory;

public class CarrosTableModel extends AbstractTableModel {

    ArrayList<Carro> lista = new ArrayList<>();
    String[] colunas = {"ID", "Marca", "Modelo", "Tipo", "Placa"};

    public CarrosTableModel() throws SQLException {
        CarroDAO carroDao = new CarroDAO();
        lista = carroDao.selectAll();
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return lista.get(linha).getId();
            case 1:
                return lista.get(linha).getMarca();
            case 2:
                return lista.get(linha).getModelo();
            case 3:
                return lista.get(linha).getTipo();
            case 4:
                return lista.get(linha).getPlaca();
        }

        return null;
    }


    public void atualiza() {
        fireTableDataChanged();
    }

    /*@param list objeto do tipo arrayList*/
    public void setList(ArrayList<Carro> list) {
        this.lista = list;
        atualiza();
    }

    public void getCarros() throws SQLException {
        lista = CarroDAO.selectAll();
    }

}
