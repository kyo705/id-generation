package org.idgeneration.annotation;


import org.idgeneration.config.IdGeneratorConfigSelector;
import org.idgeneration.enumeration.IdGenerationMode;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(IdGeneratorConfigSelector.class)
public @interface EnableIdGenerator {

    IdGenerationMode mode() default IdGenerationMode.INDIVIDUAL;
}
