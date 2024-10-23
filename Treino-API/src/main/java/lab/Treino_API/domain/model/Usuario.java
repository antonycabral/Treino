package lab.Treino_API.domain.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_user")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;
    private String sexo;
    private int idade;
    private double peso;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Treino> treinos;

    
    public Usuario() {
    }


    public Usuario(Long id, String nomeCompleto, String sexo, int idade, double peso) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.sexo = sexo;
        this.idade = idade;
        this.peso = peso;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }



    public String getNomeCompleto() {
        return nomeCompleto;
    }



    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    

}
