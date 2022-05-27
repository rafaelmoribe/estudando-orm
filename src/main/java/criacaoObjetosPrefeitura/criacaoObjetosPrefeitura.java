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
 * Classe com m�todo main para menu da atividade 2 do m�dulo FASE 1 - JAVA
 * FOUNDATIONS & UX
 * 
 * <br>O Banco de dados deve estar com o banco e as tabelas j� criadas na estrutura representada no modelo relacional representado no arquivo "modelo relacional.png"<br>
 * 
 * <br>A op��o 16 popular� as tabelas com exemplos uma vez criadas.<br>
 * 
 * <br>Passos sugeridos
 * <br>1-Importar o Maven Project
 * <br>2-ajustar o pom.xml (trocar o path para ojdbc8.jar, no caso est� em um path absoluto em c:\ojdbc8\ojdbc8.jar)
 * <br>3-ajustar o persistence.xml com user/pass e url do banco   
 * <br>4-Configurar o Build Path para JavaSE 1.8
 * <br>5-Criar o banco as tabelas de acordo com as a Annotations (Imovel, Persona, Cidadao)
 * <br>6-A Op��o 16 no meu carrega as tabelas com alguns dados (4 personas, 4 cidad�os, 4 im�veis)
 * <br>7-Realizar as op��es
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
						"Selecione op��o: \n1-Adicionar cidad�o\n2-Adicionar imovel\n3-Adicionar persona\n4-Listar Cidad�os\n5-Listar Im�veis \n6-Listar Personas\n7-Encontrar Cidad�o por Id\n8-Encontrar Im�vel por Id\n9-Encontrar persona por Id\n10-Atualizar Cidad�o\n11-Atualizar Im�vel\n12-Atualizar Persona\n13-Apagar Cidad�o\n14-Apagar Im�vel\n15-Apagar Persona\n16-CARREGAR BANCO COM DADOS (TABELAS PRECISAM ESTAR CRIADAS)\n99-SAIR");
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
					System.out.println("Numero de ocorr�ncias: " + listaPersona.size());
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
					System.out.println("Entre com Tamanho (�rea em m2)");
					imovel.setTamanho(scanner.nextInt());
					scanner.nextLine();
					System.out.println("Entre com ID do Proprietario");
					listaCidadao = cidadaoDAO.listar();

					System.out.println("Numero de ocorr�ncias: " + listaCidadao.size());
					for (Cidadao cidadaoTemp : listaCidadao) {
						System.out.println(cidadaoTemp.toString());
					}
					imovel.setCidadao(em.find(Cidadao.class, scanner.nextInt()));
					scanner.nextLine();
					imovel.calculaIPTU();
					System.out.println("Entre a Inscri��o");
					imovel.setInscricao(scanner.nextInt());
					scanner.nextLine();
					em.getTransaction().begin();
					imovelDAO.salvar(imovel);
					em.getTransaction().commit();
					break;

				case 3:// entrada persona

					Persona persona = new Persona();
					System.out.println("Entre com Descri��o da Persona");
					persona.setNome_persona(scanner.nextLine());
					em.getTransaction().begin();
					personaDAO.salvar(persona);
					em.getTransaction().commit();

					break;

				case 4:// Listar cidad�os

					listaCidadao = cidadaoDAO.listar();

					System.out.println("Numero de ocorr�ncias: " + listaCidadao.size());
					for (Cidadao cidadaoTemp : listaCidadao) {
						System.out.println(cidadaoTemp.toString());
					}
					System.out.println();

					break;

				case 5:// Listar im�veis

					System.out.println();

					listaImovel = imovelDAO.listar();
					System.out.println("Numero de ocorr�ncias: " + listaImovel.size());
					for (Imovel imovelTemp : listaImovel) {
						System.out.println(imovelTemp.toString());
					}
					System.out.println();

					break;

				case 6:// Listar personas

					listaPersona = personaDAO.listar();
					System.out.println();
					System.out.println("Numero de ocorr�ncias: " + listaPersona.size());
					for (Persona personaTemp : listaPersona) {
						System.out.println(personaTemp.toString());
					}
					System.out.println();

					break;

				case 7:// Consultar cidad�o por Id

					System.out.println();
					System.out.println("Id do cidad�o:");
					System.out.println(cidadaoDAO.recuperar(scanner.nextInt()).toString());
					scanner.nextLine();

					break;

				case 8:// Consultar endere�o por Rua por ID
					System.out.println();
					System.out.println("Id do Im�vel:");
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
							"Seleciona informa��o que ser� alterada:\n1 - Nome\n2 - CPF\n3 - Telefone\n4 - Persona");
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
						System.out.println("Numero de ocorr�ncias: " + listaPersona.size());
						for (Persona personaTemp : listaPersona) {
							System.out.println(personaTemp.toString());
						}
						System.out.println();
						cidadaoTemp.setPersona(listaPersona.get(scanner.nextInt()));
						cidadaoDAO.salvar(cidadaoTemp);
						System.out.println("Persona modificado");

						break;

					default:
						System.out.println("Op��o inv�lida, retornando para menu principal");
						break;
					}

					em.getTransaction().commit();
					System.out.println("Cidadao alterado");

					break;

				case 11: // Atualizar Im�vel
					System.out.println("Id do Im�vel que deseja atualizar dados:");
					listaImovel = imovelDAO.listar();
					for (Imovel imovelTemp : listaImovel) {
						System.out.println(imovelTemp.toString());
					}
					Imovel imovelTemp = imovelDAO.recuperar(scanner.nextInt());

					System.out.println(
							"Seleciona informa��o que ser� alterada:\n1 - Endere�o\n2 - CEP\n3 - Tamanho\n4 - Cidad�o(propriet�rio)");
					switch (scanner.nextInt()) {
					case 1:// Corrigir Endereco
						scanner.nextLine();
						em.getTransaction().begin();
						System.out.println("Digite novo endere�o:");
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
						System.out.println("Digite nova �rea em m2:");
						imovelTemp.setTamanho(scanner.nextInt());
						scanner.nextLine();
						imovelTemp.calculaIPTU();
						imovelDAO.salvar(imovelTemp);
						em.getTransaction().commit();
						break;

					case 4:// Corrigir Cidadao
						em.getTransaction().begin();
						System.out.println("Selecione novo Cidad�o:");
						listaCidadao = cidadaoDAO.listar();
						for (Cidadao cidadaoTemp1 : listaCidadao) {
							System.out.println(cidadaoTemp1.toString());
						}
						imovelTemp.setCidadao(cidadaoDAO.recuperar(scanner.nextInt()));
						scanner.nextLine();
						imovelDAO.salvar(imovelTemp);
						System.out.println("Propriet�rio do im�vel alterado");
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
					System.out.println("Id do Cidadao que ser� removido::");
					em.getTransaction().begin();
					cidadaoDAO.excluir(scanner.nextInt());
					scanner.nextLine();
					em.getTransaction().commit();
					System.out.println("Cidadao apagado");
					break;

				case 14: // Apagar Imovel
					System.out.println("Id do Im�vel que ser� removido:");
					em.getTransaction().begin();
					imovelDAO.excluir(scanner.nextInt());
					scanner.nextLine();
					em.getTransaction().commit();
					System.out.println("Im�vel apagado");
					break;

				case 15: // Apagar Persona
					System.out.println("Id da Persona que ser� removido:");
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