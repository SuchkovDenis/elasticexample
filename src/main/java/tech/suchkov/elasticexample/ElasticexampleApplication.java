package tech.suchkov.elasticexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories
public class ElasticexampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticexampleApplication.class, args);
	}

}
