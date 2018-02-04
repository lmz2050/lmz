package cn.lmz.mike.lmz.sys.util;

import java.lang.reflect.Field;
/**
 * 对读取字符分类相关操作
 * @author lmz
 */
public class TypeUtil {
	
	private final String keyWords[] = { "var", "new", "if", "while",
			"else", "for","break","return"}; // 关键字数组
	private final char operators[] = { '+', '-', '*', '/', '=', '>', '<', '&', '|',
			'!' }; // 运算符数组
	private final char separators[] = { ',', ';', '{', '}', '(', ')', '[', ']',
			':', '.', '"','\\'}; // 界符数组
	
	public static final String TYPE_KEY="TYPE_KEY";   //关键字
	public static final String TYPE_OPER="TYPE_OPER"; //运算符
	public static final String TYPE_SPAR="TYPE_SPAR"; //界符
	public static final String TYPE_ID="TYPE_ID"; //标识
	//public static final String TYPE_STR="TYPE_STR"; //标识
	//public static final String TYPE_CHR="TYPE_CHR"; //标识
	public static final String TYPE_DIGIT="TYPE_DIGIT"; //数字
	public static final String TYPE_ERR="TYPE_ERR"; //数字
	//public static final String TYPE_BR="TYPE_BR"; //换行
	
	public static final String TYPE_NEW="TYPE_NEW"; //变量
	//public static final String TYPE_CODE="TYPE_CODE"; //程序
	public static final String TYPE_VAR="TYPE_VAR"; //变量
	public static final String TYPE_DEF="TYPE_DEF"; //定义
	//public static final String TYPE_INT="TYPE_INT"; //变量
	
	public static final String TYPE_BLOCK="TYPE_BLOCK"; //变量
	
	
	public static final String TYPE_END=";";
	
	private Field[] fields = null;

	public static final String[] op_lev0= {"!","+A","-A","++","--"};
	public static final String[] op_lev1= {"*","/","%"};
	public static final String[] op_lev2= {"+","-"};
	public static final String[] op_lev3= {"<","<=",">",">="};
	public static final String[] op_lev4= {"==","!="};
	public static final String[] op_lev5= {"&&"};
	public static final String[] op_lev6= {"||"};
	public static final String[] op_lev7= {"=",">>"};
	/**
	 * 判断是否为字母
	 * @param ch 需判断的字符
	 * @return boolean
	 */
	public boolean isLetter(char ch) {
		return Character.isLetter(ch)||ch=='$'||ch=='#'||ch=='_';
	}

	/**
	 * 判断是否为数字
	 * @param ch 需判断的字符
	 * @return boolean
	 */
	public boolean isDigit(char ch) {
		return Character.isDigit(ch);
	}
	/**
	 * 判断是否为关键字
	 * @param s 需判断的字符串
	 * @return boolean
	 */
	public boolean isKeyWord(String s) {
		for (int i = 0; i < keyWords.length; i++) {
			if (keyWords[i].equals(s))
				return true;
		}
		return false;
	}

	/**
	 * 判断是否为运算符
	 * @param ch 需判断的字符
	 * @return boolean
	 */
	public boolean isOperator(char ch) {
		for (int i = 0; i < operators.length; i++) {
			if (ch == operators[i])
				return true;
		}
		return false;
	}

	/**
	 * 判断是否为分隔符
	 * @param ch 需判断的字符
	 * @return boolean
	 */
	public boolean isSeparators(char ch) {
		for (int i = 0; i < separators.length; i++) {
			if (ch == separators[i])
				return true;
		}
		return false;
	}
		
	/**
	 * 利用反射获取种别编码
	 * @param 属性名称
	 * @return	种别编码
	 */
	/*
	public int getType(String args) {
		int type = -1;
		if(fields==null){
			fields = KeyTypes.class.getDeclaredFields();
		}
		for (Field field : fields) {
			if (field.getName().equals(args)) {
				try {
					type = (Integer) field.get(new KeyTypes());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return type;
	}
*/
}
