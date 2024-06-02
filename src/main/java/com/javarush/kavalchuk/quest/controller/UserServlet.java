package com.javarush.kavalchuk.quest.controller;

import com.javarush.kavalchuk.quest.controller.enums.Faculty;
import com.javarush.kavalchuk.quest.controller.enums.UserSessionAttribute;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "userServlet", value = "/user")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter(UserSessionAttribute.USERNAME.getName());

        if (userName != null && !userName.trim().isEmpty()) {
            HttpSession session = request.getSession();
            session.setAttribute(UserSessionAttribute.USERNAME.getName(), userName);
            try {
                response.sendRedirect("questions");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                response.sendRedirect("index.jsp");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter(UserSessionAttribute.USERNAME.getName());

        if (userName != null) {
            HttpSession session = request.getSession();
            session.removeAttribute(UserSessionAttribute.USERNAME.getName());
            session.removeAttribute(UserSessionAttribute.QUESTION.getName());
            session.removeAttribute(UserSessionAttribute.RESULT.getName());
            session.removeAttribute(Faculty.GRYFFINDOR.getName());
            session.removeAttribute(Faculty.SLYTHERIN.getName());
            session.removeAttribute(Faculty.RAVENCLAW.getName());
            session.removeAttribute(Faculty.HUFFLEPUFF.getName());

            try {
                response.sendRedirect("index.jsp");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
