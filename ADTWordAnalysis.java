package textanalysistool;

import java.io.*;
import java.util.*;
public class ADTWordAnalysis {
   public int m;
   public int n;
   public int k;
   public LinkedList<WordInformation> arrayOfDifferentLengths[];
   public WordInformation sortedArray[];

    public ADTWordAnalysis(String fileName) {
       readFileAndAnalyse(fileName);
    }
   
    public void readFileAndAnalyse(String fileName){
        try{
        File f = new File(fileName);    
        Scanner input = new Scanner(f);
        
        while(input.hasNext()){
            String s = input.next();
            
            if(s.equalsIgnoreCase("\\n"))
                continue;
         
            s = s.replaceAll("(?![-'])\\p{Punct}", "");
                n++;
            
            if(s.length() > k)
                k = s.length();
            
        }
            input.close();
        
        }catch(Exception e){
            System.out.println("File not found");
        }

        arrayOfDifferentLengths = new LinkedList[k + 1];
        for ( int i = 1 ; i< arrayOfDifferentLengths.length; i ++)
                    arrayOfDifferentLengths[i] = new LinkedList<WordInformation>();
        
       try{
          File f = new File(fileName); 
          Scanner input = new Scanner(f);
          int line = 0;
          int position = 0;

          while(input.hasNext()){
          String s=input.nextLine();
          String all_words_in_one_line[]= s.split("\\s+");
          line++;
          position=0;
          
          for(int i = 0; i < all_words_in_one_line.length;i++)
          {
          if(all_words_in_one_line[i].equals("\\n"))
          {
              
              if(i<all_words_in_one_line.length-1)
              {
              line++;
              position=0;
              }
              else
              position=0;
         }
         else 
         {
          position++;
          all_words_in_one_line[i]= all_words_in_one_line[i].replaceAll("(?![-'])\\p{Punct}", "");
          add(arrayOfDifferentLengths[all_words_in_one_line[i].length()],all_words_in_one_line[i], line, position);
         }

        }
         
        }
            input.close();
           
           for(int i = 1; i < arrayOfDifferentLengths.length; i++)
                m = m + arrayOfDifferentLengths[i].getsize();
           
           sortedArray = new WordInformation[m];
           int r = 0;
            
            for(int i = 1; i < arrayOfDifferentLengths.length; i++){
                if(arrayOfDifferentLengths[i].empty())
                    continue;
                
                arrayOfDifferentLengths[i].findFirst();
                while(!arrayOfDifferentLengths[i].last()){
                    sortedArray[r++] = arrayOfDifferentLengths[i].retrieve();
                    arrayOfDifferentLengths[i].findNext();
                }
                
               sortedArray[r++] = arrayOfDifferentLengths[i].retrieve(); 
            }
            
         
         mergesort(0, m-1);  
            
        }catch(Exception e){
            
        }   
    }
    
    public void add(LinkedList<WordInformation> arrayOfDifferentLengths,String s, int l, int p){
        if(arrayOfDifferentLengths.empty()){
            WordInformation word = new WordInformation(s, new WordOccurrence(l,p), 1);
            arrayOfDifferentLengths.insert(word);
        }
        else{
           arrayOfDifferentLengths.findFirst();
            
           while(!arrayOfDifferentLengths.last()){
               if(arrayOfDifferentLengths.retrieve().word.equalsIgnoreCase(s)){
                     arrayOfDifferentLengths.retrieve().Add(l, p);
                     return;}
        
               arrayOfDifferentLengths.findNext();
            }
            
            if(arrayOfDifferentLengths.retrieve().word.equalsIgnoreCase(s))
                     arrayOfDifferentLengths.retrieve().Add(l, p);
            else{
               WordInformation word = new WordInformation(s, new WordOccurrence(l,p), 1);
               arrayOfDifferentLengths.insert(word); 
            }
        }
    }
   
    public int documentLength(){
        return n;
    }
   
    public int uniqueWords(){
       return m; 
    }
   
    public int totalWord(String s){
        
        int count = 0 ;
        if(arrayOfDifferentLengths[s.length()].getsize() > 0)
        {   
            arrayOfDifferentLengths[s.length()].findFirst();
            while(!arrayOfDifferentLengths[s.length()].last())
            {
                if (arrayOfDifferentLengths[s.length()].retrieve().word.equalsIgnoreCase(s))
                    count = arrayOfDifferentLengths[s.length()].retrieve().size;
                arrayOfDifferentLengths[s.length()].findNext();
            }
            if(arrayOfDifferentLengths[s.length()].retrieve().word.equalsIgnoreCase(s))
                count = arrayOfDifferentLengths[s.length()].retrieve().size;
        }
    
        return count;
    }
   
