/*
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fm.pattern.valex.annotations;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import fm.pattern.valex.Result;
import fm.pattern.valex.ValidationService;
import fm.pattern.valex.sequences.Delete;
import fm.pattern.valex.sequences.Update;

@Aspect
public class ValidationAdvisor {

    private final ValidationService validationService;

    public ValidationAdvisor(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Around("execution(* *(@fm.pattern.valex.annotations.Create (*)))")
    public Object aroundCreate(ProceedingJoinPoint pjp) throws Throwable {
        return around(pjp, Create.class);
    }

    @Around("execution(* *(@fm.pattern.valex.annotations.Update (*)))")
    public Object aroundUpdate(ProceedingJoinPoint pjp) throws Throwable {
        return around(pjp, Update.class);
    }

    @Around("execution(* *(@fm.pattern.valex.annotations.Delete (*)))")
    public Object aroundDelete(ProceedingJoinPoint pjp) throws Throwable {
        return around(pjp, Delete.class);
    }

    @Around("execution(* *(@fm.pattern.valex.annotations.Valid (*)))")
    public Object aroundValid(ProceedingJoinPoint pjp) throws Throwable {
        return around(pjp);
    }

    private Object around(ProceedingJoinPoint pjp, Class<?> type) throws Throwable {
        Object[] arguments = pjp.getArgs();
        if (arguments.length == 0) {
            return pjp.proceed();
        }

        Result<Object> result = validationService.validate(pjp.getArgs()[0], type);
        if (result.rejected()) {
            return result;
        }

        return pjp.proceed();
    }

    private Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object[] arguments = pjp.getArgs();
        if (arguments.length == 0) {
            return pjp.proceed();
        }

        Result<Object> result = validationService.validate(pjp.getArgs()[0]);
        if (result.rejected()) {
            return result;
        }

        return pjp.proceed();
    }

}
