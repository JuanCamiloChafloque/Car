package co.edu.javeriana.interprete.ast;

import java.util.List;
import java.util.Map;

public class FunctionCall implements ASTNode {
	
	private String name;
	private List<ASTNode> params;

	public FunctionCall(String name, List<ASTNode> params) {
		super();
		this.name = name;
		this.params = params;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable) {
		
		Function encontrada = (Function) symbolTable.get(name);
		List<String> paramsEncontrados = encontrada.getArgs();
		List<ASTNode> bodyEncontrado = encontrada.getBody();
		
		for(int i = 0; i < paramsEncontrados.size(); i++) {
			symbolTable.put(paramsEncontrados.get(i), (this.params.get(i)).execute(symbolTable));
		}
		
		for(ASTNode n: bodyEncontrado) {
			n.execute(symbolTable);
		}

		return null;
	}

}
