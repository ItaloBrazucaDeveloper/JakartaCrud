package com.brazucadev.userscrud.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns={"/users"})
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		if (req.getSession().getAttribute("userRole") == null) {
			response.getWriter().write("{\"error\":\"Access denied!\"}");
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		chain.doFilter(request, response); // Passa para o próximo filtro/servlet
	}
}
