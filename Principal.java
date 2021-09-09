package com.ti2cc;
import java.util.Scanner;
import com.ti2cc.DAO;
import com.ti2cc.Usuario;

public class Principal {
	//Listar usuários;
	public static String listar() {
		usuarios = dao.getUsuarios();
		System.out.println("==== Mostrar usuÃ¡rios === ");		
		for(int i = 0; i < usuarios.length; i++) {
			System.out.println(usuarios[i].toString());
		}
		String saida = "Processo terminado";
		return saida;
	}
	//Inserir usuários
	public static String inserir() {
		Usuario usuario = new Usuario(11, "pablo", "pablo",'M');
		if(dao.inserirUsuario(usuario) == true) {
			System.out.println("InserÃ§Ã£o com sucesso -> " + usuario.toString());
		}
		String saida = "Processo terminado";
		return saida;
	}
	//Excluir usuário
	public static String excluir() {
		dao.excluirUsuario(usuario.getCodigo());
		String saida = "Processo terminado";
		return saida;
	}
	//atualizar usuário
	public static String atualizar(){
		usuario.setSenha("nova senha");
		dao.atualizarUsuario(usuario);
		String saida = "Processo terminado";
		return saida;
	}
	
	public static void main(String[] args) {
		
		DAO dao = new DAO();
		
		dao.conectar();
		System.out.println("====Menu===");
		System.out.println("1) Listar");
		System.out.println("2) Inserir");
		System.out.println("3) Excluir");
		System.out.println("4) Atualizar");
		System.out.println("5) Sair");
		int op = 0;//opção selecionada;
		while(op != 5) {
			System.out.println("Digite um número: ");
			op = sc.nextInt();
			if(op == 1) {
				System.out.println(listar());
			}
			else if(op == 2) {
				System.out.println(inserir());
			}
			else if(op == 3) {
				System.out.println(excluir());
			}
			else if(op == 4) {
				System.out.println(atualizar());
			}
			else if(op == 5) {
				System.out.println("Saindo.");
			}
			else {System.out.println("Erro");}
		}
		dao.close();
	}
}