package co.edu.javeriana.interprete.ast;

import java.util.Map;

import co.edu.javeriana.car.Car;

public class MoveFw implements ASTNode {
	
	private ASTNode operand1;
	private Car car;

	public MoveFw(ASTNode operand1, Car car) {
		super();
		this.operand1 = operand1;
		this.car = car;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable) throws Exception{
		try {
			car.forward((float)operand1.execute(symbolTable));
			return null;
		}catch(Exception e) {
			throw new Exception("No se puede usar MoveFw con variables no númericas.");
		}
	}

}
