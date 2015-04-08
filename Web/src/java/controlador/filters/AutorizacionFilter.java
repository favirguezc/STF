/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.filters;

import controlador.controllers.PermisoController;
import controlador.controllers.SignInController;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.util.Accion;

/**
 *
 * @author fredy
 */
public class AutorizacionFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        SignInController loginBean;
        loginBean = (SignInController) ((HttpServletRequest) request).getSession().getAttribute("signInController");
        if (loginBean != null) {
            PermisoController permisoController;
            permisoController = (PermisoController) ((HttpServletRequest) request).getSession().getAttribute("permisoController");
            if (permisoController == null) {
                permisoController = new PermisoController();
                permisoController.setSignInBean(loginBean);
                ((HttpServletRequest) request).getSession().setAttribute("permisoController", permisoController);
            }
            if (permisoController.currentUserHasPermission(Accion.Leer, ((HttpServletRequest) request).getRequestURI())) {
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
