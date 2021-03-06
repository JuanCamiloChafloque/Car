package co.edu.javeriana.interprete.ast;

import java.util.Map;

public class Not implements ASTNode {
	
	private ASTNode operand1;

	public Not(ASTNode operand1) {
		super();
		this.operand1 = operand1;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable) throws Exception{
		try {
			return !(boolean)operand1.execute(symbolTable);
		}catch(Exception e) {
			throw new Exception("No se puede usar Not con variables no númericas.");
		}
	}

}
