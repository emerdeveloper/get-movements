package co.com.bancolombia.getmovements.models;

public class DataResponse {
	private HeaderResponse header;
	private boolean declaration;

	
	public boolean isDeclaration() {
		return declaration;
	}

	public void setDeclaration(boolean declaration) {
		this.declaration = declaration;
	}

	public HeaderResponse getHeader() {
		return header;
	}

	public void setHeader(HeaderResponse header) {
		this.header = header;
	}

	
}
