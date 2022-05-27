package criacaoObjetosPrefeitura;

import java.util.ArrayList;
import br.com.fiap.dao.CidadaoDAO;
import br.com.fiap.dao.ImovelDAO;
import br.com.fiap.dao.PersonaDAO;
import br.com.fiap.prefeitura.Cidadao;
import br.com.fiap.prefeitura.Imovel;
import br.com.fiap.prefeitura.Persona;
import jakarta.persistence.EntityManager;

public abstract class PopulaBD {
	public static void populaBD(EntityManager em) {

		Cidadao cidadao = new Cidadao();

		Persona persona = new Persona();

		Imovel imovel = new Imovel();

		CidadaoDAO cidadaoDAO = new CidadaoDAO(em);
		ImovelDAO imovelDAO = new ImovelDAO(em);
		PersonaDAO personaDAO = new PersonaDAO(em);

		ArrayList<Persona> personaArrayList = new ArrayList<Persona>();
		ArrayList<Cidadao> cidadaoArrayList = new ArrayList<Cidadao>();
		ArrayList<Imovel> imovelArrayList = new ArrayList<Imovel>();

		// Criando as Personas
		persona.setNome_persona("empreendedor");
		personaArrayList.add(persona);
		persona = new Persona();
		persona.setNome_persona("impostos");
		personaArrayList.add(persona);
		persona = new Persona();
		persona.setNome_persona("notícias");
		personaArrayList.add(persona);
		persona = new Persona();
		persona.setNome_persona("regulação");
		personaArrayList.add(persona);

		em.getTransaction().begin();
		for (Persona personaTemp : personaArrayList) {
			personaDAO.salvar(personaTemp);
		}
		em.getTransaction().commit();

		// Criando os Cidadãos
		cidadao.setNome("Ana");
		cidadao.setCpf("111111111");
		cidadao.setTelefone("1111111");
		cidadao.setPersona(personaArrayList.get(0));
		cidadaoArrayList.add(cidadao);

		cidadao = new Cidadao();
		cidadao.setNome("Bruno");
		cidadao.setCpf("222222222");
		cidadao.setTelefone("2222222");
		cidadao.setPersona(personaArrayList.get(1));
		cidadaoArrayList.add(cidadao);

		cidadao = new Cidadao();
		cidadao.setNome("Rafael");
		cidadao.setCpf("3333333");
		cidadao.setTelefone("33333333");
		cidadao.setPersona(personaArrayList.get(2));
		cidadaoArrayList.add(cidadao);

		cidadao = new Cidadao();
		cidadao.setNome("Tiago");
		cidadao.setCpf("44444444");
		cidadao.setTelefone("44444444");
		cidadao.setPersona(personaArrayList.get(3));
		cidadaoArrayList.add(cidadao);

		em.getTransaction().begin();
		for (Cidadao cidadoTemp : cidadaoArrayList) {
			cidadaoDAO.salvar(cidadoTemp);
		}
		em.getTransaction().commit();

		//Criando os imóveis
		imovel.setEndereco("Rua Paraguaçu");
		imovel.setCep("57073-456");
		imovel.setInscricao(111111);
		imovel.setTamanho(44);
		imovel.calculaIPTU();
		imovel.setCidadao(cidadaoArrayList.get(0));
		imovelArrayList.add(imovel);

		imovel = new Imovel();
		imovel.setEndereco("Rua Atibaia");
		imovel.setCep("06343-520");
		imovel.setInscricao(22222222);
		imovel.setTamanho(55);
		imovel.calculaIPTU();
		imovel.setCidadao(cidadaoArrayList.get(1));
		imovelArrayList.add(imovel);

		imovel = new Imovel();
		imovel.setEndereco("Rua Quinze de Novembro");
		imovel.setCep("76808-320");
		imovel.setInscricao(3333333);
		imovel.setTamanho(66);
		imovel.calculaIPTU();
		imovel.setCidadao(cidadaoArrayList.get(2));
		imovelArrayList.add(imovel);

		imovel = new Imovel();
		imovel.setEndereco("Rua Melvin Jones");
		imovel.setCep("60055-450");
		imovel.setInscricao(4444444);
		imovel.setTamanho(88);
		imovel.calculaIPTU();
		imovel.setCidadao(cidadaoArrayList.get(3));
		imovelArrayList.add(imovel);

		em.getTransaction().begin();
		for (Imovel imovelTemp : imovelArrayList) {
			imovelDAO.salvar(imovelTemp);
		}
		em.getTransaction().commit();

	}

}
