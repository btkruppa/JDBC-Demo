package driver;

import org.apache.log4j.Logger;

import dao.FlashcardDAO;
import dao.FlashcardDAOImpl;

public class Driver {
	private static Logger log = Logger.getRootLogger();

	private static FlashcardDAO fd = new FlashcardDAOImpl();

	public static void main(String[] args) {
		// Flashcard fc = fd.getFlashcardById(1);
		// System.out.println(fc);
		//
		// System.out.println("all Flashcards: " + fd.getAllFlashcards());
		//
		// Flashcard newFlashcard = new Flashcard(0, "What is the average speed of a
		// swallow",
		// "is it African or European");
		// log.info(fd.insertFlashcard(newFlashcard));
		
		fd.preparedStatementInsert();
		
		
	}
}
