package criacaoObjetosPrefeitura;

import java.util.List;
import java.util.Scanner;
import br.com.fiap.dao.CidadaoDAO;
import br.com.fiap.dao.ImovelDAO;
import br.com.fiap.dao.PersonaDAO;
import br.com.fiap.prefeitura.Cidadao;
import br.com.fiap.prefeitura.Imovel;
import br.com.fiap.prefeitura.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

/**
 * Classe com método main para menu da atividade 2 do módulo FASE 1 - JAVA
 * FOUNDATIONS & UX
 * 
 * <br>O Banco de dados deve estar com o banco e as tabelas já criadas na estrutura representada no modelo relacional representado no arquivo "modelo relacional.png"<br>
 * 
 * <br>A opção 16 populará as tabelas com exemplos uma vez criadas.<br>
 * 
 * <br>Passos sugeridos
 * <br>1-Importar o Maven Project
 * <br>2-ajustar o pom.xml (trocar o path para ojdbc8.jar, no caso está em um path absoluto em c:\ojdbc8\ojdbc8.jar)
 * <br>3-ajustar o persistence.xml com user/pass e url do banco   
 * <br>4-Configurar o Build Path para JavaSE 1.8
 * <br>5-Criar o banco as tabelas de acordo com as a Annotations (Imovel, Persona, Cidadao)
 * <br>6-A Opção 16 no meu carrega as tabelas com alguns dados (4 personas, 4 cidadãos, 4 imóveis)
 * <br>7-Realizar as opções
 * 
 * @author Ana Sofia Nunez de Abreu, Bruno Vieira Campos Gouveia, Rafael
 *         Kimihiro Moribe, Tiago Vieira Cavalcante
 * @version 1.0
 */
public class criacaoObjetosPrefeitura {

