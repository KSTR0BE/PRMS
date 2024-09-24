package kr.or.ddit.jasypt;

import static org.junit.jupiter.api.Assertions.*;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@SpringJUnitWebConfig(locations="file:src/main/resources/kr/or/ddit/spring/*-context.xml")
class JasptEncryptTest {

	@Value("${maindb.url}")
	String url;
	@Value("${maindb.user}")
	String user;
	@Value("${maindb.password}")
	String password;



	@Test
	void test() {
		SimpleStringPBEConfig pbeConfig = new SimpleStringPBEConfig();
		pbeConfig.setAlgorithm("PBEWithMD5AndDES");
		pbeConfig.setPassword("team1java");
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setConfig(pbeConfig);
		System.out.printf("maindb.url=ENC(%s)\n", encryptor.encrypt(url));
		System.out.printf("maindb.ussererurl=ENC(%s)\n", encryptor.encrypt(user));
		System.out.printf("maindb.password=ENC(%s)\n", encryptor.encrypt(password));
	}

}