    public int totalWordsForLength(int i){
       return arrayOfDifferentLengths[i].getsize();
    }
   
    public void displayUniqueWords(){
        for(int i = 0; i <m; i++)
            System.out.println(" ( " + sortedArray[i].word + " , " + sortedArray[i].size + " ), ");
    }
    
    public LinkedList<WordOccurrence> occurrences(String w){
        LinkedList<WordOccurrence> list = null;
        
        if(arrayOfDifferentLengths[w.length()].getsize() > 0)
        {   
            arrayOfDifferentLengths[w.length()].findFirst();
            while (!arrayOfDifferentLengths[w.length()].last())
            {
                if (arrayOfDifferentLengths[w.length()].retrieve().word.equalsIgnoreCase(w))
                    list = arrayOfDifferentLengths[w.length()].retrieve().occList;
                arrayOfDifferentLengths[w.length()].findNext();
            }
            if (arrayOfDifferentLengths[w.length()].retrieve().word.equalsIgnoreCase(w))
                list = arrayOfDifferentLengths[w.length()].retrieve().occList;
        }
        
        return list;
    }
   
    public boolean checkAdjacent(String w1, String w2){
        if((arrayOfDifferentLengths[w1.length()].getsize() ==0) || (arrayOfDifferentLengths[w2.length()].getsize() == 0))
            return false;
        
        LinkedList<WordOccurrence> occ1 = occurrences(w1);
        LinkedList<WordOccurrence> occ2 = occurrences(w2);
        
        if(occ1 != null && occ2 != null)
        {
            occ1.findFirst();
            occ2.findFirst();
            
            while (!occ1.last() && !occ2.last())
            {
                    int l1 = occ1.retrieve().lineNo;
                    int l2 = occ2.retrieve().lineNo;
                    if(l1 == l2)
                    {
                        int p1 = occ1.retrieve().position;
                        int p2 = occ2.retrieve().position;
                        if(Math.abs(p2 - p1) == 1)
                            return true;
                        else 
                            if((p2 - p1) > 1)
                               occ1.findNext();
                            else
                               occ2.findNext();
                    }
                    else
                        if(l1 > l2)
                          occ2.findNext();
                        else
                          occ1.findNext();  
            }
            
            while(occ1.last() && !occ2.last())
            {
                    int l1 = occ1.retrieve().lineNo;
                    int l2 = occ2.retrieve().lineNo;
                    if(l1 == l2)
                    {
                        int p1 = occ1.retrieve().position;
                        int p2 = occ2.retrieve().position;
                        if(Math.abs(p2 - p1) == 1)
                            return true;
                    }
                    occ2.findNext();
            }
                    
            while(!occ1.last() && occ2.last())
            {
                    int l1 = occ1.retrieve().lineNo;
                    int l2 =occ2.retrieve().lineNo;
                    if(l1 == l2)
                    {
                        int p1 = occ1.retrieve().position;
                        int p2 = occ2.retrieve().position;
                        if(Math.abs(p2 - p1) == 1)
                            return true;
                    }
                    occ1.findNext();
            }

            if(occ1.last() && occ2.last())
            {
                int l1 = occ1.retrieve().lineNo;
                int l2 = occ2.retrieve().lineNo;
                if (l1 == l2)
                {
                    int p1 = occ1.retrieve().position;
                    int p2 = occ2.retrieve().position;
                    if(Math.abs(p2 - p1) == 1)
                        return true;
                }
            }
        }  
        return false;}
    
    private void mergesort(int l , int r) 
    {
        if ( l >= r )
            return;
        int m = ( l + r ) / 2;
        mergesort (l , m ) ;    
        mergesort (m + 1 , r ) ;   
        merge (l , m , r ) ;          
    }

    private void merge(int l , int m , int r) 
    {
        WordInformation[] B = new WordInformation[ r - l + 1];
        int i = l , j = m + 1 , k = 0;
    
        while ( i <= m && j <= r )
        {
            if (sortedArray[i].size >= sortedArray [ j ].size)
                B [ k ++] = sortedArray[ i ++];
            else
                B [ k ++] = sortedArray[ j ++];
        }
        
        if ( i > m )
            while ( j <= r )
                B [ k ++] = sortedArray[ j ++];
        else
            while ( i <= m )
                B [ k ++] = sortedArray[ i ++];
        
        for ( k = 0; k < B . length ; k ++)
            sortedArray[ k + l ] = B [ k ];
    }  
    
}