	public static void main(String[] args) {

		EntityManager em = null;

		try {
			Scanner scanner = new Scanner(System.in);
			int entrada = 0;
			em = Persistence.createEntityManagerFactory("estudando-orm").createEntityManager();
			CidadaoDAO cidadaoDAO = new CidadaoDAO(em);
			ImovelDAO imovelDAO = new ImovelDAO(em);
			PersonaDAO personaDAO = new PersonaDAO(em);

			List<Cidadao> listaCidadao = null;
			List<Imovel> listaImovel = null;
			List<Persona> listaPersona = null;

			while (entrada != 99) {
				System.out.println(
						"Selecione opção: \n1-Adicionar cidadão\n2-Adicionar imovel\n3-Adicionar persona\n4-Listar Cidadãos\n5-Listar Imóveis \n6-Listar Personas\n7-Encontrar Cidadão por Id\n8-Encontrar Imóvel por Id\n9-Encontrar persona por Id\n10-Atualizar Cidadão\n11-Atualizar Imóvel\n12-Atualizar Persona\n13-Apagar Cidadão\n14-Apagar Imóvel\n15-Apagar Persona\n16-CARREGAR BANCO COM DADOS (TABELAS PRECISAM ESTAR CRIADAS)\n99-SAIR");
				entrada = scanner.nextInt();
				scanner.nextLine();
				switch (entrada) {
				case 1:// entrada cidadao
					Cidadao cidadao = new Cidadao();
					System.out.println("Entre com nome");
					cidadao.setNome(scanner.nextLine());
					System.out.println("Entre com cpf");
					cidadao.setCpf(scanner.next());
					System.out.println("Entre com telefone");
					cidadao.setTelefone(scanner.next());
					System.out.println("Entre com Id do persona");
					listaPersona = personaDAO.listar();
					System.out.println();
					System.out.println("Numero de ocorrências: " + listaPersona.size());
					for (Persona personaTemp : listaPersona) {
						System.out.println(personaTemp.toString());
					}
					cidadao.setPersona(em.find(Persona.class, scanner.nextInt()));
					scanner.nextLine();
					em.getTransaction().begin();
					cidadaoDAO.salvar(cidadao);
					em.getTransaction().commit();

					break;

				case 2:// entrada imovel

					Imovel imovel = new Imovel();
					System.out.println("Entre com Endereco");
					imovel.setEndereco(scanner.nextLine());
					System.out.println("Entre com CEP");
					imovel.setCep(scanner.nextLine());
					System.out.println("Entre com Tamanho (área em m2)");
					imovel.setTamanho(scanner.nextInt());
					scanner.nextLine();
					System.out.println("Entre com ID do Proprietario");
					listaCidadao = cidadaoDAO.listar();

					System.out.println("Numero de ocorrências: " + listaCidadao.size());
					for (Cidadao cidadaoTemp : listaCidadao) {
						System.out.println(cidadaoTemp.toString());
					}
					imovel.setCidadao(em.find(Cidadao.class, scanner.nextInt()));
					scanner.nextLine();
					imovel.calculaIPTU();
					System.out.println("Entre a Inscrição");
					imovel.setInscricao(scanner.nextInt());
					scanner.nextLine();
					em.getTransaction().begin();
					imovelDAO.salvar(imovel);
					em.getTransaction().commit();
					break;

				case 3:// entrada persona

					Persona persona = new Persona();
					System.out.println("Entre com Descrição da Persona");
					persona.setNome_persona(scanner.nextLine());
					em.getTransaction().begin();
					personaDAO.salvar(persona);
					em.getTransaction().commit();

					break;

				case 4:// Listar cidadãos

					listaCidadao = cidadaoDAO.listar();

					System.out.println("Numero de ocorrências: " + listaCidadao.size());
					for (Cidadao cidadaoTemp : listaCidadao) {
						System.out.println(cidadaoTemp.toString());
					}
					System.out.println();

					break;

				case 5:// Listar imóveis

					System.out.println();

					listaImovel = imovelDAO.listar();
					System.out.println("Numero de ocorrências: " + listaImovel.size());
					for (Imovel imovelTemp : listaImovel) {
						System.out.println(imovelTemp.toString());
					}
					System.out.println();

					break;

				case 6:// Listar personas

					listaPersona = personaDAO.listar();
					System.out.println();
					System.out.println("Numero de ocorrências: " + listaPersona.size());
					for (Persona personaTemp : listaPersona) {
						System.out.println(personaTemp.toString());
					}
					System.out.println();

					break;

				case 7:// Consultar cidadão por Id

					System.out.println();
					System.out.println("Id do cidadão:");
					System.out.println(cidadaoDAO.recuperar(scanner.nextInt()).toString());
					scanner.nextLine();

					break;

				case 8:// Consultar endereço por Rua por ID
					System.out.println();
					System.out.println("Id do Imóvel:");
					System.out.println(imovelDAO.recuperar(scanner.nextInt()).toString());
					scanner.nextLine();

					break;

				case 9:// Consultar persona por ID
					System.out.println();
					System.out.println("Id da Persona:");
					System.out.println(personaDAO.recuperar(scanner.nextInt()).toString());

					break;

				case 10: // Atualizar Cidadao

					System.out.println("Id do Cidadao que deseja atualizar dados");

					em.getTransaction().begin();
					Cidadao cidadaoTemp = cidadaoDAO.recuperar(scanner.nextInt());
					scanner.nextLine();
					System.out.println(
							"Seleciona informação que será alterada:\n1 - Nome\n2 - CPF\n3 - Telefone\n4 - Persona");
					switch (scanner.nextInt()) {
					case 1:
						scanner.nextLine();
						System.out.println("Digite novo nome:");
						cidadaoTemp.setNome(scanner.nextLine());
						cidadaoDAO.salvar(cidadaoTemp);
						System.out.println("Nome modificado");

						break;

					case 2:
						scanner.nextLine();
						System.out.println("Digite novo CFP:");
						cidadaoTemp.setCpf(scanner.nextLine());
						cidadaoDAO.salvar(cidadaoTemp);
						System.out.println("CPF modificado");

						break;

					case 3:
						scanner.nextLine();
						System.out.println("Digite novo telefone:");
						cidadaoTemp.setTelefone(scanner.nextLine());
						cidadaoDAO.salvar(cidadaoTemp);
						System.out.println("Telefone modificado");
						break;

					case 4:

						System.out.println("Selecione nova persona da lista:");
						listaPersona = personaDAO.listar();
						System.out.println();
						System.out.println("Numero de ocorrências: " + listaPersona.size());
						for (Persona personaTemp : listaPersona) {
							System.out.println(personaTemp.toString());
						}
						System.out.println();
						cidadaoTemp.setPersona(listaPersona.get(scanner.nextInt()));
						cidadaoDAO.salvar(cidadaoTemp);
						System.out.println("Persona modificado");

						break;

					default:
						System.out.println("Opção inválida, retornando para menu principal");
						break;
					}

					em.getTransaction().commit();
					System.out.println("Cidadao alterado");

					break;

				case 11: // Atualizar Imóvel
					System.out.println("Id do Imóvel que deseja atualizar dados:");
					listaImovel = imovelDAO.listar();
					for (Imovel imovelTemp : listaImovel) {
						System.out.println(imovelTemp.toString());
					}
					Imovel imovelTemp = imovelDAO.recuperar(scanner.nextInt());

					System.out.println(
							"Seleciona informação que será alterada:\n1 - Endereço\n2 - CEP\n3 - Tamanho\n4 - Cidadão(proprietário)");
					switch (scanner.nextInt()) {
					case 1:// Corrigir Endereco
						scanner.nextLine();
						em.getTransaction().begin();
						System.out.println("Digite novo endereço:");
						imovelTemp.setEndereco(scanner.nextLine());
						imovelDAO.salvar(imovelTemp);
						System.out.println("Endereco alterado");
						em.getTransaction().commit();
						break;
					case 2:// Corrigir CEP
						scanner.nextLine();
						em.getTransaction().begin();
						System.out.println("Digite novo CEP:");
						imovelTemp.setCep(scanner.nextLine());
						imovelDAO.salvar(imovelTemp);
						System.out.println("Endereco alterado");

						em.getTransaction().commit();
						break;
					case 3:// Corrigir Tamanho, e depois IPTU
						scanner.nextLine();
						em.getTransaction().begin();
						System.out.println("Digite nova área em m2:");
						imovelTemp.setTamanho(scanner.nextInt());
						scanner.nextLine();
						imovelTemp.calculaIPTU();
						imovelDAO.salvar(imovelTemp);
						em.getTransaction().commit();
						break;

					case 4:// Corrigir Cidadao
						em.getTransaction().begin();
						System.out.println("Selecione novo Cidadão:");
						listaCidadao = cidadaoDAO.listar();
						for (Cidadao cidadaoTemp1 : listaCidadao) {
							System.out.println(cidadaoTemp1.toString());
						}
						imovelTemp.setCidadao(cidadaoDAO.recuperar(scanner.nextInt()));
						scanner.nextLine();
						imovelDAO.salvar(imovelTemp);
						System.out.println("Proprietário do imóvel alterado");
						em.getTransaction().commit();
						break;

					default:
						break;
					}

					break;

				case 12: // Atualizar Persona
					System.out.println("Id da Persona que deseja atualizar o nome:");
					em.getTransaction().begin();
					listaPersona = personaDAO.listar();
					for (Persona personaTemp : listaPersona) {
						System.out.println(personaTemp.toString());
					}
					Persona personaTemp = personaDAO.recuperar(scanner.nextInt());
					scanner.nextLine();

					System.out.println("Digite o novo nome da Persona:");
					personaTemp.setNome_persona(scanner.nextLine());
					personaDAO.salvar(personaTemp);

					System.out.println("Nome da Persona atualizado com sucesso");
					em.getTransaction().commit();
					break;

				case 13: // Apagar Cidadao
					System.out.println("Id do Cidadao que será removido::");
					em.getTransaction().begin();
					cidadaoDAO.excluir(scanner.nextInt());
					scanner.nextLine();
					em.getTransaction().commit();
					System.out.println("Cidadao apagado");
					break;

				case 14: // Apagar Imovel
					System.out.println("Id do Imóvel que será removido:");
					em.getTransaction().begin();
					imovelDAO.excluir(scanner.nextInt());
					scanner.nextLine();
					em.getTransaction().commit();
					System.out.println("Imóvel apagado");
					break;

				case 15: // Apagar Persona
					System.out.println("Id da Persona que será removido:");
					em.getTransaction().begin();
					personaDAO.excluir(scanner.nextInt());
					scanner.nextLine();
					em.getTransaction().commit();
					System.out.println("Persona apagado");
					break;

				case 16: // Carregar banco com dados
					PopulaBD.populaBD(em);

					break;

				default:
					break;

				}

			}
			scanner.close();

		} catch (Exception e) {
			if (em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

		} finally {

			em.getTransaction().commit();
			if (em != null) {
				em.close();

			}

		}

		System.exit(0);

	}

};