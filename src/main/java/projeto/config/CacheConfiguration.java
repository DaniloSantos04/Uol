package projeto.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.TimeUnit;

import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.Duration;
import javax.cache.expiry.TouchedExpiryPolicy;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;

@EnableCaching
@Configuration
public class CacheConfiguration implements JCacheManagerCustomizer
{
	@Override
	public void customize(CacheManager cacheManager)
	{
		cacheManager.createCache("cliente", configurar(new Duration(TimeUnit.MINUTES, 5)));
		cacheManager.createCache("historico", configurar(new Duration(TimeUnit.MINUTES, 5)));
		cacheManager.createCache("localidade", configurar(new Duration(TimeUnit.MINUTES, 5)));
	}

	private MutableConfiguration<Object, Object> configurar(Duration duracao) {
		MutableConfiguration<Object, Object> configTouch5seconds  = new MutableConfiguration<>()  
				.setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(duracao)) 
				.setStoreByValue(false)
				.setStatisticsEnabled(true)
				.setManagementEnabled(true);
		return configTouch5seconds;
	}


}
