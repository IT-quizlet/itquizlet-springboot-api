package com.example.itquizletspringbootapi.web.decorators;

import com.example.itquizletspringbootapi.repository.entity.UserEntity;
import com.example.itquizletspringbootapi.service.impl.AuthServiceImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CurrentUserAspect {

    @Autowired
    private AuthServiceImpl authService;

    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping) && args(.., @CurrentUser user)")
    public Object injectCurrentUser(ProceedingJoinPoint joinPoint, @CurrentUser UserEntity user) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();

        user = authService.getUser(username);

        Object[] args = joinPoint.getArgs();
        args[args.length - 1] = user;

        return joinPoint.proceed(args);
    }
}
