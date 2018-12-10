import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * This is a simple implementation of Dijkstra algorithm with two stacks
 */
public class EvaluateArithmeticExpParanthesis {
	private static int ind=0;

	private static String[] getExpAndValues(String str){
		ArrayList<String> valLst=new ArrayList<>();
		Matcher m=Pattern.compile("((\\d*\\.\\d+)|(\\d+)|([\\+\\-\\*/\\(\\)]))").matcher(str);
	      while(m.find())
	    	  valLst.add(m.group());
	    String[] tmpArr=new String[valLst.size()];
	    for(int i=0;i<valLst.size();i++)
	    	tmpArr[i]=valLst.get(i);
	    return tmpArr;
	}
	public static Double processExpression(String str){
		String[] expAndValues=getExpAndValues(str);
		ind=0;
		Stack<String> ops = new Stack<>();
		Stack<Double> vals = new Stack<>();
		
		while(ind<expAndValues.length){
			String s=expAndValues[ind];
			if(s.equals("("));
			else if (s.equals("+")||s.equals("*")||s.equals("-")||s.equals("/")) ops.push(s);
			else if (s.equals(")")) {
				String op=ops.pop();
				if(op.equals("+")) vals.push(vals.pop()+vals.pop());
				else if (op.equals("*")) vals.push(vals.pop()*vals.pop());
				else if (op.equals("-")) vals.push(-1*vals.pop()+vals.pop());
				else if (op.equals("/")) vals.push(1/vals.pop()*vals.pop());
			}
			else
				vals.push(Double.parseDouble(s));
			ind++;
		}
		if(ops.isEmpty())
			return vals.pop();
		else{
			while(!ops.isEmpty()){
			String op=ops.pop();
			if(op.equals("+")) vals.push(vals.pop()+vals.pop());
			else if (op.equals("*")) vals.push(vals.pop()*vals.pop());
			else if (op.equals("-")) vals.push(-1*vals.pop()+vals.pop());
			else if (op.equals("/")) vals.push(1/vals.pop()*vals.pop());
			}
			return vals.pop();
		}
	}
			
		
	}

