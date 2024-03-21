package textanalysistool;

public class WordInformation {
   public String word;
   public LinkedList<WordOccurrence> occList = new LinkedList<WordOccurrence>();
   public int size;

    /*public WordInformation(String word) {
        this.word = word;
    }*/
    
    public WordInformation(String word, WordOccurrence occList, int size) {
        this.word = word;
        this.occList.insert(occList);
        this.size = size;
    }
    
    
   public void Add (int Line, int Position)    
    {
        occList.insert(new WordOccurrence(Line, Position ));
        size++;
        
    }
   
}
