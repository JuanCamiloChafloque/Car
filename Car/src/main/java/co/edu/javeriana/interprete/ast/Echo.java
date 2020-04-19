package co.edu.javeriana.interprete.ast;

import java.util.Map;

public class Echo implements ASTNode {
	
	private ASTNode data;

	public Echo(ASTNode data) {
		super();
		this.data = data;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable) {
		System.out.println(data.execute(symbolTable));
		return null;
	}

}
