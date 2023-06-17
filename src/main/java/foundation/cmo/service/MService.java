package foundation.cmo.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

import foundation.cmo.service.utils.CopyFieldsUtils;


public class MService {

	protected <T> void cloneObject(String sfield, Object repository, T value) {
		cloneObject(sfield, "findById", repository, value);
	}
	
	protected <T> void cloneObject(String sfield, String jpaMethod, Object repository, T value) {
		try {
			Class<? extends Object> type = value.getClass();
			Field field = type.getDeclaredField(sfield);
			field.setAccessible(true);

			Object obj = field.get(value);

			if (obj == null) {
				return;
			}

			Class<?> repositoryType = repository.getClass();

			Method method = CopyFieldsUtils.getMethod(repositoryType, jpaMethod);
			method.setAccessible(true);

			Optional<?> optional = (Optional<?>) method.invoke(repository, obj);
			if (optional.isPresent()) {
				CopyFieldsUtils.copyAtoB(optional.get(), value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
