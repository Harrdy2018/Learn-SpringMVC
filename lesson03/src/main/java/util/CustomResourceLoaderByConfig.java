package util;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CustomResourceLoaderByConfig implements ResourceLoaderAware {
    private String location;

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String showResourceData() {
        Resource banner = resourceLoader.getResource(getLocation());
        StringBuffer sb = new StringBuffer();
        try (
                InputStream in = banner.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        ){
            while (true) {
                String line = reader.readLine();
                if (line == null)
                    break;
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
