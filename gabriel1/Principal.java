package gabriel1;

import java.util.Scanner;

public class Principal{
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args){
		DAO dao = new DAO();
		dao.conectar();
		
		//Menu;
		int i = 0;
		do {
			System.out.println("==== Menu === ");
			System.out.println("Escolha uma das operações");
			System.out.println("1) Listar");
			System.out.println("2) Inserir");
			System.out.println("3) Excluir");
			System.out.println("4) Atualizar");
			System.out.println("5) Sair");
			i = sc.nextInt();
			//Listar;
			if(i == 1) {
				Usuario[] usuarios = dao.getUsuarios();
				System.out.println("== Usuários ==");
				for(int j = 0; j < usuarios.length; j++) {
					System.out.println(usuarios[j].toString());
				}
			}
			//Inserir;
			else if(i == 2) {
				System.out.println("== Inserir novo Usuário ==");
				int newcod = sc.nextInt();
				String newlogin = sc.nextLine();
				String newsenha = sc.nextLine();
				int newidade = sc.nextInt();
				String newestado = sc.nextLine();
				Usuario usuario = new Usuario(newcod, newlogin, newsenha, newidade, newestado);
				if(dao.inserirUsuario(usuario) == true) {System.out.println("Inserção com sucesso -> " + usuario.toString());}
				else {System.out.println("Erro na Inserção");}
			}
			//Excluir;
			else if(i == 3){
				System.out.println("== Remover Usuário ==");
				System.out.println("== Insira o código do usuário ==");
				int remover = sc.nextInt();
				dao.excluirUsuario(remover);
			}
			//Atualizar;
			else if(i == 4) {
				System.out.println("== Atualizar Usuário ==");
				int cod = sc.nextInt();
				String novasenha = sc.nextLine();
				dao.atualizarUsuario(cod, novasenha);
			}
			//Sair;
			else if(i == 5) {System.out.println("Saindo");}
			else {System.out.println("Erro");}
		}while(i != 5);
		dao.close();
	}	
}