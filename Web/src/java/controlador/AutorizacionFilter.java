/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import datos.produccion.administracion.RolPersonaDAO;
import datos.util.EntityManagerFactorySingleton;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.produccion.administracion.Rol;

/**
 *
 * @author fredy
 */
public class AutorizacionFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LoginController loginBean;
        loginBean = (LoginController) ((HttpServletRequest) request).getSession().getAttribute("loginController");
        if (loginBean != null) {
            String requestPath = ((HttpServletRequest) request).getPathInfo();
            Rol rol = new RolPersonaDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).findMostImportantRol(loginBean.getUserPersona());
            if (PermisosPorRol.tienePermiso(rol, requestPath)) {
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
