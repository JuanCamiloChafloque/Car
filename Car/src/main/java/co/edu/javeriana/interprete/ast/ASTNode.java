package co.edu.javeriana.interprete.ast;

import java.util.Map;

public interface ASTNode {
	public Object execute(Map<String, Object> symbolTable) throws Exception;
}
