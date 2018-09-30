package br.com.nutmeg.enums;

public enum MessageError {
	
	USUARIO_DUPLICADO(0, "Já existe um usuário com o login informado."),
	USUARIO_FALHA_INESPERADA(1, "Ops! Houve falha na inclusão do usuário. Tente novamente em alguns instantes.");
	
	private int codigo;
	private String mensagem;
	private MessageError(int codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}
	
	public static MessageError find(int codigo) {
		for (MessageError me : MessageError.values()) {
			if (me.codigo == codigo) {
				return me;
			}
		}
		throw new IllegalArgumentException("Mensagem de código " + codigo + " não encontrada");
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getMensagem() {
		return mensagem;
	}
}
