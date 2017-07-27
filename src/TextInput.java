import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.*;

public class TextInput {
	NaturalLanguageUnderstanding service;
	AnalyzeOptions parameters;
	
	TextInput()
		{

			service = new NaturalLanguageUnderstanding(
			  NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
			  "5937d36a-ce66-4ab4-ba37-cc40f287756c",
			  "KAqUgdRsmrEF"
			);

			 			
		}
		
	
		public AnalysisResults process(Features f, String text)
		{
			parameters = new AnalyzeOptions.Builder()
			  .text(text)
			  .features(f)
			  .build();
			
			AnalysisResults response = (AnalysisResults) service
					  .analyze(parameters)
					  .execute();
			return response;
					
		}
		
		
		public HashMap testMethod(String text)
		{
			System.out.println("1. Concept");
			System.out.println("2. Sentiment");
			System.out.println("3. Keywords");
			System.out.println("4. Emotions");
			System.out.println("5. Entities");
			System.out.println("6. Categories");
			System.out.println("7. MetaData");
			System.out.println("Enter your choice: ");
			Scanner scan = new Scanner(System.in);
			int choice = scan.nextInt();
						
			AnalysisResults response=null;
			Features features;
			HashMap scoreMap = new HashMap();
			Iterator iterator = null;
			switch(choice)
			{
			case 1:
				System.out.println("Concept tab");
				ConceptsOptions conceptsOptions = new ConceptsOptions.Builder().build();
				features = new Features.Builder()
				  .concepts(conceptsOptions)
				  .build();
				response = process(features,text);
				iterator = response.getConcepts().iterator();
				
				while (iterator.hasNext())
				{
					ConceptsResult c = (ConceptsResult)iterator.next();
					String s = (c.getText());
					Double score = c.getRelevance();
					scoreMap.put(s,score);
				}	
				break;
				
			case 2:
				System.out.println("Sentiment tab");
				SentimentOptions sentimentOptions = new SentimentOptions.Builder().build();
				features = new Features.Builder()
				  .sentiment(sentimentOptions)
				  .build();
				response = process(features, text);
				DocumentSentimentResults dr = response.getSentiment().getDocument();
				Double score = (dr.getScore());
				scoreMap.put("Sentiment Score",score);
		
				break;
			case 3:
				System.out.println("Keywords tab");
				KeywordsOptions keywordsOptions = new KeywordsOptions.Builder().build();
				features = new Features.Builder()
				  .keywords(keywordsOptions)
				  .build();
				response = process(features, text);
				iterator = response.getKeywords().iterator();
				while (iterator.hasNext())
				{
					KeywordsResult c = (KeywordsResult)iterator.next();
					String s = (c.getText());
					Double sc = c.getRelevance();
					scoreMap.put(s,sc);
				}
				break;
				
				
			case 4:
				System.out.println("Emotions tab");
				EmotionOptions emotionOptions = new EmotionOptions.Builder().build();
				features = new Features.Builder()
				  .emotion(emotionOptions)
				  .build();
				response = process(features, text);
				DocumentEmotionResults er = response.getEmotion().getDocument();
				EmotionScores es = er.getEmotion();
				scoreMap.put("Anger",es.getAnger());
				scoreMap.put("Disgust",es.getDisgust());
				scoreMap.put("Fear",es.getFear());
				scoreMap.put("Joy",es.getJoy());
				scoreMap.put("Sadness",es.getSadness());
				break;
				
			case 5:
				System.out.println("Entities tab");
				EntitiesOptions entitiesOptions = new EntitiesOptions.Builder().build();
				features = new Features.Builder()
				  .entities(entitiesOptions)
				  .build();
				response = process(features, text);
				iterator = response.getEntities().iterator();
				while (iterator.hasNext())
				{
					EntitiesResult c = (EntitiesResult)iterator.next();
					String s = (c.getText()+" "+c.getType());
					Double sc = c.getRelevance();
					scoreMap.put(s,sc);
				}
				break;
				
			case 6:
				System.out.println("Categories tab");
				CategoriesOptions categoriesOptions = new CategoriesOptions();
				features = new Features.Builder()
				  .categories(categoriesOptions)
				  .build();
				response = process(features, text);
				iterator = response.getCategories().iterator();
				while (iterator.hasNext())
				{
					CategoriesResult c = (CategoriesResult)iterator.next();
					String s = c.getLabel();
					Double sc = c.getScore();
					scoreMap.put(s,sc);
				}
				
				break;
				
			case 7:
				System.out.println("MetaData Tab");
				MetadataOptions metadataOptions = new MetadataOptions();
				features = new Features.Builder().metadata(metadataOptions).build();
				response = process(features, text);
				MetadataResult metadataResult = response.getMetadata();
				String s=null;
				s="Title: "+metadataResult.getTitle()+"\n"+"Authors:";
				iterator = metadataResult.getAuthors().iterator();
				while(iterator.hasNext())
				{
					s = s+" "+iterator.next();
				}
				s=s+"\nPublication Date: "+metadataResult.getPublicationDate();
				Double d = new Double(0);
				scoreMap.put(s,d);
				break;
			default :
					System.out.println("Default Case");
					break;
			}
			
			return scoreMap;
			
		}
}

	

