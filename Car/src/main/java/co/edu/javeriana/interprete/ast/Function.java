package co.edu.javeriana.interprete.ast;

import java.util.List;
import java.util.Map;

public class Function implements ASTNode {
	
	private String name;
	private List<String> args;
	private List<ASTNode> body;
	
	public Function(String name, List<String> args, List<ASTNode> body) {
		super();
		this.name = name;
		this.args = args;
		this.body = body;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable) {
		symbolTable.put(name, this);
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getArgs() {
		return args;
	}

	public void setArgs(List<String> args) {
		this.args = args;
	}

	public List<ASTNode> getBody() {
		return body;
	}

	public void setBody(List<ASTNode> body) {
		this.body = body;
	}
	
	

}
