package pl.ksb.agape.tools.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;

public class ReflectTools {

	public static Map<String, EntityField> getEntityFileds(
			@SuppressWarnings("rawtypes") Class clazz) {
		Map<String, EntityField> ret = new HashMap<String, EntityField>();
		Field fs[] = clazz.getDeclaredFields();
		for (Field f : fs) {
			f.setAccessible(true);
			Annotation a = f.getAnnotation(Column.class);

			if (a != null) {
				ret.put(f.getName(), new EntityField(f.getName(),
						getDbNameFromColumn(a.toString()), f.getType()));
			}
		}
		return ret;

	}

	private static String getDbNameFromColumn(String s) {
		String ret = s.substring(s.indexOf("name=") + 5);
		ret = ret.substring(0, ret.indexOf(","));
		return ret;

	}

}
