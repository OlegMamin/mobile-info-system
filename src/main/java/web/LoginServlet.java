package web;

import dao.ClientsDAO;
import dao.ContractsDAO;
import dao.OptionsDAO;
import dao.TariffsDAO;
import entities.Client;
import entities.Contract;
import entities.Option;
import entities.Tariff;

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
import java.util.List;

/**
 * Created by otherz on 08.11.2019.
 */
@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        EntityManagerFactory factory = StartupListener.getFactory(req.getServletContext());
        EntityManager manager = factory.createEntityManager();
        ClientsDAO dao = new ClientsDAO(manager);
        ContractsDAO contractsDAO = new ContractsDAO(manager);
        try {
            Client found = dao.findByLoginAndPassword(login, password);
            req.getSession().setAttribute("clientId", found.getId());
            req.getSession().setAttribute("clientName", found.getFirstName());

            resp.sendRedirect("/dashboard");
        } catch (NoResultException notFound) {
            req.getRequestDispatcher("/index.jsp?login=" + login).forward(req, resp);
        } finally {
            manager.close();
        }
    }
}
