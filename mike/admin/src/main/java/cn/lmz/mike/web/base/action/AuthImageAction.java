package cn.lmz.mike.web.base.action;

import cn.lmz.mike.common.V;
import cn.lmz.mike.common.sec.CodeImg;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Map;

@Controller()
@Scope("prototype")
public class AuthImageAction extends ActionSupport implements SessionAware {

	private static final Logger log = LoggerFactory.getLogger(AuthImageAction.class);
	private static final long serialVersionUID = -8870366228092313224L;
	@SuppressWarnings("unchecked")
	private Map session;
	private ByteArrayInputStream imageStream;

	@SuppressWarnings("unchecked")
	public void setSession(Map session) {
		this.session = session;
	}
	
	public String code(){
		try{
			//生成随机字串
			String verifyCode = CodeImg.generateVerifyCode(4);
			log.info(verifyCode);
			session.put(V.CODE_SESSION_KEY, verifyCode.toLowerCase());
			//生成图片
			int w = 200, h = 80;
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			CodeImg.outputImage(w, h, os, verifyCode);
			
			BufferedImage image = CodeImg.createImage(w, h, verifyCode);
			imageStream = CodeImg.convertImageToStream(image);

		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		
		return SUCCESS;
	}

	public ByteArrayInputStream getImageStream() {
		return imageStream;
	}

	public void setImageStream(ByteArrayInputStream imageStream) {
		this.imageStream = imageStream;
	}
}
