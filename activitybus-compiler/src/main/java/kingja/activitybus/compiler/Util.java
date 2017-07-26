package kingja.activitybus.compiler;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

import javax.lang.model.element.Element;

/**
 * Description:TODO
 * Create Time:2017/7/26 14:14
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Util {
    public static TypeName getTypeName(Element element) {
        return ClassName.get(element.asType());
    }

    public static boolean isArraysType(TypeName typeName) {
        String type = typeName.toString();
        return "boolean[]".equals(type) || "byte[]".equals(type) || "char[]".equals(type) || "short[]".equals(type)
                || "int[]".equals(type) || "long[]".equals(type) || "float[]".equals(type) || "double[]".equals(type);
    }

    public static String getArrayTypeStr(TypeName typeName) {
        String arrayType = typeName.toString().replace("[]", "");
        return String.valueOf(arrayType.charAt(0)).toUpperCase() + arrayType.substring(1);
    }

}
