package ProfileMaker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import ProfileMaker.Profile.Profile;
import ProfileMaker.Profile.Publication;

public class GoogleScholarExtractor {
    public Profile Extract(String searchName, Profile profile) {

        Google g=new Google();
        String link=g.FindOnGoogleScholar(searchName);
        if (link=="") {
            return profile;
        }
        Document doc = null;
        String picUrl,name,title;
        String[] publications;
        try {
            doc = Jsoup.connect(link).timeout(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        publications
        System.out.println("=========== Research Papers ===========");
        Elements pub = doc != null ? doc.select("tr.gsc_a_tr") : null;
        Publication pb;
        publications=new String[pub.size()];
        for (int i = 0; i < pub.size() ; i++) {
            System.out.println(pub.get(i).select("td>a.gsc_a_at").text());
            System.out.println("Authors: "+pub.get(i).select("div.gs_gray").get(0).text());
            System.out.println("Publisher: "+pub.get(i).select("div.gs_gray").get(1).text());
            System.out.println("Citations: "+pub.get(i).select("td.gsc_a_c").get(0).text());
            System.out.println("Year: " + pub.get(i).select("td.gsc_a_y").get(0).text());
            System.out.println("---------------------------------\n");
            
            pb=new Publication();
            pb.name=pub.get(i).select("td>a.gsc_a_at").text();
            pb.authors=pub.get(i).select("div.gs_gray").get(0).text();
            try{
                pb.citations=Integer.parseInt(("0"+pub.get(i).select("td.gsc_a_c").get(0).text()).trim());
            }catch(NumberFormatException e){
                System.out.println("Number format exception");
            }
            pb.year=pub.get(i).select("td.gsc_a_y").get(0).text();
            profile.publicationList.add(pb);
        }
        return profile;
    }
}
