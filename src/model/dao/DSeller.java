package model.dao;

import exception.Exception;
import exception.IntegrityException;
import model.entity.ESeller;
import util.Conexao;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DSeller {
    /*
    * CONNECTION
    * RESULTSET
    * STATEMENT
    * PREPAREDSTATEMENT
    * */

    Connection cnn = null;
    ResultSet rs = null;
    Statement st = null;
    PreparedStatement prds = null;

    public  DSeller(){
        cnn = Conexao.getConnection();
    }

    public void listarSeller(){

        try {
            String sql = "select *from seller;";
            st = cnn.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("===== LISTANDO SELLER =====");

            while(rs.next()){

                System.out.println("Id: " + rs.getInt("id"));
                System.out.println("Vendedor: " + rs.getString("name"));
                System.out.println("Data de Nascimento: " + rs.getDate("birthdate"));
                System.out.println("Salario Base: " + rs.getDouble("basesalary"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Id Departamento Filiado: " + rs.getInt("departmentid"));
                System.out.println("-------------------------");
            }

        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }

    }
    public List<ESeller> retornaListaSeller(){
        List<ESeller>lista = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            String sql = "select *from seller;";
            st = cnn.createStatement();
            rs = st.executeQuery(sql);

            while(rs.next()){
                ESeller seller = new ESeller();
                seller.setId(rs.getInt("id"));
                seller.setName(rs.getString("name"));


                seller.setBirthdate(new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("birthdate")));
                seller.setBaseSalary(rs.getDouble("basesalary"));
                seller.setDepartment(rs.getInt("departmentid"));
                seller.setEmail(rs.getString("email"));
                lista.add(seller);
            }

        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }

        return lista;
    }


    public void inserir(ESeller seller){
        try{

            String sql = "INSERT INTO seller (name, email, birthdate, basesalary,departmentid) values " +
                    "(?,?,?,?,?);";
            prds = cnn.prepareStatement(sql);

            prds.setString(1,seller.getName());
            prds.setString(2, seller.getEmail());
            prds.setDate(3, new java.sql.Date(seller.getBirthdate().getTime()));
            prds.setDouble(4, seller.getBaseSalary());
            prds.setInt(5, seller.getDepartment());
            prds.executeUpdate();


        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
    }//fim inserir
    public void deletar(int id){
        try{
            cnn = Conexao.getConnection();
            String sql = "DELETE FROM seller WHERE id = ?;";
            prds = cnn.prepareStatement(sql);
            prds.setInt(1, id);
            int qd = prds.executeUpdate();
            if(qd >0){
                System.out.println("VENDEDOR DELETADO!!!");
            }else{
                System.out.println("VENDEDOR NÃO ENCONTRADO!!!");
            }


        } catch (SQLException e) {
            throw new IntegrityException(e.getMessage());
        }

    }
    public void inserirMostrarIdInserido(ESeller seller){
        try{
            String sql = "insert into seller (name,email, birthdate, basesalary,department) " +
                    " values (?,?,?,?,?);";
            prds = cnn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);


            prds.setString(1, seller.getName());
            prds.setString(2, seller.getEmail());
            prds.setDate(3, new java.sql.Date(seller.getBirthdate().getTime()));
            prds.setDouble(4, seller.getBaseSalary());
            prds.setInt(5, seller.getId());
            int id = prds.executeUpdate();
            rs = prds.getGeneratedKeys();
            if(id > 0){
                while(rs.next()){
                    id = rs.getInt(1);
                    System.out.println("ID: " + id);
                }

            }else{
                System.out.println("Nenhuma Linha Alterada");
            }
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }

    }

    public void aumentarSalarioSeller(ESeller seller){
        try{
            cnn = Conexao.getConnection();
            String sql = "UPDATE seller SET basesalary = basesalary + ? WHERE (id = ?);";

            prds = cnn.prepareStatement(sql);

            prds.setDouble(1, seller.getBaseSalary());
            prds.setInt(2, seller.getId());
            prds.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }

    }
    public void atualizarDadosSeller(ESeller seller){
        try{
            cnn = Conexao.getConnection();
            String sql = "UPDATE seller SET name = ?, email = ?, birthdate = ?, basesalary = ?, departmentid = ? " +
                    "WHERE id = ?;";

            prds = cnn.prepareStatement(sql);
            prds.setString(1, seller.getName());
            prds.setString(2, seller.getEmail());
            prds.setDate(3, new java.sql.Date(seller.getBirthdate().getTime()));
            prds.setDouble(4, seller.getBaseSalary());
            prds.setInt(5, seller.getDepartment());
            prds.setInt(6, seller.getId());
            prds.executeUpdate();

        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }

    }



}
