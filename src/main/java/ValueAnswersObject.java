public class ValueAnswersObject {

    private String  question;
    private String  answer;
    private String  category;

    public ValueAnswersObject(){};


    public ValueAnswersObject(String q, String a, String c){
        this.question   = new String(q);
        this.answer     = new String(a);
        this.category   = new String(c);

    }

    /*===============================| Getter / Setter |===============================*/
    public String   getQuestion()           {return this.question;}
    public void     setQuestion(String s)   {this.question = new String(s);}

    public String   getCategory()           {return this.category;}
    public void     setCategory(String s)   {this.category = new String(s);}

    public String   getAnswer()             {return this.answer;}
    public void     setAnswer(String s)     {this.answer = new String(s);}
    /*=================================================================================*/
}
