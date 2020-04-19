package co.edu.javeriana.interprete.ast;

import java.util.Map;

import co.edu.javeriana.car.Car;

public class SetRgba implements ASTNode {
	
	private ASTNode operand1;
	private ASTNode operand2;
	private ASTNode operand3;
	private ASTNode operand4;
	private Car car;
	

	public SetRgba(ASTNode operand1, ASTNode operand2, ASTNode operand3, ASTNode operand4, Car car) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.operand3 = operand3;
		this.operand4 = operand4;
		this.car = car;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable) {
		car.color((float)operand1.execute(symbolTable), (float)operand2.execute(symbolTable), (float)operand3.execute(symbolTable), (float)operand4.execute(symbolTable));
		return null;
	}

}
