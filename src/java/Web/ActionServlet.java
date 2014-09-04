/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import ProfileMaker.Profile.Profile;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;
import org.json.simple.JSONObject;

/**
 *
 * @author Anushka
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/actionservlet"})
public class ActionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectClass nn = new ObjectClass();
        String imgeJSON = "";
        String detailJSON = "";
        PrintWriter out = response.getWriter();
        Profile pr = new Profile(request.getParameter("user"));

        System.out.println(".......................................");
        imgeJSON = "<div class=\"profile\">\n"
                + "                                <img src=\"" + pr.pic_url + "\" alt=\"profile\">                                \n"
                + "                            </div>\n"
                + "                            <h1 class=\"nameNtitle\"><span>" + pr.name + " </span><span>" + pr.title + "</span></h1>";
        if (request.getParameter("user").toString().equals("")) {
            imgeJSON = "<h1><span>Ha! </span><span>tried a blank search didn't you? try again</span></h1>";
        }

        if (pr.name != null) {
            detailJSON = "<section>\n"
                    + "<h2>Personal Details</h2>" + "<p>" + pr.name + "</p><p>";
            if (pr.title != null) {
                detailJSON += pr.title + "<br>";
            }
            if (pr.education != null) {
                detailJSON += "<h3>Education</h3><p>" + pr.education.replace("\n", "<br>");
            }
            detailJSON += "</p></section>";
        }
//        publication listing
        if (pr.publicationList != null) {
            detailJSON = detailJSON + "<hr><section>\n"
                    + "<h2>Publications</h2><p>";
            for (int i = 0; i < pr.publicationList.size(); i++) {
                detailJSON += "<b>" + pr.publicationList.get(i).name + "</b><br>";
                if (!pr.publicationList.get(i).authors.isEmpty()) {
                    detailJSON += pr.publicationList.get(i).authors + "<br><br>";
                }
                if (!pr.publicationList.get(i).summary.isEmpty()) {
                    detailJSON += pr.publicationList.get(i).summary + "<br>";
                }
                detailJSON += "<br>";
            }
            detailJSON += "</p></section>";
        }
//        projects listing
        if (!pr.projectsList.isEmpty()) {
            detailJSON = detailJSON + "<hr><section>\n"
                    + "<h2>Projects</h2><p>";
            for (int i = 0; i < pr.projectsList.size(); i++) {
                detailJSON += "<b>" + pr.projectsList.get(i).name + "</b><br>";
                if (!pr.projectsList.get(i).summary.isEmpty()) {
                    detailJSON += pr.projectsList.get(i).summary + "<br>";
                }
                detailJSON += "<br>";
            }
            detailJSON += "</p></section>";
        }
        
        response.setContentType("application/json");
        try {
            JSONObject result = new JSONObject();
            result.put("profile", imgeJSON);
            result.put("details", detailJSON);
            String jsonResult = JSONObject.toJSONString(result);
            out.println(jsonResult);
        } catch (Exception ex) {
            out.println("{\"message\":\"Error - caught exception in ExportServlet\"}");
        } finally {
            out.flush();
            out.close();
        }
    }
}
