package com.example.rentcarsportalee.filter;


import com.example.rentcarsportalee.model.User;
import com.example.rentcarsportalee.model.UserRole;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = {"/changeCar","/addCar","/deleteCar","/changeRental","/deleteRental"})
public class AdminAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    if(servletRequest instanceof HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if(user != null && user.getUserRole() == UserRole.ADMIN){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            ((HttpServletResponse)servletResponse).sendRedirect("/home");
        }
    }
    }
}
