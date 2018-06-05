/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.app.repositorio;

import br.com.app.negocio.Pessoa;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author leandro
 */
public class RepositorioPessoa {

    private EntityManager manager = null;

    public RepositorioPessoa() {
        //Properties properties = new  Properties();
        Map properties = new HashMap();

        properties.put("javax.persistence.jdbc.url", "jdbc:postgresql://localhost:5432/teste");
        properties.put("javax.persistence.jdbc.user", "postgres");
        properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
        properties.put("javax.persistence.jdbc.password", "1010010");
        properties.put("javax.persistence.schema-generation.database.action", "drop-and-create");

        manager = Persistence.createEntityManagerFactory("AppPostgres", properties).createEntityManager();
    }

    private EntityManager getManager() {
        return manager;
    }

    public List<Pessoa> listaPessoas() {

        try {
            List<Pessoa> pessoas = getManager().createNamedQuery("Pessoa.listaPessoas").getResultList();
            return pessoas;
        } catch (Exception e) {
            return null;
        }
    }

    public Pessoa getPessoa(long _idPessoa) {

        try {
            Pessoa pessoa = getManager().find(Pessoa.class, _idPessoa);
            return pessoa;
        } catch (Exception e) {
            return null;
        }
    }

    public Pessoa consultarPessoa(String _idPessoa) {

        try {

            Query consulta = getManager().createNamedQuery("Pessoa.consultarZap");
            consulta.setParameter("buscar", _idPessoa);
            return (Pessoa) consulta.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Object[][] listaPessoasMatriz() {
        Object[][] lista = new Object[listaPessoas().size()][];
        int count = 0;
        try {

            for (int i = 0; i < listaPessoas().size(); i++) {

                Pessoa p = (Pessoa) listaPessoas().get(i);
                Object[] l = {String.valueOf(p.getIdPessoa()), p.getNome(), p.getZap()};
                lista[count++] = l;
            }

            return lista;
        } catch (Exception e) {
            return null;
        }
    }

    public String salvarPessoa(Pessoa pessoa) {
        try {
            getManager().getTransaction().begin();
            getManager().merge(pessoa);
            getManager().getTransaction().commit();

            return "Pessoa cadastrada com sucesso.";
        } catch (Exception e) {
            return "falha na tentiva de cadastrar a pessoa.";
        }
    }

}
