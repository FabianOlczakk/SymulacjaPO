package engine.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.InputStream;
import java.util.Scanner;

public class ResourceLoader {

    /**
     * Loads the content of a resource file as a string.
     *
     * @param filePath The path of the resource file to load.
     * @return The content of the resource file as a string.
     * @throws Exception If the resource file cannot be loaded.
     */
    public static String loadResource(String filePath) throws Exception {
        // Get the input stream for the specified resource file
        InputStream in = Class.forName(ResourceLoader.class.getName()).getResourceAsStream(filePath);

        // Create a scanner to read the input stream as text using UTF-8 encoding
        Scanner scanner = new Scanner(in, "UTF-8");

        // Read the entire content of the resource file as a single string
        String content = scanner.useDelimiter("\\A").next();

        return content;
    }
}
