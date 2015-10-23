package eu.jmlabs.research.jiraRESTparser.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import eu.jmlabs.research.jiraRESTparser.dto.*;
import eu.jmlabs.research.jiraRESTparser.entity.*;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Mark0
 * Date: 9/6/13
 * Time: 11:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class ParseJiraData {

    private static Logger log = LoggerFactory.getLogger(ParseJiraData.class.getName());

    private int jiraProjectId;
    private String jiraProjectKey;
    private String jiraRESTlink;
    private JsonParser parser;
    private String secret;

    Set<Integer> issues = new HashSet<Integer>();
    Set<String> authors = new HashSet<String>();
    Set<Integer> groups = new HashSet<Integer>();

    private EntityManager em;

    public ParseJiraData() {
    }

    public ParseJiraData(String jiraRESTlink, int jiraProjectId, String jiraProjectKey, String secret, EntityManager em) {

        this.jiraProjectId = jiraProjectId;
        this.jiraProjectKey = jiraProjectKey;
        this.jiraRESTlink = jiraRESTlink;
        this.secret = secret;
        this.em = em;

        parser = new JsonParser();
    }

    public String makeRESTrequest(String requestPath, String requestMethodType, String input) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(jiraRESTlink+requestPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(requestMethodType);
            conn.setRequestProperty("Content-Type", "application/json");
            if(secret != "" && !secret.equals("")) {
                conn.setRequestProperty("Authorization", "Basic " + secret);
            }

            if(requestMethodType.equals("GET")) {
                if (conn.getResponseCode() != 200) {
                    if(conn.getResponseCode() == 403) {
                        log.info("ERROR: Failed : HTTP error code: " +  conn.getResponseCode() + " MSG: " +  conn.getResponseMessage());
                        return null;
                    } else {
                        throw new RuntimeException("Failed : HTTP error code : "
                              + conn.getResponseCode() + " MSG: " +  conn.getResponseMessage());
                    }

                }
            }

            if(requestMethodType.equals("POST")) {
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                os.write(input.getBytes());
                os.flush();

                if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + conn.getResponseCode());
                }
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                result.append(output);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * Parse Jira Issues Types! First check if already exists, and in that case
     * only update the current content.
     *
     * @return status of parsing
     */
    public boolean parseJiraIssueType() {
        //read Jira Issue Types

        String jsonString = makeRESTrequest("issuetype", "GET", "");

        Type listType = new TypeToken<List<JiraIssueTypeDTO>>() {}.getType();
        List<JiraIssueTypeDTO> issueTypes = new Gson().fromJson(jsonString, listType);

        em.getTransaction().begin();
        for (JiraIssueTypeDTO issueType : issueTypes) {

            JiraIssueTypeEntity entity;
            try {
                entity = em.createQuery("SELECT e FROM JiraIssueTypeEntity e where e.issueTypeId = :issueTypeId", JiraIssueTypeEntity.class)
                        .setParameter("issueTypeId", issueType.getId()).getSingleResult();
            } catch (NoResultException e) {
                //create new issue type!
                entity = new JiraIssueTypeEntity();
                entity.setIssueTypeId(issueType.getId());
            }

            entity.setName(issueType.getName());
            entity.setSubtask(issueType.isSubtask());
            //entity.setIcon();
            entity.setDescription(issueType.getDescription());

            em.merge(entity);
        }
        em.getTransaction().commit();
        return true;
    }

    /**
     * Parse Jira Priority! First check if already exists, and in that case
     * only update the current content.
     *
     * @return status of parsing
     */
    public boolean parseJiraPriority() {

        String jsonString = makeRESTrequest("priority", "GET", "");

        Type listType = new TypeToken<List<JiraPriorityDTO>>() {}.getType();
        List<JiraPriorityDTO> priorities = new Gson().fromJson(jsonString, listType);

        em.getTransaction().begin();
        for (JiraPriorityDTO priority : priorities) {
            JiraPriorityEntity entity;
            try {
                entity = em.createQuery("SELECT e FROM JiraPriorityEntity e where e.priorityId = :priorityId", JiraPriorityEntity.class)
                        .setParameter("priorityId", priority.getId()).getSingleResult();
            } catch (NoResultException e) {
                //create new user!
                entity = new JiraPriorityEntity();
                entity.setPriorityId(priority.getId());
            }

            entity.setName(priority.getName());
            entity.setDescription(priority.getDescription());
            //entity.setIcon();
            entity.setStatusColor(priority.getStatusColor());

            em.merge(entity);
        }
        em.getTransaction().commit();
        return true;
    }

    /**
     * Parse Jira Status! First check if already exists, and in that case
     * only update the current content.
     *
     * @return status of parsing
     */
    public boolean parseJiraStatus() {
        String jsonString = makeRESTrequest("status", "GET", "");

        Type listType = new TypeToken<List<JiraStatusDTO>>() {}.getType();
        List<JiraStatusDTO> statues = new Gson().fromJson(jsonString, listType);

        em.getTransaction().begin();
        for (JiraStatusDTO status : statues ) {
            JiraStatusEntity entity;
            try {
                entity = em.createQuery("SELECT e FROM JiraStatusEntity e where e.statusId = :statusId", JiraStatusEntity.class)
                        .setParameter("statusId", status.getId()).getSingleResult();
            } catch (NoResultException e) {
                //create new user!
                entity = new JiraStatusEntity();
                entity.setStatusId(status.getId());
            }

            entity.setName(status.getName());
            entity.setDescription(status.getDescription());
            //entity.setIcon();

            em.merge(entity);
        }
        em.getTransaction().commit();
        return true;
    }

    /**
     * Parse Jira Resolution! First check if already exists, and in that case
     * only update the current content.
     *
     * @return status of parsing
     */
    public boolean parseJiraResolution() {
        String jsonString = makeRESTrequest("resolution", "GET", "");

        Type listType = new TypeToken<List<JiraResolutionDTO>>() {}.getType();
        List<JiraResolutionDTO> resolutions = new Gson().fromJson(jsonString, listType);

        em.getTransaction().begin();
        for (JiraResolutionDTO resolution : resolutions) {
            JiraResolutionEntity entity;
            try {
                entity = em.createQuery("SELECT e FROM JiraResolutionEntity e where e.resolutionId = :resolutionId", JiraResolutionEntity.class)
                        .setParameter("resolutionId", resolution.getId()).getSingleResult();
            } catch (NoResultException e) {
                //create new user!
                entity = new JiraResolutionEntity();
                entity.setResolutionId(resolution.getId());
            }

            entity.setName(resolution.getName());
            entity.setDescription(resolution.getDescription());

            em.merge(entity);
        }
        em.getTransaction().commit();
        return true;
    }

    /**
     * Parse Jira Issue link type! First check if already exists, and in that case
     * only update the current content.
     *
     * @return status of parsing
     */
    public boolean parseJiraIssueLinkType() {
        String jsonString = makeRESTrequest("issueLinkType", "GET", "");

        jsonString = jsonString.substring(18, jsonString.length()-1);
        Type listType = new TypeToken<List<JiraIssueLinkTypeDTO>>() {}.getType();
        List<JiraIssueLinkTypeDTO> issueLinkTypes = new Gson().fromJson(jsonString, listType);

        em.getTransaction().begin();
        for (JiraIssueLinkTypeDTO issueLinkType : issueLinkTypes) {
            JiraIssueLinkTypeEntity entity;
            try {
                entity = em.createQuery("SELECT e FROM JiraIssueLinkTypeEntity e where e.issueLinkTypeId = :issueLinkTypeId",
                        JiraIssueLinkTypeEntity.class)
                        .setParameter("issueLinkTypeId", issueLinkType.getId()).getSingleResult();
            } catch (NoResultException e) {
                //create new user!
                entity = new JiraIssueLinkTypeEntity();
                entity.setIssueLinkTypeId(issueLinkType.getId());
            }

            entity.setLinkname(issueLinkType.getName());
            entity.setInward(issueLinkType.getInward());
            entity.setOutward(issueLinkType.getOutward());

            em.merge(entity);
        }
        em.getTransaction().commit();
        return true;
    }

    /**
     * Parse Jira Project! First check if already exists, and in that case
     * only update the current content.
     *
     * @return status of parsing
     */
    public boolean parseJiraProject() {
        String jsonString = makeRESTrequest("project/"+jiraProjectKey, "GET", "");

        Type listType = new TypeToken<JiraProjectDTO>() {}.getType();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        JiraProjectDTO project = gson.fromJson(jsonString, listType);

        //store all the info about the project to DB
        em.getTransaction().begin();
        JiraProjectEntity entity;

        try {
            entity = em.createQuery("SELECT e FROM JiraProjectEntity e where e.projectId = :projectId", JiraProjectEntity.class)
                    .setParameter("projectId", project.getId()).getSingleResult();
        } catch (NoResultException e) {
            //create new project!
            entity = new JiraProjectEntity();
            entity.setProjectId(project.getId());
        }

        entity.setName(project.getName());
        entity.setDescription(project.getDescription());
        entity.setLeader(project.getLead().getKey());
        entity.setProjectKey(jiraProjectKey);

        em.merge(entity);
        em.getTransaction().commit();

        em.getTransaction().begin();
        //store data about versions and components
        for(JiraVersionDTO version : project.getVersions()) {
            JiraVersionEntity entityVersion;

            try {
                entityVersion = em.createQuery("SELECT e FROM JiraVersionEntity e where e.versionId = :versionId",
                        JiraVersionEntity.class)
                        .setParameter("versionId", version.getId()).getSingleResult();
            } catch (NoResultException e) {
                //create new version!
                entityVersion = new JiraVersionEntity();
                entityVersion.setVersionId(version.getId());
            }

            entityVersion.setName(version.getName());
            entityVersion.setDescription(version.getDescription());
            entityVersion.setProject(entity);
            if(version.getReleaseDate() != null)
                entityVersion.setReleaseDate(new Timestamp(version.getReleaseDate().getTime()));

            em.merge(entityVersion);
        }

        for(JiraComponentDTO component : project.getComponents()) {
            JiraComponentEntity entityComponent;
            try {
                entityComponent = em.createQuery("SELECT e FROM JiraComponentEntity e where e.componentId = :componentId",
                        JiraComponentEntity.class)
                        .setParameter("componentId", component.getId()).getSingleResult();
            } catch (NoResultException e) {
                //create new version!
                entityComponent = new JiraComponentEntity();
                entityComponent.setComponentId(component.getId());
            }

            entityComponent.setName(component.getName());
            //TODO entityComponent.setLead(node.valueOf("@lead"));
            entityComponent.setProjectId(jiraProjectId);

            em.merge(entityComponent);
        }

        em.getTransaction().commit();

        return true;
    }


    /**
     * Parse Jira Issues! First check if already exists, and in that case
     * only update the current content. Before parsing issues its important that we parse
     * all code lists.
     *
     * @return status of parsing
     */
    public boolean parseJiraIssuesDetails() {

        //read all issues  and for each issue store subtaks, comments, worklog, attachments and changeLog
        List<Object[]> jiraIssues = em.createQuery("SELECT e.issueId, e.id FROM JiraIssueEntity e " +
                "WHERE e.pkey LIKE :pkey AND e.issueDetailsParsed IS NULL ORDER by e.issueId DESC", Object[].class)
                .setParameter("pkey", "%" + jiraProjectKey + "%")
                .getResultList();

        log.info("SIZE: " + jiraIssues.size());

        int counter = 0;
        em.getTransaction().begin();

        issues: for(Object[] issue : jiraIssues) {
            int issueId = (Integer)issue[0];
            int issueArtificialId = (Integer)issue[1];
            counter++;
            //do request and store data!
            String jsonString = makeRESTrequest("issue/"+issueId+"?expand=changelog", "GET", "");

            if(jsonString == null) {
                log.info("ISSUE: " + issueId + " FORBIDDEN!");
                continue issues;
            }
            //log.info("Working on issue: " + jiraIssue.getPkey());
            log.info("Working on issue: " + issueId);

            JsonObject issueObject = new JsonParser().parse(jsonString).getAsJsonObject();

            //get fields
            JsonObject issueFields = issueObject.get("fields").getAsJsonObject();

            //subtasks
            JsonArray subtasks = issueFields.get("subtasks").getAsJsonArray();
            for(int j=0; j<subtasks.size(); j++) {
                storeSubtask(subtasks.get(j).getAsJsonObject(), issueId);
            }

            //comments
            JsonArray comments = issueFields.get("comment").getAsJsonObject().get("comments").getAsJsonArray();
            for(int j=0; j<comments.size(); j++) {
                storeComment(comments.get(j).getAsJsonObject(), issueId);
            }

            //worklog
            if(issueFields.has("worklog")) {
                JsonArray worklog = issueFields.get("worklog").getAsJsonObject().get("worklogs").getAsJsonArray();
                for (int j = 0; j < worklog.size(); j++) {
                    storeWorklog(worklog.get(j).getAsJsonObject(), issueId);
                }
            }

            //attachemnts
            if(issueFields.has("attachment")) {
                JsonArray attachments = issueFields.get("attachment").getAsJsonArray();
                for (int j = 0; j < attachments.size(); j++) {
                    storeAttachment(attachments.get(j).getAsJsonObject(), issueId);
                }
            }

            //changeLog
            JsonArray changelog = issueObject.get("changelog").getAsJsonObject().get("histories").getAsJsonArray();
            for(int j=0; j<changelog.size(); j++) {
                storeChangeLog(changelog.get(j).getAsJsonObject(), issueId);
            }

            JiraIssueEntity jiraIssue = em.find(JiraIssueEntity.class, issueArtificialId);
            jiraIssue.setIssueDetailsParsed(true);
            em.merge(jiraIssue);

            if(counter%200 == 0) {
                log.info(counter +"/"+jiraIssues.size() + " parsed so far!");
                em.getTransaction().commit();
                em.getTransaction().begin();
            }

        }
        em.getTransaction().commit();
        return true;
    }

    /**
     * Parse Jira Issues! First check if already exists, and in that case
     * only update the current content. Before parsing issues its important that we parse
     * all code lists.
     *
     * @return status of parsing
     */
    public boolean parseJiraIssues() {
        int startAt = 0;
        int maxResults = 500;
        int totalResults = 1000000;

        //fill issue set
        List<Integer> issueIds = em.createQuery("SELECT e.issueId FROM JiraIssueEntity e WHERE e.projectId = :projectId " +
                " AND e.reporter IS NOT NULL", Integer.class).setParameter("projectId", jiraProjectId).getResultList();
        log.info("#ISSUES from database size: " + issueIds.size());
        issues.addAll(issueIds);
        log.info("#ISSUES added to set: " + issues.size());

        //fill authors
        List<String> authorsList = em.createQuery("SELECT e.username FROM JiraUserEntity e", String.class).getResultList();
        authors.addAll(authorsList);
        log.info("#AUTHORS added to set: " + authors.size());

        while(startAt < totalResults) {
            //do query
            String jsonString = makeRESTrequest("search?jql=project="+jiraProjectKey+"&startAt="+startAt+"&maxResults="+maxResults, "GET", "");
            JsonObject jsonObj = new JsonParser().parse(jsonString).getAsJsonObject();

            //set total results
            totalResults = jsonObj.get("total").getAsInt();
            JsonArray issuesAray = jsonObj.getAsJsonArray("issues");
            for(int i=0; i<issuesAray.size(); i++) {
                JsonObject issue = issuesAray.get(i).getAsJsonObject();
                int issueId = issue.get("id").getAsInt();
                String issuePKey = issue.get("key").getAsString();

                if (!issues.contains(issueId)) {

                    log.debug("ISSUE: " + issueId + " - " + issuePKey);
                    //get fields
                    JsonObject issueFields = issue.get("fields").getAsJsonObject();

                    //check if reporter and assignee already exists in database. If not create both

                    //store users - reporter and assignee
                    JsonObject reporter = null;
                    String reporterUsername = null;
                    if(issueFields.get("reporter").isJsonObject()) {
                        reporter = issueFields.get("reporter").getAsJsonObject();
                        reporterUsername = reporter.get("name").getAsString();
                    } else {
                        log.info("ERROR: reporter not a JSON Object or not exists! -> check if creator exists!");
                        if(issueFields.has("creator")) {
                            if(issueFields.get("creator").isJsonObject()) {
                                reporter = issueFields.get("creator").getAsJsonObject();
                                reporterUsername = reporter.get("name").getAsString();
                            } else {
                                log.info("ERROR: creator not a JSON Object or not exists!");
                                log.info("Reporter: " + issueFields.get("reporter").toString());
                            }
                            log.info("Creator exists! - " + reporterUsername);
                        } else {
                            log.info("Creator do not exists!");
                        }
                    }

                    String assigneeUsername = null;
                    if (!issueFields.get("assignee").isJsonNull()) {
                        JsonObject assignee = issueFields.get("assignee").getAsJsonObject();
                        assigneeUsername = assignee.get("name").getAsString();
                    }
                    if (reporterUsername != null && !authors.contains(reporterUsername)) {
                        storeUserToDB(reporter);
                        authors.add(reporterUsername);
                    }
                    if (assigneeUsername != null && !authors.contains(assigneeUsername)) {
                        storeUserToDB(issueFields.get("assignee").getAsJsonObject());
                        authors.add(reporterUsername);
                    }


                    em.getTransaction().begin();
                    JiraIssueEntity entity;
                    try {
                        entity = em.createQuery("SELECT e FROM JiraIssueEntity e where e.issueId = :issueId AND e.pkey = :pkey ",
                                JiraIssueEntity.class)
                                .setParameter("issueId", issueId).setParameter("pkey", issuePKey).getSingleResult();
                        log.info("INFO: Issue exists!");
                    } catch (NoResultException e) {
                        //create new version!
                        entity = new JiraIssueEntity();
                        entity.setIssueId(issueId);
                    }

                    entity.setProjectId(jiraProjectId);
                    entity.setPkey(issue.get("key").getAsString());

                    //set reporter and assignee
                    entity.setReporter(reporterUsername);
                    entity.setAssignee(assigneeUsername);

                    entity.setTypeId(issueFields.get("issuetype").getAsJsonObject().get("id").getAsInt());
                    entity.setSummary(issueFields.get("summary").getAsString());
                    if (!issueFields.get("description").isJsonNull())
                        entity.setDescription(issueFields.get("description").getAsString());
                    if (issueFields.has("environment") && !issueFields.get("environment").isJsonNull())
                        entity.setEnvironment(issueFields.get("environment").getAsString());

                    if (!issueFields.get("priority").isJsonNull())
                        entity.setPriorityId(issueFields.get("priority").getAsJsonObject().get("id").getAsInt());
                    if (!issueFields.get("resolution").isJsonNull())
                        entity.setResolutionId(issueFields.get("resolution").getAsJsonObject().get("id").getAsInt());
                    entity.setStatusId(issueFields.get("status").getAsJsonObject().get("id").getAsInt());

                    DateTime dt = new DateTime(issueFields.get("created").getAsString());
                    entity.setCreatedDate(new Timestamp(dt.getMillis()));

                    dt = new DateTime(issueFields.get("updated").getAsString());
                    entity.setUpdatedDate(new Timestamp(dt.getMillis()));

                    if (!issueFields.get("resolutiondate").isJsonNull()) {
                        dt = new DateTime(issueFields.get("resolutiondate").getAsString());
                        entity.setResolutionDate(new Timestamp(dt.getMillis()));
                    }
                    if (issueFields.has("votes") && !issueFields.get("votes").isJsonNull()) {
                        entity.setVotes(issueFields.get("votes").getAsJsonObject().get("votes").getAsInt());
                    }
                    if (issueFields.has("watches") && !issueFields.get("watches").isJsonNull()) {
                        entity.setWatches(issueFields.get("watches").getAsJsonObject().get("watchCount").getAsInt());
                    }

                    em.merge(entity);
                    em.getTransaction().commit();
                    issues.add(issueId);

                    //issue links
                    JsonArray issueLinks = issueFields.get("issuelinks").getAsJsonArray();
                    for (int j = 0; j < issueLinks.size(); j++) {
                        storeIssueLink(issueLinks.get(j).getAsJsonObject(), issueId);
                    }

                    //components
                    JsonArray components = issueFields.get("components").getAsJsonArray();
                    for (int j = 0; j < components.size(); j++) {
                        storeIssueComponentRelation(components.get(j).getAsJsonObject(), issueId);
                    }

                    //fixVersions
                    JsonArray versions = issueFields.get("fixVersions").getAsJsonArray();
                    for (int j = 0; j < versions.size(); j++) {
                        storeIssueVersionRelation(versions.get(j).getAsJsonObject(), issueId);
                    }
                }
            }

            startAt += maxResults;
            if(startAt%500 == 0) {
                log.info(startAt +"/"+totalResults + " parsed so far!");
            }
        }
        return true;
    }

    public void storeUserToDB(JsonObject user) {
        boolean activeTransaction = true;
        if(!em.getTransaction().isActive()) {
            em.getTransaction().begin();
            activeTransaction = false;
        }
        String username = user.get("name").getAsString();
        String jsonString = makeRESTrequest("user?username="+username, "GET", "");
        user = new JsonParser().parse(jsonString).getAsJsonObject();

        JiraUserEntity entity;
        try {
            entity = em.createQuery("SELECT u FROM JiraUserEntity u where u.username = :username", JiraUserEntity.class)
                    .setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            //create new user!
            entity = new JiraUserEntity();
            entity.setUsername(username);
        }

        if(user.has("emailAddress")) {
            entity.setEmail(user.get("emailAddress").getAsString());
        }
        if(user.has("displayName")) {
            entity.setDisplayName(user.get("displayName").getAsString());
        }
        if(user.has("active")) {
            entity.setIsActive(user.get("active").getAsBoolean());
        }

        em.merge(entity);
        if (!activeTransaction) {
            em.getTransaction().commit();
        }
    }

    public void storeIssue(JsonObject issue) {
        boolean activeTransaction = true;
        int issueId = issue.get("id").getAsInt();
        if(!em.getTransaction().isActive()) {
            em.getTransaction().begin();
            activeTransaction = false;
        }
        JiraIssueEntity entity;
        try {
            entity = em.createQuery("SELECT e FROM JiraIssueEntity e where e.issueId = :issueId", JiraIssueEntity.class)
                    .setParameter("issueId", issueId).getSingleResult();
        } catch (NoResultException e) {
            //create new version!
            entity = new JiraIssueEntity();
            entity.setIssueId(issueId);
            entity.setProjectId(jiraProjectId);
            entity.setPkey(issue.get("key").getAsString());

            //get fields
            JsonObject issueFields = issue.get("fields").getAsJsonObject();

            entity.setTypeId(issueFields.get("issuetype").getAsJsonObject().get("id").getAsInt());
            entity.setSummary(issueFields.get("summary").getAsString());
            if(issueFields.has("priority") && !issueFields.get("priority").isJsonNull())
                entity.setPriorityId(issueFields.get("priority").getAsJsonObject().get("id").getAsInt());
            if(issueFields.has("status") && !issueFields.get("status").isJsonNull())
                entity.setStatusId(issueFields.get("status").getAsJsonObject().get("id").getAsInt());
            em.persist(entity);
        }

        if(!activeTransaction) {
            em.getTransaction().commit();
        }
    }

    public void storeIssueLink(JsonObject issueLink, int issueId) {
        //create issue if not exists!
        //vhodna povezava
        JsonObject inwardIssue = null;
        Integer inwardIssueId = null;
        JsonObject outwardIssue = null;
        Integer outwardIssueId = null;
        if(issueLink.has("inwardIssue")) {
            inwardIssue = issueLink.getAsJsonObject("inwardIssue");
            inwardIssueId = inwardIssue.get("id").getAsInt();
            if(!issues.contains(inwardIssueId)) {
                storeIssue(inwardIssue);
                //issues.add(inwardIssueId);
            }
        }
        //izhodna povezava
        else if(issueLink.has("outwardIssue")) {
            outwardIssue = issueLink.getAsJsonObject("outwardIssue");
            outwardIssueId = outwardIssue.get("id").getAsInt();
            if(!issues.contains(outwardIssueId)) {
                storeIssue(outwardIssue);
                //issues.add(outwardIssueId);
            }
        }

        em.getTransaction().begin();
        JiraIssueLinkEntity entity;
        int issueLinkId = issueLink.get("id").getAsInt();

        try {
            entity = em.createQuery("SELECT e FROM JiraIssueLinkEntity e where e.issueLinkId = :issueLinkId ", JiraIssueLinkEntity.class)
                    .setParameter("issueLinkId", issueLinkId).getSingleResult();
        } catch (NoResultException e) {
            //create new issue link!
            entity = new JiraIssueLinkEntity();
            entity.setIssueLinkId(issueLinkId);
        }

        entity.setLinkTypeId(issueLink.get("type").getAsJsonObject().get("id").getAsInt());

        //vhodna povezava
        if(inwardIssueId != null) {
            entity.setSourceIssueId(issueLink.getAsJsonObject("inwardIssue").get("id").getAsInt());
            entity.setDestinationIssueId(issueId);
        }
        //izhodna povezava
        else if(outwardIssueId != null) {
            entity.setSourceIssueId(issueId);
            entity.setDestinationIssueId(issueLink.getAsJsonObject("outwardIssue").get("id").getAsInt());
        } else {
            log.info("ERROR - nor inward nor outward exists!");
        }
        em.merge(entity);

        em.getTransaction().commit();
    }

    public void storeIssueComponentRelation(JsonObject relation, int issueId) {
        em.getTransaction().begin();
        int componentId = relation.get("id").getAsInt();

        JiraIssueComponentEntity entity;
        try {
            entity = em.createQuery("SELECT u FROM JiraIssueComponentEntity u where u.componentId = :componentId " +
                    "AND u.issueId = :issueId", JiraIssueComponentEntity.class)
                    .setParameter("componentId", componentId).setParameter("issueId", issueId)
                    .getSingleResult();
        } catch (NoResultException e) {
            //create new relation!
            entity = new JiraIssueComponentEntity();
            entity.setComponentId(componentId);
            entity.setIssueId(issueId);
        }

        em.merge(entity);
        em.getTransaction().commit();
    }

    public void storeIssueVersionRelation(JsonObject relation, int issueId) {
        em.getTransaction().begin();
        int versionId = relation.get("id").getAsInt();

        JiraIssueFixVersionEntity entity;
        try {
            entity = em.createQuery("SELECT u FROM JiraIssueFixVersionEntity u where u.versionId = :versionId " +
                    "AND u.issueId = :issueId", JiraIssueFixVersionEntity.class)
                    .setParameter("versionId", versionId).setParameter("issueId", issueId)
                    .getSingleResult();
        } catch (NoResultException e) {
            //create new relation!
            entity = new JiraIssueFixVersionEntity();
            entity.setVersionId(versionId);
            entity.setIssueId(issueId);
        }

        em.merge(entity);
        em.getTransaction().commit();
    }

    public void storeChangeLog(JsonObject changeLog, int issueId) {
        boolean exists = true;
        int changeGroupId = changeLog.get("id").getAsInt();

        JsonObject author = null;
        String authorName = null;
        if(changeLog.has("author") && !changeLog.get("author").isJsonNull()) {
            author = changeLog.get("author").getAsJsonObject();
            authorName = author.get("name").getAsString();
            if(!authors.contains(authorName)) {
                storeUserToDB(author);
                authors.add(authorName);
            }
        }

        JiraChangeGroupEntity entity;
        try {
            entity = em.createQuery("SELECT e FROM JiraChangeGroupEntity e where e.changeGroupId = :changeGroupId",
                    JiraChangeGroupEntity.class)
                    .setParameter("changeGroupId", changeGroupId).getSingleResult();
        } catch (NoResultException e) {
            //create new change group!
            exists = false;
            entity = new JiraChangeGroupEntity();
            entity.setChangeGroupId(changeGroupId);
        }

        entity.setIssueId(issueId);
        if(author != null)
            entity.setAuthor(authorName);
        DateTime dt = new DateTime(changeLog.get("created").getAsString());
        entity.setCreatedDate(new Timestamp(dt.getMillis()));

        em.merge(entity);

        //TODO also check and update items if they already exists!

        //if not exists store its items!
        if(!exists) {
            JsonArray changeItems = changeLog.getAsJsonArray("items");
            for(int i=0; i<changeItems.size(); i++) {
                JsonObject changeItem = changeItems.get(i).getAsJsonObject();

                //store item
                JiraChangeItemEntity entityItem = new JiraChangeItemEntity();
                entityItem.setChangeGroupId(changeGroupId);
                entityItem.setField(changeItem.get("field").getAsString());
                if(changeItem.has("fieldtype"))
                    entityItem.setFieldType(changeItem.get("fieldtype").getAsString());
                if(!changeItem.get("fromString").isJsonNull())
                    entityItem.setOldString(changeItem.get("fromString").getAsString());
                if(!changeItem.get("from").isJsonNull())
                    entityItem.setOldValue(changeItem.get("from").getAsString());
                if(!changeItem.get("toString").isJsonNull())
                    entityItem.setNewString(changeItem.get("toString").getAsString());
                if(!changeItem.get("to").isJsonNull())
                    entityItem.setNewValue(changeItem.get("to").getAsString());

                em.persist(entityItem);

            }
        }
    }

    public void storeSubtask(JsonObject subtask, int issueId) {
        //first store issue if not exists, then store info about relations
        int childIssueId = subtask.get("id").getAsInt();

        if(!issues.contains(childIssueId)) {
            storeIssue(subtask);
        }

        JiraSubtaskEntity entity;
        try {
            entity = em.createQuery("SELECT u FROM JiraSubtaskEntity u where u.parentIssueId = :parentIssueId " +
                    "AND u.childIssueId = :childIssueId", JiraSubtaskEntity.class)
                    .setParameter("parentIssueId", issueId).setParameter("childIssueId", childIssueId).getSingleResult();
        } catch (NoResultException e) {
            //create new relation!
            entity = new JiraSubtaskEntity();
            entity.setParentIssueId(issueId);
            entity.setChildIssueId(childIssueId);
        }

        em.merge(entity);
    }

    public void storeComment(JsonObject comment, int issueId) {
        //check if authors exists!
        JsonObject author = comment.get("author").getAsJsonObject();
        String authorName = author.get("name").getAsString();
        JsonObject updateAuthor = comment.get("updateAuthor").getAsJsonObject();
        String updateAuthorName = updateAuthor.get("name").getAsString();

        if(!authors.contains(authorName)) {
            storeUserToDB(author);
            authors.add(authorName);
            //log.info("ERROR - author not exists!");
        }
        if(!authors.contains(updateAuthorName)) {
            storeUserToDB(updateAuthor);
            authors.add(updateAuthorName);
            //log.info("ERROR - update author not exists!");
        }

        int commentId = comment.get("id").getAsInt();

        JiraIssueCommentEntity entity;
        try {
            entity = em.createQuery("SELECT u FROM JiraIssueCommentEntity u where u.commentId = :commentId " +
                    "AND u.issueId = :issueId", JiraIssueCommentEntity.class)
                    .setParameter("commentId", commentId).setParameter("issueId", issueId).getSingleResult();
        } catch (NoResultException e) {
            //create new relation!
            entity = new JiraIssueCommentEntity();
            entity.setIssueId(issueId);
            entity.setCommentId(commentId);
        }

        entity.setAuthor(authorName);
        entity.setUpdateAuthor(updateAuthorName);
        entity.setBody(comment.get("body").getAsString());
        DateTime dt = new DateTime(comment.get("created").getAsString());
        entity.setCreated(new Timestamp(dt.getMillis()));
        dt = new DateTime(comment.get("updated").getAsString());
        entity.setUpdated(new Timestamp(dt.getMillis()));

        em.merge(entity);
    }

    /**
     * Parse Jira Worklog, which is related to IssueId! First check if already exists, and in that case
     * only update the current content. Before parsing worklog its important that we parse
     * all code lists and all issues.
     *
     * @return status of parsing
     */
    public boolean storeWorklog(JsonObject worklog, int issueId) {
        //check if authors exists!
        JsonObject author = worklog.get("author").getAsJsonObject();
        String authorName = author.get("name").getAsString();
        JsonObject updateAuthor = worklog.get("updateAuthor").getAsJsonObject();
        String updateAuthorName = updateAuthor.get("name").getAsString();

        if (!authors.contains(authorName)) {
            storeUserToDB(author);
            authors.add(authorName);
            //log.info("ERROR - author not exists!");
        }
        if (!authors.contains(updateAuthorName)) {
            storeUserToDB(updateAuthor);
            authors.add(updateAuthorName);
            //log.info("ERROR - update author not exists!");
        }

        int worklogId = worklog.get("id").getAsInt();

        JiraWorklogEntity entity;
        try {
            entity = em.createQuery("SELECT u FROM JiraWorklogEntity u where u.worklogId = :worklogId " +
                    "AND u.issueId = :issueId", JiraWorklogEntity.class)
                    .setParameter("worklogId", worklogId).setParameter("issueId", issueId).getSingleResult();
        } catch (NoResultException e) {
        //create new relation!
        entity = new JiraWorklogEntity();
        entity.setIssueId(issueId);
        entity.setWorklogId(worklogId);
        }

        entity.setAuthor(authorName);
        entity.setUpdateAuthor(updateAuthorName);
        DateTime dt = new DateTime(worklog.get("created").getAsString());
        entity.setCreatedDate(new Timestamp(dt.getMillis()));
        dt = new DateTime(worklog.get("updated").getAsString());
        entity.setUpdateDate(new Timestamp(dt.getMillis()));
        dt = new DateTime(worklog.get("started").getAsString());
        if(dt.getYear() > 1990) {
            entity.setStartDate(new Timestamp(dt.getMillis()));
        }
        if(worklog.has("comment") && !worklog.get("comment").isJsonNull()) {
            entity.setContent(worklog.get("comment").getAsString());
        }
        entity.setTimeWorked(worklog.get("timeSpentSeconds").getAsLong());

        em.merge(entity);
        return true;
    }

    /**
     * Parse Jira Worklog, which is related to IssueId! First check if already exists, and in that case
     * only update the current content. Before parsing worklog its important that we parse
     * all code lists and all issues.
     *
     * @return status of parsing
     */
    public boolean storeAttachment(JsonObject attachment, int issueId) {

        int attachmentId = attachment.get("id").getAsInt();

        JiraAttachmentEntity entity = new JiraAttachmentEntity();
        ;

        //check if authors exists!
        if (attachment.has("author")) {
            JsonObject author = attachment.get("author").getAsJsonObject();
            String authorName = author.get("name").getAsString();

            if (!authors.contains(authorName)) {
                storeUserToDB(author);
                authors.add(authorName);
                //log.info("ERROR - author not exists!");
            }
            entity.setAuthor(authorName);
        }

        try {
            entity = em.createQuery("SELECT u FROM JiraAttachmentEntity u where u.attachmentId = :attachmentId " +
                    "AND u.issueId = :issueId", JiraAttachmentEntity.class)
                    .setParameter("attachmentId", attachmentId).setParameter("issueId", issueId).getSingleResult();
        } catch (NoResultException e) {
            //create new relation!
            entity.setIssueId(issueId);
            entity.setAttachmentId(attachmentId);
        }


        DateTime dt = new DateTime(attachment.get("created").getAsString());
        entity.setCreatedDate(new Timestamp(dt.getMillis()));
        entity.setFilename(attachment.get("filename").getAsString());
        entity.setFileSize(attachment.get("size").getAsLong());
        entity.setMimeType(attachment.get("mimeType").getAsString());

        em.merge(entity);
        return true;
    }
}
