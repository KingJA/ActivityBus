package kingja.activitybus.compiler;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

import static kingja.activitybus.compiler.Util.getTypeName;

/**
 * Description:TODO
 * Create Time:2017/7/26 14:03
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GeneratedFragment extends GeneratedFile {

    public GeneratedFragment(Messager mMessager, Elements mElementUtils, TypeElement typeElement) {
        super(mMessager, mElementUtils, typeElement);
    }

    @Override
    protected TypeSpec.Builder createTypeSpec(TypeSpec.Builder typeSpec) {
        return typeSpec.addMethod(createNewInstanceMethod());
    }


    private MethodSpec createNewInstanceMethod() {
        MethodSpec.Builder builder = MethodSpec.methodBuilder("newInstance")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(getTypeName(typeElement));

        for (VariableElement variable : params) {
            builder.addParameter(getTypeName(variable), variable.getSimpleName().toString());
        }
        builder.addStatement("$T target = new $T()", getTypeName(typeElement), getTypeName(typeElement));
        for (VariableElement variable : params) {
            String fieldName = variable.getSimpleName().toString();
            builder.addStatement("target."+fieldName+" = "+fieldName);
        }
        builder.addStatement("return target");
        return builder.build();
    }

}
