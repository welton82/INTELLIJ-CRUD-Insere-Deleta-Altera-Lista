package util;

import exception.Exception;
import model.dao.DDepartment;
import model.dao.DSeller;
import model.entity.EDepartment;
import model.entity.ESeller;

import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        operacao(sc);

    }
    public static int menu(Scanner sc){
        System.out.println("0 - sair");
        System.out.println("1 - Listar Departamentos");
        System.out.println("2 - Listar Vendedores");
        System.out.println("3 - Inserir Departamento");
        System.out.println("4 - Inserir Vendedores");
        System.out.println("5 - Aumentar Salario de Vendedor");
        System.out.println("6 - Atualizar ");
        System.out.println("7 - Deletar Departamento");
        System.out.println("8 - Deletar Vendedor");
        System.out.print("Informe a Operação: ");
        int op = sc.nextInt();
        sc.nextLine();
        return op;
    }
    public static void operacao(Scanner sc){
        int op = -1;


        while(op != 0){

            op = menu(sc);
            switch(op){
                case 1:
                    System.out.println("LISTANDO DEPARTAMENTOS CADASTRADOS");
                    DDepartment dp = new DDepartment();
                    dp.listarDepartment();
                    break;
                case 2:
                    System.out.println("LISTANDO VENDEDORES CADASTRADOS");
                    DSeller sl = new DSeller();
                    sl.listarSeller();
                    break;
                case 3:
                    System.out.println("INSERINDO DEPARTAMENTO");
                    System.out.print("Nome do novo departamento: ");
                    String nome = sc.nextLine();
                    EDepartment eDp = new EDepartment(nome);
                    DDepartment p = new DDepartment();
                    p.inserir(eDp);
                    break;
                case 4:
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                    java.util.Date da;
                    System.out.print("Digite o Vendedor: ");
                    String name = sc.nextLine();
                    System.out.println("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Digite data de Nascimento: ");
                    String dat = sc.next();

                    System.out.print("Informe o salario: ");
                    Double salary = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Departamento Filiado: ");
                    Integer id = sc.nextInt();
                    ESeller seller = new ESeller(name,email,dat,salary,id);
                    DSeller d = new DSeller();
                    d.inserir(seller);
                    break;
                case 5:
                    System.out.print("Informe o novo salario: ");
                    Double sal = sc.nextDouble();
                    System.out.print("Informe o Código do Vendedor: ");
                    id = sc.nextInt();
                    ESeller sel = new ESeller();
                    sel.setId(id);
                    sel.setBaseSalary(sal);
                    DSeller ds = new DSeller();
                    ds.aumentarSalarioSeller(sel);
                    break;
                case 6:

                    System.out.print("Informe O id do Vendedor: ");
                    int idSel = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Digite o Vendedor: ");
                    name = sc.nextLine();
                    System.out.print("Email: ");
                    email = sc.nextLine();
                    System.out.print("Digite data de Nascimento: ");
                    dat = sc.next();

                    System.out.print("Informe o salario: ");
                    salary = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Departamento Filiado: ");
                    id = sc.nextInt();
                    seller = new ESeller(name,email,dat,salary,id);
                    seller.setId(idSel);
                    d = new DSeller();
                    d.atualizarDadosSeller(seller);
                    break;
                case 7:
                    DDepartment dep = new DDepartment();
                    dep.listarDepartment();
                    System.out.println();
                    System.out.print("Informe o Id Do Departamento para Deletar: ");
                    id = sc.nextInt();
                    dep.deletar(id);
                    break;
                case 8:
                    DSeller se = new DSeller();
                    se.listarSeller();
                    System.out.println();
                    System.out.print("Informe o id Do vendedor para deletar: ");
                    id = sc.nextInt();
                    se.deletar(id);
                    break;
            }


        }

        Conexao.closeConnection();

    }

}
