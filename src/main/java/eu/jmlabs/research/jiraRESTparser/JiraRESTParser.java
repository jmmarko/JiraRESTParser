package eu.jmlabs.research.jiraRESTparser;

import eu.jmlabs.research.jiraRESTparser.util.ParseJiraData;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Author: Mark0
 * Created: 20/10/15
 * Updated: 20/10/2015
 */
public class JiraRESTParser {

    private static Logger log = LoggerFactory.getLogger(JiraRESTParser.class.getName());

    public static String jiraRESTurl;
    public static int jiraProjectId;
    public static String jiraProjectKey;
    public static String secret;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("databaseConn");
        EntityManager em = emf.createEntityManager();

        //initialization - read data about Jira rest link, project id and key, Jira Version, etc...
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter url to REST API (e.g.: https://jira.company.net/rest/api/latest/): ");
        jiraRESTurl = scanner.nextLine();
        System.out.print("Enter Project ID (e.g.: 10000): ");
        jiraProjectId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Project key (e.g.: TEST): ");
        jiraProjectKey = scanner.nextLine();
        System.out.print("Enter data for authentication (user:password): ");
        secret = new String(Base64.encodeBase64(scanner.nextLine().getBytes()));

        //initialize Jira Parser
        ParseJiraData jiraParser = new ParseJiraData(jiraRESTurl, jiraProjectId, jiraProjectKey, secret, em);

        //get and store data from Jira

        //issue types
        jiraParser.parseJiraIssueType();
        //issue priorities
        jiraParser.parseJiraPriority();
        //issue statues
        jiraParser.parseJiraStatus();
        //issue resolutions
        jiraParser.parseJiraResolution();
        //issue link types
        jiraParser.parseJiraIssueLinkType();

        //project & Components & Versions
        jiraParser.parseJiraProject();

        //jira issues
        jiraParser.parseJiraIssues();

        //issue subtaks, comments, worklog, attachments and changeLog
        jiraParser.parseJiraIssuesDetails();

        em.close();
        emf.close();
    }
}
