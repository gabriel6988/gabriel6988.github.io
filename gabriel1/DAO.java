package gabriel1;

import java.sql.*;

public class DAO {
	private Connection conexao;
	public DAO() {conexao = null;}
	
	public boolean conectar(){
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "Exercicio2";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;
		//Abrir conex�o com o postgres;
		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conex�o efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conex�o n�o efetuada com o postgres -- Driver n�o encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conex�o n�o efetuada com o postgres -- " + e.getMessage());
		}
		return status;
	}
	//Fechar a conex�o;
	public boolean close() {
		boolean status = false;
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {System.err.println(e.getMessage());}
		return status;
	}
	//Inserir usu�rios;
	public boolean inserirUsuario(Usuario usuario){
		boolean status = false;
		try{  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO usuario (codigo, login, senha, idade, estado) "
					       + "VALUES ("+usuario.getCodigo()+ ", '" + usuario.getLogin() + "', '"  
					       + usuario.getSenha() + "', '" + usuario.getIdade() + "', '" + usuario.getEstado() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	//Atualizar usu�rios;
	public boolean atualizarUsuario(int cod, String senha){
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("UPDATE usuario SET senha='" + senha + "' WHERE codigo=" + cod);
			st.close();
			status = true;
		} catch (SQLException u) {throw new RuntimeException(u);}
		return status;
	}
	//Excluir usu�rios;
	public boolean excluirUsuario(int codigo){
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM usuario WHERE codigo = " + codigo);
			st.close();
			status = true;
		} catch (SQLException u) {throw new RuntimeException(u);}
		return status;
	}
	//Pegar os usu�rios;
	public Usuario[] getUsuarios(){
		Usuario[] usuarios = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM usuario");		
	         if(rs.next()){
	             rs.last();
	             usuarios = new Usuario[rs.getRow()];
	             rs.beforeFirst();
	             for(int i = 0; rs.next(); i++) {
	                usuarios[i] = new Usuario(rs.getInt("codigo"), rs.getString("login"), 
	                rs.getString("senha"), rs.getInt("idade"), rs.getString("estado"));
	             }
	          }
	          st.close();
		} catch (Exception e) {System.err.println(e.getMessage());}
		return usuarios;
	}
}
