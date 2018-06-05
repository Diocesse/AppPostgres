/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.app.negocio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "_pessoas", schema = "public")
@SequenceGenerator(name = "seq_Pessoa", sequenceName = "seq_Pessoa", allocationSize = 1,initialValue = 1)
@NamedQueries({
@NamedQuery(name = "Pessoa.listaPessoas",query = "SELECT p FROM  Pessoa p"),
@NamedQuery(name = "Pessoa.consultarZap",query = "SELECT p FROM  Pessoa p WHERE p.zap like :buscar"),
})
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_Pessoa")
    @Column(name = "_idPessoa")
    private long idPessoa;
    @Column
    private String nome;
    @Column(unique = true)
    private String zap;
    @Column
    private String endereco;

    public long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getZap() {
        return zap;
    }

    public void setZap(String zap) {
        this.zap = zap;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
