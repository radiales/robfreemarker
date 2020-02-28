import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import freemarker.template.*;

public class Conversion {

    private static Object Version;

    public static void main(String[] args) throws IOException, TemplateException {

        // 1. Configure FreeMarker
        //
        // You should do this ONLY ONCE, when your application starts,
        // then reuse the same Configuration object elsewhere.

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);

        // Where do we load the templates from:
        cfg.setClassForTemplateLoading(ValueAnswersObject.class, "templates");

        // Some other recommended settings:
        cfg.setIncompatibleImprovements(new Version(2, 3, 29));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.GERMAN);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // 2. Proccess template(s)
        //
        // You will do this for several times in typical applications.

        // 2.1. Prepare the template input:

        Map<String, Object> input = new HashMap<String, Object>();

        input.put("title", "radiales example");

        input.put("answerObject", new ValueAnswersObject("Java object", "me", "Yolo"));

        List<ValueAnswersObject> systems = new ArrayList<ValueAnswersObject>();
        systems.add(new ValueAnswersObject("Android", "Google", "Google"));
        input.put("systems", systems);

        // 2.2. Get the template

        Template template = cfg.getTemplate("conv.ftl");

        // 2.3. Generate the output

        // Write output to the console
        Writer consoleWriter = new OutputStreamWriter(System.out);
        template.process(input, consoleWriter);

        // For the sake of example, also write output into a file:
        Writer fileWriter = new FileWriter(new File("output.html"));
        try {
            template.process(input, fileWriter);
        } finally {
            fileWriter.close();
        }

    }
    }

