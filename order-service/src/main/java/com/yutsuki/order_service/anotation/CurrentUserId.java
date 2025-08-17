package com.yutsuki.order_service.anotation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation สำหรับการดึง User ID จาก Security Context
 * โดยใช้ @AuthenticationPrincipal(expression = "subject")
 */
@Target(ElementType.PARAMETER) // Annotation นี้สามารถใช้กับ Parameter ของ Method ได้
@Retention(RetentionPolicy.RUNTIME) // Annotation นี้จะยังคงอยู่ ณ Runtime
@AuthenticationPrincipal(expression = "subject") // ใช้ @AuthenticationPrincipal เพื่อดึง subject
public @interface CurrentUserId {
}
