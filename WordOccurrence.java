package textanalysistool;

public class WordOccurrence {
  public int lineNo;
  public int position;

    public WordOccurrence(int lineNo, int position) {
        this.lineNo = lineNo;
        this.position = position;
    }

    public String toString() {
            return " (" + lineNo + " ," + position + ") ";
    }
}
