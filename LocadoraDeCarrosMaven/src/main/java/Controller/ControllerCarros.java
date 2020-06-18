//package Controller;
//
//import DAOs.CarroDAO;
//import java.awt.Color;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//import Models.Carro;
//import TableModels.CarrosTableModel;
//
//
//public class ControllerCarros {
//private static ControllerCarros INSTANCE;
//    private String erros;
//    
//    private CarrosTableModel tableModel = new CarrosTableModel();
//    
//    //Padrao de Projeto SINGLETON, garante uma unica instancia dessa classe
//    public static ControllerCarros getProdutoController(){
//        if(INSTANCE != null){
//            return INSTANCE;
//        }else{
//            return INSTANCE = new ControllerCarros();
//        }
//    }
//
//    public Carro pesquisaPorId (int id){
//        CarroDAO dao = new CarroDAO();
//        Carro carro = dao.buscaPorId(id);
//        if(carro != null){
//            return carro;
//        }else{
//            return null;
//        }
//    }
//
//    public boolean filtrarPorId(String id){
//        int aux = Integer.parseInt(id);
//        CarroDAO dao = new CarroDAO();
//        Carro carro = dao.buscaPorId(aux);
//        if(carro != null){
//            ArrayList<Carro> lista = new ArrayList<>();
//            lista.add(carro);
//            tableModel.setList(lista);
//            tableModel.atualiza();
//            return true;
//        }else{
//            return false;
//        }
//    }
//
//    public String getErros() {
//        String aux = erros;
//        erros = "";
//        return aux;
//    }
//    
//    public void listaCompleta() throws SQLException{
//        tableModel.getCarros();
//    }
//}
//
