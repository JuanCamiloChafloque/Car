package co.edu.javeriana.interprete.ast;

import java.util.Map;

public class And implements ASTNode {
	
	private ASTNode operand1;
	private ASTNode operand2;

	public And(ASTNode operand1, ASTNode operand2) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable) throws Exception {
		try {
			return (boolean)operand1.execute(symbolTable)&&(boolean)operand2.execute(symbolTable);
		}catch(Exception e) {
			throw new Exception("No se puede usar And con variables no booleanas.");
		}
	}

}
