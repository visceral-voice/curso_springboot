package br.com.srpp.cursosb.domain.enums;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pendente de pagamento"),
	QUITADO(2, "Quitado"),
	CANCELADO(3,"Cancelado");
	
	private int codigo;
	private String descricao;
	
	private EstadoPagamento(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public String getDescricao() {
		return this.descricao;
	}	

	public static EstadoPagamento toEnum (Integer codigo) {
		if (codigo == null) {
			return null;
		}
		
		for(EstadoPagamento x : EstadoPagamento.values()) {
			if(codigo.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("id inválido " + codigo);
	}
}
