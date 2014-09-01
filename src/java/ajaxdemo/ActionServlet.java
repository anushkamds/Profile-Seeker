/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ajaxdemo;

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
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
        String imgeJSON = null;
        String detailJSON = "";
        PrintWriter out = response.getWriter();
        ProfileDetailGenerator profile = new ProfileDetailGenerator();
        Profile pr=new Profile(request.getParameter("user").toString());
//        Profile pr=new Profile("thilina premasiri");
        
        System.out.println(".......................................");
        profile.detailFiller();

        imgeJSON = "<div class=\"profile\">\n"
                + "                                <img src=\"" + pr.pic_url + "\" alt=\"profile\">                                \n"
                + "                            </div>\n"
                + "                            <h1><span>" + pr.name + " </span><span>" + pr.title + "</span></h1>";
        if (request.getParameter("user").toString().equals("")) {
            imgeJSON = "<h1><span>Ha! </span><span>tried a blank search didn't you? try again</span></h1>";
        }

        if (profile.getPersonalDetails() != null) {
            detailJSON = "<section>\n"
                    + "<h2>Personal Details</h2>" + profile.getPersonalDetails() + "</section>";
        }
        
        if (profile.getProjects() != null) {
            detailJSON = detailJSON+"<section>\n"
                    + "<h2>Project Details</h2>" + profile.getProjects() + "</section>";
        }
        if (profile.getPublications() != null) {
            detailJSON = detailJSON+"<section>\n"
                    + "<h2>Publication Details</h2>" + profile.getPublications() + "</section>";
        }
//                + "                            <h2>Web Development</h2>\n"
//                + "                            <p>Scenario archetype complementary responsive script pixel sidebar sitemap keep it simple. Complementary visuals footer CSS from alan cooper delightful. Photoshop iconography simplicity user experience affordance narrative ascenders contour. Slab serif interstitial skeuomorphism illustrator design by committee simplicity alan cooper eye tracking. Typography contrast mental model typesetting affordance narrative from CSS. Retina simplicity design by committee typography oblique.</p>\n"
//                + "                            <p>Delightful ascenders contrast prototype. Ligature jakob nielsen user measure. Ligature contrast glyph rule of thirds composition interstitial dribbble. Mental model typography urbanized balance. Resolution rounded corners IDEO constraints dribbble persona. Portfolio sketch baseline 66-character line. Usability testing mental model simplicity aspect ratio pencil type. Usable sans-serif visualization leading prägnanz baseline pencil fireworks clarity omnigraffle.</p>\n"
//                + "                        </section>\n"
//                + "                        <section>\n"
//                + "                            <h2>Constraints</h2>\n"
//                + "                            <p>Card-sorting dropdown constraints alan cooper prägnanz iconography. Stakeholder balsamiq italic vector composition ethnography CSS monospace hierarchy. Eye tracking serif persona focus group.</p>\n"
//                + "                            <p>Typesetting artifact rounded corners eye tracking braindump drawer menu mockup dropdown measure. Jony ive eye tracking script monospace heuristic tabs sketches. Affordance eye tracking scenario usable ligature typesetting clarity responsive. Information architecture golden ratio drawer menu header affordance aspect ratio. Balsamiq slab serif IDEO cognitive dissonance kerning pixel pixel-perfect. Pixel rounded corners header workflow portfolio.</p>\n"
//                + "                            <p>Scenario navigation complementary jony ive helvetica archetype interstitial serif front-end. Constraints pencil usability gestalt design language mockup usability testing affordance jakob nielsen.</p>\n"
//                + "                        </section>\n"
//                + "                        <section>\n"
//                + "                            <h2>Ligature</h2>\n"
//                + "                            <p>Delightful ascenders contrast prototype. Ligature jakob nielsen user measure. Ligature contrast glyph rule of thirds composition interstitial dribbble. Mental model typography urbanized balance. Resolution rounded corners IDEO constraints dribbble persona. Portfolio sketch baseline 66-character line. Usability testing mental model simplicity aspect ratio pencil type. Usable sans-serif visualization leading prägnanz baseline pencil fireworks clarity omnigraffle.</p>\n"
//                + "                            <p>Paper prototype urbanized headroom typography splash screen pencil modal branding. Retina omnigraffle objectified descender navigation adobe ethnography. Innovate design by committee modern hero message. Contrast user-centered color theory keep it simple visuals bevel adobe descender splash screen. From focus group accessibility sans-serif archetype pixel-perfect complementary skeuomorphism. Focus group iconography figure-ground navigation user-centered omnigraffle from.</p>\n"
//                + "                        </section>\n"
//                + "                        <section>\n"
//                + "                            <h2>Ligature</h2>\n"
//                + "                            <p>Delightful ascenders contrast prototype. Ligature jakob nielsen user measure. Ligature contrast glyph rule of thirds composition interstitial dribbble. Mental model typography urbanized balance. Resolution rounded corners IDEO constraints dribbble persona. Portfolio sketch baseline 66-character line. Usability testing mental model simplicity aspect ratio pencil type. Usable sans-serif visualization leading prägnanz baseline pencil fireworks clarity omnigraffle.</p>\n"
//                + "                            <p>Paper prototype urbanized headroom typography splash screen pencil modal branding. Retina omnigraffle objectified descender navigation adobe ethnography. Innovate design by committee modern hero message. Contrast user-centered color theory keep it simple visuals bevel adobe descender splash screen. From focus group accessibility sans-serif archetype pixel-perfect complementary skeuomorphism. Focus group iconography figure-ground navigation user-centered omnigraffle from.</p>\n"
//                + "                        </section>\n"
//                + "                        <section>\n"
//                + "                            <h2>Typesetting</h2>\n"
//                + "                            <p>Typesetting artifact rounded corners eye tracking braindump drawer menu mockup dropdown measure. Jony ive eye tracking script monospace heuristic tabs sketches. Affordance eye tracking scenario usable ligature typesetting clarity responsive. Information architecture golden ratio drawer menu header affordance aspect ratio. Balsamiq slab serif IDEO cognitive dissonance kerning pixel pixel-perfect. Pixel rounded corners header workflow portfolio.</p>\n"
//                + "                            <p>Fireworks mobile skeuomorphism sitemap. Workflow iconography interaction design pixel-perfect serif. Mental model monospace typeface behavior change bauhaus from usability testing. Color theory user experience paper prototype narrative palette serif gradient header. Oblique modal 66-character line sketch responsive portfolio. Comic sans fireworks prägnanz monospace gradient design language jakob nielsen. Figure-ground pixel aspect ratio sketches rounded corners jony ive constraints mental model splash screen.</p>\n"
//                + "                            <p>Placeholder text by <a href=\"http://www.designeripsum.com/\">Designer Ipsum</a>.</p>\n"
//                + "                            <p>Profile images are licensed under a <a href=\"http://creativecommons.org/licenses/by-nc-sa/2.0/deed.en\">Creative Commons BY-NC-SA 2.0</a> license. The images are from Greg Peverill-Conti's <a href=\"http://www.flickr.com/photos/gregpc/\">1,000 faces project</a>.</p>\n"
//                + "                        </section>";
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
