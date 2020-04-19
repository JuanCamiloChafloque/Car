package co.edu.javeriana.interprete.ast;

import java.util.Map;

public class VariableDecl implements ASTNode {
	
	private String name;

	public VariableDecl(String name) {
		super();
		this.name = name;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable) {
		symbolTable.put(name, new Object());
		return null;
	}

}
