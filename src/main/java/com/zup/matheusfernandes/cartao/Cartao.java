package com.zup.matheusfernandes.cartao;

<<<<<<< HEAD
import java.util.List;

import javax.persistence.CascadeType;
=======
>>>>>>> 982ed91ecc3aa0ca55eb15eea272f0431f54c83f
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
import javax.persistence.OneToMany;

import com.zup.matheusfernandes.biometria.Biometria;
=======
>>>>>>> 982ed91ecc3aa0ca55eb15eea272f0431f54c83f

@Entity
public class Cartao {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String numeroCartao;
<<<<<<< HEAD
	@OneToMany(cascade = CascadeType.MERGE)
	private List<Biometria> biometrias;
=======
>>>>>>> 982ed91ecc3aa0ca55eb15eea272f0431f54c83f
	
	@Deprecated
	public Cartao() {
	}

	public Cartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}
	
<<<<<<< HEAD
	public void cadastrarBiometria(Biometria biometria) {
		biometria.setCartao(this);
		biometrias.add(biometria);
	}
	
=======
>>>>>>> 982ed91ecc3aa0ca55eb15eea272f0431f54c83f
}
