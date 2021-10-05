package gabriel1;


public class Usuario{
	private int codigo;
	private String login;
	private String senha;
	private int idade;
	private String estado;
	
	public Usuario(){
		this.codigo = -1;
		this.login = "";
		this.senha = "";
		this.idade = -1;
		this.estado = "";
	}
	
	public Usuario(int codigo, String login, String senha, int idade, String estado){
		this.codigo = codigo;
		this.login = login;
		this.senha = senha;
		this.idade = idade;
		this.estado = estado;
	}
	//Codigo;
	public int getCodigo(){return codigo;}
	public void setCodigo(int codigo){this.codigo = codigo;}
	//Login;
	public String getLogin(){return login;}
	public void setLogin(String login){this.login = login;}
	//Senha;
	public String getSenha(){return senha;}
	public void setSenha(String senha){this.senha = senha;}
	//Idade;
	public int getIdade(){return idade;}
	public void setIdade(int idade){this.idade = idade;}
	//Estado;
	public String getEstado(){return estado;}
	public void setEstado(String estado){this.estado = estado;}

	public String toString() {
		return "Usuario [codigo = " + codigo + ", login = " + login + ", senha = " + senha + ", idade = " + idade + ", estado = " + estado + "]";
	}	
}