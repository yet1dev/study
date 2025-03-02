//================================================================
//               PROG-2 VA1: Emmanuel N. C. Brito
//================================================================
import java.util.*;

public class prova {
	public static void main(String[] str){
		my.print("%nQUESTAO 5E (Classe Teste): %n");
		Teste.main();
		my.print("---------------------------------%n");
	
	// TESTES DE OUTRAS QUESTOES
		my.print("%nQUESTAO 5A (Classe Pessoa): %n");		
		AutoRepo.get(0).pessoa.getResumo();

		my.print("%nQUESTAO 5B (Classe Residencia): %n");		
		AutoRepo.get(0).residencia.getResumo();

		my.print("%nQUESTAO 5C (Classe Aluguel): %n");
		AutoRepo.get(0).aluguel.getResumo();

		my.print("%nQUESTAO 5D (Metodo toString): %n");		
		AutoRepo.get(0).pessoa.toString();
		AutoRepo.get(0).residencia.toString();
		AutoRepo.get(0).aluguel.toString();
	
		my.print("%nQUESTAO 6A (Lista de Alugueis): %n");
		RepositorioAluguel repo = new RepositorioAluguel();
		repo.add(AutoRepo.get(0).aluguel);  // adiciona primeiro aluguel
		repo.add(AutoRepo.get(2).aluguel);  // adiciona terceiro aluguel
		repo.add(AutoRepo.get(3).aluguel);  // adiciona quarto aluguel
	
		System.out.println("\n  LISTA: "+repo.lista + 
				"\n     | Em java o metodo toString identifica a conversao"+
				"\n     | da classe para string. Como a questao 5D obriga"+
				"\n     | que printe algo, este algo sera printado para"+
				"\n     | cada elemento que for convertido.");
		
		my.print("%nQUESTAO 6B (Pessoas que alugaram mesma Casa): %n");
		System.out.println("  LISTA (Casa_3): "+repo.alugueis("Casa_3"));

		my.print("%nQUESTAO 6C (Casas alugadas pela mesma Pessoa): %n");
		System.out.println("  LISTA (John): "+repo.pessoas("John"));

		my.print("%nQUESTAO 6 Extra (Teste AutoRepo) %n");
		System.out.println("  LISTA (John): "+AutoRepo.findPessoa("John"));
		System.out.println("  LISTA (Casa_3): "+AutoRepo.findCasa("Casa_3"));
	}
}

//================================================================
//                     MINI BIBLIOTECA
//================================================================
class my {
	static int toint(boolean test){ return test ? 1 : 0; }
	static String toStr(int num){ return Integer.toString(num); }
	static String toStr(double num){ return Double.toString(num); }

	static void print(String msg, Object... args) { System.out.printf(msg, args); }
	static Scanner read(String msg, Object... args) { print(msg, args); return new Scanner(System.in); }
}

//================================================================
//                     5A - CLASSE PESSOA
//================================================================
public class Pessoa{
	public int fidelidade = 0; // Guardar pontuacao da pessoa

	public String nome;
	public String nasc;
	public String cpf;  // CPFs devem ser Strings. pois, codigos
                      // mudam com o tempo e podem inserir letras.

// 5D - METODO TO STRING        <<-------------------------- 5D
	public String toString(){
		my.print("  %s %n", this.nome);
		return this.nome;
	}

// GETS DA CLASSE
//-------------------------------------------------------------
	public String getCpf(){ return this.cpf; }
	public String getName(){ return this.nome; }
	public String getNasc(){ return this.nasc; }
	public int getPontos(){ return this.fidelidade; }

	public void getResumo(){ my.print("  %s, cpf: %s, nascido em %s, tem %d pontos; %n", 
			this.nome, this.cpf, this.nasc, this.fidelidade); }

// SETS DA CLASSE
	public void setName(String nome){ this.nome = nome; }
	public void setNasc(String nome){ this.nasc = nasc; }
	public void setCpf(String nome){ this.cpf = cpf; }
	public void setPontos(int pontos){ this.fidelidade = pontos; }
	
// CONSTRUTOR
//-------------------------------------------------------------
	public Pessoa(String nome, String nasc, String cpf){
		this.nome = nome;
		this.nasc = nasc;
		this.cpf = cpf;
	}
}
//================================================================
//                  5B - CLASSE RESIDENCIA
//================================================================
public class Residencia{
	public String nome;
	public String descricao;
	public int precoCentavos; // Nao podemos usar float para dinheiro

// 5D - METODO TO STRING        <<-------------------------- 5D
	public String toString(){
		my.print("  %s: R$ %d.%02d %n",
			this.nome, this.precoCentavos/100, this.precoCentavos%100);
		return this.nome+", "+this.precoCentavos;
	} 

// GETS DA CLASSE
//-------------------------------------------------------------
	public String getNome(){ return this.nome; }
	public int getPreco(){ return this.precoCentavos; }
	public String getDescricao(){ return this.descricao; }

