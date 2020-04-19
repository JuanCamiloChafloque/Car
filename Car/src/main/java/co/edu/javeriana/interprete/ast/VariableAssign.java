package co.edu.javeriana.interprete.ast;

import java.util.Map;

public class VariableAssign implements ASTNode {
	
	private String name;
	private ASTNode expression;
	
	public VariableAssign(String name, ASTNode expression) {
		super();
		this.name = name;
		this.expression = expression;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable) {
		symbolTable.put(name, expression.execute(symbolTable));
		return null;
	}

}
