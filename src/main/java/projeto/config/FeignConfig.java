package projeto.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

import projeto.integration.IpVigilanteClient;
import projeto.integration.MetaweatherClient;

@Configuration
@EnableFeignClients(clients= {IpVigilanteClient.class, MetaweatherClient.class})
public class FeignConfig {

	
}