	public void getResumo(){
		my.print("  %s tem diaria de R$ %d.%02d. Descricao: %s; %n",
			this.nome, this.precoCentavos/100, this.precoCentavos%100, this.descricao);
	}

// SETS DA CLASSE
	public void setName(String nome){ this.nome = nome; }
	public void setPreco(int preco){ this.precoCentavos = preco; }
	public void setDescricao(String desc){ this.descricao = desc; }

// CONSTRUTOR DA CLASSE
//-------------------------------------------------------------
	public Residencia(String nome, String descricao, int precoCentavos){
		this.nome = nome;
		this.descricao = descricao;
		this.precoCentavos = precoCentavos;  // dinheiro deve ser armazenado em centavos
	}
}
//================================================================
//                     5C - CLASSE ALUGUEL
//================================================================
public class Aluguel{
	public Aluguel aluguel;
	public Pessoa pessoa;
	public Residencia residencia;
	public String dataEntrada;
	public int quantDiarias;
	public int valor;           // valor: Dinheiro deve ser armazenado em centavos
	public int fidelidade;      // fidelidade do cliente no momento do aluguel
	public int desconto = 0;    // valor percentual do desconto

// 5D - METODO TO STRING        <<-------------------------- 5D
	public String toString(){
		my.print("  %s: %s (%d Diarias) %n", this.pessoa.nome, this.residencia.nome, this.quantDiarias);
		return String.format("%s/%s: %s, R$ %d.%02d", 
			this.pessoa.nome, this.residencia.nome,
			this.dataEntrada, this.valor/100, this.valor%100);
	}

// SETS DA CLASSE
//-------------------------------------------------------------
	public void setValor(int valor){ this.valor = valor; }
	public void setDiarias(int quant){ this.quantDiarias = quant; }
	public void setPessoa(Pessoa pessoa){ this.pessoa = pessoa; }
	public void setEntrada(String data){ this.dataEntrada = data; }
	public void setResidencia(Residencia res){ this.residencia = res; }

// GETS DA CLASSE
	public int getValor(){ return this.valor; }
	public int getDiarias(){ return this.quantDiarias; }
	public String getPessoa(){ return this.pessoa.nome; }
	public String getEntrada(){ return this.dataEntrada; }
	public String getResidencia(){ return this.residencia.nome; }

	public void getResumo(){
		my.print("  %s %s: %s a R$ %d.%02d por %d dias com %d%% de desconto e ficou com %d Pontos; %n", 
				this.dataEntrada, this.pessoa.nome, this.residencia.nome,
				this.valor/100, this.valor%100,
				this.quantDiarias, this.desconto, this.fidelidade);
	}

	public void getFidelidade(){  // +1 ponto por teste positivo
		this.pessoa.fidelidade += 
			my.toint(this.valor>=200*100) + 
			my.toint(this.valor>=801*100) +
			my.toint(this.valor>2000*100);
	}

