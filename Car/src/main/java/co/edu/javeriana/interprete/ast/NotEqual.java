package co.edu.javeriana.interprete.ast;

import java.util.Map;

public class NotEqual implements ASTNode {
	
	private ASTNode operand1;
	private ASTNode operand2;

	public NotEqual(ASTNode operand1, ASTNode operand2) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable) throws Exception{
		try {
			boolean a = ((Object)operand1.execute(symbolTable)).equals(((Object)operand2.execute(symbolTable))); 
			return !((boolean) a);
		}catch(Exception e) {
			throw new Exception("No se puede usar NotEqual con variables no booleanas.");
		}
	}

}
