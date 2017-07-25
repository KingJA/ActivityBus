package kingja.activitybus.compiler;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

/**
 * Description:TODO
 * Create Time:2017/7/19 11:21
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GeneratedBody {
    private Set<VariableElement> variables = new LinkedHashSet<>();
    private int requestCode;
    private Messager mMessager;
    private TypeElement typeElement;
    private final String SUFFIX = "Bus";
    private final String packageName;
    private final String fullName;
    private ClassName ClassName_Activity = ClassName.get("android.app", "Activity");
    private ClassName ClassName_Intent = ClassName.get("android.content", "Intent");
    private ClassName ClassName_String = ClassName.get("java.lang", "String");
    private ClassName ClassName_Serializable = ClassName.get("java.io", "Serializable");

    public GeneratedBody(Messager mMessager, Elements mElementUtils, TypeElement typeElement) {
        this.mMessager = mMessager;
        this.typeElement = typeElement;
        PackageElement packageElement = mElementUtils.getPackageOf(typeElement);
        String className = typeElement.getSimpleName().toString();
        packageName = packageElement.getQualifiedName().toString();
        fullName = className + SUFFIX;
    }

    public void putPassenage(VariableElement element) {
        variables.add(element);
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public void generateBody(Filer filer) {
        TypeSpec.Builder typeSpec = TypeSpec.classBuilder(fullName).addModifiers(Modifier.FINAL);
        typeSpec.addMethod(createGoActivityMethod());
        typeSpec.addMethod(createRegisterMethod());

        try {
            JavaFile.builder(packageName, typeSpec.build()).addFileComment("Generated code from ActivityBus. Do not " +
                    "modify!")
                    .build().writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MethodSpec createGoActivityMethod() {
        MethodSpec.Builder builder = MethodSpec.methodBuilder("goActivity").addModifiers(Modifier.PUBLIC, Modifier
                .STATIC).returns(void.class)
                .addParameter(ClassName_Activity, "activity");
        for (VariableElement variable : variables) {
            builder.addParameter(getClassName(variable), variable.getSimpleName().toString());
        }
        builder.addStatement("$T intent = new $T(activity, " + typeElement.getSimpleName().toString() + "" +
                ".class)", ClassName_Intent, ClassName_Intent);

        for (VariableElement variable : variables) {
            TypeName typeName = getClassName(variable);

            mMessager.printMessage(Diagnostic.Kind.NOTE, "getClassName:" + variable.asType().toString());

            if (typeName.isPrimitive() || ClassName_String.equals(typeName) || isArraysType(typeName) || ("java.lang" +
                    ".String[]").equals(typeName.toString())) {
                builder.addStatement("intent.putExtra($S, " + variable.getSimpleName().toString() + ")", variable
                        .getSimpleName().toString());
            } else {
                builder.addStatement("intent.putExtra($S, ($T)" + variable.getSimpleName().toString() + ")", variable
                        .getSimpleName().toString(), ClassName_Serializable);
            }
        }
        if (requestCode>0) {
            builder.addStatement("activity.startActivityForResult(intent," + requestCode + ")");
        } else {
            builder.addStatement("activity.startActivity(intent)");
        }


        return builder.build();

    }

    public MethodSpec createRegisterMethod() {
        MethodSpec.Builder builder = MethodSpec.methodBuilder("register").addModifiers(Modifier.PUBLIC, Modifier
                .STATIC).returns(void.class)
                .addParameter(getClassName(typeElement), "activity");
        for (VariableElement variable : variables) {
            getIntentData(builder, variable);
        }

        return builder.build();

    }

    private void getIntentData(MethodSpec.Builder builder, VariableElement variable) {
        String var = variable.getSimpleName().toString();
        TypeName typeClassName = getClassName(variable);
        if (typeClassName.isPrimitive()) {
            mMessager.printMessage(Diagnostic.Kind.NOTE, "isPrimitive:" + typeClassName.toString());
            if (TypeName.BOOLEAN.equals(typeClassName)) {
                builder.addStatement("activity." + var + "=activity.getIntent().getBooleanExtra($S,false)", var);
            } else if (TypeName.BYTE.equals(typeClassName)) {
                builder.addStatement("activity." + var + "=activity.getIntent().getByteExtra($S, (byte)0)", var);
            } else if (TypeName.SHORT.equals(typeClassName)) {
                builder.addStatement("activity." + var + "=activity.getIntent().getShortExtra($S,(short)0)", var);
            } else if (TypeName.INT.equals(typeClassName)) {
                builder.addStatement("activity." + var + "=activity.getIntent().getIntExtra($S,0)", var);
            } else if (TypeName.LONG.equals(typeClassName)) {
                builder.addStatement("activity." + var + "=activity.getIntent().getLongExtra($S,0L)", var);
            } else if (TypeName.CHAR.equals(typeClassName)) {
                builder.addStatement("activity." + var + "=activity.getIntent().getCharExtra($S, '\\u0000')", var);
            } else if (TypeName.FLOAT.equals(typeClassName)) {
                builder.addStatement("activity." + var + "=activity.getIntent().getFloatExtra($S,0f)", var);
            } else if (TypeName.DOUBLE.equals(typeClassName)) {
                builder.addStatement("activity." + var + "=activity.getIntent().getDoubleExtra($S,0d)", var);
            }

        } else {
            if (ClassName_String.equals(typeClassName)) {
                builder.addStatement("activity." + var + "=activity.getIntent().getStringExtra($S)", var);
            } else if ("java.lang.String[]".equals(typeClassName.toString())) {
                builder.addStatement("activity." + var + "=activity.getIntent().getStringArrayExtra($S)", var);
            } else if (isArraysType(typeClassName)) {

                builder.addStatement("activity." + var + "=activity.getIntent().get" + getArrayTypeStr(typeClassName)
                        + "ArrayExtra($S)", var);
            } else {
                builder.addStatement("activity." + var + "=($T)activity.getIntent().getSerializableExtra($S)",
                        getClassName(variable), var);
            }
        }
    }

    private TypeName getClassName(Element element) {
        return ClassName.get(element.asType());
    }

    private boolean isArraysType(TypeName typeName) {
        String type = typeName.toString();
        return "boolean[]".equals(type) || "byte[]".equals(type) || "char[]".equals(type) || "short[]".equals(type)
                || "int[]".equals(type) || "long[]".equals(type) || "float[]".equals(type) || "double[]".equals(type);
    }

    private String getArrayTypeStr(TypeName typeName) {
        String arrayType = typeName.toString().replace("[]", "");
        return String.valueOf(arrayType.charAt(0)).toUpperCase() + arrayType.substring(1);
    }
}
