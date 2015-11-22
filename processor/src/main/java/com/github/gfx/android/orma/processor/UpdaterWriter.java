package com.github.gfx.android.orma.processor;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Modifier;

public class UpdaterWriter {

    private final SchemaDefinition schema;

    private final ProcessingEnvironment processingEnv;

    public UpdaterWriter(SchemaDefinition schema, ProcessingEnvironment processingEnv) {
        this.schema = schema;
        this.processingEnv = processingEnv;
    }

    public TypeSpec buildTypeSpec() {
        TypeSpec.Builder classBuilder = TypeSpec.classBuilder(schema.getUpdaterClassName().simpleName());
        classBuilder.addModifiers(Modifier.PUBLIC);
        classBuilder.superclass(Types.getUpdater(schema.getModelClassName(), schema.getUpdaterClassName()));

        classBuilder.addFields(buildFieldSpecs());
        classBuilder.addMethods(buildMethodSpecs());

        return classBuilder.build();
    }

    public List<FieldSpec> buildFieldSpecs() {
        List<FieldSpec> fieldSpecs = new ArrayList<>();
        return fieldSpecs;
    }

    public List<MethodSpec> buildMethodSpecs() {
        List<MethodSpec> methodSpecs = new ArrayList<>();

        methodSpecs.add(
                MethodSpec.constructorBuilder()
                        .addParameter(Types.OrmaConnection, "conn")
                        .addParameter(schema.getSchemaClassName(), "schema")
                        .addStatement("super(conn, schema)")
                        .build()
        );

        schema.getColumnsWithoutAutoId().forEach(column -> {
            RelationDefinition r = column.getRelation();
            if (r == null) {
                CodeBlock.Builder valueExpr = CodeBlock.builder();

                if (Types.needsTypeAdapter(column.type)) {
                    valueExpr.add("conn.getTypeAdapters().serialize($T.$L.type, value)",
                            schema.getSchemaClassName(), column.name);
                }
                else {
                    valueExpr.add("value");
                }

                methodSpecs.add(
                        MethodSpec.methodBuilder(column.name)
                                .addModifiers(Modifier.PUBLIC)
                                .returns(schema.getUpdaterClassName())
                                .addParameter(
                                        ParameterSpec.builder(column.getType(), "value")
                                                .build()
                                )
                                .addStatement("contents.put($S, $L)", column.columnName, valueExpr.build())
                                .addStatement("return this")
                                .build()
                );
            } else {
                // FIXME: in case the column represents a relationship
            }
        });

        return methodSpecs;
    }
}
