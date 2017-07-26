package kingja.activitybus.compiler;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

/**
 * Description:TODO
 * Create Time:2017/7/19 11:21
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class GeneratedFile {
    protected Set<VariableElement> params = new LinkedHashSet<>();
    protected Messager mMessager;
    protected TypeElement typeElement;
    private final String SUFFIX = "Bus";
    private final String packageName;
    private final String fullName;


    public GeneratedFile(Messager mMessager, Elements mElementUtils, TypeElement typeElement) {
        this.mMessager = mMessager;
        this.typeElement = typeElement;
        PackageElement packageElement = mElementUtils.getPackageOf(typeElement);
        String className = typeElement.getSimpleName().toString();
        packageName = packageElement.getQualifiedName().toString();
        fullName = className + SUFFIX;
    }

    public void addRequestParam(VariableElement element) {
        params.add(element);
    }


    public void createFile(Filer filer) {
        TypeSpec.Builder typeSpec = TypeSpec.classBuilder(fullName).addModifiers(Modifier.FINAL);
        typeSpec=createTypeSpec(typeSpec);

        try {
            JavaFile.builder(packageName, typeSpec.build()).addFileComment("Generated code from ActivityBus. Do not " +
                    "modify!")
                    .build().writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract TypeSpec.Builder createTypeSpec(TypeSpec.Builder typeSpec);

}
