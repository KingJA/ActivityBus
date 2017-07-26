package kingja.activitybus.compiler;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

import kingja.activitybus.annotations.ActivityBus;

import static kingja.activitybus.compiler.Util.getArrayTypeStr;
import static kingja.activitybus.compiler.Util.getTypeName;
import static kingja.activitybus.compiler.Util.isArraysType;


/**
 * Description:TODO
 * Create Time:2017/7/19 11:21
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GeneratedActivity extends GeneratedFile {
    private ClassName ClassName_Activity = ClassName.get("android.app", "Activity");
    private ClassName ClassName_Intent = ClassName.get("android.content", "Intent");
    private ClassName ClassName_String = ClassName.get("java.lang", "String");
    private ClassName ClassName_Serializable = ClassName.get("java.io", "Serializable");

    public GeneratedActivity(Messager mMessager, Elements mElementUtils, TypeElement typeElement) {
        super(mMessager, mElementUtils, typeElement);
    }

    @Override
    protected TypeSpec.Builder createTypeSpec(TypeSpec.Builder typeSpec) {
        return typeSpec.addMethod(createGoActivityMethod()).addMethod(createRegisterMethod());
    }

    public MethodSpec createGoActivityMethod() {
        MethodSpec.Builder builder = MethodSpec.methodBuilder("goActivity").addModifiers(Modifier.PUBLIC, Modifier
                .STATIC).returns(void.class)
                .addParameter(ClassName_Activity, "activity");
        for (VariableElement variable : params) {
            builder.addParameter(getTypeName(variable), variable.getSimpleName().toString());
        }
        builder.addStatement("$T intent = new $T(activity, " + typeElement.getSimpleName().toString() + "" +
                ".class)", ClassName_Intent, ClassName_Intent);

        for (VariableElement variable : params) {
            TypeName typeName = getTypeName(variable);


            if (typeName.isPrimitive() || ClassName_String.equals(typeName) || isArraysType(typeName) || ("java.lang" +
                    ".String[]").equals(typeName.toString())) {
                builder.addStatement("intent.putExtra($S, " + variable.getSimpleName().toString() + ")", variable
                        .getSimpleName().toString());
            } else {
                builder.addStatement("intent.putExtra($S, ($T)" + variable.getSimpleName().toString() + ")", variable
                        .getSimpleName().toString(), ClassName_Serializable);
            }
        }

        ActivityBus activityBusAnnotation = typeElement.getAnnotation(ActivityBus.class);
        if (activityBusAnnotation != null && activityBusAnnotation.requestCode() >= 0) {
            builder.addStatement("activity.startActivityForResult(intent," + activityBusAnnotation.requestCode() + ")");
        } else {
            builder.addStatement("activity.startActivity(intent)");
        }
        return builder.build();

    }

    public MethodSpec createRegisterMethod() {
        MethodSpec.Builder builder = MethodSpec.methodBuilder("register").addModifiers(Modifier.PUBLIC, Modifier
                .STATIC).returns(void.class)
                .addParameter(getTypeName(typeElement), "activity");
        for (VariableElement variable : params) {
            getIntentData(builder, variable);
        }

        return builder.build();

    }

    private void getIntentData(MethodSpec.Builder builder, VariableElement variable) {
        String var = variable.getSimpleName().toString();
        TypeName typeClassName = getTypeName(variable);
        if (typeClassName.isPrimitive()) {
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
                        getTypeName(variable), var);
            }
        }
    }

}
