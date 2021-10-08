package dao;
import model.Produto;
import java.sql.*;
import java.util.List;

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
		//Abrir conexão com o postgres;
		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão não efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão não efetuada com o postgres -- " + e.getMessage());
		}
		return status;
	}
	//Fechar a conexão;
	public boolean close() {
		boolean status = false;
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {System.err.println(e.getMessage());}
		return status;
	}
	//Inserir Produto;
	public boolean add(Produto produto) {
		boolean status = false;
		try{  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO produto (id, descricao, preco, quantidade) "
					       + "VALUES ("+produto.getId()+ ", '" + produto.getDescricao() + "', '"  
					       + produto.getPreco() + "', '" + produto.getQuant() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	//Atualizar produto;
	public void update(Produto p) {
		int index = produtos.indexOf(p);
		if (index != -1) {
			produtos.set(index, p);
			this.saveToFile();
		}
	}
	//Excluir produtos;
	public void remove(Produto p) {
		int index = produtos.indexOf(p);
		if (index != -1) {
			produtos.remove(index);
			this.saveToFile();
		}
	}
	
	public List<Produto> getAll() {
		return produtos;
	}
	
	public Produto get(int id) {
		for (Produto produto : produtos) {
			if (id == produto.getId()) {
				return produto;
			}
		}
		return null;
	}
	//Pegar os usuários;
	public Produto[] getProduto(){
		Produto[] produtos = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM produto");		
	         if(rs.next()){
	             rs.last();
	             produtos = new Produto[rs.getRow()];
	             rs.beforeFirst();
	             for(int i = 0; rs.next(); i++) {
	                produtos[i] = new produto(rs.getInt("id"), rs.getString("descricao"), 
	                rs.getFloat("preco"), rs.getInt("quantidade"));
	             }
	          }
	          st.close();
		} catch (Exception e) {System.err.println(e.getMessage());}
		return produtos;
	}
}
