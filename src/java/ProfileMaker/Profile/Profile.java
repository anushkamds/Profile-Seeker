/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProfileMaker.Profile;

import ProfileMaker.GitHubExtractor;
import ProfileMaker.GoogleScholarExtractor;
import ProfileMaker.LinkedInExtractor;
import java.util.ArrayList;

public class Profile {

    public String name, title, summary, pic_url,education;
    public ArrayList<Experience> experienceList = new ArrayList<Experience>();
    public ArrayList<Project> projectsList = new ArrayList<Project>();
    public ArrayList<Publication> publicationList = new ArrayList<Publication>();

    public Profile(String searchName) {

        LinkedInExtractor linkedIn = new LinkedInExtractor();
        GoogleScholarExtractor gscholar = new GoogleScholarExtractor();
        GitHubExtractor github = new GitHubExtractor("69e07dde89a8a0a6713f810cfd4c461f04f47e85");

        linkedIn.Extract1(searchName, this);
        gscholar.Extract(searchName, this);
        github.Extract(searchName, this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public ArrayList getExperienceList() {
        return experienceList;
    }

    public void setExperienceList(ArrayList experienceList) {
        this.experienceList = experienceList;
    }

    public ArrayList getProjectsList() {
        return projectsList;
    }

    public void setProjectsList(ArrayList projectsList) {
        this.projectsList = projectsList;
    }

    public ArrayList getPublicationList() {
        return publicationList;
    }

    public void setPublicationList(ArrayList publicationList) {
        this.publicationList = publicationList;
    }

    public void addProject(Project project) {
        projectsList.add(project);
    }

    public void addExperience(Experience experience) {
        experienceList.add(experience);
    }

    public void addPublication(Publication publication) {
        publicationList.add(publication);
    }
}
