package engine.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ResourceLoader {

    public static String loadResource(String filePath) throws Exception{
        InputStream in = Class.forName(ResourceLoader.class.getName()).getResourceAsStream(filePath);
        Scanner scanner = new Scanner(in,"UTF-8");

        return scanner.useDelimiter("\\A").next();
    }
}
