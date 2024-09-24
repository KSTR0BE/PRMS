package kr.or.ddit.spring.conf;

import java.io.IOException;

import org.jasypt.encryption.pbe.PBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.PBEConfig;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.encryption.pbe.config.StringPBEConfig;
import org.jasypt.spring31.properties.EncryptablePropertyPlaceholderConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class DataSourceContext {

	@Bean
	public StringPBEConfig pbeConfig() {
		SimpleStringPBEConfig pbeConfig = new SimpleStringPBEConfig();
		pbeConfig.setAlgorithm("PBEWithMD5AndDES");
		pbeConfig.setPassword(System.getProperty("jasypt.password"));
		return pbeConfig;
	}

	@Bean("configurationEncryptor")
	public PBEStringEncryptor configurationEncryptor(PBEConfig pbeConfig) {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setConfig(pbeConfig);
		return encryptor;
	}

	@Bean
	public EncryptablePropertyPlaceholderConfigurer dbInfoProperties(
		@Value("classpath:kr/or/ddit/db/DbInfo.properties") Resource location
		, PBEStringEncryptor encryptor
	) throws IOException {
		EncryptablePropertyPlaceholderConfigurer placeHolderConfig = new EncryptablePropertyPlaceholderConfigurer(encryptor);
		placeHolderConfig.setLocation(location);
		return placeHolderConfig;
	}

}
