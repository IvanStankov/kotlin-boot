package com.ivan

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations
import org.springframework.context.annotation.Bean
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import java.lang.reflect.Method
import java.util.stream.Collectors.mapping
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition

const val BASE_API_PATH = "api"

@SpringBootApplication
class BlogApplication : WebMvcConfigurationSupport() {

    override fun requestMappingHandlerMapping(): RequestMappingHandlerMapping {
        return object : RequestMappingHandlerMapping() {
            override fun registerHandlerMethod(handler: Any, method: Method, mapping: RequestMappingInfo) {
                val declaringClass: Class<*> = method.declaringClass
                val ann: RestController? = AnnotationUtils.findAnnotation(declaringClass, RestController::class.java)

                val resolvedMapping: RequestMappingInfo =
                    if (ann != null) {
                        val apiPattern = PatternsRequestCondition(BASE_API_PATH)
                            .combine(mapping.patternsCondition)

                        RequestMappingInfo(
                            mapping.name, apiPattern,
                            mapping.methodsCondition, mapping.paramsCondition,
                            mapping.headersCondition, mapping.consumesCondition,
                            mapping.producesCondition, mapping.customCondition
                        )
                    } else {
                        mapping
                    }
                super.registerHandlerMethod(handler, method, resolvedMapping)
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<BlogApplication>(*args)
}