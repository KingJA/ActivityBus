package kingja.activitybus.compiler;

import com.google.auto.service.AutoService;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

import kingja.activitybus.annotations.ActivityBus;
import kingja.activitybus.annotations.RequestParam;

@AutoService(Processor.class)
public class ComponentBusProcessor extends AbstractProcessor {

    private Messager mMessager;
    private Map<String, GeneratedBody> generatedBodys = new HashMap<>();
    private Filer mFiler;
    private Elements mElementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mMessager = processingEnvironment.getMessager();
        mFiler = processingEnvironment.getFiler();
        mElementUtils = processingEnvironment.getElementUtils();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotationTypes = new LinkedHashSet<>();
        annotationTypes.add(RequestParam.class.getCanonicalName());
        return annotationTypes;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        generatedBodys.clear();

        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(RequestParam.class);
        for (Element element : elements) {
            VariableElement variableElement = (VariableElement) element;
            TypeElement typeElement = (TypeElement) variableElement.getEnclosingElement();
            String className = typeElement.getQualifiedName().toString();

            GeneratedBody generatedBody = generatedBodys.get(className);
            if (generatedBody == null) {
                generatedBody = new GeneratedBody(mMessager,mElementUtils,typeElement);
            }
            generatedBody.putPassenage(variableElement);
            generatedBodys.put(className, generatedBody);

        }

        Set<? extends Element> busElements = roundEnvironment.getElementsAnnotatedWith(ActivityBus.class);
        for (Element element : busElements) {
            TypeElement typeElement = (TypeElement) element;
            String className = typeElement.getQualifiedName().toString();

            GeneratedBody generatedBody = generatedBodys.get(className);
            if (generatedBody == null) {
                generatedBody = new GeneratedBody(mMessager,mElementUtils,typeElement);
            }
            generatedBodys.put(className, generatedBody);
            ActivityBus annotation = typeElement.getAnnotation(ActivityBus.class);
            int requestCode = annotation.requestCode();
            generatedBody.setRequestCode(requestCode);

        }
        for (String key : generatedBodys.keySet()) {
            GeneratedBody generatedBody = generatedBodys.get(key);
            generatedBody.generateBody(mFiler);
        }
        return true;
    }
}
