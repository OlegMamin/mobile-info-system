package ru.levelup.junior.web;

import ru.levelup.junior.dao.ContractsDAO;
import ru.levelup.junior.dao.ClientsDAO;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;
import ru.levelup.junior.dao.ContractsDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by otherz on 11.11.2019.
 */
@WebServlet(urlPatterns = "/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory factory = StartupListener.getFactory(req.getServletContext());
        EntityManager manager = factory.createEntityManager();
        ClientsDAO clientsDAO = new ClientsDAO(manager);
        ContractsDAO contractsDAO = new ContractsDAO(manager);

        try {
            int clientId = (int) req.getSession().getAttribute("clientId");
            Client found = clientsDAO.findById(clientId);
            List<Contract> contracts = contractsDAO.findByClient(found);

            req.setAttribute("contracts", contracts);

            req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
        } catch (NoResultException notFound) {
            req.getRequestDispatcher("/").forward(req, resp);

        } finally {
            manager.close();
        }
    }
}
