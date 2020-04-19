package co.edu.javeriana.interprete.ast;

import java.util.Map;

public class VariableRef implements ASTNode {
	
	private String name;
	
	public VariableRef(String name) {
		super();
		this.name = name;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable) {
		return symbolTable.get(name);
	}

}
