package com.gillianocampos.cursospringangular.services.validation;

//copiar codigo pronto do curso e mudar nome para ClientInsert na interface
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

//mudar nome para ClientInsert que sera a classe que implementa o validator..criar esta classe e copiar codigo pronto do curso
@Constraint(validatedBy = ClienteUpdateValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)

//nome da anotação que estamos personalizando
public @interface ClientUpdate {
	String message() default "Erro de validação";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
