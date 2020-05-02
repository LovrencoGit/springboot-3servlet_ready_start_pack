package com.certimeter.safestadium.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogEndpointFilter implements Filter {

	private static final Logger LOG = LoggerFactory.getLogger(LogEndpointFilter.class);
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String endpoint = req.getRequestURI();
		String httpMethod = req.getMethod();

		String idTransaction = UUID.randomUUID().toString();
		
		LOG.info("#########################");
		LOG.info("#####     START     #####   -   {} {} ---------- [id={}]", endpoint, httpMethod, idTransaction);
		LOG.info("#########################");

		//logHttpHeader(request);
		
		chain.doFilter(request, response);
		
		LOG.info("#########################");
		LOG.info("#####      END      #####   -   {} {} ---------- [id={}]", endpoint, httpMethod, idTransaction);
		LOG.info("#########################");
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	@Override
	public void destroy() {
	}


	
	/***********************************************************************************************************/
	/**											Metodi ausiliari											  **/
	/***********************************************************************************************************/

	@SuppressWarnings("unused")
	private void logHttpHeader( ServletRequest request ) {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		Enumeration<String> headers = httpRequest.getHeaderNames();
		while( headers.hasMoreElements() ) {
			String nextElement = headers.nextElement();
			LOG.info( "[HEADER] " +nextElement+"  :  "+ httpRequest.getHeader( nextElement ) );
		}
	}

}
