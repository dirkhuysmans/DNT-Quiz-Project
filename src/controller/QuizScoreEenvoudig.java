package controller;

import model.OpdrachtAntwoord;
import model.Quizdeelname;

public class QuizScoreEenvoudig implements IQuizScore{

	@Override
	public double berekenQuizScore(Quizdeelname qd) {
		// TODO Auto-generated method stub
		double score = 0;
		
		for (OpdrachtAntwoord oa : qd.getListOpdrachtAntwoord()) {
			if (oa.getQuizOpdracht().getOpdracht().isJuisteAntwoord(oa.getLaatsteAntwoord())){
				score += oa.getQuizOpdracht().getMaxScore();
				}
			
		}
		return score;
	}

}
