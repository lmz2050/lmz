package cn.lmz.mike.lmz.sys.expression;

import cn.lmz.mike.common.io.FileU;
import cn.lmz.mike.common.log.O;
import cn.lmz.mike.lmz.sys.L;
import cn.lmz.mike.lmz.sys.context.Cfg;
import cn.lmz.mike.lmz.sys.context.Const;
import cn.lmz.mike.lmz.sys.context.Context;
import cn.lmz.mike.lmz.sys.lexer.Code;
import cn.lmz.mike.lmz.sys.util.TypeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;



public class LexerExpression implements IExpression {

	private static final Logger log = LoggerFactory.getLogger(LexerExpression.class);

	private StringBuffer buffer = new StringBuffer(); // 缓冲区
	private int i = 0;
	//private char ch; // 字符变量，存放最新读进的源程序字符
	private String strToken; // 字符数组，存放构成单词符号的字符串
	private TypeUtil tu = new TypeUtil();
	private Character ch;
	
	public Character getCharI(int i){
		if(i<buffer.length()){
			return buffer.charAt(i);
		}
		return null;
	}
	
	@Override
	public void interpret(Context ctx) throws Exception{
		List<Code> clist = new ArrayList<Code>();
		
		String fileSrc = (String)ctx.getCfg().get(Const.EXP_SRC_FILE);
		O.pNull(fileSrc, " fileSrc is null "+fileSrc);
		FileU.readFile(buffer, fileSrc);
		
		strToken = ""; // 置strToken为空串

		boolean is_In_Quote = false; //是否在引号之中
		boolean is_In_Char = false;
		boolean is_In_Def = false;
		
		List<String> slist = null;
		Code preCode = null;
		
		while (i < buffer.length()) {
			getChar();

			if(!is_In_Char&&!is_In_Def&&'"'==ch){
				if(!is_In_Quote){
					is_In_Quote = true;
				}else{
					is_In_Quote = false;
					
					preCode = new Code(TypeUtil.TYPE_VAR,ctx.put(strToken),strToken);
					clist.add(preCode);
					strToken="";
				}
				continue;
			}
			
			if(!is_In_Quote&&!is_In_Def&&'\''==ch){
				if(!is_In_Char){
					is_In_Char = true;
					strToken="";
				}else{
					is_In_Char = false;
					
					preCode = new Code(TypeUtil.TYPE_VAR,ctx.put(strToken),strToken);
					clist.add(preCode);
					strToken="";
				}
				continue;
			}
			
			if(!is_In_Quote&&!is_In_Char&&!is_In_Def&&'<'==ch){
				Character nc = getCharI(i);
				if(nc!=null&&'<'==nc){
					is_In_Def = true;
					i++;
					strToken="";
					slist = new ArrayList<String>();
					continue;
				}	
			}
			
			if(!is_In_Quote&&!is_In_Char&&is_In_Def&&'>'==ch){
				Character nc = getCharI(i);
				if(nc!=null&&'>'==nc){
					is_In_Def = false;
					i++;
					
					slist.add(strToken);
					Code codeChr = new Code(TypeUtil.TYPE_VAR,ctx.put(slist), L.string.toStr(slist));
					preCode = new Code(TypeUtil.TYPE_OPER,">>",">>");
					clist.add(codeChr);
					clist.add(preCode);
					
					strToken="";
					continue;
				}
				
			}
			
			
			
			
			if(is_In_Quote||is_In_Char||is_In_Def){
				
				if(is_In_Def&&(ch ==10 || ch==13 || ch==9)){
					if(strToken.length()>0){
						slist.add(strToken);
						strToken = "";
					}
					continue;
				}
				
				concat();
				continue;
			}
			
			getBC();
			if(ch==null) continue;

			if (tu.isLetter(ch)) { // 如果ch为字母
				while (ch!=null&&(tu.isLetter(ch) || tu.isDigit(ch))) {
					concat();
					getChar();
				}
				retract(' '); // 回调
				if (tu.isKeyWord(strToken)) { 
					preCode = new Code(TypeUtil.TYPE_KEY,strToken,strToken);
					clist.add(preCode); //strToken为关键字
				} else { 
					preCode = new Code(TypeUtil.TYPE_ID,strToken,strToken);
					clist.add(preCode); //strToken为标识符
				}
				strToken = "";
			} else if (tu.isDigit(ch)) { 
				while (ch!=null&&tu.isDigit(ch)) {//ch为数字
					concat();
					getChar();
				}
				if(ch==null||!tu.isLetter(ch)){//不能数字+字母
					retract(' '); // 回调
					preCode = new Code(TypeUtil.TYPE_DIGIT,strToken,strToken);
					clist.add(preCode); // 是整形
				}else{
					preCode = new Code(TypeUtil.TYPE_ERR,strToken,strToken);
					clist.add(preCode); // 非法
				}
				strToken = "";
			} else if (tu.isOperator(ch)) { //运算符
				if(ch == '/'){
					getChar();
					if(ch == '*') {//为/*注释
						while(ch!=null&&true){
							getChar();
							if(ch == '*'){// 为多行注释结束
								getChar();
								if(ch == '/') {
									getChar();
									break;
								}
							}
						}
					}
					if(ch == '/'){//为//单行注释
						while(ch!=null&&ch != 10&&ch!=13&&ch!=9){
							//System.out.println(ch+"   "+(int)ch);
							getChar();
						}
						//System.out.println(ch+"A   "+(int)ch);
					}
					//continue;
					retract(' ');
				} 
				
				preCode = new Code(TypeUtil.TYPE_OPER,ch+"",ch);
				clist.add(preCode); // 是符号
				
			} else if (tu.isSeparators(ch)) { // 界符
				if(ch==';'){
					if(preCode!=null&&';'!=preCode.getCh()&&'{'!=preCode.getCh()&&'}'!=preCode.getCh()&&' '!=preCode.getCh()){
						preCode = new Code(TypeUtil.TYPE_SPAR,ch+"",ch);// 是界符
						clist.add(preCode); // 是界符
					}
					continue;
				}else{
					preCode = new Code(TypeUtil.TYPE_SPAR,ch+"",ch);// 是界符
					clist.add(preCode); // 是符号
				}
			}else if(ch ==10 || ch==13 || ch==9){
				if(preCode!=null&&';'!=preCode.getCh()&&'{'!=preCode.getCh()&&'}'!=preCode.getCh()&&' '!=preCode.getCh()){
					preCode = new Code(TypeUtil.TYPE_SPAR,";",';');
					clist.add(preCode); // 是界符
				}
				continue;
			} else if(ch=='@'){
				preCode = new Code(TypeUtil.TYPE_ID,ch+"",ch);
				clist.add(preCode); 
			} else{
				preCode = new Code(TypeUtil.TYPE_ERR,ch+"",ch);// 非法
				clist.add(preCode); // 是符号
			} 
		}
		
		ctx.getCfg().put(Const.EXP_CODE_LIST, clist);
		
	}

	
	
