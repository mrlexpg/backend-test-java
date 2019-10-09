package br.com.fcamara.asnpark.error.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = PlacaVeiculoValidator.class)
@Documented
public @interface PlacaVeiculo {

	String message() default "Veiculo ja cadastrado.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}