package beans;

public class Flashcard {
	private long flashcardId;
	private String question;
	private String answer;

	public Flashcard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Flashcard(long flashcardId, String question, String answer) {
		super();
		this.flashcardId = flashcardId;
		this.question = question;
		this.answer = answer;
	}

	public long getFlashcardId() {
		return flashcardId;
	}

	public void setFlashcardId(long flashcardId) {
		this.flashcardId = flashcardId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result + (int) (flashcardId ^ (flashcardId >>> 32));
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flashcard other = (Flashcard) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		if (flashcardId != other.flashcardId)
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Flashcard [flashcardId=" + flashcardId + ", question=" + question + ", answer=" + answer + "]";
	}

}
