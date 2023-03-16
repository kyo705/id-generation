package org.idgeneration.config;

import org.idgeneration.idgenerator.IdGenerator;
import org.idgeneration.aop.IdGenerationAspect;
import org.idgeneration.idgenerator.IdGenerators;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SingletonConfig {

    @Value("${id-generation.nodeId}")
    private long nodeId;

    @Bean
    public IdGenerators idGenerator(){

        IdGenerators idGenerators = new IdGenerators();
        idGenerators.addIdGenerator("publicIdGenerator", new IdGenerator(nodeId));

        return idGenerators;
    }

    @Bean
    public IdGenerationAspect idGenerationAspect(IdGenerators idGenerators){

        return new IdGenerationAspect(idGenerators);
    }
}
