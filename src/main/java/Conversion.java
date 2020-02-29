import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

import freemarker.template.*;
import org.yaml.snakeyaml.constructor.Constructor;

public class Conversion {


    public static void main(String[] args) throws IOException, TemplateException {


        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);

        cfg.setClassForTemplateLoading(ValueAnswersObject.class, "templates");


        cfg.setIncompatibleImprovements(new Version(2, 3, 29));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.GERMAN);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);


        Map<String, Object> input = new HashMap<String, Object>();

        Yaml yaml = new Yaml(new Constructor(ValueAnswersObject.class));

        File file = new File("expo.yaml");
        BufferedReader br = new BufferedReader(new FileReader(file));

        input.put("title", "Quiz");
        List<ValueAnswersObject> systems = new ArrayList<ValueAnswersObject>();

        String line;
        while((line = br.readLine()) != null){                                                                          //YAML wird eingelesen
            if(line.contains("frage")){
                systems.add(new ValueAnswersObject());
            } else if(line.contains("question")){
                systems.get(systems.size() - 1).setQuestion(line.substring(line.indexOf("\"") + 1, line.length()-1));
            } else if(line.contains("answer")){
                systems.get(systems.size() - 1).setAnswer(line.substring(line.indexOf("\"") + 1, line.length()-1));
            }
        }


        input.put("systems", systems);

        Template template = cfg.getTemplate("conv.ftl");

        Writer consoleWriter = new OutputStreamWriter(System.out);
        template.process(input, consoleWriter);

        Writer fileWriter = new FileWriter(new File("output.html"));
        try {
            template.process(input, fileWriter);
        } finally {
            fileWriter.close();
        }

    }
    }

