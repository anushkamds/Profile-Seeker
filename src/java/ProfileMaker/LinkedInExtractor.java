package ProfileMaker;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import ProfileMaker.Profile.Profile;
import ProfileMaker.Profile.Publication;

public class LinkedInExtractor {

    public Profile Extract1(String searchName, Profile profile) {

        ProfileMaker.Google g = new ProfileMaker.Google();
        String link = g.FindOnLinkedIn(searchName);
        System.out.println(link);
        if (link == "") {
            profile.setName("not found");
            return profile;
        }
        Document doc = null;
        String picUrl, name, title;
        String[] publications;
        try {
            doc = Jsoup.connect(link).timeout(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
//      profile picture
        Element profile_picture = doc != null ? doc.select("div[id=profile-picture] > img").first() : null;
//        System.out.println(profile_picture);
//        System.out.println(profile_picture.attr("src"));
        if (profile_picture == null) {
            Extract2(searchName, profile);
            return profile;
        }
        profile.pic_url = profile_picture.attr("src");
        System.out.println("pic url " + profile.pic_url);
//        name
        Element nameDiv = doc != null ? doc.select("span.full-name").first() : null;
        System.out.println(nameDiv.text());
        profile.setName(nameDiv.text());
        name = nameDiv.text();
        profile.name = nameDiv.text();

//        title
        Element titleP = doc != null ? doc.select("p").first() : null;
        System.out.println(titleP.text());
        title = titleP.text();
        profile.title = titleP.text();
//        publications
        Publication pb;
        Elements pub = doc != null ? doc.select("ul[class=publications documents] > li >h3 ") : null;
        publications = new String[pub.size()];
        for (int i = 0; i < pub.size(); i++) {
            System.out.println(pub.get(i).text());
            publications[i] = pub.get(i).text();

            pb = new Publication();
            pb.name = pub.get(i).text();
            profile.publicationList.add(pb);
        }
        return profile;
    }

    public Profile Extract2(String searchName, Profile profile) {

        ProfileMaker.Google g = new ProfileMaker.Google();
        String link = g.FindOnLinkedIn(searchName);
        System.out.println(link);
        if (link == "") {
            profile.setName("not found");
            return profile;
        }
        Document doc = null;
        String picUrl, name, title;
        String[] publications;
        try {
            doc = Jsoup.connect(link).timeout(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
//      profile picture
        Element profile_picture = doc != null ? doc.select("div.profile-picture> a > img").first() : null;
//        System.out.println(profile_picture);
//        System.out.println(profile_picture.attr("src"));

        profile.pic_url = profile_picture.attr("src");
        System.out.println("pic url " + profile.pic_url);
//        name
        Element nameDiv = doc != null ? doc.select("span.full-name").first() : null;
        System.out.println(nameDiv.text());
        profile.setName(nameDiv.text());
        name = nameDiv.text();
        profile.name = nameDiv.text();

//        title
        Element titleP = doc != null ? doc.select("p").first() : null;
        System.out.println(titleP.text());
        title = titleP.text();
        profile.title = titleP.text();
//        publications
        Publication pb;
        Elements pub = doc != null ? doc.select("ul[class=publications documents] > li >h3 ") : null;
        publications = new String[pub.size()];
        for (int i = 0; i < pub.size(); i++) {
            System.out.println(pub.get(i).text());
            publications[i] = pub.get(i).text();

            pb = new Publication();
            pb.name = pub.get(i).text();
            profile.publicationList.add(pb);
        }
        return profile;
    }
}
