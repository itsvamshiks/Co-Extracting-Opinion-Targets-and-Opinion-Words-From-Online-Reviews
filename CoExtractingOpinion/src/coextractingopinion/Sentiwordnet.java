package coextractingopinion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;


public class Sentiwordnet 
{
    private Map<String, Double> dictionary;

    public Sentiwordnet(String path)
    {
        try
        {
        
            dictionary = new HashMap<String, Double>();
    
            HashMap<String, HashMap<Integer, Double>> tempDictionary = new HashMap<String, HashMap<Integer, Double>>();

            BufferedReader csv = null;
            try 
            {
                csv = new BufferedReader(new FileReader(path));
                int lineNumber = 0;

                String line;
                while ((line = csv.readLine()) != null) 
                {
                    lineNumber++;
                    if (!line.trim().startsWith("#")) 
                    {
                        String[] data = line.split("\t");
                        String wordTypeMarker = data[0];

                        if (data.length != 6) 
                        {
                            throw new IllegalArgumentException("Incorrect tabulation format in file, line: "+ lineNumber);
                        }

                        Double synsetScore = Double.parseDouble(data[2])- Double.parseDouble(data[3]);

                        String[] synTermsSplit = data[4].split(" ");

                        for (String synTermSplit : synTermsSplit) 
                        {
                    	    String[] synTermAndRank = synTermSplit.split("#");
                            String synTerm = synTermAndRank[0] + "#" + wordTypeMarker;
                           
                            int synTermRank = Integer.parseInt(synTermAndRank[1]);
	  
                            if (!tempDictionary.containsKey(synTerm)) 
                            {
                                tempDictionary.put(synTerm, new HashMap<Integer, Double>());
                            }

                            tempDictionary.get(synTerm).put(synTermRank, synsetScore);
                        }
                    }
                }

                for (Map.Entry<String, HashMap<Integer, Double>> entry : tempDictionary.entrySet()) 
                {
                    String word = entry.getKey();
                    Map<Integer, Double> synSetScoreMap = entry.getValue();

                    double score = 0.0;
                    double sum = 0.0;
                    for (Map.Entry<Integer, Double> setScore : synSetScoreMap.entrySet()) 
                    {
                        score += setScore.getValue() / (double) setScore.getKey();
                        sum += 1.0 / (double) setScore.getKey();
                    }
                    score /= sum;
                    
                    dictionary.put(word, score);
                }
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            } 
            finally 
            {
                if (csv != null) 
                {
                    csv.close();
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public double extract(String word, String pos) 
    {
        return dictionary.get(word + "#" + pos);
    }  
}
