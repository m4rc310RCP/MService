package foundation.cmo.service.graphql.mappers.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface MCase {
	EmumTypeCase value() default EmumTypeCase.NONE;
	
	
	public enum EmumTypeCase{
		UPPERCASE,
		LOWERCASE,
		NONE
	}
}