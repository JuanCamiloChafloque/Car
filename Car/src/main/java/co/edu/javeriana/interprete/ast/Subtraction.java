package co.edu.javeriana.interprete.ast;

import java.util.Map;

public class Subtraction implements ASTNode {

	private ASTNode operand1;
	private ASTNode operand2;
	
	public Subtraction(ASTNode operand1, ASTNode operand2) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable) {
		return (float)operand1.execute(symbolTable) - (float)operand2.execute(symbolTable);
	}

}
