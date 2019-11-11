package web;

import dao.ClientsDAO;
import entities.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by otherz on 08.11.2019.
 */
@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private EntityManagerFactory factory;

    @Override
    public void init() throws ServletException {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
    }

    @Override
    public void destroy() {
        if (factory != null) {
            factory.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        EntityManager manager = factory.createEntityManager();
        ClientsDAO clientsDAO = new ClientsDAO(manager);

        try {
            Client found = clientsDAO.findByLoginAndPassword(login, password);
            req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
        } catch (NoResultException notFound) {
            req.getRequestDispatcher("/index.jsp?login=" + login).forward(req, resp);
        } finally {
            manager.close();
        }

    }
}
