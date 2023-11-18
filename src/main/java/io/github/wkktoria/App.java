package io.github.wkktoria;

import io.github.wkktoria.product.ProductServlet;
import io.github.wkktoria.utils.HibernateUtil;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.component.LifeCycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        logger.info("App started");

        var server = new Server(Integer.parseInt(System.getProperty("port")));

        var handler = new ServletHandler();
        server.setHandler(handler);
        server.addEventListener(new LifeCycle.Listener() {
            @Override
            public void lifeCycleStopped(LifeCycle event) {
                HibernateUtil.close();
            }
        });
        
        handler.addServletWithMapping(ProductServlet.class, "/api/products/*");

        server.start();
        server.join();
    }
}
