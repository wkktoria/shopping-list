package io.github.wkktoria.shopping_list.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(name = "Product", urlPatterns = {"/api/products/*"})
public class ProductServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ProductServlet.class);
    private final ProductService service;
    private final ObjectMapper mapper;

    /**
     * Servlet container needs it.
     */
    @SuppressWarnings("unused")
    public ProductServlet() {
        this(new ProductService(), new ObjectMapper());
    }

    ProductServlet(ProductService service, ObjectMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("Got request with parameters: " + req.getParameterMap());

        resp.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(resp.getOutputStream(), service.findAll());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("Got request with parameters: " + req.getParameterMap());

        var product = mapper.readValue(req.getInputStream(), ProductDTO.class);

        resp.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(resp.getOutputStream(), service.addProduct(product));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("Got request with parameters: " + req.getParameterMap() + ", and path: " + req.getPathInfo());

        String pathInfo = req.getPathInfo();

        try {
            var productId = Integer.valueOf(pathInfo.replace("/", ""));
            if (service.deleteProduct(productId)) {
                resp.setStatus(204);
            } else {
                resp.setStatus(400);
            }
        } catch (NumberFormatException e) {
            logger.warn("Wrong path used: " + pathInfo);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("Got request with parameters: " + req.getParameterMap() + ", and path: " + req.getPathInfo());

        String pathInfo = req.getPathInfo();

        try {
            var productId = Integer.valueOf(pathInfo.replace("/", ""));
            var product = service.toggleBought(productId);

            resp.setContentType("application/json;charset=UTF-8");
            mapper.writeValue(resp.getOutputStream(), product);
        } catch (NumberFormatException e) {
            logger.warn("Wrong path used: " + pathInfo);
        }
    }
}
