package eu.jmlabs.research.jiraRESTparser.dto;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Mark0
 * Date: 6/12/14
 * Time: 3:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class JiraIssueLinkTypeDTO implements Serializable {
    int id;
    String inward;
    String outward;
    String name;

    public JiraIssueLinkTypeDTO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInward() {
        return inward;
    }

    public void setInward(String inward) {
        this.inward = inward;
    }

    public String getOutward() {
        return outward;
    }

    public void setOutward(String outward) {
        this.outward = outward;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