	/**
	 * 将下一个输入字符读到ch中，搜索指示器前移一个字符
	 */
	public void getChar() {
		ch = getCharI(i);
		i++;
		//System.out.println(ch+"---"+(int)ch);
	}
	/** 检查ch中的字符是否为空白，若是则调用getChar()直至ch中进入一个非空白字符*/
	public void getBC() {
		//isSpaceChar(char ch) 确定指定字符是否为 Unicode 空白字符。
		//上述方法不能识别换行符
		//while (Character.isWhitespace(ch)){//确定指定字符依据 Java 标准是否为空白字符。
		while (ch!=null&&Character.isSpaceChar(ch)){
			//System.out.println(ch+"A---"+(int)ch);
			getChar();
		}
	}
	
	/**将ch连接到strToken之后*/
	public void concat() {
		strToken += ch;
	}
	/** 将搜索指示器回调一个字符位置，将ch值为空白字 */
	public void retract(Character ch0) {
		i--;
		ch = ch0;
	}
	
	public static void main(String[] args){
		try {
			String srcFile = L.string.getRoot()+"input.txt";
			Context ctx = new Context();
			Cfg cfg = new Cfg();
			ctx.setCfg(cfg);
			ctx.getCfg().put(Const.EXP_SRC_FILE, srcFile);
			
			LexerExpression lexer = new LexerExpression();
			lexer.interpret(ctx);
			
			List clist = (List)ctx.getCfg().get(Const.EXP_CODE_LIST);

			for(int i=0;i<clist.size();i++){
				System.out.println(clist.get(i).toString());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
