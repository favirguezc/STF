/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.filters;

import controller.controllers.PermissionController;
import controller.controllers.SignInController;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.util.Action;

/**
 *
 * @author fredy
 */
public class AutorizationFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        SignInController loginBean;
        loginBean = (SignInController) ((HttpServletRequest) request).getSession().getAttribute("signInController");
        if (loginBean != null) {
            PermissionController permissionController;
            permissionController = (PermissionController) ((HttpServletRequest) request).getSession().getAttribute("permissionController");
            if (permissionController == null) {
                permissionController = new PermissionController();
                permissionController.setSignInBean(loginBean);
                ((HttpServletRequest) request).getSession().setAttribute("permissionController", permissionController);
            }
            if (permissionController.currentUserHasPermission(Action.READ, ((HttpServletRequest) request).getRequestURI())) {
                chain.doFilter(request, response);
            } else {
                HttpServletRequest req = (HttpServletRequest) request;
                HttpServletResponse res = (HttpServletResponse) response;
                res.sendRedirect(req.getContextPath() + "/faces/error.xhtml");
            }
        }
    }

    public void destroy() {

    }

}
