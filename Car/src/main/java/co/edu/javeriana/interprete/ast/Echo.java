package co.edu.javeriana.interprete.ast;

import java.util.Map;

public class Echo implements ASTNode {
	
	private ASTNode data;

	public Echo(ASTNode data) {
		super();
		this.data = data;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable) throws Exception {
		try {
			System.out.println(data.execute(symbolTable));
			return null;
		}catch(Exception e) {
			throw new Exception("No se puede imprimir.");
		}
	}

}
