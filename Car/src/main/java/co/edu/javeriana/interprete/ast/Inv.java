package co.edu.javeriana.interprete.ast;

import java.util.Map;

public class Inv implements ASTNode {
	
	private ASTNode operand1;
	
	public Inv(ASTNode operand1) {
		super();
		this.operand1 = operand1;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable) throws Exception{
		try {
			return (float) operand1.execute(symbolTable) * -1;
		}catch(Exception e) {
			throw new Exception("No se puede usar Inv con variables no númericas.");
		}
	}

}
