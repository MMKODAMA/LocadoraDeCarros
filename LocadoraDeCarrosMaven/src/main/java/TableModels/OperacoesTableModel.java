package TableModels;

import DAOs.OperacoesDAO;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import Models.Operacoes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import locadora.locadoradecarrosmaven.ConnectionFactory;

public class OperacoesTableModel extends AbstractTableModel{

   ArrayList<Operacoes> lista = new ArrayList<>();
   String[] colunas = {"ID", "Tipo", "ID-Carro", "Valor(Entrada/Saida em caixa)", "Kilometragem","Tanque"};
    
   public OperacoesTableModel()throws SQLException{
       OperacoesDAO operacoesDao = new OperacoesDAO();
       lista = operacoesDao.selectAll();
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
                return lista.get(linha).getTipo();
            case 2:
                return lista.get(linha).getCarro();
            case 3:
                return lista.get(linha).getValor();
            case 4:
                return lista.get(linha).getKilometragem();
            case 5:
                return lista.get(linha).getTanque();
        }

        return null;
    }


    public void atualiza() {
        fireTableDataChanged();
    }

    /*@param list objeto do tipo arrayList*/
    public void setList(ArrayList<Operacoes> list) {
        this.lista = list;
        atualiza();
    }

    public void getCarros() throws SQLException {
        lista = OperacoesDAO.selectAll();
    }

}


