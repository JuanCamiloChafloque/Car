/*Creado por: Juan Camilo Chafloque y Brayan Gonzalez */
/* Proyecto semestral Lenguajes de Programación 2020-1 */
/*Car.g4  Implementación de un interprete para el lenguaje CAR */

grammar Car;

@parser::header {
	import java.util.Map;
	import java.util.HashMap;
	import co.edu.javeriana.interprete.ast.*;
}

@parser::members {
	Map<String, Object> symbolTable = new HashMap<String, Object>();

	private Car car;
	
	public CarParser(TokenStream input, Car car) {
	    this(input);
	    this.car = car;
	}
}

program: 
{
	List<ASTNode> body = new ArrayList<ASTNode>();
}(sentence{body.add($sentence.node);})* 
{
	for(ASTNode n: body){
		n.execute(symbolTable);
	}
};

sentence returns[ASTNode node]:
		var_decl {$node = $var_decl.node;}
		|var_assign {$node = $var_assign.node;}
		|var_decl_assign {$node = $var_decl_assign.node;}
		|move_fw {$node = $move_fw.node;}
		|move_bw {$node = $move_bw.node;}
		|turn_lt {$node = $turn_lt.node;}
		|turn_rt {$node = $turn_rt.node;}
		//|set_rgba {$node = $set_rgba.node;}
		|echo {$node = $echo.node;}
		|conditional {$node = $conditional.node;}
		|loop {$node = $loop.node;}
		|func_def {$node = $func_def.node;}
		|func_call {$node = $func_call.node;};

echo returns[ASTNode node]: ECHO expression {$node = new Echo($expression.node);};
move_fw returns[ASTNode node]: MOVEFW expression {$node = new MoveFw($expression.node, car);};
move_bw returns[ASTNode node]: MOVEBW expression {$node = new MoveBw($expression.node, car);};
turn_lt returns[ASTNode node]: TURNLT expression {$node = new TurnLt($expression.node, car);};
turn_rt returns[ASTNode node]: TURNRT expression {$node = new TurnRt($expression.node, car);};		
//set_rgba returns[ASTNode node]: SETRGBA COLOR COMMA NUM {$node = new SetRgba(Integer.valueOf($COLOR.text.substring(1,3),16).floatValue(),
//																			 Integer.valueOf($COLOR.text.substring(3,5),16).floatValue(),
//																			 Integer.valueOf($COLOR.text.substring(5,7),16).floatValue(),
//																			 Float.parseFloat($NUM.text)*255,
//																			 car
//																			);};
var_decl returns[ASTNode node]: DEFVAR ID {$node = new VariableDecl($ID.text);};
var_assign returns[ASTNode node]: ID ASSIGN expression {$node = new VariableAssign($ID.text, $expression.node);};
var_decl_assign returns[ASTNode node]: DEFVAR ID ASSIGN expression {$node = new VariableAssign($ID.text, $expression.node);};

func_def returns[ASTNode node]:
			{
				List<String> params = new ArrayList<String>();
				List<ASTNode> body = new ArrayList<ASTNode>();
			}
			PROC id1=ID PAR_OP (id2=ID{params.add($id2.text);})* (COMMA* id3=ID{params.add($id3.text);})* PAR_CL
			(s1=sentence {body.add($s1.node);})*
			END
			{
				$node = new Function($id1.text, params, body);	
			}; 

func_call returns[ASTNode node]:
			{
				List<ASTNode> params = new ArrayList<ASTNode>();
			}
			ID PAR_OP (e1=expression{params.add($e1.node);})* (COMMA* e2=expression{params.add($e2.node);})* PAR_CL
			{
				$node = new FunctionCall($ID.text, params);
			};
			
loop returns[ASTNode node]:
			WHILE PAR_OP logic PAR_CL
			{
				List<ASTNode> whileBody = new ArrayList<ASTNode>();
			} 
			(s1=sentence {whileBody.add($s1.node);})* 
			ENDWHILE
			{
				$node = new While($logic.node, whileBody);
			};

conditional returns[ASTNode node]:
			IF PAR_OP logic PAR_CL
			{
				List<ASTNode> ifBody = new ArrayList<ASTNode>();
				List<ASTNode> elseBody = new ArrayList<ASTNode>();
			}
			(s1=sentence {ifBody.add($s1.node);})*
			(ELSE
			(s2=sentence {elseBody.add($s2.node);})*)*
			ENDIF
			{
				$node = new If($logic.node, ifBody, elseBody);
			};

logic returns[ASTNode node]:
			t1 = comparison {$node = $t1.node;}
			(AND t2=comparison {$node = new And($t1.node, $t2.node);}
			|
			OR t2=comparison {$node = new Or($t1.node, $t2.node);}	
			)*;

comparison returns[ASTNode node]:
			t1 = expression {$node = $t1.node;}
			(GT t2=expression {$node = new GreaterThan($t1.node, $t2.node);}
			|LT t2=expression {$node = new LessThan($t1.node, $t2.node);}
			|GEQ t2=expression {$node = new GreaterEqualThan($t1.node, $t2.node);}
			|LEQ t2 = expression {$node = new LessEqualThan ($t1.node, $t2.node);}
			|EQ t2 = expression {$node = new Equal ($t1.node, $t2.node);}
			|NEQ t2 = expression {$node = new NotEqual ($t1.node, $t2.node);})
			|NOT t2 = expression {$node = new Not ($t2.node);};

expression returns[ASTNode node]:
			t1 = factor {$node = $t1.node;}
				(PLUS t2=factor {$node = new Addition($t1.node, $t2.node);}
				|
				MINUS t2=factor {$node = new Subtraction($t1.node, $t2.node);}
				)*;

factor returns[ASTNode node]:
			t1 = term {$node = $t1.node;}
				(MULT t2=term {$node = new Multiplication($t1.node, $t2.node);}
				|
				DIV t2=term {$node = new Division($t1.node, $t2.node);}	
				)*;	

term returns[ASTNode node]:
			NUM {$node = new Constant(Float.parseFloat($NUM.text));}
			|STRING {$node = new Constant(String.valueOf($STRING.text).replace("\"",""));}
			|BOOLEAN {$node = new Constant(Boolean.parseBoolean($BOOLEAN.text));}
			|ID {$node = new VariableRef($ID.text);}
			|PAR_OP expression {$node = $expression.node;} PAR_CL;

		   
MOVEFW:'move_fw';
MOVEBW:'move_bw';
TURNLT:'turn_lt';
TURNRT:'turn_rt';
SETRGBA:'set_rgba';

IF: 'if';
ENDIF: 'endif';
ELSE: 'else';
WHILE: 'while';
ENDWHILE: 'endwhile';
ECHO: 'echo';
PROC: 'proc';
END: 'end';

PLUS: '+';
MINUS: '-';
MULT: '*';
DIV: '/';

AND: 'and';
OR: 'or';
NOT: 'not';

GT: '>';
LT: '<';
LEQ: '<=';
GEQ: '>=';
EQ: '=';
NEQ: '<>';

ASSIGN: ':=';

PAR_OP: '(';
PAR_CL: ')';
COMMA: ',';

DEFVAR: 'def_var';
ID: [a-zA-Z][a-zA-Z0-9_]*;
BOOLEAN: 'true'|'false';
STRING: '"'('\\"'|.)*?'"';
NUM: [0-9]+'.'?[0-9]*;
COLOR: '#'[0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f];


WS: [ \t\r\n]+ -> skip;