	public void getDesconto(){   // Desconto e diminui pontos  
		if (pessoa.fidelidade>=30){
			this.desconto = 40;          // 40% de desconto
			pessoa.fidelidade-=30;
	
		} else if (pessoa.fidelidade>=20){
			this.desconto = 25;          // 25% de desconto
			pessoa.fidelidade-=20;
		
		} else if (pessoa.fidelidade>=10){
			this.desconto = 10;          // 10% de desconto
			pessoa.fidelidade-=10;
		}

		this.valor -= this.desconto*this.valor/100;  // aplica desconto
	}

// CONSTRUTOR DA CLASSE
//-------------------------------------------------------------
	public Aluguel(Pessoa pessoa, Residencia residencia, String dataEntrada, int quantDiarias){
		this.pessoa = pessoa;
		this.aluguel = this;
		this.residencia = residencia;
		this.dataEntrada = dataEntrada;
		this.quantDiarias = quantDiarias;
		this.valor = this.quantDiarias * this.residencia.getPreco();

		getFidelidade();                            // adiciona pontos
		getDesconto();                              // fornece desconto
		this.fidelidade = this.pessoa.fidelidade;   // guarda fidelidade atual
		AutoRepo.add(this);              // guarda no repositorio
	}
}
//================================================================
//                    5E - CLASSE TESTE
//================================================================
public class Teste {
	public static void main(){
		Pessoa pessoaA = new Pessoa("John", "22/01/1998", "12312312312");
		Pessoa pessoaB = new Pessoa("Maria", "22/01/1998", "12312312312");
		Pessoa pessoaC = new Pessoa("Felipe", "22/01/1998", "12312312312");
	
		Residencia res1 = new Residencia("Casa_1", "bonita", 200*100);  // +1 ponto
		Residencia res2 = new Residencia("Casa_2", "bonita", 801*100);  // +2 pontos
		Residencia res3 = new Residencia("Casa_3", "bonita", 2001*100); // +3 pontos
		
		Aluguel aluguel1 = new Aluguel(pessoaA, res1, "01/01/2025", 1);
		Aluguel aluguel2 = new Aluguel(pessoaB, res2, "02/01/2025", 1);
		Aluguel aluguel3 = new Aluguel(pessoaC, res3, "03/01/2025", 1);
		Aluguel aluguel4 = new Aluguel(pessoaA, res3, "04/01/2025", 1);
		
		pessoaA.getResumo();
		pessoaB.getResumo();
		pessoaC.getResumo();
		my.print("%n");
		
		res1.getResumo();
		res1.getResumo();
		res1.getResumo();
		my.print("%n");
		
		aluguel1.getResumo();
		aluguel2.getResumo();
		aluguel3.getResumo();
		aluguel4.getResumo();
	}
}

//================================================================
//               6A - CLASSE RepositorioAluguel
//================================================================
public class RepositorioAluguel {
	ArrayList<Aluguel> lista;

// CONSTRUTOR DA CLASSE
	public RepositorioAluguel(){
		this.lista = new ArrayList<Aluguel>();  // Iniciado no construtor - 6A
	}

// GETS E SETS DA CLASSE
	public Aluguel get(int i){ return this.lista.get(i); }
	public void add(Aluguel aluguel){ this.lista.add(aluguel); }

// QUESTAO 6B (Metodo alugueis)
	ArrayList<String> alugueis(String casa){
		ArrayList<String> pessoas = new ArrayList<String>();

		for (int i=0; i < this.lista.size(); i++){
			if (this.get(i).getResidencia() == casa){
				pessoas.add(this.get(i).getPessoa());
			}
		}
		return pessoas;
	}

// QUESTAO 6C (Metodo pessoas) 
	ArrayList<String> pessoas(String pessoa){
		ArrayList<String> casas = new ArrayList<String>();
		
		for (int i=0; i < this.lista.size(); i++){
			if (this.get(i).getPessoa() == pessoa){
				casas.add(this.get(i).getResidencia());
			}
		}
		return casas;
	}
}

//================================================================
// AutoRepo: Uma alternativa que tem lista estatica, pois todo
// aluguel sera adicionado automaticamente assim que criado.
// Para funcionar basta adicionar isto no construtor de Aluguel. 
//================================================================
public class AutoRepo {
	static ArrayList<Aluguel> lista = new ArrayList<Aluguel>();

// SETS E GETS DA CLASSE
	static Aluguel get(int i){ return lista.get(i); }
	static void add(Aluguel aluguel){ lista.add(aluguel); }

// METODO DE PESQUISA POR CASA
	static ArrayList<String> findCasa(String casa){
		ArrayList<String> pessoas = new ArrayList<String>();

		for (int i=0; i < lista.size(); i++){
			if (get(i).getResidencia() == casa){
				pessoas.add(get(i).getPessoa());
			}
		}
		return pessoas;
	}
 
// METODO DE PESQUISA POR PESSOA
	static ArrayList<String> findPessoa(String pessoa){
		ArrayList<String> casas = new ArrayList<String>();
		
		for (int i=0; i < lista.size(); i++){
			if (get(i).getPessoa() == pessoa){
				casas.add(get(i).getResidencia());
			}
		}
		return casas;
	}
}

