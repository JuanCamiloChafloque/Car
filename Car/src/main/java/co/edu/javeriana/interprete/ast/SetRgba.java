package co.edu.javeriana.interprete.ast;

import java.util.Map;

import co.edu.javeriana.car.Car;

public class SetRgba implements ASTNode {
	
	private ASTNode operand1;
	private ASTNode operand2;
	private Car car;
	

	public SetRgba(ASTNode operand1, ASTNode operand2, Car car) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.car = car;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable) throws Exception{
		try {
			String color = (String)operand1.execute(symbolTable);
			car.color(Integer.valueOf(color.substring(1,3),16).floatValue(),
					  Integer.valueOf(color.substring(3,5),16).floatValue(),
					  Integer.valueOf(color.substring(5,7),16).floatValue(),
					  (float) operand2.execute(symbolTable) * 255);
			return null;
		}catch(Exception e) {
			throw new Exception("No se puede usar SetRgba con variables no númericas.");
		}
	}

}
