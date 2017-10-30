package student.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.ServletContext;

@Repository
public class StorageProperties {

    @Autowired
    public void setServletContext(ServletContext servletContext) {
        location = servletContext.getRealPath ("/WEB-INF/upload-dir");
        System.out.println(location);
    }

    /**
     * Folder location for storing files
     */
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
