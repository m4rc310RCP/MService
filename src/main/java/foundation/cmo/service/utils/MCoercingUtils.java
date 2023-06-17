package foundation.cmo.service.utils;
import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;

public class MCoercingUtils {

	public static <T> Coercing<T, String> get(Class<T> type, MFunction<String, T> fromString,
			MFunction<T, String> toString) {
		return new Coercing<T, String>() {

			@Override
			@SuppressWarnings("unchecked")
			public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
				if (type.isInstance(dataFetcherResult)) {
					try {
						return toString.apply((T) dataFetcherResult);
					} catch (Exception e) {
						throw new CoercingSerializeException("Value " + dataFetcherResult + " could not be serialized");
					}
				}
				throw new CoercingSerializeException("Value " + dataFetcherResult + " could not be serialized");
			}

			@Override
			public T parseValue(Object input) throws CoercingParseValueException {
				if (type.isInstance(input)) {
					return type.cast(input);
				}
				throw new CoercingSerializeException("Could not be parseValue");
			}

			@Override
			public T parseLiteral(Object input) throws CoercingParseLiteralException {

				try {
					if (input instanceof StringValue) {
						return fromString.apply(((StringValue) input).getValue());
					}

					if (input instanceof String) {
						return fromString.apply((String) input);
					}

					throw new CoercingSerializeException("Could not be parseValue");
				} catch (Exception e) {
					throw new CoercingSerializeException(e);
				}
			}
		};
	}

	@FunctionalInterface
	public interface MFunction<T, R> {
		R apply(T t) throws Exception;
	}

}