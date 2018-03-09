package cn.lmz.mike.oauth2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.PostConstruct;
import java.io.IOException;


@SpringBootApplication
public class Oauth2Application{

	private static final Logger log = LoggerFactory.getLogger(Oauth2Application.class);
	@PostConstruct
	public void initApplication() throws IOException {
		log.info("Running with Spring profile(s) : {}");
		ErrorController e;
		UsernamePasswordAuthenticationFilter u;
	}

	public static void main(String[] args) {
		//SpringApplication.run(MainApplication.class, args);
		SpringApplication app=new SpringApplication(Oauth2Application.class);
		ConfigurableApplicationContext ctx=app.run(args);
/*
		UserRepository ur = (UserRepository)ctx.getBean("userRepository");
		L_SYS_User su= ur.findByUsername("admin");
		System.out.println("密码"+su.getPassword());
		System.out.println("名字"+su.getName());
		BCryptPasswordEncoder bc=new BCryptPasswordEncoder(4);//将密码加密
		su.setPassword(bc.encode(su.getPassword()));
		System.out.println("密码"+su.getPassword());
		ur.save(su);
*/
	}
}
