package studentmodule.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.ServletContext;

@Repository
public class StorageProperties {

    @Autowired
    public void setServletContext(ServletContext servletContext) {
        offsetUri = "/SM";
        relativeLocation = "/resources/upload-dir";
        location = servletContext.getRealPath (relativeLocation);
    }

    /**
     * Folder location for storing files
     */
    private String location;

    private String offsetUri;

    private String relativeLocation;

    public String getLocation() {
        return location;
    }

    public String getRelativeLocation() { return  relativeLocation; }

    public String getOffsetUri() { return offsetUri; }

    public void setLocation(String location) {
        this.location = location;
    }

}
