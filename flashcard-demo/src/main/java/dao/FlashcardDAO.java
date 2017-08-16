package dao;

import java.util.List;

import beans.Flashcard;

/**
 * 
 * @author USER
 *
 */
public interface FlashcardDAO {

	/**
	 * This method is for inserting new flashcards into the database.
	 * 
	 * @param flashcard
	 * @return the id generated for the newly created flashcard or 0 if no flashcard
	 *         exists
	 */
	public long insertFlashcard(Flashcard flashcard);

	public boolean updateFlashcard(Flashcard flashcard);

	public Flashcard getFlashcardById(long id);

	public List<Flashcard> getAllFlashcards();

}
