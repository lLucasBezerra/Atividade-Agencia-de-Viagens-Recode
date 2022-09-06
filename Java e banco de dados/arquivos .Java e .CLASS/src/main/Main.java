package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import dao.ClienteDAO;
import dao.DestinoDAO;
import dao.PromocoesDAO;
import dao.VooDAO;
import model.Cliente;
import model.Destino;
import model.Promocoes;
import model.Voo;



//RECOMENDO COMEÇAR A PELA CLASSE MAIN NA LINHA 339
public class Main {
	static Scanner entrada = new Scanner(System.in);
	static SimpleDateFormat modeloData = new SimpleDateFormat("dd/MM/yyyy");
	
	static Cliente cliente = new Cliente();
	static Destino destino = new Destino();
	static ArrayList<Cliente> clientes = new ArrayList<>();
	
	static ClienteDAO cliDAO = new ClienteDAO();
	static DestinoDAO destDAO = new DestinoDAO();
	static VooDAO vooDAO = new VooDAO();
	static PromocoesDAO promoDAO = new PromocoesDAO();
	
	//PARA O MENU CLIENTE ---------------------------------------- {>
	//CADASTRANDO UM CLIENTE
	public static void cadastrarCli(Cliente cliente) throws ParseException, SQLException{
		System.out.println("---------------------------------------");
		
			System.out.println("Informe Seu CPF: [APENAS NÚMERO]");
			cliente.setCpf(entrada.next());
		
			System.out.println("Informe o sua cidade atual:");
			entrada.nextLine();													//INFORMAÇÕES BASICAS
			cliente.setOrigem(entrada.nextLine());
			
			System.out.println("Informe a data de Ida: [dd/MM/yyyy]");
			cliente.setDataIda(modeloData.parse(entrada.next()));
			
			System.out.println("Informe a data que quer voltar:[dd/MM/yyyy]");
			cliente.setDataVolta(modeloData.parse(entrada.next()));
			
			cliDAO.save(cliente); //INSERINDO
			System.out.println("--- CLIENTE SALVO ---");
			System.out.println("---------------------------------------");
			System.out.println("--                          --");
			System.out.println("ESCOLHA SEU DESTINO PELO -ID-:");				 //ESCOLHENDO UM DESTINO
			System.out.println("--                          --");
			try {
				ResultSet resultado = destDAO.getDestinos();
				while(resultado.next()) {
					System.out.println("-=-===-=-===-=-===-=-===-=-===-=-");
					System.out.println("ID -- > "+resultado.getInt(1));
					System.out.println("PAÍS -- > "+resultado.getString(2));
					System.out.println("CIDADE -- >"+resultado.getString(3));	
					System.out.println("OBRA RELACIONADA -- > "+resultado.getString(4));

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Destino destino = new Destino();
			int opcaoDestino = entrada.nextInt();
			cliente.setDestino(destino);
			cliente.getDestino().setId(opcaoDestino); //ESCOLHA
			System.out.println("--- DESTINO SALVO ---");
			
			System.out.println("---------------------------------------");
			System.out.println("--                                  --");
			System.out.println("ESCOLHA UMA COMPANHIA AÉREA PELO -ID-:");				 //ESCOLHENDO UMA COMPANHIA
			System.out.println("--                                  --");
			ResultSet resultado = vooDAO.getTodosVoo();
			
			while(resultado.next()) {
				System.out.println("-=-===-=-===-=-===-=-===-=-===-=-");
				System.out.println("ID -- > "+ resultado.getInt(1));
				System.out.println("COMPANHIA -- > "+ resultado.getString(2));
				System.out.println("PREÇO -- > "+ resultado.getDouble(3));
			}
			
			int opcaoComp = entrada.nextInt();
			Voo voo = new Voo();
			cliente.getDestino().setVoo(voo);
			cliente.getDestino().getVoo().setId(opcaoComp); //ESCOLHA
			clientes.add(cliente);
			
			cliDAO.pegarID(cliente); //PEGANDO ID
			cliDAO.fazerRelacao(cliente); // PARA RELAÇÃO MUITO PARA MUITO
		System.out.println("---------------------------------------");
		
	}
	//CONSULTANDO OS DADOS DO CLIENTE
	public static void consulta() throws SQLException{
		System.out.println("---------------------------------------");
		System.out.println("--- VOCÊ... ---");
		System.out.println("1 -- > ESTÁ CADASTRANDO AGORA");
		System.out.println("2 -- > JÁ CADASTROU ANTES"); //FOI UMA FORMA QUE ENCONTEI PRA NÃO HAVER ERRO CASO JA TENHA CADASTRADO 
														//E NÃO PRECISE FAZER TUDO NOVAMENTE
		int opcaoConsulta = entrada.nextInt();
		
		switch(opcaoConsulta){
			case 1:
				System.out.println("Informe seu CPF: [APENAS NÚMERO]");
				String cpf = entrada.next();
				
				if(!clientes.isEmpty()) {
					
					for(Cliente cliente : clientes) {
						
						if(cliente.getCpf().equalsIgnoreCase(cpf)) {
							
							System.out.println("-=-===-=-===-=-===-=-===-=-===-=-");
							cliDAO.getCpfCliente(cliente);
							System.out.println("-=-===-=-===-=-===-=-===-=-===-=-");
							destDAO.getUmDestino(cliente.getDestino().getId());
							
							if(cliente.getDestino().getVoo().getId() != 0) {
								
								System.out.println("-=-===-=-===-=-===-=-===-=-===-=-");
								vooDAO.getIdVoo(cliente.getDestino().getVoo().getId());
							}
								
						}
					}
				}else {
					System.out.println("--- CADASTRE-SE PRIMEIRO! ---");
				}
				break;
			case 2:
				System.out.println("Informe seu cpf: [APENAS NÚMERO]");
				Cliente cpff = new Cliente();
				cpff.setCpf(entrada.next());
				
				System.out.println("-=-===-=-===-=-===-=-===-=-===-=-");
				cliDAO.getCpfCliente(cpff);
				System.out.println("-=-===-=-===-=-===-=-===-=-===-=-");
				
				System.out.println("Informe o id do seu Destino:");
				int idDest = entrada.nextInt();
				
				System.out.println("-=-===-=-===-=-===-=-===-=-===-=-");
				destDAO.getUmDestino(idDest);
				System.out.println("-=-===-=-===-=-===-=-===-=-===-=-");
				
				System.out.println("Informe o id da sua escolha de Companhia:");
				int idVoo = entrada.nextInt();
				
				System.out.println("-=-===-=-===-=-===-=-===-=-===-=-");
				vooDAO.getIdVoo(idVoo);
				System.out.println("-=-===-=-===-=-===-=-===-=-===-=-");
				break;
		}
	}
	
	
	//<}---------------------------------------- FIM DAS OPÇÕES PARA O CLIENTE
	
	
	//PARA O MENU FUNCIONARIO ---------------------------------------- {>
	//CONSULTAR TODOS OS CLIENTE
	public static void consultarTodos() {
		System.out.println("---------------------------------------");
		System.out.println("---     MOSTRANDO TODOS OS CLIENTES     ---");
		try {
			ResultSet resultado = cliDAO.getClientes();
			while(resultado.next()) {
				System.out.println("-=-===-=-===-=-===-=-===-=-===-=-");
				System.out.println("ID -- > "+resultado.getInt(1));
				System.out.println("CPF -- > "+resultado.getString(2));
				System.out.println("CIDADE DE ORIGEM -- >"+resultado.getString(3));	
				System.out.println("DATA DE VOLTA -- > "+resultado.getDate(4));
				System.out.println("DATA DE IDA -- > "+resultado.getDate(5));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("---------------------------------------");	
	}
	//DELETANDO CLIENTE
	public static void deletarCli() {
		System.out.println("---------------------------------------");
		System.out.println("Informe o id do cliente que queira deletar:");
		int deletarCli = entrada.nextInt();
		cliDAO.removerRelação(deletarCli);
		cliDAO.removeById(deletarCli);
		System.out.println("deletado com sucesso.");
		System.out.println("---------------------------------------");
		
	}
	//ADICIONANDO UMA PROMOÇÃO
	public static void adcPromo() {
		System.out.println("---------------------------------------");
		System.out.println("digite a porcentagem q você desejar por nessa promoção:");
		int descontar = entrada.nextInt();
		
		System.out.println("digite o nome para essa nova promoção");
		entrada.nextLine();
		String nomePromo = entrada.nextLine();
		Promocoes promo = new Promocoes();
		promo.setDesconto(descontar);
		promo.setNomePromo(nomePromo);
		promoDAO.create(promo);
		System.out.println("--- PROMOÇÃO ADICIONADA ---");
		System.out.println("---------------------------------------");
			
	}
	//VER TODAS AS PROMOÇÕES
	public static void verPromos() throws SQLException {
		System.out.println("---------------------------------------");
		System.out.println("--                --");
		System.out.println("MOSTRANDO PROMOÇÕES:");
		System.out.println("--                --");
		ResultSet resultado = promoDAO.getPromos();
		
		while(resultado.next()) {
			System.out.println("-=-===-=-===-=-===-=-===-=-===-=-");
			System.out.println("ID -- > "+ resultado.getInt(1));
			System.out.println("DESCONTO -- > "+ resultado.getInt(2));
			System.out.println("NOME DA PROMOÇÃO -- > "+ resultado.getString(3));
		}
		
		System.out.println("deseja adicionar uma promoção em um destino?");
		System.out.println("1-SIM / 2-NÃO");
		int opPromo = entrada.nextInt();
		if(opPromo == 1) {
			System.out.println("Digite o id do Destino:");
			int idDest = entrada.nextInt();
			System.out.println("Digite o id da Promoção que queira ligar ao Destino:");
			int idPromo = entrada.nextInt();
			destDAO.adcUmaPromo(idDest, idPromo);
			System.out.println("--- PROMOÇÃO APLICADA ---");
		}
		System.out.println("---------------------------------------");
		
	}
	//ADICIONAR DESTINO
	public static void adcDestino() {
		System.out.println("---------------------------------------");
		System.out.println("Digite o nome do país do novo destino:");
		entrada.nextLine();
		destino.setPais(entrada.nextLine());
		System.out.println("Digite a cidade do novo destino");
		destino.setCidade(entrada.next());
		System.out.println("Digite a obra relacionada ao destino");
		entrada.nextLine();
		destino.setObraR(entrada.nextLine());
		
		destDAO.save(destino);
		
		System.out.println("--- DESTINO SALVO ---");
		System.out.println("---------------------------------------");
	}
	//DELETANDO DESTINO
	public static void deletarDestino() {
		System.out.println("---------------------------------------");
		System.out.println("Informe o ID do destino que queira deletar:");
		int deletarDest = entrada.nextInt();
		destDAO.removeById(deletarDest);
		System.out.println("deletado com sucesso.");
		System.out.println("---------------------------------------");
		
	}
	//MOSTAR TODOS DESTINOS
	public static void mostrarDestino() throws SQLException {
ResultSet resultado = destDAO.getDestinos();
		
		while(resultado.next()) {
			System.out.println("-=-===-=-===-=-===-=-===-=-===-=-");
			System.out.println("ID -- > "+ resultado.getInt(1));
			System.out.println("PAÍSES -- > "+ resultado.getString(2));
			System.out.println("CIDADE -- > "+ resultado.getString(3));
			System.out.println("OBRA RELACIONADA -- > "+ resultado.getString(4));
			System.out.println("ID PROMOÇÃO -- > "+ resultado.getDouble(5));
		}
	}
	
	public static void deletarPromo() {
		System.out.println("---------------------------------------");
		System.out.println("Digite o ID da promoção que queira deletar:");
		int deletarPromo = entrada.nextInt();
		promoDAO.removeById(deletarPromo);
		System.out.println("--- PROMOÇÃO REMOVIDA ---");
		System.out.println("---------------------------------------");
	}
	//<}---------------------------------------- FIM DAS OPÇÕES PARA O FUNCIONARIO
	
	
	//CRIANDO OS MENUS ---------------------------------------- {>
	//MENU PRINCIPAL
	public static int menuFuncoes() {
		System.out.println("-------- VOCÊ É: --------");
		System.out.println("1 -- > CLIENTE");
		System.out.println("2 -- > FUNCIONÁRIO");
		int opcao = entrada.nextInt();
		return opcao;
	}
	//MENU PARA CLIENTE
	public static int menuCliente() {
		System.out.println("========== BEM VINDO A AGENCIA VIAGENS&CINEMA ==========");
		System.out.println("-------- MENU DE ESCOLHA --------");
		System.out.println("1 -- > CADASTRO E DEFINIR DESTINO");
		System.out.println("2 -- > CONSULTAR DADOS CADASTRADO");
		System.out.println("3 -- > SAIR");
		int opcaoCli = entrada.nextInt();
		return opcaoCli;
	}
	//MENU PARA "FUNCIONÁRIO"
	public static int menuFuncionario() {
		System.out.println("-------- ESCOLHA O QUE QUER FAZER --------");
		System.out.println("1 -- > CONSULTAR TODOS OS CLIENTES");
		System.out.println("2 -- > APAGAR CLIENTE(POR ID)");
		System.out.println("3 -- > ADICONAR PROMOÇÃO");
		System.out.println("4 -- > CONSULTAR TODAS AS PROMOÇÕES");
		System.out.println("5 -- > ADICIONAR UM DESTINO");
		System.out.println("6 -- > REMOVER DESTINO");
		System.out.println("7 -- > MOSTRAR TODOS OS DESTINOS");
		System.out.println("8 -- > REMOVER PROMOÇÃO");
		System.out.println("9 -- > SAIR");
		
		int opcaoFunc = entrada.nextInt();
		return opcaoFunc;
	}
	//<}---------------------------------------- FIM MENU
	
	
	
	public static void main(String[] args)  throws ParseException, SQLException{
		int menuFunc = menuFuncoes(); //COMEÇANDO MENU PRINCIPAL   //MENU FUNCÕES NA LINHA 303
		
		
		switch(menuFunc) {
			
			case 1:
				int menuCliente = menuCliente(); //INICIANDO MENU DO CLIENTE  //MENU CLIENTE NA LINHA 311
				boolean loop = true;  //VARIAVEL PARA O LOOP WHILE DE CLIENTE
				
				while(loop) {
					
					switch(menuCliente) {
					case 1:
						cadastrarCli(cliente); // CODIGO NA LINHA 37
						menuCliente = menuCliente();
						break;
					
					case 2:
						consulta(); // CODIGO NA LINHA 104
						menuCliente = menuCliente();
						break;
					
					case 3:
						System.out.println("-------- SAINDO DO MENU CLIENTE. . . --------");
						loop = false;
						break;
						
					default:
						System.out.println("OPÇÃO INVALIDA");
						menuCliente = menuCliente(); //pôr isso é extremamente importante
						break;
					}
					
				}
				break;
				
			case 2:
				int menuFuncionario = menuFuncionario(); //INICIANDO MENU DO FUNCIONARIO  //MENU FUNCIONÁRIO 321
				boolean laco = true; //VARIAVEL PARA O LOOP WHILE DE FUNCIONARIO
				
				while(laco) {
					
					switch(menuFuncionario) {
					case 1:
						consultarTodos(); // CODIGO NA LINHA 172
						menuFuncionario = menuFuncionario();
						break;
					case 2:
						deletarCli(); // CODIGO NA LINHA 193
						menuFuncionario = menuFuncionario();
						break;
					case 3:
						adcPromo(); // CODIGO NA LINHA 204
						menuFuncionario = menuFuncionario();
						break;
					case 4:
						verPromos(); // CODIGO NA LINHA 221
						menuFuncionario = menuFuncionario();
						break;
					case 5:
						adcDestino(); // CODIGO NA LINHA 250
						menuFuncionario = menuFuncionario();
						break;
					case 6:
						deletarDestino(); // CODIGO NA LINHA 267
						menuFuncionario = menuFuncionario();
						break;
					case 7:
						mostrarDestino(); // CODIGO NA LINHA 277
						menuFuncionario = menuFuncionario();
						break;
					case 8:
						deletarPromo(); // CODIGO NA LINHA 290
						menuFuncionario = menuFuncionario();
					case 9:
						System.out.println("-------- SAINDO DO MENU FUNCIONARIO. . . --------");
						laco = false;
						break;
					default:
						System.out.println("OPÇÃO INVALIDA");
						menuFuncionario = menuFuncionario();
						break;
					}
					
				}
				break;
		}
	
		
		
	}
}
